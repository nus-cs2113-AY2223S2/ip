import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;
import duke.Task;
import duke.Deadline;
import duke.Event;
import duke.Todo;

public class Duke {
    private static int userTaskCount = 0;
    private static final String LINE = "--------------------------------------------";
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static ArrayList<Task> userTaskList = new ArrayList<>();
    private static String userInput;
    private static Scanner scan = new Scanner(System.in);
    private static final String FILE_PATH = "tasks.txt";

    private enum taskType {
        TODO, DEADLINE, EVENT
    }

    private enum markStorageTask {
        MARK, UNMARK
    }

    public static void reportError(String description) {
        System.out.println(LINE);
        System.out.println("[ERROR] " + description);
        System.out.println(LINE);
    }

    public static void greetUser() {
        System.out.println(LINE);
        System.out.println("Hello from\n" + LOGO);
        System.out.println("How can I help you?\n");
        System.out.println("Here are some possibly useful commands:");
        System.out.println(LINE);
        System.out.println("/todo { description } - Add a todo task to ur task list.");
        System.out.println("/deadline { description } /by { cutoff } - Add a deadline task to ur task list.");
        System.out.println(
                "/event { description } /start { start time } /end { end time } - Add an event task to ur task list.");
        System.out.println("/list - List out all the tasks in ur task list.");
        System.out.println("/mark { numerical index } - Mark a specific task done.");
        System.out.println("/unmark { numerical index } - Mark a specific task undone.");
        System.out.println("/delete { numerical index } - Delete a specific task.");
        System.out.println("/bye - Terminate the program.");
        System.out.println(LINE);
    }

    public static void goodbyeUser() {
        System.out.println(LINE);
        System.out.println("Bye! Hope to see you again soon!");
        System.out.println(LINE);
    }

    public static void addTask(String[] userInputArray, taskType variation) throws MissingCommandException {
        /** Check if task description is left empty **/
        if (userInputArray.length == 1) {
            throw new MissingCommandException("Please enter a description for your task!");
        }
        Task task;
        /** Handle different task types **/
        if (variation == taskType.TODO) {
            /** Handle todo tasks **/
            task = new Todo(userInput);
        } else if (variation == taskType.DEADLINE) {
            /** Handle deadline tasks **/
            if (!userInput.contains("/by")) {
                throw new MissingCommandException("Please specify a deadline via the /by command!");
            }
            task = new Deadline(userInput);
        } else {
            /** Handle event tasks **/
            if (!userInput.contains("/start") || !userInput.contains("/end")) {
                throw new MissingCommandException(
                        "Please specify both start and end dates/times via the /start and /end commands!");
            }
            task = new Event(userInput);
        }
        userTaskList.add(task);
        saveTask(task, userTaskCount);
        System.out.println(LINE);
        System.out.println("Added the following task:\n" + userTaskList.get(userTaskCount));
        System.out.println(LINE);
        userTaskCount++;

    }

    public static void listTasks() {
        if (userTaskCount == 0) {
            reportError("You have no tasks added!");
            return;
        }
        System.out.println("These are the following tasks you have:");
        for (int i = 0; i < userTaskCount; i++) {
            System.out.println(LINE);
            int index = i;
            index++;
            System.out.print(index + ".");
            System.out.println(userTaskList.get(i));
            System.out.println(LINE);
        }

    }

    public static void markStorageTask(int index, Duke.markStorageTask mark) {
        String indexToRemove = Integer.toString(index);
        ArrayList<String> savedTasksList = new ArrayList<>();
        // copy all saved tasks into ArrayList
        try {
            savedTasksList.addAll(Files.readAllLines(Paths.get("tasks.txt")));
        } catch (IOException e) {
            reportError("Error accessing file!");
        }
        // edit the selected task
        try {
            File file = new File("tasks.txt");
            Scanner s = new Scanner(file);
            while (s.hasNextLine()) {
                String line = s.nextLine();
                String[] lineArray = line.split(":");
                if (lineArray[0].equals(indexToRemove)) {
                    if (mark == markStorageTask.MARK) {
                        lineArray[2] = "true";
                    } else {
                        lineArray[2] = "false";
                    }
                    savedTasksList.set(Integer.parseInt(indexToRemove), String.join(":", lineArray));
                }
            }

            s.close();
        } catch (IOException e) {
            reportError("Error accessing file!");
        }
        // rewrite tasks into tasks.txt
        try {
            FileWriter writer = new FileWriter("tasks.txt");
            for (String task : savedTasksList) {
                writer.write(task + "\n");
            }
            writer.close();
        } catch (IOException e) {
            reportError("Error accessing file!");
        }
    }

    public static void markTask(String[] userInputArray) {
        try {
            int taskIndex = Integer.parseInt(userInputArray[1]);
            taskIndex--;
            if (userTaskList.get(taskIndex).getStatusIcon().equals("X")) {
                reportError("Task is already marked!");
                return;
            }
            userTaskList.get(taskIndex).markAsDone();
            System.out.println(LINE + System.lineSeparator() + "The following task has been marked done: [X] "
                    + userTaskList.get(taskIndex).description + System.lineSeparator() + LINE);
            // Edit tasks.txt to reflect marked
            markStorageTask(taskIndex, markStorageTask.MARK);
        } catch (Exception e) {
            reportError("Please enter a valid numerical index of the task!");
            return;
        }

    }

    public static void unmarkTask(String[] userInputArray) {
        try {
            int taskIndex = Integer.parseInt(userInputArray[1]);
            taskIndex--;
            if (userTaskList.get(taskIndex).getStatusIcon().equals(" ")) {
                reportError("Task is already unmarked!");
                return;
            }
            userTaskList.get(taskIndex).markAsUndone();
            System.out.println(LINE + System.lineSeparator() + "The following task has been marked undone: [ ] "
                    + userTaskList.get(taskIndex).description + System.lineSeparator() + LINE);
            // Edit tasks.txt to reflect marked
            markStorageTask(taskIndex, markStorageTask.UNMARK);
        } catch (Exception e) {
            // potential IndexOutOfBoundsException or NumberFormatException
            reportError("Please enter a valid numerical index of the task!");
        }
    }

    public static void deleteStorageTask(int index) {
        String indexToRemove = Integer.toString(index);
        ArrayList<String> savedTasksList = new ArrayList<>();
        int newTaskIndex = 0;
        // Add all tasks to ArrayList except task to be deleted
        try {
            File file = new File("tasks.txt");
            Scanner s = new Scanner(file);
            while (s.hasNextLine()) {
                String line = s.nextLine();
                String[] lineArray = line.split(":");
                if (!(lineArray[0].equals(indexToRemove))) {
                    lineArray[0] = Integer.toString(newTaskIndex);
                    String newTaskString = String.join(":", lineArray);
                    savedTasksList.add(newTaskString);
                    newTaskIndex++;
                }
            }
            s.close();
        } catch (IOException e) {
            reportError("Error accessing file!");
        }
        // rewrite tasks into tasks.txt
        try {
            FileWriter writer = new FileWriter("tasks.txt");
            for (String task : savedTasksList) {
                writer.write(task + "\n");
            }
            writer.close();
        } catch (IOException e) {
            reportError("Error accessing file!");
        }
    }

    public static void deleteTask(String[] userInputArray) {
        try {
            int taskIndex = Integer.parseInt(userInputArray[1]);
            taskIndex--;
            System.out.println(LINE + System.lineSeparator() + "The following task has been removed: [ ] "
                    + userTaskList.get(taskIndex).description + System.lineSeparator() + LINE);
            userTaskList.remove(taskIndex);
            deleteStorageTask(taskIndex);
            userTaskCount--;
        } catch (Exception e) {
            reportError("Please enter a valid numerical index of the task!");
        }
    }

    /*
     * arr[0] -> index
     * arr[1] -> task type
     * arr[2] -> isDone
     * arr[3] -> task description
     * 
     * For event:
     * arr[4] -> start
     * arr[5] -> end
     * 
     * For deadline:
     * arr[4] -> cutoff
     */
    public static void addSavedTasks() {
        try (Scanner s = new Scanner(new File(FILE_PATH))) {
            while (s.hasNextLine()) {
                String task = s.nextLine();
                Task newTask;
                String[] taskStringArray = task.split(":");
                if (taskStringArray[1].equals("Todo")) {
                    String inputFormat = "/todo " + taskStringArray[3];
                    newTask = new Todo(inputFormat);
                    userTaskList.add(newTask);
                } else if (taskStringArray[1].equals("Deadline")) {
                    String inputFormat = "/deadline " + taskStringArray[3] + " /by " + taskStringArray[4];
                    newTask = new Deadline(inputFormat);
                    userTaskList.add(newTask);
                } else {
                    String inputFormat = "/event " + taskStringArray[3] + " /start " + taskStringArray[4] + " /end "
                            + taskStringArray[5];
                    newTask = new Event(inputFormat);
                    userTaskList.add(newTask);
                }
                if (taskStringArray[2].equals("true")) {
                    newTask.markAsDone();
                }
                userTaskCount++;
            }
        } catch (IOException e) {
            reportError("Error accessing file!");
        }
    }

    public static void saveTask(Task task, Integer taskCount) {
        try {
            FileWriter fw = new FileWriter(FILE_PATH, true);
            fw.write(taskCount.toString() + ":" + task.formattedString() + "\n");
            fw.close();
        } catch (IOException e) {
            reportError("Error accessing file!");
        }

    }

    public static void processFile() {
        try {
            File f = new File(FILE_PATH);
            if (!f.createNewFile()) {
                addSavedTasks();
            }
        } catch (IOException e) {
            reportError("Error accessing file!");
        }
    }

    public static void processUserInput() {
        userInput = scan.nextLine();
        /** Handle single-word input commands with no arguments **/
        if (userInput.equals("/bye")) {
            goodbyeUser();
            scan.close();
            System.exit(0);
        }
        if (userInput.equals("/list")) {
            listTasks();
        } else {
            /** Handle multi-word input commands with required arguments **/
            String[] userInputArray = userInput.split(" ");
            if (userInputArray[0].equals("/todo")) {
                try {
                    addTask(userInputArray, taskType.TODO);
                } catch (MissingCommandException e) {
                    System.out.println(e.getMessage());
                }
            } else if (userInputArray[0].equals("/deadline")) {
                try {
                    addTask(userInputArray, taskType.DEADLINE);
                } catch (MissingCommandException e) {
                    System.out.println(e.getMessage());
                }
            } else if (userInputArray[0].equals("/event")) {
                try {
                    addTask(userInputArray, taskType.EVENT);
                } catch (MissingCommandException e) {
                    System.out.println(e.getMessage());
                }
            } else if (userInputArray[0].equals("/mark")) {
                markTask(userInputArray);
            } else if (userInputArray[0].equals("/unmark")) {
                unmarkTask(userInputArray);
            } else if (userInputArray[0].equals("/delete")) {
                deleteTask(userInputArray);

            } else {
                /** Handle non-command inputs **/
                reportError("Please enter a valid command!");
            }
        }

    }

    public static void main(String[] args) {
        greetUser();
        processFile();
        while (scan.hasNextLine()) {
            processUserInput();
        }
    }
}
