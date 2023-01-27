import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {

        final String DOTTED_LINE = "    --------------------------------------------------";
        final String TAB_SIZE = "     ";
        ArrayList<ArrayList<String>> todoList = new ArrayList<ArrayList<String>>();

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

                sayBye(TAB_SIZE);
            } else if (userSplitInputs[0].equals("list")) {

                listItems(todoList, TAB_SIZE);
            } else if (userSplitInputs[0].equals("done")) {

                markTasks(todoList, Integer.parseInt(userSplitInputs[1]),
                        TAB_SIZE);
            } else if (userSplitInputs[0].equals("undone")) {

                unMarkTasks(todoList, Integer.parseInt(userSplitInputs[1]),
                        TAB_SIZE);
            } else {

                add(todoList, userCommand, TAB_SIZE);
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

    /** greets the user */
    public static void greetUser(final String TAB_SIZE) {

        System.out.println(TAB_SIZE + "Hello! I'm Bob the Bot, aka BtB.");
        System.out.println(TAB_SIZE + "Please enter some tasks in the todo list.");
    }

    /**
     * Say bye to the user
     *
     * @param TAB_SIZE white spaces in front of the printed text for aesthetic purposes
     */
    public static void sayBye(final String TAB_SIZE) {

        System.out.println(TAB_SIZE + "Bye. Hope to see you again sooooooon (┬┬﹏┬┬)!");
    }

    /**
     * add items to an ArrayList
     *
     * @param todoList the ArrayList that stores the item added
     * @param toAdd the item to be added to todoList
     * @param TAB_SIZE white spaces in front of the printed text for aesthetic purposes
     */
    public static void add(ArrayList<ArrayList<String>> todoList,
                           String toAdd, final String TAB_SIZE) {

        ArrayList<String> tempToAdd = new ArrayList<>();
        tempToAdd.add(toAdd);
        tempToAdd.add("undone");
        todoList.add(tempToAdd);

        System.out.println(TAB_SIZE + "You have added [" + toAdd + "] to your todo list");
    }

    /**
     * lists down the items in the ArrayList and print
     * them to the terminal
     *
     * @param todoList the ArrayLists that contains the stored items
     * @param TAB_SIZE white spaces in front of the printed text for aesthetic purposes
     */
    public static void listItems(ArrayList<ArrayList<String>> todoList,
                                 final String TAB_SIZE) {

        if (todoList.size() == 0) {
            System.out.println(TAB_SIZE + "Your todo list is current void of task,"
                    + " please add some tasks.");
        } else {
            System.out.println(TAB_SIZE + "TODO LIST:");
        }

        for (int i = 1; i <= todoList.size(); i++) {
            boolean isDone = todoList.get(i - 1).get(1).equals("done");
            if (isDone) {
                System.out.println(TAB_SIZE + TAB_SIZE + i
                        + ".[✔] " + todoList.get(i - 1).get(0));
            } else {
                System.out.println(TAB_SIZE + TAB_SIZE + i
                        + ".[ ] " + todoList.get(i - 1).get(0));
            }

        }
    }

    public static void markTasks(ArrayList<ArrayList<String>> todoList, int taskNumber,
                                 final String TAB_SIZE) {

        todoList.get(taskNumber - 1).set(1, "done");
        System.out.println(TAB_SIZE + "Nice! I've marked this task as done:");
        System.out.println(TAB_SIZE + " [✔] " + todoList.get(taskNumber - 1).get(0));
    }

    public static void unMarkTasks(ArrayList<ArrayList<String>> todoList, int taskNumber,
                                   final String TAB_SIZE) {

        todoList.get(taskNumber - 1).set(1, "undone");
        System.out.println(TAB_SIZE + "Ok! I've marked this task as undone:");
        System.out.println(TAB_SIZE + " [ ] " + todoList.get(taskNumber - 1).get(0));
    }
}
