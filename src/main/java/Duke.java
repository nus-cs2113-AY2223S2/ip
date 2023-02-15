import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Duke {

    private final static String LOAD_PATH = "save/tasks.txt";

    private static void addTask(ArrayList<Task> tasks, Task task) {
        tasks.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.printf("Now you have %d tasks in the list\n", tasks.size());
    }

    private static void doMark(ArrayList<Task> tasks, String[] wordList) throws DukeException {
        if (wordList.length != 2) {
            throw new DukeException("☹ OOPS!!! Wrong number of arguments for mark");
        }
        try {
            int index = Integer.parseInt(wordList[1]) - 1;
            tasks.get(index).setDone(true);
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(tasks.get(index));

        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    private static void doUnmark(ArrayList<Task> tasks, String[] wordList) throws DukeException {
        if (wordList.length != 2) {
            throw new DukeException("☹ OOPS!!! Wrong number of arguments for unmark");
        }
        try {
            int index = Integer.parseInt(wordList[1]) - 1;
            tasks.get(index).setDone(false);
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(tasks.get(index));
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    private static void doEvent(String line, ArrayList<Task> tasks) throws DukeException {
        if (line.length() <= 6 || line.substring(6).isBlank()) {
            throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
        }
        try {
            line = line.substring(6);
            String description = line.split(" /from ")[0];
            String eventTime = line.split(" /from ")[1];
            String startTime = eventTime.split(" /to ")[0];
            String endTime = eventTime.split(" /to ")[1];
            Event eventTask = new Event(description, startTime, endTime);
            addTask(tasks, eventTask);
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    private static void doTodo(String line, ArrayList<Task> tasks) throws DukeException {
        if (line.length() <= 5 || line.substring(5).isBlank()) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        String description = line.substring(5);
        Todo todoTask = new Todo(description);
        addTask(tasks, todoTask);
    }

    private static void doDeadline(String line, ArrayList<Task> tasks) throws DukeException {
        if (line.length() <= 9 || line.substring(9).isBlank()) {
            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
        }
        try {
            line = line.substring(9);
            String description = line.split(" /by ")[0];
            String by = line.split(" /by ")[1];
            Deadline deadlineTask = new Deadline(description, by);
            addTask(tasks, deadlineTask);
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    private static void printList(ArrayList<Task> tasks, String[] wordList) throws DukeException {
        if (wordList.length > 1) {
            throw new DukeException("☹ OOPS!!! Too much arguments for list");
        }
        if (tasks.size() > 0) {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i += 1) {
                System.out.printf("%d. %s\n", i + 1, tasks.get(i));
            }
        } else {
            System.out.println("There is no tasks");
        }
    }

    private static void doDelete(ArrayList<Task> tasks, String[] wordList) throws DukeException {
        if (wordList.length != 2) {
            throw new DukeException("☹ OOPS!!! Wrong number of arguments for delete");
        }
        try {
            int index = Integer.parseInt(wordList[1]) - 1;
            if (index + 1 > tasks.size()) {
                throw new DukeException("☹ OOPS!!! index out of bounds");
            } else {
                System.out.println("Noted. I've removed this task:");
                System.out.println(tasks.get(index));
                tasks.remove(index);
                System.out.printf("Now you have %d tasks in the list\n", tasks.size());
            }

        } catch (Exception exception) {
            System.out.println(exception);
        }
        return;
    }

    private static void processInput(String line, ArrayList<Task> tasks, String[] wordList, String command) throws
            DukeException, IOException {
        switch (command) {
        case "list":
            printList(tasks, wordList);
            break;
        case "unmark":
            doUnmark(tasks, wordList);
            doSave(tasks);
            break;
        case "mark":
            doMark(tasks, wordList);
            doSave(tasks);
            break;
        case "deadline":
            doDeadline(line, tasks);
            doSave(tasks);
            break;
        case "todo":
            doTodo(line, tasks);
            doSave(tasks);
            break;
        case "event":
            doEvent(line, tasks);
            doSave(tasks);
            break;
        case "delete":
            doDelete(tasks, wordList);
            doSave(tasks);
            break;
        default:
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    private static void doSave(ArrayList<Task> tasks) throws DukeException, IOException {
        File file = new File(LOAD_PATH);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        if (!file.exists()) {
            file.createNewFile();
        }
        FileWriter fileWriter = new FileWriter(file);
        for (Task task : tasks) {
            fileWriter.write(task.toString());
            fileWriter.write(System.lineSeparator());
        }
        fileWriter.close();
    }

    private static void doLoad(ArrayList<Task> tasks, File file) throws DukeException {
        try {
            if (file.exists()) {
                System.out.println("Welcome back!");
                System.out.println("Here are the tasks from last time:");
                Scanner readFile = new Scanner(file);
                while (readFile.hasNextLine()) {
                    Task task;
                    String currTask = readFile.nextLine();
                    String taskType = currTask.substring(0, 3);
                    String taskDetails = currTask.substring(7);
                    switch (taskType) {
                    case "[T]":
                        task = new Todo(taskDetails);
                        if (currTask.substring(3, 7).contains("X")) {
                            task.setDone(true);
                        }
                        break;
                    case "[D]":
                        String deadlineName = taskDetails.split("\\(by:")[0];
                        String by = taskDetails.split(" \\(by: ")[1].split("\\)")[0];
                        task = new Deadline(deadlineName, by);
                        if (currTask.substring(3, 7).contains("X")) {
                            task.setDone(true);
                        }
                        break;
                    case "[E]":
                        String eventName = taskDetails.split(" \\(from:")[0];
                        String eventTime = taskDetails.split("\\(from: ")[1];
                        String startTime = eventTime.split(" to: ")[0];
                        String endTime = eventTime.split(" to: ")[1].split("\\)")[0];
                        task = new Event(eventName, startTime, endTime);
                        if (currTask.substring(3, 7).contains("X")) {
                            task.setDone(true);
                        }
                        break;
                    default:
                        throw new DukeException("task type not saved properly");
                    }
                    tasks.add(task);
                }
                for (int i = 0; i < tasks.size(); i += 1) {
                    System.out.println(i + 1 + ": " + tasks.get(i));
                }
            }
        } catch (DukeException | IOException exception) {
            throw new DukeException("error reading load file");
        }
    }

    public static void main(String[] args) throws DukeException {
        System.out.println("Hello! I'm Duke");

        ArrayList<Task> tasks = new ArrayList<Task>();

        File file = new File(LOAD_PATH);
        doLoad(tasks, file);

        System.out.println("What can I do for you?\n");


        Scanner in = new Scanner(System.in);
        String line = in.nextLine();

        while (!line.equals("bye")) {
            if (line.length() == 0) {
                line = in.nextLine();
                continue;
            }
            String[] wordList = line.split(" ");
            String command = wordList[0];
            try {
                processInput(line, tasks, wordList, command);
            } catch (DukeException | IOException exception) {
                System.out.println(exception);
            }
            line = in.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
