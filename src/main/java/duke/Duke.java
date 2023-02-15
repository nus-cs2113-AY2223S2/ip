package duke;

import duke.tasks.Deadline;
import duke.tasks.Todo;
import duke.tasks.Task;
import duke.tasks.Event;

import duke.exception.DukeException;
import duke.exception.EmptyCommandException;
import duke.exception.InvalidCommandException;
import duke.exception.InvalidIndexException;


import java.util.ArrayList;

import java.io.IOException;

import java.util.Scanner;

public class Duke {

    public static final String LONG_LINE = "____________________________________________________________";

    public static String userInput = "start";
    private static ArrayList<Task> taskList = new ArrayList<>();
    private static int listCount = 0;

    private static Database database;

    public static void main(String[] args) {
        printGreetings();
        Scanner in = new Scanner(System.in);
        database = new Database();
        taskList = database.taskList;
        listCount = taskList.size();
        while (!userInput.equals("bye")) {
            userInput = in.nextLine();
            if (!userInput.equals("bye")) {
                try {
                    handleUserInputs(userInput);
                } catch (DukeException e) {
                    System.out.println("error");
                }
            }
            System.out.println(LONG_LINE);
        }
        printExit();
        return;
    }

    public static void handleUserInputs(String userInput) throws InvalidCommandException {
        String[] cases = userInput.split(" ", 2);
        String command = cases[0];
        System.out.println(LONG_LINE);
        try {
            if (command.equals("todo")) {
                createTodo(cases);
            } else if (command.equals("deadline")) {
                createDeadline(cases);
            } else if (command.equals("event")) {
                createEvent(cases);
            } else if (command.equals("list")) {
                printList();
            } else if (command.equals("mark")) {
                markTask(cases[1]);
            } else if (command.equals("unmark")) {
                unmarkTask(cases[1]);
            } else if (command.equals("delete")) {
                deleteTask (cases[1]);
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

    private static void deleteTask(String input) throws InvalidIndexException {
        int index = Integer.parseInt(input) - 1;
        if (index >= taskList.size()) {
            throw new InvalidIndexException();
        }
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + taskList.get(index).toString());
        taskList.remove(index);
        try {
            database.updateDatabase();
        } catch (IOException e){
            System.out.println("Failed to update database");
        }
        --listCount;
        System.out.println("Now you have " + Integer.toString(listCount) + " tasks in the list.");
    }
    private static void printList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < listCount; ++i) {
            String index = Integer.toString(i + 1);
            System.out.println(index + '.' + taskList.get(i).toString());
        }
    }

    private static void printAddedTaskCommand() {
        int lastElement = taskList.size() - 1;
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + taskList.get(lastElement).toString());
        System.out.println("Now you have " + Integer.toString(listCount) + " tasks in the list.");
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


    private static void createTodo( String[] cases) throws EmptyCommandException{
        if (cases.length == 1) {
            throw new EmptyCommandException();
        }
        String input = cases[1];
        Task currTask = new Todo(input);
        taskList.add(currTask);
        addToDatabase(currTask);
        ++listCount;
        printAddedTaskCommand();
    }

    private static void createDeadline(String[] cases) throws EmptyCommandException {
        if (cases.length == 1) {
            throw new EmptyCommandException();
        }
        String input = cases[1];
        String[] splitInput = input.split("/", 2);
        String task = splitInput[0].trim();
        String by = splitInput[1].substring(3);
        Task currTask = new Deadline(task, by);
        taskList.add(currTask);
        addToDatabase(currTask);
        ++listCount;
        printAddedTaskCommand();
    }

    private static void createEvent(String[] cases) throws EmptyCommandException {
        if (cases.length == 1) {
            throw new EmptyCommandException();
        }
        String input = cases[1];
        String[] splitInput = input.split("/", 3);
        String task = splitInput[0].trim();
        String from = splitInput[1].substring(5).trim();
        String to = splitInput[2].substring(3);
        Task currTask = new Event(task, from, to);
        taskList.add(currTask);
        addToDatabase(currTask);
        ++listCount;
        printAddedTaskCommand();
    }

    private static void markTask(String input) throws InvalidIndexException {
        int index = Integer.parseInt(input) - 1;
        if (index >= taskList.size()) {
            throw new InvalidIndexException();
        }
        taskList.get(index).setDone(true);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(taskList.get(index).toString());
    }

    private static void unmarkTask(String input) throws InvalidIndexException {
        int index = Integer.parseInt(input) - 1;
        if (index >= taskList.size()) {
            throw new InvalidIndexException();
        }
        taskList.get(index).setDone(false);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(taskList.get(index).toString());
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
