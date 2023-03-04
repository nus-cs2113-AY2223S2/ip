package duke.main;

import duke.exceptions.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;

/**
 * A class that acts as a CLI that keeps tracks of tasks you write and mark down
 */
public class Duke {

    public static void main(String[] args) throws IOException {
        greetings();
        manageInput();
        goodbye();
    }

    private static int selectTask(String echoInput, int counter, ArrayList<Task> storedTask) {
        try {
            if (echoInput.startsWith("mark")) {
                String stringListNumber = echoInput.substring(5, echoInput.length());
                int index = Integer.parseInt(stringListNumber) - 1;
                storedTask.get(index).setIsDone(true);
                listTasks(counter, storedTask);
            }
            else if (echoInput.startsWith("delete")) {
                String stringListNumber = echoInput.substring(7, echoInput.length());
                int index = Integer.parseInt(stringListNumber) - 1;
                deleteTask(index, storedTask);
                return counter - 1;
            }
        } catch (NumberFormatException ex) {
            printMarkError();
        } catch (ArrayIndexOutOfBoundsException ex) {
            printMarkError();
        } catch (IndexOutOfBoundsException ex) {
            printMarkError();
        } catch (NullPointerException ex) {
            printMarkError();
        }
        return counter;
    }

    private static void deleteTask(int removeIndex, ArrayList<Task> storedTask) {
        System.out.println("____________________________________________________________");
        System.out.println("Noted. I've removed this task:\n" +
                "       " + storedTask.get(removeIndex).toString() + "\n" +
                "Now you have " + (storedTask.size() - 1) + " tasks in the list.");
        System.out.println("____________________________________________________________\n");
        storedTask.remove(removeIndex);
    }

    private static void manageInput() throws IOException {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        ArrayList<Task> storedTask = new ArrayList<Task>();

        dukeFile(storedTask);
        int counter = storedTask.size();

        while (!input.equals("bye")) {
            try {
                input = scanner.nextLine();
                if (input.equals("bye")) {
                    break;
                } else if (input.equals("list")) {
                    listTasks(counter, storedTask);
                } else if (input.startsWith("mark")) {
                    selectTask(input, counter, storedTask);
                    updateDukeFile(storedTask);
                } else if (input.startsWith("todo")) {
                    blankTodo(input);
                    Task tempTask = new Todo(input.substring(5));
                    storedTask.add(tempTask);
                    counter++;
                    printTaskInput(tempTask, counter);
                    writeDukeFile(tempTask, true);
                } else if (input.startsWith("deadline") && input.contains("/")) {
                    Task tempTask = new Deadline(input.substring(9), input.substring(input.lastIndexOf("/") + 1));
                    storedTask.add(tempTask);
                    counter++;
                    printTaskInput(tempTask, counter);
                    writeDukeFile(tempTask, true);
                } else if (input.startsWith("event") && input.matches(".*/.*/.*")) {
                    String tempInput = input.substring(input.indexOf("/") + 1);
                    String fromString = tempInput.substring(0, tempInput.indexOf("/"));
                    String toString = tempInput.substring(tempInput.lastIndexOf("/") + 1);

                    Task tempTask = new Event(input.substring(6), fromString, toString);
                    storedTask.add(tempTask);
                    counter++;
                    printTaskInput(tempTask, counter);
                    writeDukeFile(tempTask, true);
                } else if (input.startsWith("delete")) {
                    counter = selectTask(input, counter, storedTask);
                } else {
                    invalidInput();
                }
            } catch (DukeException ex) {
                continue;
            }
        }
    }

    private static void updateDukeFile(ArrayList<Task> storedTask) throws IOException {
        writeDukeFile(storedTask.get(0), false);
        for (int i = 1; i < storedTask.size(); i++) {
            writeDukeFile(storedTask.get(i), true);
        }
    }

    private static void dukeFile(ArrayList<Task> storedTask) throws IOException {
        java.nio.file.Path dukeFilePath = getDukeFilePath();
        boolean fileExists = java.nio.file.Files.exists(dukeFilePath);
        java.nio.file.Path dataDirPath = getDataDirPath();
        boolean dirExists = java.nio.file.Files.exists(dataDirPath);
        File dukeFile = new File(dukeFilePath.toString());

        if (fileExists) {
            readDukeFile(storedTask, dukeFile);
        } else {
            try {
                if (!dirExists) {
                    File dataDir = new File(dataDirPath.toString());
                    dataDir.mkdir();
                }
                dukeFile.createNewFile();
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
    }

    private static java.nio.file.Path getDukeFilePath() {
        File f = new File("");
        String home = f.getAbsolutePath();
        return java.nio.file.Paths.get(home, "src", "data", "duke.txt");
    }

    private static java.nio.file.Path getDataDirPath() {
        File f = new File("");
        String home = f.getAbsolutePath();
        return java.nio.file.Paths.get(home, "src", "data");
    }

    private static void readDukeFile(ArrayList<Task> storedTask, File dukeFile) throws IOException {
        Scanner s = new Scanner(dukeFile);
        while (s.hasNextLine()) {
            String task = s.nextLine();
            String taskType = task.substring(0, 1);
            boolean isMarked = task.substring(2, 3).equals("0") ? false : true;
            String taskDesc = task.substring(4);

            if (taskType.equals("T")) {
                Task tempTask = new Todo(taskDesc, isMarked);
                storedTask.add(tempTask);
            } else if (taskType.equals("D")) {
                Task tempTask = new Deadline(taskDesc, taskDesc.substring(taskDesc.lastIndexOf("/") + 1), isMarked);
                storedTask.add(tempTask);
            } else if (taskType.equals("E")) {
                String tempInput = taskDesc.substring(taskDesc.indexOf("/") + 1);
                String fromString = tempInput.substring(0, tempInput.indexOf("/"));
                String toString = tempInput.substring(tempInput.lastIndexOf("/") + 1);

                Task tempTask = new Event(taskDesc, fromString, toString, isMarked);
                storedTask.add(tempTask);
            }
        }
    }

    private static void writeDukeFile(Task task, boolean appendFile) throws IOException {
        String line = (task.getIsDone() ? "1" : "0") + "|" + task.getDescription();
        if (task instanceof Todo) {
            line = "T|" + line;
        } else if (task instanceof Deadline) {
            line = "D|" + line;
        } else if (task instanceof Event) {
            line = "E|" + line;
        }
        File dukeFile = new File(getDukeFilePath().toString());
        FileWriter fw = new FileWriter(dukeFile, appendFile);
        if (appendFile) {
            fw.write("\n" + line);
        } else {
            fw.write(line);
        }
        fw.close();
    }

    private static void blankTodo(String input) throws DukeException {
        if (input.length() == 4 || input.substring(4).isBlank()) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
    }

    private static void invalidInput() throws DukeException {
        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    private static void listTasks(int counter, ArrayList<Task> storedTask) {
        System.out.println("____________________________________________________________");
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < counter; i++) {
            System.out.println((i + 1) + ". " + storedTask.get(i).toString());
        }
        System.out.println("____________________________________________________________\n");
    }

    private static void printMarkError() {
        System.out.println("____________________________________________________________");
        System.out.println("*DID NOT ENTER A VALID NUMBER*");
        System.out.println("____________________________________________________________\n");
    }

    private static void printTaskInput(Task task, int counter) {
        System.out.println("____________________________________________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + counter + " tasks in the list.");
        System.out.println("____________________________________________________________\n");
    }

    private static void greetings() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________\n");
    }

    private static void goodbye() {
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________\n");
    }
}