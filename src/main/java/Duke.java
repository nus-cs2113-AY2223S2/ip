import java.util.Objects;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        System.out.println("Good day. YodaBot, I am.");
        System.out.println("Assistance, you need?");
        Scanner scanner = new Scanner(System.in);
        Task[] taskArray = new Task[100];
        int taskIndex = 0;
        while (true) {
            String command = scanner.nextLine(); //reads in input
            String[] split = command.split(" "); //separates mark and integer
            //if command is mark, mark the appropriate entry in the list
            if (split[0].equalsIgnoreCase("mark")) {
                if (taskIndex == 0) {
                    System.out.println("Empty, list is.");
                } else {
                    int listIndex = Integer.parseInt(split[1]);
                    taskArray[listIndex].setIsDone("[X]");
                    System.out.println("Marked it, I have:");
                    System.out.println(listIndex + ". " + taskArray[listIndex].getIsDone() + " " + taskArray[listIndex].getDescription());
                }
            }
            //if command is unmark, unmark the appropriate entry in the list
            if (split[0].equalsIgnoreCase("unmark")) {
                if (taskIndex == 0) {
                    System.out.println("Empty, list is.");
                } else {
                    int listIndex = Integer.parseInt(split[1]);
                    taskArray[listIndex].setIsDone("[ ]");
                    System.out.println("Unmarked it, I have:");
                    System.out.println(listIndex + ". " + taskArray[listIndex].getIsDone() + " " + taskArray[listIndex].getDescription());
                }
            }
            //if command is not list or bye or mark, add to list
            if (!command.equalsIgnoreCase("list") && !command.equalsIgnoreCase("bye") && !split[0].equalsIgnoreCase("mark")&& !split[0].equalsIgnoreCase("unmark")) {
                taskArray[taskIndex] = new Task(command, "[ ]");
                System.out.println(command + " - Added, I have.");
                ++taskIndex;
            }
            //if command is list, either display empty or display list
            if (command.equalsIgnoreCase("list")) {
                if (taskIndex == 0) {
                    System.out.println("Empty, list is.");
                } else {
                    System.out.println("As shown, list is:");
                    for (int j = 0; j < taskIndex; ++j) {
                        System.out.println(j + ". " + taskArray[j].getIsDone() + " " + taskArray[j].getDescription());
                    }
                }
            }
            //if command is bye, exit
            if (command.equalsIgnoreCase("bye")) {
                System.out.println("See you soon, I hope. Goodbye.");
                break;
            }
        }
    }
}
