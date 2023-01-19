import java.util.ArrayList;
public class Response {
    private void printLine() {
        System.out.println("____________________________________________________________");
    }

    private void printSignature() {
        System.out.print("[Luke]: ");
    }

    public void sayHi() {
        LukeLogo logo = new LukeLogo();
        printLine();
        printSignature();
        System.out.println("Hello I'm\n" + logo.getRandLogo());
        System.out.println("What can I do for you?");
        printLine();
    }

    public void sayBye() {
        printLine();
        printSignature();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }

    public void printString(String toPrint) {
        printLine();
        printSignature();
        System.out.println(toPrint);
        printLine();
    }

    public void printAddTask(String task) {
        printLine();
        printSignature();
        System.out.println("Added: " + task);
        printLine();
    }

    public void printMarkTask(String taskName) {
        printLine();
        printSignature();
        System.out.println(taskName + " has been marked as complete.");
        printLine();
    }

    public void printUnmarkTask(String taskName) {
        printLine();
        printSignature();
        System.out.println(taskName + " has been marked as incomplete.");
        printLine();
    }

    public void printTaskList(ArrayList<Task> task) {
        printLine();
        printSignature();
        int index = 1;
        System.out.println("Printing Tasks...");
        for (Task i : task) {
            System.out.print(index + ". ");
            i.printTaskName();
            index += 1;
        }
        printLine();
    }

    public void emptyList() {
        printLine();
        printSignature();
        System.out.println("The list is empty!");
        printLine();
    }

    public void invalidCommand() {
        printLine();
        printSignature();
        System.out.println("You have entered an invalid command!");
        printLine();
    }

    public void outOfBounds() {
        printLine();
        printSignature();
        System.out.println("You have entered an index that does not exist!");
        printLine();
    }


}
