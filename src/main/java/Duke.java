import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?\n");

        String line;
        Task[] tasks = new Task[100];
        int tasksIndex = 0;
        Scanner in = new Scanner(System.in);

        line = in.nextLine();
        while (!line.equals("bye")) {
            if (line.equals("list")) { // if it's list, list all the tasks and their current marked status
                for (int i = 0; i < tasksIndex; i++) {
                    System.out.print(i + 1);
                    System.out.print(". ");
                    tasks[i].printTask();
                }
            } else if (line.substring(0, 4).equals("mark")) { // if it's mark X, mark task X (looks like buggy if a task is called "mark ..."
                int markIndex = Integer.parseInt(line.substring(5)) - 1;
                tasks[markIndex].markTask();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("[X] " + tasks[markIndex].getTaskName());
            } else if (line.substring(0, 6).equals("unmark")) { // if it's unmark task X, unmark task X
                int unmarkIndex = Integer.parseInt(line.substring(7)) - 1;
                tasks[unmarkIndex].unmarkTask();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("[ ] " + tasks[unmarkIndex].getTaskName());
            } else { // if it's a task, add the task
                tasks[tasksIndex] = new Task(line, false);
                tasksIndex++;
            }


            // read the next line
            line = in.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
        return;
    }
}
