import duke.command.TaskManager;
import duke.ui.Ui;

public class Duke {

    private TaskManager tasks;
    private Ui ui;
    public Duke (){
        ui = new Ui();
        tasks = new TaskManager();
    }
    public void run(){
        ui.showWelcome();
        boolean isExit = false;
        while(!isExit){
            String fullCommand = ui.getUserCommand();
        }
    }

    public static void main(String[]args){
        new Duke().run();
    }

    /*
    public final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    public static void printSeparator(){
        System.out.println("_______________________________________________________________");
    }

    public static void printGreeting() {
        printSeparator();
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        printSeparator();
    }

    public static void printGoodbye() {
        printSeparator();
        System.out.println("Bye. Hope to see you again soon!");
        printSeparator();
    }


    public static void main(String[] args) {

        //System.out.println("Hello from\n" + logo);
        printGreeting();
        boolean byeFlag = false;
        Scanner line = new Scanner(System.in);
        TaskManager dukeManager = new TaskManager();
        //TaskItems.Task[] userList = new TaskItems.Task[100]; //no more than 100 tasks
        while (!byeFlag) {
            String userInput = line.nextLine();
            if (userInput.equals("bye")) {
                byeFlag = true;
                printGoodbye();
            } else {
                try {
                    dukeManager.handleCommand(userInput);
                    printSeparator();
                } catch (IllegalCommandException e) {
                    System.out.println("ಠ_ಠ idk what you're saying bro ಠ_ಠ");
                }
            }
        }
    }

     */
}
