import java.util.Scanner;

/**
 * A class that acts as a CLI that keeps tracks of tasks you write and mark down
 */
public class Duke {

    public static void main(String[] args) {
        greetings();
        manageInput();
        goodbye();
    }

    private static void listTasks(int counter, Task[] storedTask) {
        System.out.println("____________________________________________________________");
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < counter; i++) {
            System.out.println((i + 1) + ". " + storedTask[i].toString());
        }
        System.out.println("____________________________________________________________\n");
    }

    private static void markingAsDone(String echoInput, int counter, Task[] storedTask) {
        try {
            String stringListNumber = echoInput.substring(5, echoInput.length());
            int listNumber = Integer.parseInt(stringListNumber);

            storedTask[listNumber-1].setIsDone(true);
            listTasks(counter, storedTask);
        }
        catch (NumberFormatException ex) {
            printMarkError();
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            printMarkError();
        }
        catch (NullPointerException ex) {
            printMarkError();
        }
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

    private static void manageInput() {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        Task[] storedTask = new Task[100];
        int counter = 0;

        while (!input.equals("bye")) {
            input = scanner.nextLine();
            if (input.equals("bye")) {
                break;
            }
            else if (input.equals("list")) {
                listTasks(counter, storedTask);
            }
            else if (input.startsWith("mark")) {
                markingAsDone(input, counter, storedTask);
            }
            else if (input.startsWith("todo ")) {
                Task tempTask = new Todo(input);
                storedTask[counter] = tempTask;
                counter = counter + 1;
                printTaskInput(tempTask, counter);
            }
            else if (input.startsWith("deadline") && input.contains("/")) {
                Task tempTask = new Deadline(input, input.substring(input.lastIndexOf("/") + 1));
                storedTask[counter] = tempTask;
                counter = counter + 1;
                printTaskInput(tempTask, counter);
            }
            else if (input.startsWith("event") && input.matches(".*/.*/.*")) {
                String tempInput = input.substring(input.indexOf("/") + 1);
                String fromString = tempInput.substring(0, tempInput.indexOf("/"));
                String toString = tempInput.substring(tempInput.lastIndexOf("/") + 1);

                Task tempTask = new Event(input, fromString, toString);
                storedTask[counter] = tempTask;
                counter = counter + 1;
                printTaskInput(tempTask, counter);
            }
            else {
                System.out.println("____________________________________________________________");
                System.out.println("Invalid input");
                System.out.println("____________________________________________________________\n");
            }
        }
    }

    private static void greetings() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________\n");
    }

    private static void goodbye() {
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________\n");
    }
}