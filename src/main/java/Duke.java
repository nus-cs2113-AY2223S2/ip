public class Duke {
    public static void drawLine(String symbol) {
        for (int i = 0; i < 40; i++) {
            System.out.print(symbol);
        }
        System.out.println();
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        drawLine("_");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        drawLine("_");
        System.out.println("Bye. Hope to see you again soon!");
        drawLine("_");
    }
}
