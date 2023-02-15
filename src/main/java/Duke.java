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
        int currentIndex = 0;
        int maxIndex = tasksSize - 1;
        Save.filePath = "./taskSave.txt";
        Save.createSaveFile();
        Save.loadSaveFile(tasks);
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
                    Save.updateSaveFile(tasks);
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
                    Save.updateSaveFile(tasks);
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
                    currentIndex--;
                    printTaskDeletedStatement(currentIndex, tasks.get(taskIndex-1));
                    tasks.remove(taskIndex-1);
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
            } else if (input.startsWith("todo") & (currentIndex <= maxIndex)) {
                try {
                    String[] temp = input.split("todo "); //separates todo description
                    String description = temp[1];
                    ToDo todo = new ToDo(description);
                    tasks.add(todo);
                    currentIndex++;
                    Save.updateSaveFile(tasks);
                    printTaskAddedStatement(currentIndex, todo);
                } catch (IndexOutOfBoundsException exception) {
                    printDottedLine();
                    System.out.println("☹ OOPS!!! The description of a todo cannot be empty");
                    printDottedLine();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else if (input.startsWith("deadline") & (currentIndex <= maxIndex)) {
                try {
                    String[] temp = input.split("deadline | /by "); //separates deadline description and time
                    String description = temp[1];
                    String by = temp[2];
                    Deadline deadline = new Deadline(description, by);
                    tasks.add(deadline);
                    currentIndex++;
                    Save.updateSaveFile(tasks);
                    printTaskAddedStatement(currentIndex, deadline);
                } catch (IndexOutOfBoundsException exception) {
                    printDottedLine();
                    System.out.println("☹ OOPS!!! The description of a deadline cannot be empty");
                    printDottedLine();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else if (input.startsWith("event") & (currentIndex <= maxIndex)) {
                try {
                    String[] temp = input.split(("event | /from | /to ")); //separates event description and times
                    String description = temp[1];
                    String from = temp[2];
                    String to = temp[3];
                    Event event = new Event(description, from, to);
                    tasks.add(event);
                    currentIndex++;
                    Save.updateSaveFile(tasks);
                    printTaskAddedStatement(currentIndex, event);
                } catch (IndexOutOfBoundsException exception) {
                    printDottedLine();
                    System.out.println("☹ OOPS!!! The description of a event cannot be empty");
                    printDottedLine();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else if ((input.startsWith("todo") | input.startsWith("deadline") | input.startsWith("event"))
                    & (currentIndex > maxIndex)) {
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

    private static void printTaskAddedStatement(int currentIndex, Task task) {
        printDottedLine();
        System.out.println("Got it. I've added this task:");
        System.out.println(" " + task);
        if (currentIndex == 1) {
            System.out.println("Now you have " + currentIndex + " task in the list.");
        } else {
            System.out.println("Now you have " + currentIndex + " tasks in the list.");
        }
        printDottedLine();
    }

    private static void printTaskDeletedStatement(int currentIndex, Task task) {
        printDottedLine();
        System.out.println("Noted. I've removed this task:");
        System.out.println(" " + task);
        if (currentIndex == 1) {
            System.out.println("Now you have " + currentIndex + " task in the list.");
        } else {
            System.out.println("Now you have " + currentIndex + " tasks in the list.");
        }
        printDottedLine();
    }

    private static void printUnknownCommandError() {
        printDottedLine();
        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        printDottedLine();
    }
}