/*
 * UI class contains methods that interact with user through text
 */
public class UI {
    /*
     * Prints greeting statement and logo to terminal
     */
    public void greet(TaskList taskList) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        taskList.list();
        System.out.println("What can I do for you?");
    }

    public static void horizontalLine() {
        System.out.println("------------------------------------");
    }
}
