public class Duke {
    public static void dashSeperator() {
        System.out.println("____________________________________________________________");
    }

    public static void sayGreeting() {
        dashSeperator();
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        dashSeperator();
        System.out.println("Bye. Hope to see you again soon!");
        dashSeperator();
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        //System.out.println("Hello from\n" + logo);
        sayGreeting();
    }
}
