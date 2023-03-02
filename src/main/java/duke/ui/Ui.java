package duke.ui;

import duke.task.TaskList;
import duke.task.Tasks;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Ui {
    private String userInput;
    private static final String DIVIDER = "\t____________________________________________________________";

    public Ui() {
        userInput = null;
    }

    public String getUserInput() {
        return userInput;
    }

    public static void sayHi() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.print(DIVIDER + '\n'
                + " \t Hello! I'm Duke\n\t What can I do for you?\n" + DIVIDER + "\n\n");
    }

    public static void sayBye() {
        System.out.println(DIVIDER + "\n\t Bye. Hope to see you again soon!\n" + DIVIDER);
    }

    public static void displayMark(Tasks task) {
        System.out.println(DIVIDER + "\n\t Nice! I've marked this task as done:\n\t  " + task);
        System.out.println(DIVIDER + '\n');
    }

    public static void displayUnmark(Tasks task) {
        System.out.println(DIVIDER + "\n\t Nice! I've marked this task as not done yet:\n\t  " + task);
        System.out.println(DIVIDER + '\n');
    }

    public static void displayAddTask(Tasks task) {
        System.out.println(DIVIDER + "\n\t Got it. I've added this task:\n\t  "
                + task);
        System.out.println("\t Now you have " + TaskList.getNumberOfTasks() + " item(s) in your list.\n"
                + DIVIDER + '\n');
    }

    public static void displayList() {
        int totalNumberOfTasks = TaskList.getNumberOfTasks();
        System.out.println(DIVIDER + "\n\t Here are the tasks in your list:");
        for (int num = 1; num <= totalNumberOfTasks; ++num) {
            Tasks thisTask = TaskList.getTaskList().get(num - 1);
            System.out.println("\t  " + num + ". " + thisTask);
        }
        System.out.println(DIVIDER + '\n');

    }

    public static void displayFind(String item) {
        int totalNumberOfTasks = TaskList.getNumberOfTasks();
        int num2 = 0;
        System.out.println(DIVIDER + "\n\t Here are the matching tasks in your list:");
        for (int num = 1; num <= totalNumberOfTasks; ++num) {
            Tasks thisTask = TaskList.getTaskList().get(num - 1);
            if (Pattern.compile(Pattern.quote(item), Pattern.CASE_INSENSITIVE).matcher(String.valueOf(thisTask)).find()) {
                ++num2;
                System.out.println("\t  " + num2 + ". " + thisTask);
            }
        }
        System.out.println(DIVIDER);
    }

    public static void displayDelete(Tasks task) {
        System.out.println(DIVIDER + "\n\t Got it. I've deleted this task:\n\t  "
                + task);
        System.out.println("\t Now you have " + (TaskList.getNumberOfTasks() - 1) + " item(s) in your list.\n"
                + DIVIDER + '\n');
    }

    public static void displayHelp() {
        System.out.println(DIVIDER + "\n\t Please read the User Guide in docs/README.md for more help.\n" +
                DIVIDER + '\n');
    }

    public void readUserInput() {
        System.out.println("Please enter your data below: (send '/bye' to exit) ");
        Scanner in = new Scanner(System.in);
        this.userInput = in.nextLine();
    }

    public static void displayErrorFileNotFoundException() {
        System.out.println("Duke.txt file does not exist in /data. Creating one for you...");
    }

    public static void displayErrorFolderNotFoundException() {
        System.out.println("data folder does not exist. Creating one for you...");
    }

    public static void displayErrorNoKeyException() {
        System.out.println("Missing Keyword");
    }

    public static void displayErrorIOException() {
        System.out.println("Trouble accessing files");
    }

}
