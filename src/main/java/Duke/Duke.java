package Duke;

import Duke.Exception.EmptyDeadlineException;
import Duke.Exception.EmptyEventsException;
import Duke.Exception.EmptyToDoException;
import Duke.Exception.NullCommandException;
import Duke.Task.Deadlines;
import Duke.Task.Events;
import Duke.Task.Task;
import Duke.Task.ToDos;

import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.io.FileNotFoundException;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        ArrayList<Task> list = new ArrayList<>();
        try {
            list = stringToArraylistConverter(printFileContents("dukeData.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        greetLine();
        addList(list);
        exitLine();
    }

    private static final int MARK_INDEX = 5;
    private static final int UNMARK_INDEX = 7;
    private static final int DELETE_INDEX = 7;
    private static final int BRACKETSKIP_INDEX = 7;
    private static final int FIRSTBRACKET_FRONT = 0;
    private static final int FIRSTBRACKET_BACK = 3;
    private static final int SECONDBRACKET_FRONT = 3;
    private static final int SECONDBRACKET_BACK = 6;

    public static void greetLine() {
        System.out.println("How may I be of service today?");
    }

    public static void echo() {
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        while (!line.equals("bye")) {
            System.out.println(line);
            line = in.nextLine();
        }
    }

    public static void exitLine() {
        System.out.println("Glad I could be of help!");
    }

    public static void addList(ArrayList<Task> list) {
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        while (!line.equals("bye")) { // condition to shut down program
            if (line.equals("list")) { // users wants to know all text so far
                printCurrentList(list);
            } else if (line.startsWith("mark")) {
                mark(line, list);
                dukeDataStorage(arraylistToStringConverter(list));
            } else if (line.startsWith("unmark")) {
                unmark(line, list);
                dukeDataStorage(arraylistToStringConverter(list));
            } else if (line.startsWith("delete")) {
                delete(line, list);
                dukeDataStorage(arraylistToStringConverter(list));
            } else {// new tasks keyed in by user
                try {
                    Task newTask = new Task(line);
                    if (line.startsWith("todo")) {
                        newTask = new ToDos(line);
                        list.add(newTask);
                    } else if (line.startsWith("deadline")) {
                        newTask = new Deadlines(line);
                        list.add(newTask);
                    } else if (line.startsWith("event")) {
                        newTask = new Events(line);
                        list.add(newTask);
                    } else {
                        throw new NullCommandException();
                    }
                    dukeDataStorage(arraylistToStringConverter(list));
                } catch (EmptyToDoException e) {
                    System.out.println("Sire, you have yet to tell me what is it you want to do.");
                } catch (EmptyDeadlineException e) {
                    System.out.println("Sire, what is it that is due your specified time?");
                } catch (EmptyEventsException e) {
                    System.out.println("Sire, your event is unclear. Please specify.");
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("Don't hold back Sire. I am here to serve.");
                } catch (NullCommandException e) {
                    System.out.println("Sire, I am not trained to understand gibberish.");
                }
            }
            line = in.nextLine(); // read in next line of text
        }
    }

    public static void mark(String task, ArrayList<Task> list) {
        String indexOfTask = task.substring(MARK_INDEX); // get the number of task to be marked
        Task taskToBeMarked = list.get(Integer.parseInt(indexOfTask) - 1); // convert from 1-based to 0-based
        taskToBeMarked.markTask();
        System.out.println("Sir, your task has been marked as completed.");
        System.out.println(list.get(Integer.parseInt(indexOfTask) - 1));
    }

    public static void unmark(String task, ArrayList<Task> list) {
        String indexOfTask = task.substring(UNMARK_INDEX);
        Task taskToBeUnmarked = list.get(Integer.parseInt(indexOfTask) - 1);
        taskToBeUnmarked.unmarkTask();
        System.out.println("Sir, your task has been unmarked as requested.");
        System.out.println(list.get(Integer.parseInt(indexOfTask) - 1));
    }

    public static void printCurrentList(ArrayList<Task> list) {
        System.out.println("Your current list of items as requested, sir.");
        for (int i = 0; i < list.size(); ++i) {
            System.out.println((i + 1) + "." + list.get(i));
        }
    }

    public static void delete(String task, ArrayList<Task> list) {
        String indexOfTask = task.substring(DELETE_INDEX);
        System.out.println("Sire, I have removed this task from your schedule");
        System.out.println(list.get(Integer.parseInt(indexOfTask) - 1));
        list.remove(Integer.parseInt(indexOfTask) - 1);
        System.out.println("You now have " + list.size() + " items left");
    }

    public static void dukeDataStorage(String taskToBeStored) {
        File f = new File("dukeData.txt");
        try {
            if (!f.exists()) {
                f.createNewFile();
            }
            writeToFile("dukeData.txt", taskToBeStored);
        } catch (IOException e) {
            System.out.println("File creation of writing invalid");
        }
    }

    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    private static String printFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        String fileContent = "";
        if (!f.exists()) { // for first log in, there is no file
            return "";
        }
        System.out.println("Good day sire, I have listed down your current plan below for you:");
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            String taskStored = s.nextLine();
            System.out.println(taskStored);
            fileContent = fileContent + taskStored + System.lineSeparator();
        }
        return fileContent;
    }

    private static String arraylistToStringConverter(ArrayList<Task> list) {
        String output = "";
        for (Task t : list) {
            output = output + t.toString() + System.lineSeparator();
        }
        return output;
    }

    private static ArrayList<Task> stringToArraylistConverter(String fileContent) {
        ArrayList<Task> list = new ArrayList<>();
        if (fileContent.equals("")) {
            return list;
        }
        String[] bufferArray = fileContent.split(System.lineSeparator());
        for (String t : bufferArray) {
            Task task = new Task(t);
            try {
                if (t.substring(FIRSTBRACKET_FRONT, FIRSTBRACKET_BACK).equals("[T]")) {
                    String todoDetail = "todo " + t.substring(BRACKETSKIP_INDEX);
                    task = new ToDos(todoDetail);
                    task.setMark(t.substring(SECONDBRACKET_FRONT, SECONDBRACKET_BACK));
                } else if (t.substring(FIRSTBRACKET_FRONT, FIRSTBRACKET_BACK).equals("[D]")) {
                    String deadlineDetail = "deadline " + t.substring(BRACKETSKIP_INDEX, t.indexOf("(")) + "/" +
                            t.substring(t.indexOf("(") + 1, t.indexOf(")"));
                    task = new Deadlines(deadlineDetail);
                    task.setMark(t.substring(SECONDBRACKET_FRONT, SECONDBRACKET_BACK));
                } else {
                    String eventDetail = "event " + t.substring(BRACKETSKIP_INDEX, t.indexOf("(")) + "/" +
                            t.substring(t.indexOf("(") + 1, t.indexOf("to")) + "/" +
                            t.substring(t.indexOf("to"), t.indexOf(")"));
                    eventDetail = eventDetail.replace(":", "");
                    task = new Events(eventDetail);
                    task.setMark(t.substring(SECONDBRACKET_FRONT, SECONDBRACKET_BACK));
                }
            } catch (EmptyToDoException e) {
                System.out.println("Corrupt todo detected, it is empty");
            } catch (EmptyDeadlineException e) {
                System.out.println("Corrupt Deadline detected, it is empty");
            } catch (EmptyEventsException e) {
                System.out.println("Corrupt Events detected, it is empty");
            }
            list.add(task);
        }
        return list;
    }
}
