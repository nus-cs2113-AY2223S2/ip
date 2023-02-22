import java.util.Scanner;

public class Duke {

    protected static void printLine() {
        System.out.println("\t==========================================");
    }

    protected static void printOutOfBoundsMessage() {
        printLine();
        System.out.println("\tIndex out of range, the list not so long");
        printLine();
    }

    public static void printExitMessage() {
        printLine();
        System.out.print("\tBye. Hope to see you again soon!\n");
        printLine();
    }

    public static void printList(Task t[], int counter) {
        printLine();
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < counter; i++) {
            System.out.println("\t" + (i + 1) + "." + t[i].getDescription());
        }
        printLine();
    }

    public static void printNumberOfTasks(int counter) {
        System.out.println("\tNow you  have " + counter + " tasks in the list");
    }

    public static void printTodoMessage(Task t, String tododescription, int counter) {
        printLine();
        System.out.println("\tGot it. I've added this task: ");
        System.out.println("\t" +  t.getDescription());
        printNumberOfTasks(counter);
        printLine();
    }

    public static void printDeadlineMessage(Task t, String deadlineDescription, int counter) {
        printLine();
        System.out.println("\tGot it. I've added this task: ");
        System.out.println("\t" + t.getDescription());
        printNumberOfTasks(counter);
        printLine();
    }

    public static void printEventMessage(Task t, String eventDescription, int counter) {
        printLine();
        System.out.println("\tGot it. I've added this task: ");
        System.out.println("\t" + t.getDescription());
        printNumberOfTasks(counter);
        printLine();
    }

    /*
     * public static void addTask(Task t[], int counter, String input) {
     * t[counter] = new Task(input);
     * printLine();
     * System.out.println("added: " + t[counter].description);
     * counter++;
     * printLine();
     * }
     */

    public static void main(String[] args) {

        String logo = "\t ____   _              __    __ \n"
                + "\t/  __\\ | |     _____  |   \\/   |\n"
                + "\t| /    | |    /  _  \\ | |\\  /| |\n"
                + "\t| \\ __ | |___ | |_| | | | \\/ | |\n"
                + "\t\\____/ |____/ \\_____/ |_|    |_|\n";

        System.out.println("\tHello from\n" + logo);

        System.out.println("\tHello! I'm CLoM!\n");
        System.out.print("\tWhat can I do for you?\n");
        printLine();

        int counter = 0; // number of items in the list
        boolean isExit = false;

        Task t[] = new Task[100]; // task list

        while (!isExit) {
            Scanner scan = new Scanner(System.in);
            String input = scan.nextLine();
            String[] command = input.split(" ", 2); // split the input into an array of strings

            if (input.equals("bye")) {
                // terminate clom, print goodbye message
                isExit = true;
                printExitMessage();

            } else if (input.equals("list")) {
                // Display list of tasks
                printList(t, counter);

            } else if (input.contains(" ")) {

                if (command[0].equals("mark")) {
                    // mark task as done
                    int markIndex = Integer.parseInt(command[1]) - 1;

                    if (markIndex > counter - 1 || markIndex < 0) {
                        printOutOfBoundsMessage();
                    } else {
                        printLine();
                        System.out.println("\tNice! I've marked this task as done:");
                        t[markIndex].markAsDone();
                        System.out.println("\t" + t[markIndex].getType() + t[markIndex].getStatusIcon() + t[markIndex].description);
                        printLine();
                    }

                } else if (command[0].equals("unmark")) {
                    // mark task as undone
                    int unmarkIndex = Integer.parseInt(command[1]) - 1;
                    if (unmarkIndex > counter - 1 || unmarkIndex < 0) {
                        printOutOfBoundsMessage();
                    } else {
                        printLine();
                        System.out.println("\tOK, I've marked this task as not done yet:");
                        t[unmarkIndex].markAsNotDone();
                        System.out.println("\t" + t[unmarkIndex].getType() + t[unmarkIndex].getStatusIcon() + t[unmarkIndex].description);
                        printLine();
                    }

                } else if (command[0].equals("todo")) {
                    String todoDescription = command[1];
                    t[counter] = new Todo(todoDescription);
                    printTodoMessage(t[counter], todoDescription, counter + 1);
                    counter++;

                } else if (command[0].equals("deadline")) {
                    String[] deadlineCommand = command[1].split(" /by", 2);
                    String deadlineDescription = deadlineCommand[0];
                    String byDate = deadlineCommand[1];
                    t[counter] = new Deadline(deadlineDescription, byDate);
                    printDeadlineMessage(t[counter], deadlineDescription, counter + 1);
                    counter++;

                } else if (command[0].equals("event")) {
                    String[] eventCommand = command[1].split(" /from | /to");
                    String eventDescription = eventCommand[0];
                    String eventFromDate = eventCommand[1];
                    String eventToDate = eventCommand[2];
                    t[counter] = new Event(eventDescription, eventFromDate, eventToDate);
                    printEventMessage(t[counter], eventDescription, counter + 1);
                    counter++;
                }

                // } else {
                // // Add task to the task list
                // // addTask(t, counter, input);
                // t[counter] = new Task(input);
                // printLine();
                // System.out.println("added: " + t[counter].description);
                // counter++;
                // printLine();
            }
        }
    }

}
