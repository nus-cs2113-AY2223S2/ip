public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String divider = "____________________________________";

        String welcomeMessage = "Hello! I'm Duke\nWhat can I do for you?";

        String exitMessage = "Bye. Hope to see you again soon!";

        System.out.println(divider);
        System.out.println(logo);
        System.out.println(welcomeMessage + '\n' + divider);
        System.out.println(exitMessage + '\n' + divider);
    }
}
