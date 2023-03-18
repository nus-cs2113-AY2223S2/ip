package duke;

public class Ui {

    public void showLine() {
        System.out.println("    _________________________________________");
    }

    public void showGreetingMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("    Hello from\n" + logo);
        showLine();
        System.out.println("    Hello! I'm Duke");
        System.out.println("    What can I do for you?");
        showLine();
        System.out.println("     ");
    }

    public void showExitMessage() {
        System.out.println("    Bye. Hope to see you again soon!");
        showLine();
        System.out.println("     ");
    }
}

