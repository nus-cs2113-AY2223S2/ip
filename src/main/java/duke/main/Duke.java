package duke.main;

import duke.exceptions.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A class that acts as a CLI that keeps track of tasks you write and mark down
 */
public class Duke {

    public static void main(String[] args) {
        greetings();
        manageInput();
        goodbye();
    }

    private static int selectTask(String echoInput, int counter, ArrayList<Task> storedTask) {
        try {
            if (echoInput.startsWith("mark")) {
                String stringListNumber = echoInput.substring(5, echoInput.length());
                int index = Integer.parseInt(stringListNumber) - 1;
                storedTask.get(index).setIsDone(true);
                listTasks(counter, storedTask);
            }
            else if (echoInput.startsWith("delete")) {
                String stringListNumber = echoInput.substring(7, echoInput.length());
                int index = Integer.parseInt(stringListNumber) - 1;
                deleteTask(index, storedTask);
                return counter - 1;
            }
        } catch (NumberFormatException ex) {
            printMarkError();
        } catch (ArrayIndexOutOfBoundsException ex) {
            printMarkError();
        } catch (IndexOutOfBoundsException ex) {
            printMarkError();
        } catch (NullPointerException ex) {
            printMarkError();
        }
        return counter;
    }

    private static void deleteTask(int removeIndex, ArrayList<Task> storedTask) {
        System.out.println("____________________________________________________________");
        System.out.println("Noted. I've removed this task:\n" +
                "       " + storedTask.get(removeIndex).toString() + "\n" +
                "Now you have " + (storedTask.size() - 1) + " tasks in the list.");
        System.out.println("____________________________________________________________\n");
        storedTask.remove(removeIndex);
    }

    private static void manageInput() {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        ArrayList<Task> storedTask = new ArrayList<Task>();
        int counter = 0;

        while (!input.equals("bye")) {
            try {
                input = scanner.nextLine();
                if (input.equals("bye")) {
                    break;
                } else if (input.equals("list")) {
                    listTasks(counter, storedTask);
                } else if (input.startsWith("mark")) {
                    selectTask(input, counter, storedTask);
                } else if (input.startsWith("todo")) {
                    blankTodo(input);
                    Task tempTask = new Todo(input);
                    storedTask.add(tempTask);
                    counter = counter + 1;
                    printTaskInput(tempTask, counter);
                } else if (input.startsWith("deadline") && input.contains("/")) {
                    Task tempTask = new Deadline(input, input.substring(input.lastIndexOf("/") + 1));
                    storedTask.add(tempTask);
                    counter = counter + 1;
                    printTaskInput(tempTask, counter);
                } else if (input.startsWith("event") && input.matches(".*/.*/.*")) {
                    String tempInput = input.substring(input.indexOf("/") + 1);
                    String fromString = tempInput.substring(0, tempInput.indexOf("/"));
                    String toString = tempInput.substring(tempInput.lastIndexOf("/") + 1);

                    Task tempTask = new Event(input, fromString, toString);
                    storedTask.add(tempTask);
                    counter = counter + 1;
                    printTaskInput(tempTask, counter);
                } else if (input.startsWith("delete")) {
                    counter = selectTask(input, counter, storedTask);
                } else {
                    invalidInput();
                }
            } catch (DukeException ex) {
                continue;
            }
        }
    }

    private static void blankTodo(String input) throws DukeException {
        if (input.length() == 4 || input.substring(4).isBlank()) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
    }

    private static void invalidInput() throws DukeException {
        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    private static void listTasks(int counter, ArrayList<Task> storedTask) {
        System.out.println("____________________________________________________________");
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < counter; i++) {
            System.out.println((i + 1) + ". " + storedTask.get(i).toString());
        }
        System.out.println("____________________________________________________________\n");
    }

    private static void printMarkError() {
        System.out.println("____________________________________________________________");
        System.out.println("*DID NOT ENTER A VALID NUMBER*");
        System.out.println("____________________________________________________________\n");
    }

    private static void printTaskInput(Task task, int counter) {
        System.out.println("____________________________________________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + counter + " tasks in the list.");
        System.out.println("____________________________________________________________\n");
    }

    private static void greetings() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm duke.main.Duke");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________\n");
    }

    private static void goodbye() {
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________\n");
    }
}