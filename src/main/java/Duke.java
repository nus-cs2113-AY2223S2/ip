import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    // global variables
    private static final String DOTTED_LINE = "    --------------------------------------------------";
    private static final String TAB_SIZE = "     "; // 5 spaces

    public static void main(String[] args) {

        ArrayList<Task> todoList = new ArrayList<>();

        // greets user
        System.out.println(DOTTED_LINE);
        printLogo(TAB_SIZE);
        greetUser(TAB_SIZE);
        System.out.println(DOTTED_LINE + '\n');

        String userCommand;
        String[] userSplitInputs;

        do {
            Scanner scanner = new Scanner(System.in);
            userCommand = scanner.nextLine();
            userSplitInputs = userCommand.split(" ");

            // top dotted line
            System.out.println(DOTTED_LINE);

            // body
            if (userSplitInputs[0].equals("bye")) {
                sayBye();
            } else if (userSplitInputs[0].equals("list")) {
                listItems(todoList);
            } else if (userSplitInputs[0].equals("done")) {
                errorCheckingForMarkTasks("done", userSplitInputs,
                        todoList);
            } else if (userSplitInputs[0].equals("undone")) {
                errorCheckingForMarkTasks("undone", userSplitInputs,
                        todoList);
            } else {
                add(todoList, userCommand);
            }

            // bottom dotted line
            System.out.println(DOTTED_LINE + '\n');

        } while (!userCommand.equals("bye"));

    }


    /**
     * prints the logo of the chatBot
     *
     * @param TAB_SIZE white spaces in front of the printed text for aesthetic purposes
     */
    public static void printLogo(final String TAB_SIZE) {

        String logo = "      ____     _    ____\n"
                + TAB_SIZE + "|  _ \\ __| |__|  _ \\\n"
                + TAB_SIZE + "| | //|__   __| | //\n"
                + TAB_SIZE + "| |_\\\\   |  |_| |_\\\\\n"
                + TAB_SIZE + "|____/   |___/|____/\n";

        System.out.println(logo);
    }

    /**
     * greets the user
     */
    public static void greetUser(final String TAB_SIZE) {

        System.out.println(TAB_SIZE + "Hello! I'm Bob the Bot, aka BtB.");
        System.out.println(TAB_SIZE + "Please enter some tasks in the todo list.");
    }

    /**
     * Say bye to the user
     */
    public static void sayBye() {

        System.out.println(TAB_SIZE + "Bye. Hope to see you again sooooooon (┬┬﹏┬┬)!");
    }

    /**
     * add items to an ArrayList
     *
     * @param todoList the ArrayList that stores the task added
     * @param taskDescription the item to be added to todoList
     */
    public static void add(ArrayList<Task> todoList,
                           String taskDescription) {

        Task task = new Task(taskDescription);
        todoList.add(task);

        System.out.println(TAB_SIZE + "You have added [" + taskDescription +
                "] to your todo list");
    }

    /**
     * lists down the items in the ArrayList and print
     * them to the terminal
     *
     * @param todoList the ArrayLists that contains the stored items
     */
    public static void listItems(ArrayList<Task> todoList) {

        if (todoList.size() == 0) {
            System.out.println(TAB_SIZE + "Your todo list is current void of task," +
                    " please add some tasks.");
        } else {
            System.out.println(TAB_SIZE + "TODO LIST:");
        }

        for (int i = 1; i <= todoList.size(); i++) {

            System.out.println(TAB_SIZE + TAB_SIZE +
                    i + "." + todoList.get(i-1).getStatusIcon() +
                    todoList.get(i-1).getDescription());
        }
    }

    /**
     * marks the task number that user had completed
     *
     * @param todoList ArrayList that contains the tasks
     * @param taskNumber task number that the user wants to mark as completed
     */
    public static void markTasks(ArrayList<Task> todoList, int taskNumber) {

        todoList.get(taskNumber - 1).setDone(true);
        System.out.println(TAB_SIZE + "Nice! I've marked this task as done:");
        System.out.println(TAB_SIZE + " [✔] " +
                todoList.get(taskNumber - 1).getDescription());
    }

    /**
     * unmarks the task number from the todo list
     *
     * @param todoList ArrayList that contains the tasks
     * @param taskNumber task number that the user wants to mark as incomplete
     */
    public static void unMarkTasks(ArrayList<Task> todoList, int taskNumber) {

        todoList.get(taskNumber - 1).setDone(false);
        System.out.println(TAB_SIZE + "Ok! I've marked this task as undone:");
        System.out.println(TAB_SIZE + " [ ] " + todoList.get(taskNumber - 1).getDescription());
    }


    /**
     * checks for potential errors in markTasks and unMarkTasks function
     *
     * @param command the command that the user inputs
     * @param userSplitInputs String array that contains the split user command
     * @param todoList Array List that contains the tasks
     */
    public static void errorCheckingForMarkTasks(String command, String[] userSplitInputs,
                                                 ArrayList<Task> todoList) {

        if (userSplitInputs.length == 1) {

            System.out.println(TAB_SIZE+ "Please try again and include a task number.");
            System.out.println(TAB_SIZE + "i.e., done 1");

            return;
        }

        if (todoList.size() == 0) {
            System.out.println(TAB_SIZE + "The todo list is currently empty. \n" +
                    TAB_SIZE + "Please add some tasks before marking as done.");

            return;
        }

        if (command.equals("done")) {
            markTasks(todoList, Integer.parseInt(userSplitInputs[1]));
        } else {
            unMarkTasks(todoList, Integer.parseInt(userSplitInputs[1]));
        }

    }
}
