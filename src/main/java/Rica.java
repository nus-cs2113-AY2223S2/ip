import java.util.ArrayList;
import java.util.Scanner;

public class Rica {
    private static final String LINE = "____________________________________________________________";
    private static final String LIST_TRIGGER = "list";
    private static final String BYE_TRIGGER = "bye";
    private static final String ADD_PHRASE = " New task I'll remember: ";
    private static final String BYE_PHRASE = " Leaving so soon? Come back anytime, I'll be happy to help!";
    private static ArrayList<Task> pastTasks = new ArrayList<>();

    private static ArrayList<Task> getPastTexts() {
        return Rica.pastTasks;
    }

    private static void addTask(String description) {
        Task newTask = new Task(description);
        Rica.pastTasks.add(newTask);
    }

    public static void main(String[] args) {
        // Define horizontal line in a variable since it's printed multiple times

        printlnWithIndent(Rica.LINE);
        printlnWithIndent(" Hello! I'm R.I.C.A.");
        printlnWithIndent((" That's Really-Intelligent-Chat-Assistant for you!"));
        printlnWithIndent(" How may I be of assistance?");
        printlnWithIndent(Rica.LINE);
        Rica.runCommands();
    }

    private static void printlnWithIndent(String line) {
        System.out.print("    ");
        System.out.println(line);
    }

    private static void printTexts() {
        ArrayList<Task> tasks = Rica.getPastTexts();
        printlnWithIndent(" I think you have these tasks:");
        for (int i = 1; i <= tasks.size(); i += 1) {
            printlnWithIndent(" " + i + ". " + tasks.get(i - 1));
        }
    }

    private static void runCommands() {
        Scanner in = new Scanner(System.in);
        String command = "";
        do {
            command = in.nextLine();
            printlnWithIndent(Rica.LINE);
            switch (command) {
                case Rica.BYE_TRIGGER:
                    printlnWithIndent(Rica.BYE_PHRASE);
                    break;
                case Rica.LIST_TRIGGER:
                    printTexts();
                    break;
                default:
                    printlnWithIndent(Rica.ADD_PHRASE + command);
                    Rica.addTask(command);
                    break;
            }

            printlnWithIndent(Rica.LINE);
        } while (!command.equals(Rica.BYE_TRIGGER));
    }
}
