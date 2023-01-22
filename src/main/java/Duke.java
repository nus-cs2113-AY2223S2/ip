import java.util.Scanner;
public class Duke {
    private static Task[] tasks = new Task[100];
    private static int currentStoredTaskIndex = 0;
    private static final String indent = "      ";
    public static void main(String[] args) {
        printIntro();
        mainLoop();
        printExit();

    }

    public static void addToList(Task task) {
        tasks[currentStoredTaskIndex] = task;
        currentStoredTaskIndex ++;
        printMessage("added: " + task.description);
    }

    public static void list() {

        printMessage(getFormattedList());
    }

    public static void mark(String[] words) {
        int taskIndex = Integer.parseInt(words[1]) - 1;
        tasks[taskIndex].markAsDone();
        String[] message = {
                "Cool! I've marked this task as done:",
                tasks[taskIndex].getDisplayString()
        };
        printMessage(message);
    }

    public static void unmark(String[] words) {
        int taskIndex = Integer.parseInt(words[1]) - 1;
        tasks[taskIndex].markAsNotDone();
        String[] message = {
                "Ok, I've marked this task as not done yet:",
                tasks[taskIndex].getDisplayString()
        };
        printMessage(message);
    }
    public static  void mainLoop() {
        Scanner in = new Scanner(System.in);
        String currentInput = in.nextLine();
        while (!currentInput.equals("bye")) {
            String[] words = currentInput.split(" ");
            switch (words[0]) {
                case "list":
                    list();
                    break;
                case "mark":
                    mark(words);
                    break;
                case "unmark" :
                    unmark(words);
                    break;
                default:
                    Task newTask = new Task(currentInput);
                    addToList(newTask);
                    break;
            }
            currentInput = in.nextLine();
        }
    }

    public static String getFormattedTask(Task task, int number) {
        return number + ". " + task.getDisplayString();
    }
    public static String[] getFormattedList() {
        String[] formattedList = new String[currentStoredTaskIndex + 1];
        formattedList[0] = "Here are the tasks in your list:";
        for (int i = 0; i < currentStoredTaskIndex ; i ++) {
            formattedList[i + 1] = getFormattedTask(tasks[i], i + 1);
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
