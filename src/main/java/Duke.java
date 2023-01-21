import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        Scanner in = new Scanner(System.in);
        String userInput = "";
        ArrayList<Task> listOfTasks = new ArrayList<Task>();
        boolean isRunning = true;

        while (isRunning) {
            userInput = in.nextLine();
            String[] userInputSplit = userInput.split(" ");
            Task task;

            switch (userInputSplit[0]) {
            case "bye":
                System.out.println("Bye. Hope to see you again soon!");
                isRunning = false;
                break;
            case "list":
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < listOfTasks.size(); i++) {
                    task = listOfTasks.get(i);
                    System.out.println(i + 1 + "." + task.description);
                }
                break;
            case "mark":
                int markTask = Integer.parseInt(userInputSplit[1]) - 1;
                task = listOfTasks.get(markTask);
                task.markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(task.getDescription());
                break;
            case "unmark":
                int unmarkTask = Integer.parseInt(userInputSplit[1]) - 1;
                task = listOfTasks.get(unmarkTask);
                task.markAsUndone();
                System.out.println("OK, I've marked this task as not done yet: ");
                System.out.println(task.getDescription());
                break;
            default:
                Task newTask = new Task("[ ] " + userInput);
                listOfTasks.add(newTask);
                System.out.println("added: " + userInput);
                break;

            }
        }
    }
}
