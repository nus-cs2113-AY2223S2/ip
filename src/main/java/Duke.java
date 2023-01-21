import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    public static void printHorizontalLine() {
        System.out.println("____________________________________________________________");
    }

    public static int checkInput(String input) {
        switch (input) {
        case "bye":
            return 0;
        case "list":
            return 1;
        case "mark":
            return 2;
        case "unmark":
            return 3;
        default:
            return -1;
        }
    }

    public static void main(String[] args) {
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
        String line;
        ArrayList<Task> listOfTasks = new ArrayList<>();
        do {
            Scanner in = new Scanner(System.in);
            line = in.nextLine();
            printHorizontalLine();
            String[] inputs = new String[2];
            inputs = line.split(" ");
            int instruction = checkInput(inputs[0]);
            switch(instruction) {
            case 0:
                System.out.println("Bye. Hope to see you again soon!");
                break;
            case 1:
                Task.listTasks(listOfTasks);
                break;
            case 2:
                listOfTasks.get(Integer.parseInt(inputs[1])-1).setStatus(true);
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  [X] " + listOfTasks.get(Integer.parseInt(inputs[1])-1).getName());
                break;
            case 3:
                listOfTasks.get(Integer.parseInt(inputs[1])-1).setStatus(false);
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("  [ ] " + listOfTasks.get(Integer.parseInt(inputs[1])-1).getName());
                break;
            default:
                Task.addToTasksList(line, listOfTasks);
                break;
            }
            printHorizontalLine();
        } while (!line.equals("bye"));
    }
}
