import java.util.ArrayList;
import java.util.Scanner;
public class Duke {
    /** Language state of the program. */
    public static boolean isSinglish = false;
    /** A fixed sized array to store all the tasks entered from the user. */
    public static ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Toggles the language setting between normal and Singlish mode.
     * Prints to the output the changes made.
     */
    public static void changeLanguage() {
        if (isSinglish) {
            isSinglish = false;
            System.out.println("You want Duke to help you instead? Can can I go call him now");
            System.out.println("Singlish mode = OFF");
            sayHello();
        } else {
            isSinglish = true;
            System.out.println("Changing language mode to Singlish...");
            System.out.println("Singlish mode = ON");
            sayHello();
        }
    }

    /**
     * Prints out horizontal lines for formatting.
     */
    public static void printHorizontalLines() {
        if (isSinglish) {
            System.out.println("************************************************************");
        } else {
            System.out.println("____________________________________________________________");
        }
    }

    /**
     * Prints out the greeting message.
     */
    public static void sayHello() {
        printHorizontalLines();
        if (isSinglish) {
            System.out.println("Hello, my name is Uncle Simon, call me Simon can liao");
            System.out.println("You need my help?");
            System.out.println("(To turn off Singlish mode, type \"change lang\")");
        } else {
            System.out.println("Hello, I'm Duke.");
            System.out.println("What can I do for you?");
            System.out.println("(To turn on Singlish mode, type \"change lang\").");
        }
        printHorizontalLines();
    }

    /**
     * Prints out the farewell message.
     */
    public static void sayGoodbye() {
        if (isSinglish) {
            System.out.println("Ok bye bye, come back soon ah!");
        } else {
            System.out.println("Bye. Hope to see you again soon!");
        }
        printHorizontalLines();
    }

    /**
     * Adds the entered task to the list of tasks.
     * if the list of tasks is full, informs the user that the task list is full and no new tasks can be added.
     *
     * @param line The task entered by the user.
     */
    public static void addToList(String line, TypeOfTask typeOfTask) {
        if (typeOfTask.equals(TypeOfTask.TODO)) {
            Todo item = new Todo(line, tasks.size() + 1);
            tasks.add(item);
        } else if (typeOfTask.equals(TypeOfTask.DEADLINE)) {
            Deadline item = new Deadline(line, tasks.size() + 1);
            tasks.add(item);
        } else if (typeOfTask.equals(TypeOfTask.EVENT))  {
            Event item = new Event(line, tasks.size() + 1);
            tasks.add(item);
        }
        printHorizontalLines();
    }

    /**
     * Prints out the task passed into it
     */
    public static void printTask(Task task) {
        System.out.println(task.index + ".[" + task.getStatusForTypeOfTask() + "]" + "[" + task.getStatusIcon() + "] " + task.getDescription());
    }

    /**
     * Prints out the entire list of tasks entered by the user.
     */
    public static void printList() {
        for (Task task : tasks) {
            printTask(task);
        }
        printHorizontalLines();
    }

    /**
     * Prints out a message informing the user that it has typed a command with invalid syntax
     */
    public static void warnWrongSyntax() {
        if (isSinglish) {
            System.out.println("Eh you typed wrongly, can try typing again?");
        } else {
            System.out.println("Invalid syntax, please try again");
        }
    }

    /**
     * Marks or Unmark the selected task whether it is done, prints out the selected task alongside its state.
     * If the user gives an invalid index, informs the user about it.
     * Does nothing if the user trys to mark a marked task and vice versa/
     *
     * @param index The index of the task selected to be marked or unmarked.
     * @param isMark Whether to mark or unmark the task.
     */
    public static void markTask(int index, boolean isMark) {
        index--;
        if (index < 0 || index >= tasks.size()) {
            if (isSinglish) {
                System.out.println("Eh, your list dun have a task at that index lah");
            } else {
                System.out.println(("The list does not have a task of that index"));
            }
        } else {
            if (tasks.get(index).getIsDone() != isMark) {
                tasks.get(index).switchIsDone();
            }

            if (isSinglish) {
                System.out.println("Ok I updated it:");
            } else {
                System.out.println("Updated the following task:");
            }
            System.out.println((index + 1) +
                            ".[" + tasks.get(index).getStatusForTypeOfTask() + "]" +
                            "[" + tasks.get(index).getStatusIcon() + "] " +
                            tasks.get(index).getDescription());
        }
    }

    public static void main(String[] args) {
        sayHello();

        while (true) {
            Scanner in = new Scanner(System.in);
            String line = in.nextLine();

            String[] commands = line.split(" ");
            if (commands[0].equals("bye")) {
                sayGoodbye();
                break;
            } else if (commands[0].equals("change") && commands.length == 2 && commands[1].equals("lang")) {
                changeLanguage();
            } else if (commands[0].equals("list") && commands.length == 1) {
                printList();
            } else if ((commands[0].equals("mark") || commands[0].equals("unmark")) && commands.length == 2) {
                if (commands[1].matches("\\d+?")) {
                    boolean isMark = commands[0].equals("mark");
                    markTask(Integer.parseInt(commands[1]), isMark);
                } else {
                    warnWrongSyntax();
                }
            } else if (commands[0].equals("todo") ) {
                String desc = line.substring(6, line.length());
                addToList(desc, TypeOfTask.TODO);
            } else if (commands[0].equals("deadline") ) {
                String desc = line.substring(10, line.length());
                addToList(desc, TypeOfTask.DEADLINE);
            } else if (commands[0].equals("event") ) {
                String desc = line.substring(7, line.length());
                addToList(desc, TypeOfTask.EVENT);
            }
        }
    }
}
