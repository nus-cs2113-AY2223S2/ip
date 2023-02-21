package duke.commands;

import duke.exception.EmptyCommandException;
import duke.exception.InvalidCommandException;
import duke.exception.InvalidIndexException;

import duke.tasks.*;
import duke.ui.Ui;

public class Parser {
    private static TaskList taskList = new TaskList();

    public static void handleUserInputs(String userInput) throws InvalidCommandException{
        String[] cases = userInput.split(" ", 2);
        String command = cases[0];
        Ui.printLine();
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
            Ui.printInvalidMessage();
        } catch (EmptyCommandException e) {
            Ui.printEmptyCommandMessage(command);
        } catch (InvalidIndexException e) {
            Ui.printInvalidIndexMessage();
        }
    }

    private static void createTodo(String[] cases) throws EmptyCommandException{
        if (cases.length == 1) {
            throw new EmptyCommandException();
        }
        String input = cases[1];
        Task currTask = new Todo(input);
        taskList.addTasks(currTask);
        Ui.printAddedTaskCommand(taskList);
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
        taskList.addTasks(currTask);
        Ui.printAddedTaskCommand(taskList);
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
        taskList.addTasks(currTask);
        Ui.printAddedTaskCommand(taskList);
    }

    private static void printList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.listCount(); ++i) {
            String index = Integer.toString(i + 1);
            System.out.println(index + '.' + taskList.getTask(i).toString());
        }
    }

    private static void markTask(String input) throws InvalidIndexException {
        int index = Integer.parseInt(input) - 1;
        if (index >= taskList.listCount()) {
            throw new InvalidIndexException();
        }
        taskList.getTask(index).setDone(true);
        Ui.markDoneMessage(taskList, index);
    }

    private static void unmarkTask(String input) throws InvalidIndexException {
        int index = Integer.parseInt(input) - 1;
        if (index >= taskList.listCount()) {
            throw new InvalidIndexException();
        }
        taskList.getTask(index).setDone(false);
        Ui.markUndoneMessage(taskList, index);
    }

    private static void deleteTask(String input) throws InvalidIndexException {
        int index = Integer.parseInt(input) - 1;
        if (index >= taskList.listCount()) {
            throw new InvalidIndexException();
        }
        Ui.deleteMessage();
        System.out.println("  " + taskList.getTask(index).toString());
        taskList.deleteTasks(index);
        Ui.printListMessage(taskList.listCount());
    }
}

