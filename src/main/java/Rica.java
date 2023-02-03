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

    /**
     * Adds a new task to the task list with the given description
     *
     * @param description Description of new task being added
     */
    private static void addTask(String description) {
        if (description.isBlank()) {
            printlnWithIndent(" Did you forget something? Don't think there's any task with no name");
            return;
        }
        Task newTask = new Task(description);
        Rica.getPastTexts().add(newTask);
        printlnWithIndent(Rica.ADD_PHRASE + description);
    }

    /**
     * Mark a given task in the task list as done
     *
     * @param indexOfTask Index of given task in the task list
     * @return Task object representing the desired task being marked as done
     */
    private static Task markDone(int indexOfTask) {
        if (indexOfTask < 0 || indexOfTask >= Rica.getPastTexts().size()) {
            printlnWithIndent(" You alright? I can't mark a task that doesn't exist as done xD");
            return null;
        }
        Task selectedTask = Rica.getPastTexts().get(indexOfTask);
        if (selectedTask.getIsDone()) {
            printlnWithIndent(" Take a break maybe? Alright marked as done my friend:");
            printlnWithIndent("    " + selectedTask);
            return selectedTask;
        }
        Rica.getPastTexts().remove(indexOfTask);
        selectedTask = selectedTask.setDone();
        Rica.getPastTexts().add(indexOfTask, selectedTask);
        printlnWithIndent(" Shall remember that this task as done:");
        printlnWithIndent("    " + selectedTask);
        return selectedTask;
    }

    /**
     * Mark a given task in the task list as not done
     *
     * @param indexOfTask Index of desired task in the task list
     * @return Task object representing the desired task being marked as not done
     */
    private static Task unmarkDone(int indexOfTask) {
        if (indexOfTask < 0 || indexOfTask >= Rica.getPastTexts().size()) {
            printlnWithIndent(" You alright? I don't think that task exists xD");
            return null;
        }
        Task selectedTask = Rica.getPastTexts().get(indexOfTask);
        if (!selectedTask.getIsDone()) {
            printlnWithIndent(" Getting a little ahead of yourself are you xD It's not even done:");
            printlnWithIndent("    " + selectedTask);
            return selectedTask;
        }
        Rica.getPastTexts().remove(indexOfTask);
        selectedTask = selectedTask.setNotDone();
        Rica.getPastTexts().add(indexOfTask, selectedTask);
        printlnWithIndent(" (Why??) Anyway, I've marked this task as not done yet:");
        printlnWithIndent("    " + selectedTask);
        return selectedTask;
    }

    private static boolean hasAnyTasks() {
        return !Rica.getPastTexts().isEmpty();
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

    /**
     * Prints out the list of tasks added so far, or inform the user if no tasks have been added
     * yet
     */
    private static void printTexts() {
        if (!Rica.hasAnyTasks()) {
            printlnWithIndent(" Hope I'm not amnesiac, but I don't remember any tasks?");
        } else {
            ArrayList<Task> tasks = Rica.getPastTexts();
            printlnWithIndent(" I think you have these tasks:");
            for (int i = 1; i <= tasks.size(); i += 1) {
                printlnWithIndent(" " + i + ". " + tasks.get(i - 1));
            }
        }
    }

    /**
     * Parse the command entered into CLI and execute the corresponding actions
     */
    private static void runCommands() {
        Scanner in = new Scanner(System.in);
        String command = "";
        do {
            command = in.nextLine();
            printlnWithIndent(Rica.LINE);
            if (command.contains(Rica.MARK_TRIGGER)) {
                String[] params = command.split(" ");
                if (params[0].equals(Rica.MARK_TRIGGER)) {
                    int indexOfTask = Integer.valueOf(params[1]) - 1;
                    Rica.markDone(indexOfTask);
                } else if (params[0].equals(Rica.UNMARK_TRIGGER)) {
                    int indexOfTask = Integer.valueOf(params[1]) - 1;
                    Rica.unmarkDone(indexOfTask);
                }
            } else {
                switch (command) {
                case Rica.LIST_TRIGGER:
                    printTexts();
                    break;
                case Rica.BYE_TRIGGER:
                    printlnWithIndent(Rica.BYE_PHRASE);
                    break;
                default:
                    Rica.addTask(command);
                    break;
                }
            }
            printlnWithIndent(Rica.LINE);
        } while (!command.equals(Rica.BYE_TRIGGER));
    }
}
