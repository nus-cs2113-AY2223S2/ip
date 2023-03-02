import java.util.Scanner;

public class UI {
    public static String HORIZONTAL_LINE = "____________________________________________________________";

    public UI(){
        greetUser();
    }

    public static void greetUser(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(HORIZONTAL_LINE);
        System.out.println("[DUKE] Hello! I'm Duke");
        printAvailableInput();
        System.out.println("What can I do for you?");
        System.out.println(HORIZONTAL_LINE);
    }

    public static  void printBye(){
        System.out.println(HORIZONTAL_LINE);
        System.out.println("[DUKE] Bye. Hope to see you again soon!");
        System.out.print(HORIZONTAL_LINE);
    }

    public static void printAvailableInput(){
        System.out.println("These are what You can do:");
        System.out.println("* |Add todo| ex) todo individual project" );
        System.out.println("* |Add deadline| ex) deadline do coursemology by 2023-01-01");
        System.out.println("* |Add event| ex) event tutorial from 2023-02-09 to 2023-03-07");
        System.out.println("* |List tasks| ex) list");
        System.out.println("* |Mark a task as Done| ex) mark 2");
        System.out.println("* |Unmark a task as Undone| ex) unmark 2");
        System.out.println("* |Delete| ex) delete 3");
        System.out.println("* |Find| ex) find project");
        System.out.println("* |Exit| ex) bye");
    }

    public static void printAddComment(Task targetTask, int totalTaskNum){
        System.out.println(HORIZONTAL_LINE);
        System.out.println("[DUKE] Okay:) You've got one more task added: ");
        System.out.println("       "+ targetTask);
        System.out.println("[DUKE] Now you have <" + totalTaskNum + "> tasks in the list.");
        System.out.println(HORIZONTAL_LINE);
    }

    public static void printDeleteComment(Task targetTask, int totalTaskNum){
        System.out.println(HORIZONTAL_LINE);
        System.out.println("[DUKE] OK, I removed this task from the list: ");
        System.out.println("       "+ targetTask);
        System.out.println("[DUKE] Now you have <" + totalTaskNum + "> tasks in the list.");
        System.out.println(HORIZONTAL_LINE);
    }

    public static void printMarkComment(Task targetTask){
        System.out.println(HORIZONTAL_LINE);
        System.out.println("[DUKE] Good job! I marked this task as done: ");
        System.out.println(targetTask);
        System.out.println(HORIZONTAL_LINE);
    }

    public static void printUnmarkComment(Task targetTask){
        System.out.println(HORIZONTAL_LINE);
        System.out.println("[DUKE] OK, I've marked this task as not done yet: ");
        System.out.println(targetTask);
        System.out.println(HORIZONTAL_LINE);
    }

    public static void printTaskList(TaskList taskList){
        System.out.println(HORIZONTAL_LINE);
        System.out.println("[DUKE] Here are the tasks in your list: ");
        System.out.print(taskList.showTaskList());
        System.out.println(HORIZONTAL_LINE);
    }

    public static void printTargetTaskList(TaskList taskList){
        System.out.println(HORIZONTAL_LINE);
        System.out.println("[DUKE] Here are the matching tasks in your list: ");
        System.out.print(taskList);
        System.out.println(HORIZONTAL_LINE);
    }

    public static void printInputErrorComment(){
        System.out.println("[DUKE] Uh oh! Wrong Input :( I Cannot Understand you. Please check input format.");
        printAvailableInput();
        System.out.println(HORIZONTAL_LINE);
    }

    public static void printNotFoundErrorComment(){
        System.out.println("[Duke] ☹ OOPS!!! I cannot find the task");
        printAvailableInput();
        System.out.println(HORIZONTAL_LINE);
    }

    public static void printEmptyDescriptionComment(String taskType){
        System.out.println("[Duke] ☹ OOPS!!! The description of a(n) " + taskType + " cannot be empty.");
    }

    public static void printFileCreatedComment(String fileName){
        System.out.println("[Duke] I created the file [" + fileName + "]");
    }

    public static void printFileNotCreatedComment(){
        System.out.println("[Duke] I cannot create File:( Please try again.");
    }

    public static void printSaveErrorComment(){
        System.out.println("[Duke] Something went wrong while saving...! Please try again.");
    }

    public static void printFileLoadingErrorComment(){
        System.out.println("[Duke] Uh oh! I cannot load file :< Please check again.");
    }

    public static String getUserCommand(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("[User] ");
        return scanner.nextLine();
    }

    public static void printDateFormatErrorComment(){
        System.out.println("[Duke] Uh oh! Check if your date format is correct [YYYY-MM-DD] :<");
    }
}
