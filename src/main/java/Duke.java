import java.util.Scanner;

public class Duke {

    public static void printInstructions() {
        System.out.println("LIST OF ALL COMMANDS:");
        System.out.println("todo + \"task name\" to add a task");
        System.out.println("deadline + \"task name\" + /by \"task deadline\" to add a task with a deadline");
        System.out.println(
                "event + \"event name\" + /from \"event start time\" + /to \"event finish time\" to add an event with a stant and finish time");
        System.out.println("list to list all events and tasks available");
    }

    public static void printHorizontalLine() {
        for (int i = 0; i <= 30; i++) {
            System.out.print("_");
        }
        System.out.println();
    }

    public static void falseInput() {
        System.out.println("Sorry Duke could not understand your input :> please follow the instructions");
        printInstructions();
    }

    public static String firstWord(String s) {
        String a[] = s.split(" ");
        return a[0];
    }

    public static void executeAddTodo(String s, int taskId, TaskManager listofItems) {
        s = s.substring("todo ".length(), s.length());
        listofItems.addTask(s, taskId);
        System.out.println("Roger. The following todo has been added:");
        System.out.println("[T][ ] " + s);
        System.out.println("You now have " + (taskId + 1) + " item in the list");
    }

    public static void executeAddDeadline(String s, int taskId, TaskManager listofItems) {
        s = s.substring("deadline ".length(), s.length());
        String[] cmd = s.split(" /by ");
        if (cmd.length == 1) {
            System.out.println("Sorry, it seems like you forgot to input the deadline. Please read instructions again");
            printInstructions();
        } else if (cmd.length > 2) {
            System.out.println("Sorry, it seems like you input more than one deadline. Please read instructions again");
            printInstructions();
        } else {
            listofItems.addDeadline(cmd[0], cmd[1], taskId);
            System.out.println("Roger. The following deadline has been added:");
            System.out.println("[D][ ] " + cmd[0] + " (by: " + cmd[1] + ")");
            System.out.println("You now have " + (taskId + 1) + " item in the list");
        }
    }

    public static void executeAddEvent(String s, int taskId, TaskManager listofItems) {
        s = s.substring("event ".length(), s.length());
        String[] cmd = s.split(" /from ");
        if (cmd.length == 1) {
            System.out
                    .println("Sorry, it seems like you forgot to input the start time. Please read instructions again");
            printInstructions();
        } else {
            String startTime = cmd[1].split(" /to ")[0];
            String endTime = cmd[1].split(" /to ")[1];
            listofItems.addEvent(cmd[0], startTime, endTime, taskId);
            System.out.println("Roger. The following event has been added:");
            System.out.println("[E][ ] " + cmd[0] + " (from: " + startTime + " to: " + endTime + ")");
            System.out.println("You now have " + (taskId + 1) + " item in the list");
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you ?");
        printHorizontalLine();
        printInstructions();
        printHorizontalLine();
        Scanner scanObj = new Scanner(System.in);
        TaskManager listofItems = new TaskManager();
        String userCmd = scanObj.nextLine();
        int taskId = 0;
        while (!userCmd.equals("bye")) {
            switch (firstWord(userCmd)) {
                case "todo":
                    executeAddTodo(userCmd, taskId, listofItems);
                    printHorizontalLine();
                    taskId++;
                    break;
                case "deadline":
                    executeAddDeadline(userCmd, taskId, listofItems);
                    printHorizontalLine();
                    taskId++;
                    break;
                case "event":
                    executeAddEvent(userCmd, taskId, listofItems);
                    printHorizontalLine();
                    taskId++;
                    break;
                case "list":
                    listofItems.listTask();
                    printHorizontalLine();
                    break;
                case "mark":
                    String markId[] = userCmd.split(" ");
                    listofItems.markTask(Integer.parseInt(markId[1]) - 1);
                    printHorizontalLine();
                    break;
                case "unmark":
                    String unmarkId[] = userCmd.split(" ");
                    listofItems.unmarkTask(Integer.parseInt(unmarkId[1]) - 1);
                    printHorizontalLine();
                    break;
                default:
                    falseInput();
                    printHorizontalLine();
            }
            userCmd = scanObj.nextLine();
        }
        scanObj.close();
        System.out.println("Bye. Duke hopes to see you again soon !");
    }
}
