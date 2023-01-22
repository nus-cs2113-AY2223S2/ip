import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        printIntro();
        mainLoop();
        printExit();

    }
    public static  void mainLoop() {
        Scanner in = new Scanner(System.in);
        String currentInput = in.nextLine();
        while (!currentInput.equals("bye")) {
            printMessage(currentInput);
            currentInput = in.nextLine();
        }
    }
    public static void printIntro() {
        printMessage("Hello! I'm Tom");
        printMessage("What can I do for you?");
    }
    public static void printExit() {
        printMessage("Bye. Hope to see you again soon!");
    }
    public static void printMessage(String message) {
        printSeparator();
        printIndent();
        System.out.print(message + "\n");
        printSeparator();
    }

    public static void printIndent() {
        System.out.print("      ");
    }
    public static void printSeparator() {
        System.out.print("   ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
    }
}
