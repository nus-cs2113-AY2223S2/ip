import java.util.ArrayList;
import java.util.Scanner;

public class Rica {
    private static final String LINE = "____________________________________________________________";
    private static final String LIST_TRIGGER = "list";
    private static final String MARK_TRIGGER = "mark";
    private static final String UNMARK_TRIGGER = "unmark";
    private static final String BYE_TRIGGER = "bye";
    private static final String ADD_PHRASE = " New task I'll remember: ";
    private static final String BYE_PHRASE = " Leaving so soon? Come back anytime, I'll be happy to help!";
    private static ArrayList<Task> pastTasks = new ArrayList<>();

    private static ArrayList<Task> getPastTexts() {
        return Rica.pastTasks;
    }

    private static void addTask(String description) {
        Task newTask = new Task(description);
        Rica.getPastTexts().add(newTask);
    }

    private static void addTask(Task newTask) {
        Rica.getPastTexts().add(newTask);
    }

    private static Task markDone(int indexOfTask) {
        Task selectedTask = Rica.getPastTexts().remove(indexOfTask);
        selectedTask = selectedTask.setDone();
        Rica.addTask(selectedTask);
        return selectedTask;
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
            if (command.contains(Rica.MARK_TRIGGER)) {
                String[] params = command.split(" ");
                int indexOfTask = Integer.valueOf(params[1]) - 1;
                Task markedTask = Rica.markDone(indexOfTask);
                printlnWithIndent(" Shall remember that this task as done: " +
                        markedTask.getDescription());
            } else if (command.contains(Rica.UNMARK_TRIGGER)) {

            } else {
                switch (command) {
                    case Rica.LIST_TRIGGER:
                        printTexts();
                        break;
                    case Rica.BYE_TRIGGER:
                        printlnWithIndent(Rica.BYE_PHRASE);
                        break;
                    default:
                        printlnWithIndent(Rica.ADD_PHRASE + command);
                        Rica.addTask(command);
                        break;
                }
            }

            printlnWithIndent(Rica.LINE);
        } while (!command.equals(Rica.BYE_TRIGGER));
    }
}
