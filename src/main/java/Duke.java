import java.util.Scanner;

/**
 * Incorrect Tags have been resolved, all commits have been tagged appropriately
 */
public class Duke {

    public static void printLine() {
        System.out.println("\t---------------------------------------------------------------------------------");
    }
    public static void listTasks(int currentIndex, Task[] taskList) {
        for(int i = 0; i<currentIndex;i+=1) {
            System.out.println('\t' + Integer.toString(i+1) + "." + taskList[i].getStatusAndDescription());
            // can be further optimized.
        }
    }

    public static boolean isTheSame(String userInput, String toCompare) {
        return userInput.split(" ")[0].equals(toCompare);
    }

    /**
     * Issue regarding index marking being off by one has been resolved.
     * @param userInput
     * @param currentIndex
     * @return boolean
     */

    public static boolean isInRange(String userInput, int currentIndex) {
        return (Integer.parseInt(userInput.split(" ")[1])>0 && Integer.parseInt(userInput.split(" ")[1])<currentIndex+1);
    }

    public static void printMarkedTask(String userInput, Task[] taskList) {
        System.out.println("\tNice! I've marked this task as done:");
        taskList[Integer.parseInt(userInput.split(" ")[1])-1].markTask();
        System.out.println("\t\t" + taskList[Integer.parseInt(userInput.split(" ")[1])-1].getStatusAndDescription());
    }

    public static void printUnmarkedTask(String userInput, Task[] taskList) {
        System.out.println("\tNice! I've marked this task as not done:");
        taskList[Integer.parseInt(userInput.split(" ")[1])-1].unMarkTask();
        System.out.println("\t\t" + taskList[Integer.parseInt(userInput.split(" ")[1])-1].getStatusAndDescription());
    }
    public static String[] getDeadline(String userInput) {
        String intermediateStage = userInput.replace("deadline ", "");
        String[] deadlineAndDescription = intermediateStage.split("/by ");
        return deadlineAndDescription;

    }
    public static String[] getEvent(String userInput) {
        String intermediateStage = userInput.replace("event ", "");
        String[] eventDescription = intermediateStage.split("/from | /to");
        return eventDescription;
    }
    public static void printNoTasks(int currentIndex) {
        if(currentIndex==1) {
            System.out.println("\tNow you have " + Integer.toString(currentIndex) + " task in the list");
        } else {
            System.out.println("\tNow you have " + Integer.toString(currentIndex) + " tasks in the list");
        }
    }

    public static void printTodo(String userInput) {
        String input = userInput.replace("todo ", "");
        taskList[currentIndex] = new Todos(input);
        currentIndex+=1;
        printNoTasks(currentIndex);
    }
    public static void printDeadline(String userInput) {
        String [] deadlineAndDescription = getDeadline(userInput);
        taskList[currentIndex] = new Deadlines(deadlineAndDescription[0], deadlineAndDescription[1]);
        currentIndex+=1;
        printNoTasks(currentIndex);
    }

    public static void printEvent(String userInput) {
        String [] eventDescription = getEvent(userInput);
        taskList[currentIndex] = new Events(eventDescription[0], eventDescription[1], eventDescription[2]);
        currentIndex+=1;
        printNoTasks(currentIndex);
    }


    final static int MAXTASKS = 100;
    public static Task[] taskList = new Task[MAXTASKS];
    public static int currentIndex = 0;
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("---------------------------------------------------------------------------------");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("---------------------------------------------------------------------------------");
        Scanner in = new Scanner(System.in);
        String userInput;
        while (true) { // ensure that the loop can stay on forever if needed.
            userInput = in.nextLine();
            if(userInput.equals("bye")) { // exit command
                break;
            } else if(userInput.equals("list")) { //displays the list if needed
                printLine();
                System.out.println("\tHere are the tasks in your list:");
                listTasks(currentIndex, taskList);
                printLine();
            } else if (isTheSame(userInput, "mark")) { //mark the task in
                while(isInRange(userInput, currentIndex)==false) {
                    printLine();
                    System.out.println("\tNice try, enter a valid index to mark:");
                    printLine();
                    userInput = in.nextLine();
                }
                printLine();
                printMarkedTask(userInput, taskList);
                printLine();
            } else if (userInput.split(" ")[0].equals("unmark")) {//unmark the task
                while(isInRange(userInput, currentIndex)==false) {
                    printLine();
                    System.out.println("\tNice try, enter a valid index to unmark:");
                    printLine();
                    userInput = in.nextLine();
                }
                printLine();
                printUnmarkedTask(userInput, taskList);
                printLine();
            } else if(isTheSame(userInput, "todo")) {
                printLine();
                printTodo(userInput);
                printLine();
                //leave this for the final refactoring
            } else if(isTheSame(userInput, "deadline")) {
                printLine();
               printDeadline(userInput);
                printLine();
            } else if(isTheSame(userInput, "event")) {
                printLine();
                printEvent(userInput);
                printLine();
            }
            else { // tells the user that we have added the task in
                taskList[currentIndex] = new Task(userInput); // set the description
                currentIndex+=1;
                printLine();
                System.out.println("\tadded: " + userInput);
                printLine();
                //leave this for refactoring
            }

        }
        printLine();
        System.out.println("\tBye. Hope to see you again soon!");
        printLine();
    }
}
