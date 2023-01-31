import java.util.Scanner;
import java.util.ArrayList;

public class ThomasShelby {
    public static void main(String[] args) {
        Task[] taskManager = new Task[100];
        int taskCount = 0;
        System.out.print("Good day, I'm Thomas Shelby.\nTo what do I owe the pleasure?\n");
        Scanner in = new Scanner(System.in);
        while (true) {
            String cmd = in.nextLine();
            String[] cmdSplit = cmd.split(" ", 2); // array of words
            int whichTask = 0; // variable holding mark/unmark task num
            switch (cmdSplit[0]) {
            case "bye":
                System.out.println("Cheers.");
                return;
            case "list":
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println((i + 1) + ". "
                            + taskManager[i].getStatusIcon() + " "
                            + taskManager[i].getDescription());
                }
                break;
            case "add":
                Task newTask = new Task(cmdSplit[1]);
                newTask.setDescription(cmdSplit[1]);
                taskManager[taskCount] = newTask;
                taskCount++;
                System.out.println("added: " + cmdSplit[1]);
                break;
            case "mark":
                whichTask = Integer.parseInt(cmdSplit[1]) - 1; // shift back index
                taskManager[whichTask].setIsDone(true);
                System.out.println("That was long due, well done.");
                System.out.println(taskManager[whichTask].getStatusIcon()
                        + " " + taskManager[whichTask].getDescription());
                break;
            case "unmark":
                whichTask = Integer.parseInt(cmdSplit[1]) - 1; // shift back index
                taskManager[whichTask].setIsDone(false);
                System.out.println("You've gotten lazy.");
                System.out.println(taskManager[whichTask].getStatusIcon()
                        + " " + taskManager[whichTask].getDescription());
                break;
            default:
                System.out.println(cmd); // echo cmd
                break;
            }
        }
    }
}