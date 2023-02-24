package duke.commands;

import duke.exception.EmptyCommandException;
import duke.exception.InvalidCommandException;
import duke.exception.InvalidIndexException;

import duke.tasks.*;
import duke.ui.Ui;

/**
 * Class containing method for parsing the commands.
 */
public class Parser {
    private static TaskList taskList = new TaskList();

    /**
     * A method that handles the user inputs by calling methods based on the command given.
     *
     * @param userInput A string that represents the user's input into the terminal.
     * @throws InvalidCommandException The exception thrown when user enters an invalid command.
     */
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
                deleteTask(cases[1]);
            } else if (command.equals("find")) {
                findTask(cases[1]);
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

    /**
     * Creates a new todo task which will be added to the task list and database.
     *
     * @param cases An array of string that contains the todo command and description.
     * @throws EmptyCommandException The exception thrown when user enters a command with no description.
     */
    private static void createTodo(String[] cases) throws EmptyCommandException{
        if (cases.length == 1) {
            throw new EmptyCommandException();
        }
        String input = cases[1];
        Task currTask = new Todo(input);
        taskList.addTasks(currTask);
        Ui.printAddedTaskCommand(taskList);
    }

    /**
     * Creates a new deadline task which will be added to the task list and database.
     *
     * @param cases An array of string that contains the deadline command, description and due time.
     * @throws EmptyCommandException The exception thrown when user only inputs the command
     */
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

    /**
     * Creates a new event task which will be added to the task list and database.
     *
     * @param cases An array of string that contains the deadline command, description and due time.
     * @throws EmptyCommandException The exception thrown when user only inputs the command.
     */
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

    /**
     * Method used to print out the task list.
     */
    private static void printList() {
        Ui.printOpeningListMessage();
        for (int i = 0; i < taskList.listCount(); ++i) {
            String index = Integer.toString(i + 1);
            System.out.println(index + '.' + taskList.getTask(i).toString());
        }
    }

    /**
     * Marks the task as done.
     *
     * @param input The index of the task to be marked as done.
     * @throws InvalidIndexException The exception thrown when user enters an invalid index.
     */
    private static void markTask(String input) throws InvalidIndexException {
        int index = Integer.parseInt(input) - 1;
        if (index >= taskList.listCount()) {
            throw new InvalidIndexException();
        }
        taskList.setDone(index, true);
        Ui.markDoneMessage(taskList, index);
    }

    /**
     * Marks the task as not done.
     *
     * @param input The index of the task to be unmarked.
     * @throws InvalidIndexException The exception thrown when user enters an invalid index.
     */
    private static void unmarkTask(String input) throws InvalidIndexException {
        int index = Integer.parseInt(input) - 1;
        if (index >= taskList.listCount()) {
            throw new InvalidIndexException();
        }
        taskList.getTask(index).setDone(false);
        Ui.markUndoneMessage(taskList, index);
    }

    /**
     * Deletes a task from the task list
     *
     * @param input The index of the task to be removed from the task list
     * @throws InvalidIndexException The exception thrown when user enters an invalid index.
     */
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

    private static void findTask(String filter) {
        Ui.printFindMessage();
        int findListIndex = 0;
        for (int i = 0; i < taskList.listCount(); ++i) {
            if(taskList.getTask(i).containsFilter(filter)) {
                ++findListIndex;
                System.out.println(Integer.toString(findListIndex )+ '.' + taskList.getTask(i).toString());
            }
        }
    }
}

