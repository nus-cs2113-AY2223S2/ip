package duke;

import duke.tasks.Deadline;
import duke.tasks.Todo;
import duke.tasks.Task;
import duke.tasks.Event;

import duke.exception.DukeException;
import duke.exception.EmptyCommandException;
import duke.exception.InvalidCommandException;
import duke.exception.InvalidIndexException;

import java.io.IOException;
import java.util.Scanner;

public class Duke {

    public static final String LONG_LINE = "____________________________________________________________";
    public static final int MAX_ARRAY_SIZE = 100;

    public static String userInput = "start";
    private static Task[] taskList = new Task[MAX_ARRAY_SIZE];
    private static int listCount = 0;

    private static Database database;

    public static void main(String[] args) {
        printGreetings();
        Scanner in = new Scanner(System.in);
        database = new Database();
        while (!userInput.equals("bye")) {
            userInput = in.nextLine();
            if (!userInput.equals("bye")) {
                try {
                    handleUserInputs(userInput, taskList);
                } catch (DukeException e) {
                    System.out.println("error");
                }
            }
            System.out.println(LONG_LINE);
        }
        printExit();
        return;
    }

    public static void handleUserInputs(String userInput, Task[] taskList) throws InvalidCommandException {
        String[] cases = userInput.split(" ", 2);
        String command = cases[0];
        System.out.println(LONG_LINE);
        try {
            if (command.equals("todo")) {
                createTodo(taskList, cases);
            } else if (command.equals("deadline")) {
                createDeadline(taskList, cases);
            } else if (command.equals("event")) {
                createEvent(taskList, cases);
            } else if (command.equals("list")) {
                printList(taskList);
            } else if (command.equals("mark")) {
                markTask(taskList, cases[1]);
            } else if (command.equals("unmark")) {
                unmarkTask(taskList, cases[1]);
            } else {
                throw new InvalidCommandException();
            }
        } catch (InvalidCommandException e) {
            printInvalidMessage();
        } catch (EmptyCommandException e) {
            printEmptyCommandMessage(command);
        } catch (InvalidIndexException e) {
            printIndexMessage();
        }
    }

    private static void printList(Task[] s) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < listCount; ++i) {
            String index = Integer.toString(i + 1);
            System.out.println(index + '.' + s[i].toString());
        }
    }

    private static void printAddedTaskCommand(Task[] taskList, int listCount) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + taskList[listCount].toString());
        System.out.println("Now you have " + Integer.toString(listCount + 1) + " tasks in the list.");
    }

    private static void printGreetings() {
        System.out.println(LONG_LINE);
        System.out.println("Hello I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println(LONG_LINE);
    }

    private static void printExit() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LONG_LINE);
    }


    private static void createTodo(Task[] taskList, String[] cases) throws EmptyCommandException{
        if (cases.length == 1) {
            throw new EmptyCommandException();
        }
        String input = cases[1];
        Task currTask = new Todo(input);
        taskList[listCount] = currTask;
        addToDatabase(currTask);
        printAddedTaskCommand(taskList, listCount);
        listCount++;
    }

    private static void createDeadline(Task[] taskList, String[] cases) throws EmptyCommandException {
        if (cases.length == 1) {
            throw new EmptyCommandException();
        }
        String input = cases[1];
        String[] splitInput = input.split("/", 2);
        String task = splitInput[0].trim();
        String by = splitInput[1].substring(3);
        Task currTask = new Deadline(task, by);
        taskList[listCount] = currTask;
        addToDatabase(currTask);
        printAddedTaskCommand(taskList, listCount);
        listCount++;
    }

    private static void createEvent(Task[] taskList, String[] cases) throws EmptyCommandException {
        if (cases.length == 1) {
            throw new EmptyCommandException();
        }
        String input = cases[1];
        String[] splitInput = input.split("/", 3);
        String task = splitInput[0].trim();
        String from = splitInput[1].substring(5).trim();
        String to = splitInput[2].substring(3);
        Task currTask = new Event(task, from, to);
        taskList[listCount] = currTask;
        addToDatabase(currTask);
        printAddedTaskCommand(taskList, listCount);
        listCount++;
    }

    private static void markTask(Task[] taskList, String input) throws InvalidIndexException {
        int index = Integer.parseInt(input) - 1;
        if (taskList[index] == null) {
            throw new InvalidIndexException();
        }
        taskList[index].setDone(true);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(taskList[index].toString());
    }

    private static void unmarkTask(Task[] taskList, String input) throws InvalidIndexException {
        int index = Integer.parseInt(input) - 1;
        if (taskList[index] == null) {
            throw new InvalidIndexException();
        }
        taskList[index].setDone(false);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(taskList[index].toString());
    }

    public static void addToDatabase(Task currTask){
        try {
            database.appendSaveTasks(currTask.taskInformation());
        } catch (IOException e){
            System.out.println("Failed to append task to database");
        }
    }

    public static void printInvalidMessage() {
        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    public static void printEmptyCommandMessage(String command) {
        System.out.println("☹ OOPS!!! The description of a " + command + " cannot be empty.");
    }

    public static void printIndexMessage(){
        System.out.println("No such task exist! Try again.");
    }
}
