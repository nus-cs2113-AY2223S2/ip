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

    public void printTaskList(ArrayList<String> task) {
        printLine();
        printSignature();
        System.out.println("Printing Tasks...");
        int index = 1;
        for (String i : task) {
            System.out.println(index + ". " + i);
            index += 1;
        }
        printLine();
    }

}
