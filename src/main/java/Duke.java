import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static void greeting() {
        System.out.println(" Hello! I'm Duke");
        System.out.println(" What can I do for you?");
    }

    public static void main(String[] args) {
        greeting();

        boolean isByeEnter = false;
        ArrayList<Task> taskNameList = new ArrayList<>();
        Scanner userInput = new Scanner(System.in);

        while (!isByeEnter) {

            String taskName = userInput.nextLine();
            Task taskList = new Task(taskName);
            //String combine = taskName + userInput.nextLine();
            String[] command = taskName.split("\\s+");
            switch (command[0]) {
            case "list":
                for (int i = 0; i < taskNameList.size(); i++) {
                    System.out.println((i + 1) + ".[" + taskNameList.get(i).getStatusIcon()
                            + "] " + taskNameList.get(i).getDescription());
                }
                break;

            case "mark":
                //  taskNameList.get(command[1]).mark();
                int indexTask = Integer.parseInt(command[1]);
                taskNameList.get(indexTask - 1).mark();
                // new Task(isComplete);
                System.out.println("Nice! I've marked this task as done:" + System.lineSeparator() +
                        "[" + taskNameList.get(indexTask - 1).getStatusIcon() + "] " + taskNameList.get(indexTask - 1).getDescription());
                break;

            case "unmark":

                //  new Task(isComplete);
                int indexOfTask = Integer.parseInt(command[1]);
                taskNameList.get(indexOfTask - 1).unMark();
                System.out.println("OK, I've marked this task as not done yet:" + System.lineSeparator() +
                        "[" + taskNameList.get(indexOfTask - 1).getStatusIcon() + "] " + taskNameList.get(indexOfTask - 1).getDescription());
                break;

            case "Bye":
                System.out.println("Bye. Hope to see you again soon!");
                isByeEnter = true;
                break;

            default:

                Task task = new Task(taskName);
                taskNameList.add(task);
                System.out.println("added: " + taskName);
                break;

            }
        }

    }
}

