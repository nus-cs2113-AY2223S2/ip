import java.util.Arrays;
import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        String userInput;
        Scanner in = new Scanner(System.in);


        //String[] userCommands = new String[0];
        Task[] userTasks = new Task[0];

        // TODO: remove trailing spaces and leading spaces

        Boolean isContinue = true;
        while (isContinue) {
            userInput = in.nextLine();

            String[] userCommands = userInput.split(" ");

            String currentTaskName = userCommands[0];
            int taskIndex; // for the mark and unmark case where userCommands has two elements 1. task name 2. task index (if mark or unmark cmd)

            switch (currentTaskName) {

            case "list":
                for(int i = 0; i < userTasks.length; i++) {
                    if (userTasks[i].getisDone()) {
                        System.out.println( (i+1) + ". [X] " + userTasks[i].getTaskName());
                    } else {
                        System.out.println((i + 1) + ". [ ] " + userTasks[i].getTaskName());
                    }
                }
                break;

            case "bye":
                System.out.println("Bye. Hope to see you again soon!");
                isContinue = false;
                break;

            case "mark":
                System.out.println("Nice! I've marked this task as done:");
                taskIndex = Integer.parseInt(userCommands[1]) - 1;
                userTasks[taskIndex].setisDone(true);
                System.out.println("  [X] " + userTasks[taskIndex].getTaskName());
                break;

            case "unmark":
                System.out.println("OK, I've marked this task as not done yet:");
                taskIndex = Integer.parseInt(userCommands[1]) - 1;
                userTasks[taskIndex].setisDone(false);
                System.out.println("  [ ] " + userTasks[taskIndex].getTaskName());
                break;

            default:
                Task newTask = new Task(userInput, false);
                userTasks = Arrays.copyOf(userTasks, userTasks.length+1);
                userTasks[userTasks.length-1] = newTask;
                System.out.println("added: " + newTask.getTaskName());
                break;
            }
        }
    }
}