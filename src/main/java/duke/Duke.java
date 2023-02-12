package duke;

import duke.exception.EmptyCommandException;
import duke.exception.IllegalCommandException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static final String GREET_MESSAGE = "Hello! I'm Duke\nWhat can I do for you?";
    public static final String EXIT_MESSAGE = "Bye. Hope to see you again soon!\n";
    public static final String LINE = "____________________________________________________________\n";
    public static final String MARK_MESSAGE = "Nice! I've marked this task as done:";
    public static final String UNMARK_MESSAGE = "OK, I've marked this task as not done yet:";

    public static boolean isProgramRunning = true;


    private static Database database = null;
    private static ArrayList<Task> tasks = null;

    public static void main(String[] args) {
        greeting();
        Scanner in = new Scanner(System.in);
        database = new Database();
        tasks = database.tasks;
        while (isProgramRunning) {
            String command = in.nextLine();
            String firstWord = command.split(" ")[0];
            if (command.equals("bye")) {
                exit();
            } else if (command.equals("list")) {
                printList();
            } else if (firstWord.equals("mark") || firstWord.equals("unmark")) {
                MarkOrUnmarkHandler(command);
            } else if (firstWord.equals("todo")) {
                todoTaskHandler(command);
            } else if (firstWord.equals("deadline")) {
                deadlineTaskHandler(command);
            } else if (firstWord.equals("event")) {
                eventTaskHandler(command);
            } else if (firstWord.equals("delete")) {
                deleteTaskHandler(command);
            } else {
                illegalCommandMessage();
            }
        }
    }

    private static void deleteTaskHandler(String command) {
        try {
            deleteTask(command);
        } catch (IllegalCommandException e) {
            illegalCommandMessage();
        }
    }

    private static void deleteTask(String command) throws IllegalCommandException, IndexOutOfBoundsException {
        String[] words = command.trim().split(" ");
        if (isInvalidString(words)) {
            throw new IllegalCommandException();
        }
        int deleteIndex = getIndex(words[1]);
        if (!isValidIndex(deleteIndex)) {
            throw new IllegalCommandException();
        }
        deleteTaskMessage(deleteIndex);
        tasks.remove(deleteIndex);
        try {
            database.updateDatabaseTask();
        } catch (IOException e) {
            System.out.println("Update database failure");
        }
    }

    private static void MarkOrUnmarkHandler(String command) {
        try {
            markAndUnmarkTask(command);
        } catch (IllegalCommandException e) {
            illegalCommandMessage();
        }
    }

    private static void todoTaskHandler(String command) {
        try {
            initTodoTask(command);
        } catch (EmptyCommandException e) {
            emptyCommandMessage("todo");
        }
    }

    private static void eventTaskHandler(String command) {
        try {
            initEventTask(command);
        } catch (IllegalCommandException e) {
            illegalCommandMessage();
        } catch (EmptyCommandException e) {
            emptyCommandMessage("event");
        }
    }

    private static void deadlineTaskHandler(String command) {
        try {
            initDeadlineTask(command);
        } catch (EmptyCommandException e) {
            emptyCommandMessage("deadline");
        } catch (IllegalCommandException e) {
            illegalCommandMessage();
        }
    }

    private static void emptyCommandMessage(String task) {
        System.out.println(LINE + " ☹ OOPS!!! The description of a " + task + " cannot be empty.\n" + LINE);
    }

    private static void illegalCommandMessage() {
        System.out.println(LINE + " ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" + LINE);
    }

    private static void initEventTask(String command) throws IllegalCommandException, EmptyCommandException {
        command = command.replace("event", "").trim();
        if (command.isEmpty()) {
            throw new EmptyCommandException();
        }
        String[] stringSplit = command.split(" /from ");
        if (isInvalidString(stringSplit)) {
            throw new IllegalCommandException();
        }
        String[] startToEndTime = stringSplit[1].split(" /to ");
        if (isInvalidString(startToEndTime)) {
            throw new IllegalCommandException();
        }
        Task currTask = new Event(stringSplit[0], startToEndTime[0], startToEndTime[1]);
        tasks.add(currTask);
        addTaskToDatabase(currTask);
        addSpecialTaskMessage();
    }


    private static boolean isInvalidString(String[] stringSplit) {
        return stringSplit.length != 2;
    }

    private static void initDeadlineTask(String command) throws EmptyCommandException, IllegalCommandException {
        command = command.replace("deadline", "").trim();
        if (command.isEmpty()) {
            throw new EmptyCommandException();
        }
        String[] stringSplit = command.split(" /by ");
        if (isInvalidString(stringSplit)) {
            throw new IllegalCommandException();
        }
        Task currTask = new Deadline(stringSplit[0], stringSplit[1]);
        tasks.add(currTask);
        addTaskToDatabase(currTask);
        addSpecialTaskMessage();
    }

    private static void initTodoTask(String command) throws EmptyCommandException {
        String todo = command.replace("todo", "").trim();
        if (todo.isEmpty()) {
            throw new EmptyCommandException();
        }
        Task currTask = new Todo(todo);
        tasks.add(currTask);
        addTaskToDatabase(currTask);
        addSpecialTaskMessage();
    }

    private static void addTaskToDatabase(Task currTask) {
        try {
            database.saveAddTask(currTask.getTaskString());
        } catch (IOException e) {
            System.out.println("save failed");
        }
    }

    private static void addSpecialTaskMessage() {
        int lastElement = tasks.size() - 1;
        System.out.println(LINE + tasks.get(lastElement).addTaskMessage() + "Now you have " + (lastElement + 1)
                + " tasks in the list." + System.lineSeparator() + LINE);
    }

    private static void deleteTaskMessage(int deleteIndex) {
        int lastElement = tasks.size() - 1;
        System.out.println(LINE + tasks.get(deleteIndex).deleteTaskMessage() + "Now you have " + (tasks.size() - 1) +
                " tasks in the list." + System.lineSeparator() + LINE);
    }

    private static void markAndUnmarkTask(String command) throws IllegalCommandException {
        String[] words = command.split(" ");
        if (words.length != 2) {
            throw new IllegalCommandException();
        }
        int indexOfMarking = getIndex(words[1]);
        if (!isValidIndex(indexOfMarking)) {
            throw new IllegalCommandException();
        }
        createMarkOrUnmark(command, words, indexOfMarking);
    }

    private static void createMarkOrUnmark(String command, String[] words, int indexOfMarking) {
        tasks.get(indexOfMarking).setDone(words[0]);
        System.out.print(LINE);
        if (command.startsWith("mark ")) {
            System.out.println(MARK_MESSAGE);
        } else {
            System.out.println(UNMARK_MESSAGE);
        }
        try {
            database.updateDatabaseTask();
        } catch (IOException e) {
            System.out.println("Update database failure");
        }
        System.out.println("  " + tasks.get(indexOfMarking).toString() + System.lineSeparator() + LINE);
    }

    private static boolean isValidIndex(int indexOfMarking) {
        if (indexOfMarking < 0 || indexOfMarking > (tasks.size() - 1)) {
            return false;
        }
        return true;
    }

    private static void exit() {
        System.out.print(LINE + EXIT_MESSAGE + System.lineSeparator() + LINE); // Duke saying goodbye and closes program
        isProgramRunning = false;
    }

    private static void printList() {
        System.out.print(LINE);
        for (int i = 0; i < tasks.size(); ++i) {
            System.out.println((i + 1) + "." + tasks.get(i).toString());
        }
        System.out.println(LINE);
    }

    private static void greeting() {
        System.out.println(LINE + GREET_MESSAGE);
    }

    private static int getIndex(String strNum) {
        // Referenced from https://www.baeldung.com/java-check-string-number
        int index = 0;
        if (strNum == null) {
            return -1;
        }
        try {
            index = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return -1;
        }
        index--;
        return index; // Returns 0-index of parsing Integer or -1 if string is not a number or empty
    }
}
