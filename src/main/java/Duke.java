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
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < tasksIndex; i++) {
                    System.out.print(i + 1);
                    System.out.print(". ");
                    System.out.println(tasks[i].toString());
                }
            } else if (line.substring(0, 4).equals("mark")) { // if it's mark X, mark task X (looks like buggy if a task is called "mark ..."
                int indexToBeMarked = Integer.parseInt(line.substring(5)) - 1;
                tasks[indexToBeMarked].setDone(true);
                System.out.println("Nice! I've marked this task as done:");
                // System.out.println("[X] " + tasks[indexToBeMarked].getTaskName());
                System.out.println(tasks[indexToBeMarked].toString());
            } else if (line.substring(0, 6).equals("unmark")) { // if it's unmark task X, unmark task X
                int indexToBeUnmarked = Integer.parseInt(line.substring(7)) - 1;
                tasks[indexToBeUnmarked].setDone(false);
                System.out.println("OK, I've marked this task as not done yet:");
                // System.out.println("[ ] " + tasks[indexToBeUnmarked].getTaskName());
                System.out.println(tasks[indexToBeUnmarked].toString());
            } else { // if it's a task, add the task
                /*
                tasks[tasksIndex] = new Task(line, false);
                tasksIndex++;
                System.out.println("added: " + line);
                */
                if(line.substring(0,8).equals("deadline")) {
                    // split the line
                    String[] words = line.split("/");
                    tasks[tasksIndex] = new Deadline(words[0].substring(9), words[1].substring(3));
                    System.out.println("Got it. I've added this task:\n" + "  " + tasks[tasksIndex].toString());
                    tasksIndex++;
                    System.out.println("Now you have " + tasksIndex + " tasks in the list.");
                } else if(line.substring(0,4).equals("todo")) {
                    tasks[tasksIndex] = new ToDo(line.substring(4));
                    System.out.println("Got it. I've added this task:\n" + "  " + tasks[tasksIndex].toString());
                    tasksIndex++;
                    System.out.println("Now you have " + tasksIndex + " tasks in the list.");
                } else if(line.substring(0,5).equals("event")) {
                    // split the line
                    String[] words = line.split("/");
                    tasks[tasksIndex] = new Event(words[0].substring(6), words[1].substring(5),
                            words[2].substring(3));
                    System.out.println("Got it. I've added this task:\n" + "  " + tasks[tasksIndex].toString());
                    tasksIndex++;
                    System.out.println("Now you have " + tasksIndex + " tasks in the list.");
                }
            }
            line = in.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
        return;
    }
}
