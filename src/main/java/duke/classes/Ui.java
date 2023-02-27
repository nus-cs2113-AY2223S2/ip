package duke.classes;

public class Ui {

    protected String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public void showWelcome() {
        System.out.println("Hello from\n" + logo + "\n" + "Hello! I'm Duke\n" + "What can I do for you\n");

    }

    public void showFileCreated() {
        System.out.println("the file has been created");
    }

    public void showFileExists() {
        System.out.println("the file already exists");
    }

    public void showFileContent() {
        System.out.println("This is the current content of the duke_list file, if any:");
    }

    public void showFileNotFoundError() {
        System.out.println("File not found");
    }

    public void showMarkTaskWarning() {
        System.out.println("You cannot mark a task that hasn't been made");
    }

    public void showUnmarkTaskWarning() {
        System.out.println("You cannot unmark a task that hasn't been made");
    }

    public void showDeleteTaskWarning() {
        System.out.println("You cannot delete a task that hasn't been made");
    }

    public void showWelcomeEnd() {
        System.out.println("----------------------End of file----------------------\n" + "Please input your commands here: ");
    }

    public void showFarewell() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
