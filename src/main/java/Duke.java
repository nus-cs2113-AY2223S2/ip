import java.util.Scanner;
public class Duke {
    private static String[] storedData = new String[100];
    private static int currentStoredDataIndex = 0;
    private static final String indent = "      ";
    public static void main(String[] args) {
        printIntro();
        mainLoop();
        printExit();

    }
    public static void addToList(String input) {
        storedData[currentStoredDataIndex] = input;
        currentStoredDataIndex ++;
        printMessage("added: " + input);
    }
    public static  void mainLoop() {
        Scanner in = new Scanner(System.in);
        String currentInput = in.nextLine();
        while (!currentInput.equals("bye")) {
            switch (currentInput) {
                case "list":
                    printMessage(getFormattedList());
                    break;
                default:
                    addToList(currentInput);
                    break;
            }
            currentInput = in.nextLine();
        }
    }

    public static String getFormattedListItem(String item, int number) {
        return Integer.toString(number) + ". " + item;
    }
    public static String[] getFormattedList() {
        String[] formattedList = new String[currentStoredDataIndex];
        for (int i = 0; i < currentStoredDataIndex; i ++) {
            formattedList[i] = getFormattedListItem(storedData[i], i + 1 );
        }
        return formattedList;
    }
    public static void printIntro() {
        String[] intro = {"Hello! I'm Tom", "What can I do for you?"};
        printMessage(intro);
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

    public static void printMessage(String[] message) {
        printSeparator();
        for (String line : message) {
            printIndent();
            System.out.print(line + "\n");
        }
        printSeparator();
    }

    public static void printIndent() {
        System.out.print(indent);
    }
    public static void printSeparator() {
        System.out.print("   ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
    }
}
