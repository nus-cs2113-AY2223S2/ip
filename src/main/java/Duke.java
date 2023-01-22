public class Duke {

    private static void printLines(String... params) {
        String divider = "____________________________________________________________";
        for (String param: params) {
            System.out.println(param);
        }
        System.out.println(divider);
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        printLines("Hello from", logo);

        printLines("Hello! I'm Duke", "What can I do for you?");

        printLines("Bye. Hope to see you again soon!");
    }
}
