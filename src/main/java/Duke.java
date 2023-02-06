import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    public static void printHorizontalLine() {
        System.out.println("____________________________________________________________");
    }

    public static void printHelloMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printHorizontalLine();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        printHorizontalLine();
    }

    public static void checkInput(String input, ArrayList<Task> listOfTasks) {
        String[] inputs = input.split(" ");
        switch (inputs[0]) {
        case "bye":
            System.out.println("Bye. Hope to see you again soon!");
            break;
        case "list":
            Task.listTasks(listOfTasks);
            break;
        case "mark":
            Task taskToBeMarked = listOfTasks.get(Integer.parseInt(inputs[1])-1);
            taskToBeMarked.setStatus(true);
            Task.printUpdateStatusMessage(taskToBeMarked.getStatus(), taskToBeMarked);
            break;
        case "unmark":
            Task taskToBeUnmarked = listOfTasks.get(Integer.parseInt(inputs[1])-1);
            taskToBeUnmarked.setStatus(false);
            Task.printUpdateStatusMessage(taskToBeUnmarked.getStatus(), taskToBeUnmarked);
            break;
        case "delete":
            int taskNumber = Integer.parseInt(inputs[1]) - 1;
            Task.deleteTask(taskNumber, listOfTasks);
            break;
        default:
            try {
                Task.checkTaskType(input, listOfTasks);
                break;
            } catch (InvalidTaskTypeException e) {
                System.out.println(e);
            }
        }
    }

    public static void main(String[] args) {
        printHelloMessage();
        String input;
        ArrayList<Task> listOfTasks = new ArrayList<>();
        do {
            Scanner in = new Scanner(System.in);
            input = in.nextLine();
            printHorizontalLine();
            checkInput(input, listOfTasks);
            printHorizontalLine();
        } while (!input.equals("bye"));
    }
}
