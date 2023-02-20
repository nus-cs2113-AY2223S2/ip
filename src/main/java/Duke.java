import exception.DukeException;
import exception.ErrorMessage;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;


public class Duke {

    public static final String LOGO =
            " ____        _\n"
            + "|  _ \\ _   _| | _____\n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private static void printWelcomeMessage() {
        System.out.println("Hello from\n" + LOGO);
    }

    private static void printHorizontalLine() {
        System.out.println("____________________________________________________________");
    }

    public static void greet() {
        printHorizontalLine();
        System.out.println("Hello! I'm Duke\nWhat can I do for you?\n");
        printHorizontalLine();
    }

    public static void exit() {
        printHorizontalLine();
        System.out.println("Bye. Hope to see you again soon!\n");
        printHorizontalLine();
    }

    public static void processCommands(ArrayList<Task> tasks, String userInput) throws DukeException {
        String[] words = userInput.split(" ");

        if (words[0].equals("list")) {
            listCommand(tasks);
        } else if (words[0].equals("todo")) {
            addTodoCommand(tasks, userInput);
        } else if (words[0].equals("event")) {
            addEventCommand(tasks, userInput);
        } else if (words[0].equals("deadline")) {
            addDeadlineCommand(tasks, userInput);
        } else if (words[0].equals("delete")) {
            deleteCommand(tasks, words);
        } else if (words[0].contains("mark")) {
            markUnmarkCommand(tasks, words);
        } else {
            throw new DukeException(ErrorMessage.INVALID_COMMAND.toString());
        }
    }

    private static void listCommand(ArrayList<Task> tasks) throws DukeException {
        if (tasks.size() > 0) {
            printHorizontalLine();
            for (Task task : tasks) {
                System.out.println((tasks.indexOf(task) + 1) + "." + task);
            }
            printHorizontalLine();
        } else {
            throw new DukeException(ErrorMessage.EMPTY_LIST.toString());
        }
    }

    private static void addTodoCommand(ArrayList<Task> tasks, String userInput) throws DukeException {
        try {
            userInput = userInput.substring(5);
            tasks.add(new Todo(userInput));
            taskAdded(tasks);
        } catch (IndexOutOfBoundsException error) {
            throw new DukeException(ErrorMessage.MISSING_TODO_PARAMETER.toString());
        }
    }

    private static void addEventCommand(ArrayList<Task> tasks, String userInput) throws DukeException {
        try {
            int firstPositionOfSlash = userInput.indexOf("/");
            if (firstPositionOfSlash == -1) {
                throw new DukeException(ErrorMessage.MISSING_TWO_EVENT_PARAMETER.toString());
            }
            String startEnd = userInput.substring(firstPositionOfSlash + 1);
            int secondPositionOfSlash = startEnd.indexOf("/");
            if (secondPositionOfSlash == -1) {
                throw new DukeException(ErrorMessage.MISSING_ONE_EVENT_PARAMETER.toString());
            }

            String start = startEnd.substring(0, secondPositionOfSlash - 1);
            String end = startEnd.substring(secondPositionOfSlash + 1);

            String description = userInput.substring(6, firstPositionOfSlash - 1);
            tasks.add(new Event(description, start, end));
            taskAdded(tasks);
        } catch (IndexOutOfBoundsException error) {
            throw new DukeException(ErrorMessage.MISSING_EVENT_PARAMETER.toString());
        }
    }

    private static void addDeadlineCommand(ArrayList<Task> tasks, String userInput) throws DukeException {
        int positionOfBy = userInput.indexOf("/by");
        if (positionOfBy == -1) {
            throw new DukeException(ErrorMessage.MISSING_DEADLINE_BY_PARAMETER.toString());
        }

        try {
            String by = userInput.substring(positionOfBy + 4);
            try {
                String description = userInput.substring(9, positionOfBy - 1);
                tasks.add(new Deadline(description, by));
                taskAdded(tasks);
            } catch (IndexOutOfBoundsException error) {
                throw new DukeException(ErrorMessage.MISSING_DEADLINE_PARAMETER.toString());
            }
        } catch (IndexOutOfBoundsException error) {
            throw new DukeException(ErrorMessage.EMPTY_DEADLINE_BY_PARAMETER.toString());
        }
    }

    public static void taskAdded(ArrayList<Task> tasks) {
        printHorizontalLine();
        boolean lessThanOne = (tasks.size() <= 1);
        System.out.println("Got it. I've added this task:\n" + tasks.get(tasks.size() - 1));
        System.out.println("Now you have " + tasks.size() + (lessThanOne ? " task" : " tasks") + " in the list");
        printHorizontalLine();
    }

    private static void deleteCommand(ArrayList<Task> tasks, String[] words) throws DukeException {
        int taskNumber = Integer.parseInt(words[1]);
        if (taskNumber > tasks.size()) {
            throw new DukeException(ErrorMessage.INVALID_DELETE.toString());
        }
        Task taskToRemove = tasks.get(taskNumber - 1);
        tasks.remove(taskNumber - 1);
        taskRemoved(tasks, taskToRemove);
    }

    public static void taskRemoved(ArrayList<Task> tasks, Task t) {
        printHorizontalLine();
        boolean isLessThanOne = (tasks.size() <= 1);
        System.out.println("Got it. I've removed this task:\n" + t.toString());
        System.out.println("Now you have " + tasks.size() + (isLessThanOne ? " task" : " tasks") + " in the list\n");
        printHorizontalLine();
    }

    private static void markUnmarkCommand(ArrayList<Task> tasks, String[] words) throws DukeException {
        printHorizontalLine();
        int taskNumber = Integer.parseInt(words[1]);
        if (taskNumber > tasks.size()) {
            throw new DukeException(ErrorMessage.INVALID_TASK.toString());
        }

        if (words[0].equals("mark")) {
            tasks.get(taskNumber - 1).markDone();
            System.out.println("Nice! I've marked this task as done:");
        } else {
            tasks.get(taskNumber - 1).markUndone();
            System.out.println("OK, I've marked this task as not done yet:");
        }
        System.out.println(tasks.get(taskNumber - 1));
        printHorizontalLine();
    }

    private static void writeToFile(ArrayList<Task> tasks) {
        try {
            FileOutputStream writeData = new FileOutputStream("duke.txt");
            ObjectOutputStream writeStream = new ObjectOutputStream(writeData);

            writeStream.writeObject(tasks);
            writeStream.flush();
            writeStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static ArrayList<Task> readFromFile(ArrayList<Task> tasks) {
        try {
            try {
                FileInputStream readData = new FileInputStream("duke.txt");
                ObjectInputStream readStream = new ObjectInputStream(readData);
                @SuppressWarnings("unchecked")
                ArrayList<Task> tasks2 = (ArrayList<Task>) readStream.readObject();
                tasks = tasks2;
                readStream.close();
            } catch (FileNotFoundException error) {
                System.out.println("File");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tasks;
    }

    public static void main(String[] args) {
        printWelcomeMessage();
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File("duke.txt");
        if (file.exists()) {
            tasks = readFromFile(tasks);
        }

        greet();
        Scanner in = new Scanner(System.in);
        while (true) {
            String userInput = in.nextLine();
            if (userInput.equals("bye")) {
                break;
            }
            try {
                processCommands(tasks, userInput);
            } catch (Exception error) {
                printHorizontalLine();
                System.out.println("Error message: " + error.getMessage());
                printHorizontalLine();
            }
        }
        writeToFile(tasks);
        exit();
    }
}
