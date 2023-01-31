import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int currentIndex = 0;

        printHelloStatement();
        while (true) {
            String input;
            input = in.nextLine();
            if (input.equals("bye")) {
                printByeStatement();
                break;
            } else if (input.equals("list")) {
                printAllTasks(tasks);
            } else if (input.startsWith("mark")) {
                String[] temp = input.split(" ", 2);
                int taskIndex = Integer.parseInt(temp[1]);
                Task curTask = tasks[taskIndex - 1];
                curTask.markAsDone();
                printTaskStatusStatement(curTask,"mark");
            } else if (input.startsWith("unmark")) {
                String[] temp = input.split(" ", 2);
                int taskIndex = Integer.parseInt(temp[1]);
                Task curTask = tasks[taskIndex - 1];
                curTask.unmarkAsDone();
                printTaskStatusStatement(curTask,"unmark");
            } else if (input.startsWith("todo")){
                String[] temp = input.split(" ", 2);
                String description = temp[1];
                ToDo todo = new ToDo(currentIndex + 1, description);
                tasks[currentIndex] = todo;
                currentIndex++;
                printTaskAddedStatement(currentIndex, todo);
            }
            else if (input.startsWith("deadline")){
                String[] temp1 = input.split(" /by ", 2);
                String by = temp1[1];
                String[] temp2 = temp1[0].split(" ", 2);
                String description = temp2[1];
                Deadline deadline = new Deadline(currentIndex + 1, description, by);
                tasks[currentIndex] = deadline;
                currentIndex++;
                printTaskAddedStatement(currentIndex, deadline);
            }
            else if (input.startsWith("event")){
                String[] temp1 = input.split(" /to ", 2);
                String to = temp1[1];
                String[] temp2 = temp1[0].split(" /from ", 2);
                String from = temp2[1];
                String[] temp3 = temp2[0].split(" ", 2);
                String description = temp3[1];
                Event event = new Event(currentIndex + 1, description, from, to);
                tasks[currentIndex] = event;
                currentIndex++;
                printTaskAddedStatement(currentIndex, event);
            }
            else {
                break;
            }
        }
    }

    private static void printAllTasks(Task[] tasks) {
        System.out.println("____________________________________________________________");
        System.out.println("Here are the tasks in your list:");
        for (Task task : tasks) {
            if (task != null) {
                System.out.println(task);
            }
        }
        System.out.println("____________________________________________________________");
    }

    private static void printHelloStatement() {
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Duke");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    private static void printByeStatement() {
        System.out.println("____________________________________________________________");
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    private static void printTaskStatusStatement(Task curTask, String status) {
        System.out.println("____________________________________________________________");
        if (status.equals("mark")){
            System.out.println("Nice! I've marked this task as done:");
        }
        else {
            System.out.println("OK, I've marked this task as not done yet:");
        }
        System.out.println(curTask);
        System.out.println("____________________________________________________________");
    }

    private static void printTaskAddedStatement(int currentIndex, Task task) {
        System.out.println("____________________________________________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        if (currentIndex == 1) {
            System.out.println("Now you have " + currentIndex + " task in the list.");
        }
        else {
            System.out.println("Now you have " + currentIndex + " tasks in the list.");
        }
        System.out.println("____________________________________________________________");
    }
}

