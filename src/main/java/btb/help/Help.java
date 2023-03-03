package btb.help;

import btb.logic.FileManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Help {
    private static boolean isDisabled = false;

    /**
     * sets whether to disable help messages when program first opens
     *
     * @param disable true or false value that determines the status of the help message
     * @throws IOException
     */
    public static void setDisabled(boolean disable) throws IOException {
        isDisabled = disable;

        if (isDisabled) {
            try (FileWriter fw = new FileWriter(FileManager.getHelpPath())) {
                fw.write("true");
            }
        } else {
            try (FileWriter fw = new FileWriter(FileManager.getHelpPath())) {
                fw.write("false");
            }
        }

    }

    /**
     * prints the help message when specified
     *
     * @param command
     * @return
     * @throws FileNotFoundException
     */
    public static boolean printHelpMessage(boolean command) throws FileNotFoundException {
        isDisabled = getHelpStatus();
        if (!isDisabled || command) {
            System.out.println("\t Here are the commands that are available in this application:");
            System.out.println("\t\t1. list: lists all the tasks in the todo list");
            System.out.println("\t\t2. todo <task>: to add a todo task in the task list");
            System.out.println("\t\t3. deadline <task> /by <end date>: " +
                    "to add a deadline task that is to be completed by <end date>");
            System.out.println("\t\t4. event <task> /from <start time> /to <end date>: " +
                    "add an event task that is from <start time> to <start date>");
            System.out.println("\t\t5. mark <task number>: to indicate that a task indicated by <task number> is completed");
            System.out.println("\t\t6. unmark <task number>: to indicate a previously completed task indicated by <task number> as incomplete");
            System.out.println("\t\t7. delete <task number>: to delete task indicated by <task number> in the todo list");
            System.out.println("\t\t8. bye: terminate the program");
            System.out.println("\t\t9. help: prints the help messages");
            System.out.println("\t\t10. find <keyword>: finds tasks from the list that contains the keyword.");
            System.out.println("\t\t11. disableHelp: stop showing the help messages everytime the program boots up");
            System.out.println("\t\t12. enableHelp: shows the help messages everytime the program boots up");
        }
        return isDisabled;
    }

    /**
     * gets the status of whether to print help message
     * everytime when the program first load
     *
     * @return true if prints help message everytime when program first load,
     * else false.
     * @throws FileNotFoundException
     */
    private static boolean getHelpStatus() throws FileNotFoundException {
        File help = new File(FileManager.getHelpPath());
        Scanner sc = new Scanner(help);
        if (sc.hasNext()) {
            return sc.nextLine().equals("true");
        }
        return false;
    }
}
