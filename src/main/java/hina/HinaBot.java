package hina;

import hina.exceptions.HinaException;
import hina.task.Deadline;
import hina.task.Event;
import hina.task.Task;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class HinaBot {
    protected static ArrayList<Task> taskList = new ArrayList<Task>();

    public static void main(String[] args) {
        String line;
        Scanner in = new Scanner(System.in);
        showGreeting();
        try {
            readSaveFile();
        } catch (FileNotFoundException exception) {
            System.out.println("Save file not found! Creating new file...");
            File newFile = new File("data/savedlist.txt");
            try {
                Path dataPath = Paths.get("data");
                Files.createDirectory(dataPath);
                newFile.createNewFile();
                System.out.println("Save file created!");
            } catch (IOException ioException) {
                System.out.println("Ahh! Something went wrong, could not create file!");
            }
        }
        while (true) {
            try {
                line = in.nextLine();
                handleCommand(line);
            } catch (HinaException cmdException) {
                System.out.println(">.< Hina does not recognise this command!");
            } catch (StringIndexOutOfBoundsException argException) {
                System.out.println("@_@ Please give Hina more details!");
            }
        }
    }

    private static void showGreeting() {
        System.out.println("Hello master!");
        System.out.println("What are your orders?");
    }

    public static void readSaveFile() throws FileNotFoundException {
        File saveFile = new File("data/savedlist.txt");
        Scanner save = new Scanner(saveFile);
        System.out.println("Saved list found, loading saved list...");
        while (save.hasNext()) {
            String line = save.nextLine();
            String[] taskDetails = line.split(" / ");
            if (taskDetails[0].equals("T")) {
                Task savedTask = new Task(taskDetails[2]);
                if (taskDetails[1].equals("0")) {
                    savedTask.setDone(false);
                } else {
                    savedTask.setDone(true);
                }
                taskList.add(savedTask);
            } else if (taskDetails[0].equals("D")) {
                Deadline savedDeadline = new Deadline(taskDetails[2], taskDetails[3]);
                if (taskDetails[1].equals("0")) {
                    savedDeadline.setDone(false);
                } else {
                    savedDeadline.setDone(true);
                }
                taskList.add(savedDeadline);
            } else if (taskDetails[0].equals("E")) {
                Event savedEvent = new Event(taskDetails[2], taskDetails[3], taskDetails[4]);
                if (taskDetails[1].equals("0")) {
                    savedEvent.setDone(false);
                } else {
                    savedEvent.setDone(true);
                }
                taskList.add(savedEvent);
            }
        }
    }

    public static void writeToFile() throws IOException {
        FileWriter saveFile = new FileWriter("data/savedlist.txt");
        for (Task taskToSave : taskList) {
            saveFile.write(taskToSave.toSave());
            saveFile.write("\n");
        }
        saveFile.close();
    }

    public static void handleCommand(String command) throws HinaException {
        if (command.equalsIgnoreCase("bye")) {
            System.out.println("Goodbye master, let's meet again soon...");
            System.exit(0);
        } else if (command.equalsIgnoreCase("list")) {
            listTasks();
        } else if (command.split(" ")[0].equalsIgnoreCase("mark")) {
            int taskIndex = Integer.parseInt(command.split(" ")[1]);
            markTask(taskIndex);
        } else if (command.split(" ")[0].equalsIgnoreCase("unmark")) {
            int taskIndex = Integer.parseInt(command.split(" ")[1]);
            unmarkTask(taskIndex);
        } else if (command.split(" ")[0].equalsIgnoreCase("todo")) {
            addTask(command.substring(5));
        } else if (command.split(" ")[0].equalsIgnoreCase("deadline")) {
            addDeadline(command.substring(9));
        } else if (command.split(" ")[0].equalsIgnoreCase("event")) {
            addEvent(command.substring(6));
        } else if (command.split(" ")[0].equalsIgnoreCase("delete")) {
            int taskIndex = Integer.parseInt(command.split(" ")[1]);
            deleteTask(taskIndex);
        } else {
            throw new HinaException();
        }
    }

    public static void addTask(String description) {
        Task newTask = new Task(description);
        taskList.add(newTask);
        System.out.println("Noted! This task has been added:");
        System.out.println(newTask.toString());
        getTaskCount();
        try {
            writeToFile();
        } catch (IOException ioexception) {
            System.out.println("Something went wrong, could not save!");
        }
    }

    public static void listTasks() {
        if (taskList.size() == 0) {
            System.out.println("There are no items on the list :o");
        }
        for (Task task : taskList) {
            int i = taskList.indexOf(task);
            System.out.print(i + 1);
            System.out.print(". ");
            System.out.println(task.toString());
        }
    }

    public static void markTask(int taskIndex) {
        taskList.get(taskIndex - 1).setDone(true);
        System.out.println("Roger that! This task is marked as done: ");
        System.out.println(taskList.get(taskIndex - 1).toString());
    }

    public static void unmarkTask(int taskIndex) {
        taskList.get(taskIndex - 1).setDone(false);
        System.out.println("Roger that! This task is marked as not done: ");
        System.out.println(taskList.get(taskIndex - 1).toString());
    }

    public static void addDeadline(String deadline) {
        String[] details = deadline.split("/");
        if (details.length < 2) {
            System.out.println("Hina needs to know the deadline for this task!");
        } else {
            Deadline newDeadline = new Deadline(details[0], details[1].substring(3));
            taskList.add(newDeadline);
            System.out.println("Noted! This task has been added:");
            System.out.println(newDeadline.toString());
            getTaskCount();
            try {
                writeToFile();
            } catch (IOException ioexception) {
                System.out.println("Something went wrong, could not save!");
            }
        }
    }

    public static void addEvent(String event) {
        String[] details = event.split("/");
        if (details.length < 3) {
            System.out.println("Please tell Hina when this event starts and ends!");
        } else {
            Event newEvent = new Event(details[0], details[1].substring(5).trim(), details[2].substring(3));
            taskList.add(newEvent);
            System.out.println("Noted! This task has been added:");
            System.out.println(newEvent.toString());
            getTaskCount();
            try {
                writeToFile();
            } catch (IOException ioexception) {
                System.out.println("Something went wrong, could not save!");
            }
        }
    }

    public static void deleteTask(int taskIndex) {
        System.out.println("Got it! This task will be removed:");
        System.out.println(taskList.get(taskIndex - 1).toString());
        taskList.remove(taskIndex - 1);
        try {
            writeToFile();
        } catch (IOException ioexception) {
            System.out.println("Something went wrong, could not save!");
        }
    }

    public static void getTaskCount() {
        System.out.printf("There are %d items on your list.\n", taskList.size());
    }
}
