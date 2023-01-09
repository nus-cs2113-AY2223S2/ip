public class Duke {
    /**
     * Main program that runs the Duke program.
     * Greets users and exits.
     * @param args
     */
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String linebreak = "________________________________________\n";
        System.out.println(logo + linebreak);

        // Greet user
        String greeting = "Hello! I'm Duke\nWhat can I do for you?\n";
        System.out.println(greeting + linebreak);
        
        // Exit the program
        String exit = "Bye. Hope to see you again soon!\n";
        System.out.println(exit + linebreak);
    }
}
