public class Duke {

    public static void drawLine() {
        for (int i = 0; i < 20; i++) {
            System.out.print("_");
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        drawLine();
        System.out.println();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        drawLine();
        System.out.println();
        System.out.println("Bye. Hope to see you again soon!");
        drawLine();

    }

}
