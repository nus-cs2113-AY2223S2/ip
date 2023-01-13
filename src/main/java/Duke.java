public class Duke {

    /**
     * Prints out the greeting message to the user
     */
    public static void greetUser(){
        String greetMessage = "Hello! I'm Duke\n"
                + "What can I do for you?";
        String exitMessage = "Bye. Hope to see you again soon!";
        System.out.println(greetMessage);
    }

    /**
     * Prints out the exit message to the user
     */
    public static void exitProgram(){
        String exitMessage = "Bye. Hope to see you again soon!";
        System.out.println(exitMessage);
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        greetUser();
        exitProgram();
    }
}
