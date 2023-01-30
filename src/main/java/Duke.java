import java.util.Scanner;

public class Duke {
    public static String HORIZONTAL_LINE = "____________________________________________________________";
    public static final String COMMAND_BYE = "bye";
    public static final String COMMAND_LIST = "list";
    public static final String COMMAND_MARK = "mark";
    public static final String COMMAND_UNMARK = "unmark";

    private static TaskList TASKLIST = new TaskList();

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        greetUser();

        while(true){
            executeCommand(getUserCommand());
        }
    }

    public static void greetUser(){
        System.out.println(HORIZONTAL_LINE);
        System.out.println("[DUKE] Hello! I'm Duke");
        showAvailableInput();
        System.out.println("What can I do for you?");
        System.out.println(HORIZONTAL_LINE);
    }

    public static void showAvailableInput(){
        System.out.println("These are what You can do:");
        System.out.println("* |Add todo| ex) todo individual project" );
        System.out.println("* |Add deadline| ex) deadline do coursemology /by Tuesday");
        System.out.println("* |Add event| ex) event tutorial /from Wednesday 12pm /to 1pm");
        System.out.println("* |List tasks| ex) list");
        System.out.println("* |Mark a task as Done| ex) mark 2");
        System.out.println("* |Unmark a task as Undone| ex) unmark 2");
        System.out.println("* |Exit| ex) bye");
    }

    public static void exitProgram(){
        System.out.println(HORIZONTAL_LINE);
        System.out.println("[DUKE] Bye. Hope to see you again soon!");
        System.out.print(HORIZONTAL_LINE);
        System.exit(0);
    }

    public static String getUserCommand(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("[User] ");
        return scanner.nextLine();
    }

    public static void executeCommand(String command){
        String[] commandsSplited = command.split(" ");
        switch(commandsSplited[0]){
            case COMMAND_BYE:
                exitProgram(); break;
            case COMMAND_LIST:
                list(); break;
            case COMMAND_MARK:
                mark(commandsSplited[1]); break;
            case COMMAND_UNMARK:
                unmark(commandsSplited[1]); break;
            default:
                add(command);
        }
       //echo(command);
    }

    public static void echo(String command){
        System.out.println(HORIZONTAL_LINE);
        System.out.println("[DUKE] "+ command);
        System.out.println(HORIZONTAL_LINE);
    }

    public static void add(String userInput){
        if (TASKLIST.addTask(userInput)) {
            printAddComment();
        } else {
            System.out.println("[DUKE] Uh oh! Wrong Input!");
            showAvailableInput();
            System.out.println(HORIZONTAL_LINE);
        }
    }

    public static void list(){
        System.out.println(HORIZONTAL_LINE);
        System.out.println("[DUKE] Here are the tasks in your list: ");
        System.out.print(TASKLIST);
        System.out.println(HORIZONTAL_LINE);
    }

    public static void mark(String taskNum){
        int taskNumInt = Integer.parseInt(taskNum);
        TASKLIST.getTaskArray()[taskNumInt-1].mark();
        //Duke's Comment
        System.out.println(HORIZONTAL_LINE);
        System.out.println("[DUKE] Good job! I marked this task as done: ");
        System.out.println(TASKLIST.getTaskArray()[taskNumInt-1]);
        System.out.println(HORIZONTAL_LINE);
    }

    public static void unmark(String taskNum) {
        int taskNumInt = Integer.parseInt(taskNum);
        TASKLIST.getTaskArray()[taskNumInt-1].unmark();
        //Duke's Comment
        System.out.println(HORIZONTAL_LINE);
        System.out.println("[DUKE] OK, I've marked this task as not done yet: ");
        System.out.println(TASKLIST.getTaskArray()[taskNumInt-1]);
        System.out.println(HORIZONTAL_LINE);
    }

    public static void printAddComment(){
        System.out.println(HORIZONTAL_LINE);
        System.out.println("[DUKE] Okay:) You've got one more task added: ");
        System.out.println("        "+ TASKLIST.getTaskArray()[TASKLIST.getTotalTaskNum()-1]);
        System.out.println("[DUKE] Now you have <" + TASKLIST.getTotalTaskNum() + "> tasks in the list.");
        System.out.println(HORIZONTAL_LINE);
    }

}
