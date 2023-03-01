package duke;
/**
 * UI handling class. Prints most UI.
 */
public class UI {
    public UI () {
    }
    public void showGreetings() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }
    public void printLinebreak() {
        System.out.println("--------------------------------------------------------------");
    }
    public void printTaskNum(int taskNum) {
        System.out.println("Now you have " + taskNum + " task(s) in the list.");
    }
    public void printListMessage() {
        System.out.println("Here are the tasks in your list:");
    }
    public void printAddTaskMessage() {
        System.out.println("Got it. I've added this task.");
    }
}
