import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.util.ArrayList;
import java.io.IOException;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        int tasksSize = 100;
        ArrayList<Task> tasks = new ArrayList<>();
        int maxIndex = tasksSize - 1;
        Storage.filePath = "./taskSave.txt";
        Storage.createSaveFile();
        Storage.loadSaveFile(tasks);
        printHelloStatement();
        while (true) {
            String input;
            input = in.nextLine();
            if (input.equals("bye")) {
                printByeStatement();
                break;
            } else if (input.equals("list")) {
                printAllTasks(tasks);
            } else if (input.startsWith("mark")) {
                try {
                    String[] temp = input.split(" ", 2);
                    int taskIndex = Integer.parseInt(temp[1]);
                    Task curTask = tasks.get(taskIndex-1);
                    curTask.markAsDone();
                    Storage.updateSaveFile(tasks);
                    printTaskStatusStatement(curTask, "mark");
                } catch (ArrayIndexOutOfBoundsException exception) {
                    printDottedLine();
                    System.out.println("☹ OOPS!!! The description of a mark cannot be empty");
                    printDottedLine();
                } catch (IndexOutOfBoundsException exception) {
                    printDottedLine();
                    System.out.println("☹ OOPS!!! The description of a mark does not exist");
                    printDottedLine();
                } catch (NumberFormatException exception) {
                    printDottedLine();
                    System.out.println("☹ OOPS!!! The description of a mark must be a number");
                    printDottedLine();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else if (input.startsWith("unmark")) {
                try {
                    String[] temp = input.split(" ", 2);
                    int taskIndex = Integer.parseInt(temp[1]);
                    Task curTask = tasks.get(taskIndex-1);
                    curTask.unmarkAsDone();
                    Storage.updateSaveFile(tasks);
                    printTaskStatusStatement(curTask, "unmark");
                } catch (ArrayIndexOutOfBoundsException exception) {
                    printDottedLine();
                    System.out.println("☹ OOPS!!! The description of a unmark cannot be empty");
                    printDottedLine();
                } catch (IndexOutOfBoundsException exception) {
                    printDottedLine();
                    System.out.println("☹ OOPS!!! The description of a unmark does not exist");
                    printDottedLine();
                } catch (NumberFormatException exception) {
                    printDottedLine();
                    System.out.println("☹ OOPS!!! The description of a unmark must be a number");
                    printDottedLine();
                }
            } else if (input.startsWith("delete")) {
                try {
                    String[] temp = input.split(" ", 2);
                    int taskIndex = Integer.parseInt(temp[1]);
                    printTaskDeletedStatement(tasks, tasks.get(taskIndex-1));
                    tasks.remove(taskIndex-1);
                    Storage.updateSaveFile(tasks);
                } catch (ArrayIndexOutOfBoundsException exception) {
                    printDottedLine();
                    System.out.println("☹ OOPS!!! The description of a delete cannot be empty");
                    printDottedLine();
                } catch (IndexOutOfBoundsException exception) {
                    printDottedLine();
                    System.out.println("☹ OOPS!!! The description of a delete does not exist");
                    printDottedLine();
                } catch (NumberFormatException exception) {
                    printDottedLine();
                    System.out.println("☹ OOPS!!! The description of a delete must be a number");
                    printDottedLine();
                }
            } else if (input.startsWith("todo") & (tasks.size() <= maxIndex)) {
                try {
                    String[] temp = input.split("todo "); //separates todo description
                    String description = temp[1];
                    ToDo todo = new ToDo(description);
                    tasks.add(todo);
                    Storage.updateSaveFile(tasks);
                    printTaskAddedStatement(tasks, todo);
                } catch (IndexOutOfBoundsException exception) {
                    printDottedLine();
                    System.out.println("☹ OOPS!!! The description of a todo cannot be empty");
                    printDottedLine();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else if (input.startsWith("deadline") & (tasks.size() <= maxIndex)) {
                try {
                    String[] temp = input.split("deadline | /by "); //separates deadline description and time
                    String description = temp[1];
                    String by = temp[2];
                    Deadline deadline = new Deadline(description, by);
                    tasks.add(deadline);
                    Storage.updateSaveFile(tasks);
                    printTaskAddedStatement(tasks, deadline);
                } catch (IndexOutOfBoundsException exception) {
                    printDottedLine();
                    System.out.println("☹ OOPS!!! The description of a deadline cannot be empty");
                    printDottedLine();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else if (input.startsWith("event") & (tasks.size() <= maxIndex)) {
                try {
                    String[] temp = input.split(("event | /from | /to ")); //separates event description and times
                    String description = temp[1];
                    String from = temp[2];
                    String to = temp[3];
                    Event event = new Event(description, from, to);
                    tasks.add(event);
                    Storage.updateSaveFile(tasks);
                    printTaskAddedStatement(tasks, event);
                } catch (IndexOutOfBoundsException exception) {
                    printDottedLine();
                    System.out.println("☹ OOPS!!! The description of a event cannot be empty");
                    printDottedLine();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else if ((input.startsWith("todo") | input.startsWith("deadline") | input.startsWith("event"))
                    & (tasks.size() > maxIndex)) {
                printDottedLine();
                System.out.println("☹ OOPS!!! The tasks list is currently full");
                printDottedLine();
            } else {
                printUnknownCommandError();
            }
        }
    }

    private static void printDottedLine() {
        System.out.println("____________________________________________________________");
    }

    private static void printAllTasks(ArrayList<Task> tasks) {
        printDottedLine();
        int counter = 1;
        System.out.println("Here are the tasks in your list:");
        for (Task task : tasks) {
            if (task != null) {
                System.out.println(counter + "." + task);
                counter++;
            }
        }
        printDottedLine();
    }

    private static void printHelloStatement() {
        printDottedLine();
        System.out.println(" Hello! I'm Duke");
        System.out.println(" What can I do for you?");
        printDottedLine();
    }

    private static void printByeStatement() {
        printDottedLine();
        System.out.println(" Bye. Hope to see you again soon!");
        printDottedLine();
    }

    private static void printTaskStatusStatement(Task curTask, String status) {
        printDottedLine();
        if (status.equals("mark")) {
            System.out.println("Nice! I've marked this task as done:");
        } else {
            System.out.println("OK, I've marked this task as not done yet:");
        }
        System.out.println(curTask);
        printDottedLine();
    }

    private static void printTaskAddedStatement(ArrayList<Task> tasks, Task task) {
        printDottedLine();
        System.out.println("Got it. I've added this task:");
        System.out.println(" " + task);
        if (tasks.size() == 1) {
            System.out.println("Now you have " + tasks.size() + " task in the list.");
        } else {
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        }
        printDottedLine();
    }

    private static void printTaskDeletedStatement(ArrayList<Task> tasks, Task task) {
        printDottedLine();
        System.out.println("Noted. I've removed this task:");
        System.out.println(" " + task);
        if (tasks.size() == 2) {
            System.out.println("Now you have " + (tasks.size() - 1) + " task in the list.");
        } else {
            System.out.println("Now you have " + (tasks.size() - 1) + " tasks in the list.");
        }
        printDottedLine();
    }

    private static void printUnknownCommandError() {
        printDottedLine();
        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        printDottedLine();
    }
}