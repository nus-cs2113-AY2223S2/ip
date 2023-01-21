import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void startDuke() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
        System.out.println("    ____________________________________________________________");
    }

    public static void endDuke() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
    }

    public static void printAddedTask(String addedTask) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     added: " + addedTask);
        System.out.println("    ____________________________________________________________");
    }

    public static void printList(ArrayList<String> taskList) {
        System.out.println("    ____________________________________________________________");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println("     " + (i+1) + ". " + taskList.get(i));
        }
        System.out.println("    ____________________________________________________________");
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        startDuke();
        Scanner input = new Scanner(System.in);
        ArrayList<String> taskList = new ArrayList<>();
        while (input.hasNextLine()) {
            String nextInput = input.nextLine();
            if (nextInput.equals("bye")) {
                break;
            }
            if (nextInput.isEmpty()) { 
                continue;
            }
            if (nextInput.equals("list")) {
                printList(taskList);
                continue;
            }
            taskList.add(nextInput);
            printAddedTask(nextInput);
        }
        endDuke();
    }

}