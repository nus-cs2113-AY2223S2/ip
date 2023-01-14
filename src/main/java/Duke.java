public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String horizontalBar = "-----------------------------------------\n";
        System.out.println("Hello from\n" + logo);

        //to greet user
        System.out.println(horizontalBar +
                "Hello! I'm Duke! \n" +
                "What can I do for you? \n" +
                horizontalBar);

        //to say bye
        System.out.println(horizontalBar +
                "Bye! Hope to see you again soon!\n" +
                horizontalBar);

    }
}
