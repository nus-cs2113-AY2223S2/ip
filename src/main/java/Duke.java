import java.util.ArrayList;
import java.nio.file.Paths;

import java.io.File;
import java.io.FileNotFoundException;

public class Duke {
    /***
     * Ensures that a fixed line formatting is made.
     */
    public static final String LINE_FORMATTING =
            "____________________________________________________________\n";
    /***
     * The fixed number of spaces when processing commands involving "by" in
     * the input.
     */
    public static final int REMOVE_BY_NUM = 4;
    /***
     * The fixed number of spaces when processing commands involving "todo" in
     * the input.
     */
    public static final int REMOVE_TODO_NUM = 5;
    /***
     * The fixed number of spaces when processing commands involving "event" in
     * the input.
     */
    public static final int REMOVE_EVENT_NUM = 6;
    /***
     * The fixed number of spaces when processing commands involving "from" in
     * the input.
     */
    public static final int REMOVE_FROM_NUM = 6;
    /***
     * The fixed number of spaces when processing commands involving "to" in
     * the input.
     */
    public static final int REMOVE_TO_NUM = 4;
    /***
     * The fixed number of spaces when processing commands involving "unmark" in
     * the input.
     */
    public static final int REMOVE_UNMARK_NUM = 7;
    /***
     * The fixed number of spaces when processing commands involving "mark" in
     * the input.
     */
    public static final int REMOVE_MARK_NUM = 5;
    /***
     * The fixed number of spaces when processing commands involving "delete" in
     * the input.
     */
    public static final int REMOVE_DELETE_NUM = 7;

    /***
     * Keeps track of the current position of task in the list.
     */
    public static int taskNum = 0;
    /***
     * Resizeable array that stores the user inputs.
     */
    public static ArrayList<Task> storedValues = new ArrayList<>();
    /***
     * Home directory for creating directory and txt file.
     */
    public static final String HOME = System.getProperty("user.home");
    /***
     * Fixed directory where txt file will be saved.
     */
    public static final File FILEPATH = Paths.get(HOME, "IdeaProjects", "ip", "src",
            "main", "data", "duke-inputs.txt").toFile();

    /***
     * Main function greets the user and runs processInputs().
     */
    public static void main(String[] args) {
        try {
            Storage.fileAvailability();
        } catch (FileNotFoundException e) {
            System.out.println("File not found!\n");
        }
    }

    /***
     * Outputs the goodbye formatting and message to the user when bye
     * command is called.
     */
    public static void showGoodbye() {
        String bye = "Bye. Hope to see you again soon!\n";
        TaskList.formattingLine();
        System.out.println(bye);
        TaskList.formattingLine();
    }

    /**
     * Shows the formatted greetings to the user when called.
     */
    public static void showGreetings() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String hello = " Hello! I'm Duke\n" +
                " What can I do for you?\n";

        System.out.println("Hello from\n" + logo);
        TaskList.formattingLine();
        System.out.println(hello);
        TaskList.formattingLine();
    }

}
