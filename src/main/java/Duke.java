import java.util.Scanner;

public class Duke {
    private static void displayList(Task[] tasks) {
        for (int i = 0; i < Task.numberOfTasks; i += 1) {
            System.out.print(i + 1 + ". ");
            tasks[i].printTask();
        }
    }

    private static void markTask(Task[] tasks, String task) {
        tasks[Integer.parseInt(task) - 1].setDone();
        System.out.println("Nice! I've marked this task as done:");
        displayList(tasks);
    }

    private static void unmarkTask(Task[] tasks, String task) {
        tasks[Integer.parseInt(task) - 1].setUndone();
        System.out.println("OK, I've marked this task as not done yet:");
        displayList(tasks);
    }

    private static void createTodo(Task[] tasks, String task) {
        tasks[Task.numberOfTasks] = new Todo(task);
        System.out.println("Got it. I've added this task:");
        tasks[Task.numberOfTasks - 1].printTask();
        System.out.println("Now you have " + Task.numberOfTasks + " tasks in your list.");
    }

    private static void createEvent(Task[] tasks, String task) {
        String[] words = task.split("/from");
        String description = words[0];
        String[] words2 = words[1].split("/to");
        String start = words2[0];
        String end = words2[1];
        tasks[Task.numberOfTasks] = new Event(description, start, end);
        System.out.println("Got it. I've added this task:");
        tasks[Task.numberOfTasks - 1].printTask();
        System.out.println("Now you have " + Task.numberOfTasks + " tasks in your list.");
    }

    private static void createDeadline(Task[] tasks, String task) {
        String[] words = task.split("/by");
        String description = words[0];
        System.out.println(words[0]);
        System.out.println(words[1]);
        String end = words[1];
        tasks[Task.numberOfTasks] = new Deadline(description, end);
        System.out.println("Got it. I've added this task:");
        tasks[Task.numberOfTasks - 1].printTask();
        System.out.println("Now you have " + Task.numberOfTasks + " tasks in your list.");
    }

    private static String[] getInput() {
        Scanner input = new Scanner(System.in);
        String text = input.nextLine(); // input the whole sentence into text
        return text.split(" ", 2);
    }
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        String[] splitText = getInput();
        Task[] tasks = new Task[100];
        Task.numberOfTasks = 0;
        while (!splitText[0].equals("bye")) {
            switch (splitText[0]) {
            case "mark":
                markTask(tasks, splitText[1]);
                break;
            case "unmark":
                unmarkTask(tasks, splitText[1]);
                break;
            case "list":
                displayList(tasks);
                break;
            case "todo":
                createTodo(tasks, splitText[1]);
                break;
            case "deadline":
                createDeadline(tasks, splitText[1]);
                break;
            case "event":
                createEvent(tasks, splitText[1]);
                break;
            }
            splitText = getInput();
        }
        System.out.println("Bye! Hope to see you again soon!");
    }
}