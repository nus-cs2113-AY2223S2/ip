import duke.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class UI {
    public String getInput() {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        return input;
    }
    public void printDottedLine() {
        System.out.println("____________________________________________________________");
    }
    public void printAllTasks(TaskList tasks) {
        printDottedLine();
        int counter = 1;
        System.out.println("Here are the tasks in your list:");
        ArrayList<Task> taskList = tasks.getTaskList();
        for (Task task : taskList) {
            if (task != null) {
                System.out.println(counter + "." + task);
                counter++;
            }
        }
        printDottedLine();
    }

    public void printHelloStatement() {
        printDottedLine();
        System.out.println(" Hello! I'm Duke");
        System.out.println(" What can I do for you?");
        printDottedLine();
    }

    public void printByeStatement() {
        printDottedLine();
        System.out.println(" Bye. Hope to see you again soon!");
        printDottedLine();
    }

    public void printTaskStatusStatement(Task curTask, String status) {
        printDottedLine();
        if (status.equals("mark")) {
            System.out.println(" Nice! I've marked this task as done:");
        } else {
            System.out.println(" OK, I've marked this task as not done yet:");
        }
        System.out.println(curTask);
        printDottedLine();
    }

    public void printTaskAddedStatement(TaskList tasks, Task task) {
        printDottedLine();
        System.out.println(" Got it. I've added this task:");
        System.out.println(" " + task);
        if (tasks.sizeOfTaskList() == 1) {
            System.out.println(" Now you have " + tasks.sizeOfTaskList() + " task in the list.");
        } else {
            System.out.println(" Now you have " + tasks.sizeOfTaskList() + " tasks in the list.");
        }
        printDottedLine();
    }

    public void printTaskDeletedStatement(TaskList tasks, Task task) {
        printDottedLine();
        System.out.println(" Noted. I've removed this task:");
        System.out.println(" " + task);
        if (tasks.sizeOfTaskList() == 1) {
            System.out.println(" Now you have " + (tasks.sizeOfTaskList() - 1) + " task in the list.");
        } else {
            System.out.println(" Now you have " + (tasks.sizeOfTaskList() - 1) + " tasks in the list.");
        }
        printDottedLine();
    }
}

