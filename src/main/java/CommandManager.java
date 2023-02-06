import java.util.ArrayList;

public class CommandManager {
    //print Duke
    //get user input
    //
    private String userInput;
    private String userOutput;
    private static ArrayList<String> printList = new ArrayList<>();

    public static final String DIVIDER = "\t____________________________________________________________";

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

    public CommandManager() {
        userInput = null;
        userOutput = null;
    }

    public String getUserInput() {
        return userInput;
    }

    public void setUserInput(String input) {
        this.userInput = input;
    }

    public String getUserOutput() {
        return userOutput;
    }

    public void setUserOutput(String userOutput) {
        this.userOutput = userOutput;
    }
    public static void addToPrintList(String toPrint){

        printList.add(toPrint);
    }

    public void printOutput(Tasks task) {
        String taskLine = printList.get(task.getID());
        switch (userOutput) {
        case "mark": {
            if (taskLine.contains("[ ]")) {
                printList.set(task.getID(), printList.get(task.getID()).replace("[ ]", "[X]"));
            };
            System.out.println(DIVIDER + "\n\t Nice! I've marked this task as done:\n\t  " +
                    "[X] " + task.getItem());
            System.out.println(DIVIDER);
        }
        case "unmark": {
            System.out.println(DIVIDER + "\n\t Nice! I've marked this task as not done yet:\n\t  " +
                    "[ ] " +  task.getItem());
            System.out.println(DIVIDER);
        }
        case task: {
            int timer = Tasks.getNumberOfTasks();
            System.out.println(DIVIDER + "\n\t Here are the tasks in your list:");
            for(int i = 1; i <= timer; ++i) {

            }
        }

        }
    }
}

