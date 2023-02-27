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

import java.util.ArrayList;

public class Duke {

    public static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {

        Storage.openFile();
        Ui.printStartMessage();

        String userInput;
        do {
            userInput = Ui.getUserInput();
            try {
                Parser.processCommand(userInput, tasks);
            } catch (CommandNotRecognisedException e) {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                Ui.printDivider();
            } catch (IllegalCharacterException e) {
                System.out.println("☹ OOPS!!! Input should not contain '|' or '-'.");
                Ui.printDivider();
            }
        } while (!userInput.equals(Command.COMMAND_BYE));

        Storage.writeToFile(tasks);
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
                Ui.printDivider();
            } catch (InvalidTaskNumberException e) {
                System.out.println("☹ OOPS!!! The task specified does not exist in the task list.");
                Ui.printDivider();
            } catch (IndexOutOfBoundsException e) {
                System.out.println("☹ OOPS!!! The description of 'mark' cannot be empty.");
                Ui.printDivider();
            }
        }
    }

    public static void printTaskList() {
        if (tasks.size() == 0) {
            System.out.println("You are free today :)");
        } else {
            for (int i = 0; i < tasks.size(); ++i) {
                System.out.print(i+1 + ".");
                System.out.println(tasks.get(i));
            }
        }
        Ui.printDivider();
    }


    public static void markTaskDone(Integer taskIndex) throws InvalidTaskNumberException {
        if (taskIndex < 0 || taskIndex > tasks.size()) {
            throw new InvalidTaskNumberException();
        } else {
            tasks.get(taskIndex).markDone();
        }
    }


    public static void markTaskUndone(Integer taskIndex) throws InvalidTaskNumberException {
        if (taskIndex < 0 || taskIndex > tasks.size()) {
            throw new InvalidTaskNumberException();
        } else {
            tasks.get(taskIndex).markUndone();
        }
    }


    public static void addTodoTask(String task) throws EmptyTaskException {
        if (task.equals("")) {
                throw new EmptyTaskException();
        } else {
            tasks.add(new ToDo(task));
        }
    }


    public static void addDeadlineTask(String task, String deadline) {
        tasks.add(new Deadline(task, deadline));
    }

    public static void addEventTask(String task, String fromDate, String byDate) {
        tasks.add(new Event(task, fromDate, byDate));
    }

    public static void printTaskAdded() {
        System.out.println("Got it. I've added this task:\n " + tasks.get(tasks.size()-1)
                + "\nNow you have " + tasks.size() + " tasks in the list.");
    }

    public static void deleteTask(int taskIndex) throws InvalidTaskNumberException {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new InvalidTaskNumberException();
        } else {
            System.out.println("Noted. I've removed this task:\n " + tasks.get(taskIndex)
                    + "\nNow you have " + (tasks.size()-1) + " tasks in the list.");
            tasks.remove(tasks.get(taskIndex));
        }
    }
}
