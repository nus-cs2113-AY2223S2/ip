import java.util.Objects;
import java.util.Scanner;


public class Duke {
    public static void main(String[] args) {

        System.out.println("Good day. YodaBot, I am.");
        System.out.println("Assistance, you need?");
        Scanner scanner = new Scanner(System.in);
        Task[] taskArray = new Task[100];
        int taskIndex = 0;
        boolean isEnd = false;
        while (true) {
            String command = scanner.nextLine(); //reads in input
            switch (command) {
            //if command is list, either display empty or display list
            case "list":
                if (taskIndex == 0) {
                    System.out.println("Empty, list is.");
                } else {
                    System.out.println("As shown, list is:");
                    for (int j = 0; j < taskIndex; ++j) {
                        System.out.println(j + ". " + taskArray[j].getIsDone() + " " + taskArray[j].getDescription());
                    }
                }
                break;
            //if command is mark, mark the appropriate entry in the list
            case "mark":
                System.out.println("Mark what, I ask?");
                String listIndex = scanner.nextLine();
                int temp1 = Integer.parseInt(listIndex);
                if (taskIndex == 0 || temp1 > taskIndex) {
                    System.out.println("In list, it is not.");
                } else {
                    taskArray[temp1].setIsDone("[X]");
                    System.out.println("Marked it, I have:");
                    System.out.println(temp1 + ". " + taskArray[temp1].getIsDone() + " " + taskArray[temp1].getDescription());
                }
                break;
                //if command is unmark, unmark the appropriate entry in the list
            case "unmark":
                System.out.println("Unmark what, I ask?");
                String listIndex2 = scanner.nextLine();
                int temp2 = Integer.parseInt(listIndex2);
                if (taskIndex == 0 || temp2 > taskIndex) {
                    System.out.println("In list, it is not.");
                    break;
                } else {
                    taskArray[temp2].setIsDone("[ ]");
                    System.out.println("Unmarked it, I have:");
                    System.out.println(temp2 + ". " + taskArray[temp2].getIsDone() + " " + taskArray[temp2].getDescription());
                }
                break;
                //if command is bye, end program
            case "bye":
                System.out.println("See you soon, I hope. Goodbye.");
                isEnd = true;
                break;
            default:
                taskArray[taskIndex] = new Task(command, "[ ]");
                System.out.println(command + " - Added, I have.");
                ++taskIndex;
                break;
            }
            if(isEnd) {
                break;
            }
        }
    }
}
