public class Ui {
    public void printLine() {
        System.out.println("____________________________________________________________");
    }

    public void greetUser() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);
        printLine();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        printLine();
    }

    public void sayBye() {
        printLine();
        System.out.println("Bye. Hope to see you again soon! :)");
        printLine();
    }

    public void printErrorMessage() {
    }
}
