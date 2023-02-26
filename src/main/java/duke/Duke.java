package duke;

import command.Command;
import exception.CommandNotRecognisedException;
import exception.EmptyTaskException;
import exception.IllegalCharacterException;
import exception.InvalidTaskNumberException;
import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;


import java.util.Scanner;

import java.util.ArrayList;

public class Duke {

    public static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {

        FileManager.openFile();
        printStartMessage();

        String input;
        Scanner in = new Scanner(System.in);

        do {
            input = in.nextLine();
            try {
                processCommand(input);
            } catch (CommandNotRecognisedException e) {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                printDivider();
            } catch (IllegalCharacterException e) {
                System.out.println("☹ OOPS!!! Input should not contain '|' or '-'.");
                printDivider();
            }
        } while (!input.equals(Command.COMMAND_BYE));


        FileManager.writeToFile(tasks);
    }


    public static void initialiseTaskList(String line) {
        String[] task = line.split("[|\\-]");

        switch(task[0].trim()) {
        case "T":
            try {
                addTodoTask(task[2].trim());
            } catch (EmptyTaskException e) {
                System.out.println("This shouldn't be happening :O");
            }
            break;
        case "D":
            addDeadlineTask(task[2].trim(), task[3].trim());
            break;
        case "E":
            addEventTask(task[2].trim(), task[3].trim(), task[4].trim());
            break;
        }

        if (task[1].contains("X")) {
            try {
                markTaskDone(tasks.size()-1);
            } catch (NumberFormatException e) {
                System.out.println("☹ OOPS!!! Task number should be an integer.");
                printDivider();
            } catch (InvalidTaskNumberException e) {
                System.out.println("☹ OOPS!!! The task specified does not exist in the task list.");
                printDivider();
            } catch (IndexOutOfBoundsException e) {
                System.out.println("☹ OOPS!!! The description of 'mark' cannot be empty.");
                printDivider();
            }
        }
    }

    public static void printDivider () {
        System.out.println("____________________________________________________________");
    }

    public static void printStartMessage() {
        String logo = " ____\n"
                + "|    \\ ___ _ _ ___\n"
                + "|  |  | .'| | | -_|\n"
                + "|____/|__,|\\_/|___|\n";

        printDivider();
        System.out.println("List Summary:");
        printTaskList();
        System.out.print(logo);
        System.out.println("Hi, I'm Dave!\n"
                + "What can I do for you?");
        printDivider();
    }

    //Problem here: somehow accept empty task :(
    public static void processCommand (String input) throws CommandNotRecognisedException, IllegalCharacterException {
        if (input.contains("|") || input.contains("-")) {
            throw new IllegalCharacterException();
        }

        String action = input.split(" ")[0];
        String[] taskDesc;

        switch (action) {
        case Command.COMMAND_BYE:
            printBye();
            break;
        case Command.COMMAND_LIST:
            printTaskList();
            break;
        case Command.COMMAND_MARK:
            try {
                markTaskDone(Integer.parseInt(input.split(" ")[1]) - 1);
                tasks.get(Integer.parseInt(input.split(" ")[1].trim())-1).printUnmarkMessage();
            } catch (NumberFormatException e) {
                System.out.println("☹ OOPS!!! Task number should be an integer.");
            } catch (InvalidTaskNumberException e) {
                System.out.println("☹ OOPS!!! The task specified does not exist in the task list.");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("☹ OOPS!!! The description of 'mark' cannot be empty.");
            }
            printDivider();
            break;
        case Command.COMMAND_UNMARK:
            try {
                markTaskUndone(Integer.parseInt(input.split(" ")[1].trim())-1);
                tasks.get(Integer.parseInt(input.split(" ")[1].trim())-1).printUnmarkMessage();
            } catch (NumberFormatException e) {
                System.out.println("☹ OOPS!!! Task number should be an integer.");
            } catch (InvalidTaskNumberException e) {
                System.out.println("☹ OOPS!!! The task specified does not exist in the task list.");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("☹ OOPS!!! The description of 'unmark' cannot be empty.");
            }
            printDivider();
            break;
        case Command.COMMAND_TODO:
            try {
                addTodoTask(input.substring(Command.COMMAND_TODO.length()).trim());
                //addTodoTask(input.split(" ")[1].trim());
                printTaskAdded();
            } catch (EmptyTaskException e) {
                System.out.println("☹ OOPS!!! The description of 'todo' cannot be empty.");
            }
            printDivider();
            break;
        case Command.COMMAND_DEADLINE:
            try {
                taskDesc = input.split("/by");
                addDeadlineTask(taskDesc[0].substring(Command.COMMAND_DEADLINE.length()).trim(), taskDesc[1].trim());
                printTaskAdded();
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("☹ OOPS!!! The description of 'deadline' should include a task and deadline.");
            }
            printDivider();
            break;
        case Command.COMMAND_EVENT:
            try {
                taskDesc = input.split("/from|/to");
                addEventTask(taskDesc[0].substring(Command.COMMAND_EVENT.length()).trim()
                        , taskDesc[1].trim(), taskDesc[2].trim());
                printTaskAdded();
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("☹ OOPS!!! The description of 'event' should include a task and time period.");
            }
            printDivider();
            break;
        case Command.COMMAND_DELETE:
            try {
                deleteTask(Integer.parseInt(input.split(" ")[1]) - 1);
            } catch (NumberFormatException e) {
                System.out.println("☹ OOPS!!! Task number should be an integer.");
            } catch (InvalidTaskNumberException e) {
                System.out.println("☹ OOPS!!! The task specified does not exist in the task list.");

            }
            printDivider();
            break;
        default:
            throw new CommandNotRecognisedException();
        }
    }

    public static void printBye() {
        System.out.println("Bye. Hope to see you again soon!");
        printDivider();
    }

    private static void printTaskList() {
        if (tasks.size() == 0) {
            System.out.println("You are free today :)");
        } else {
            for (int i = 0; i < tasks.size(); ++i) {
                System.out.print(i+1 + ".");
                System.out.println(tasks.get(i));
            }
        }
        printDivider();
    }


    private static void markTaskDone(Integer taskIndex) throws InvalidTaskNumberException {
        if (taskIndex < 0 || taskIndex > tasks.size()) {
            throw new InvalidTaskNumberException();
        } else {
            tasks.get(taskIndex).markDone();
        }
    }


    private static void markTaskUndone(Integer taskIndex) throws InvalidTaskNumberException {
        if (taskIndex < 0 || taskIndex > tasks.size()) {
            throw new InvalidTaskNumberException();
        } else {
            tasks.get(taskIndex).markUndone();
        }
    }


    private static void addTodoTask(String task) throws EmptyTaskException {
        if (task.equals("")) {
                throw new EmptyTaskException();
        } else {
            tasks.add(new ToDo(task));
        }
    }


    private static void addDeadlineTask(String task, String deadline) {
        tasks.add(new Deadline(task, deadline));
    }

    private static void addEventTask(String task, String fromDate, String byDate) {
        tasks.add(new Event(task, fromDate, byDate));
    }

    private static void printTaskAdded() {
        System.out.println("Got it. I've added this task:\n " + tasks.get(tasks.size()-1)
                + "\nNow you have " + tasks.size() + " tasks in the list.");
    }

    private static void deleteTask(int taskIndex) throws InvalidTaskNumberException {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new InvalidTaskNumberException();
        } else {
            System.out.println("Noted. I've removed this task:\n " + tasks.get(taskIndex)
                    + "\nNow you have " + (tasks.size()-1) + " tasks in the list.");
            tasks.remove(tasks.get(taskIndex));
        }
    }
}
