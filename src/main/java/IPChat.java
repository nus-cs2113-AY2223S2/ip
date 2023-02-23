import java.util.Arrays;
import java.util.Scanner;

public class IPChat {

    public static Task[] tasks = new Task [100];
    public static int tasksCount = 0; // used camelCase

    // Accepts the input of the user
    public static void inputUser() {
        Scanner input =  new Scanner(System.in);
        String statements = input.nextLine(); // changed statement to statements
        mySequence(statements);
    }

    // Says bye to the user and exits the program
    public static void sayBye() {
        System.out.println("------------------------------------------");
        System.out.println("Bye, Hope to see you soon");
        System.out.println("------------------------------------------");
        System.exit(0);
    }

    // Create a list of the task
    public static void listTasks() {
        System.out.println("------------------------------------------");
        System.out.println("Here is the list of tasks for the day! All the best :) \n");
        for (int i = 0; i < tasksCount; i += 1) {
            System.out.println((i + 1) + "." +"[" + tasks[i].getStatusIcon() + "] " + tasks[i].toString());
        }
        System.out.println("------------------------------------------");
    }

    // Marks a task as done
    public static void markDone(String statements) {
        int taskIndex = Integer.parseInt(statements.substring(statements.length()-1)) - 1; // changed index to taskIndex
        tasks[taskIndex].markAsDone();
        System.out.println("I have marked the task as done");
        System.out.println("------------------------------------------");
    }

    // tasks to do
    public static void toDoTasks (String statements) {
        int todoLength = statements.length();
        String todoName = statements.substring(5, todoLength);
        tasks[tasksCount] = new ToDo (todoName);
        System.out.println("------------------------------------------");
        System.out.println("Got it. I've added this task:\n" + tasks[tasksCount].toString() + "\n");
        tasksCount += 1;
        System.out.println("Now you have " + tasksCount + " tasks in the list.\n");
        System.out.println("------------------------------------------");
    }

    // Tasks having deadlines
    public static void deadlineTasks (String statements) {
        int deadlineLength = statements.length();
        String deadlineName = statements.substring(9, statements.lastIndexOf("/"));
        String by = statements.substring((statements.lastIndexOf("/") + 4), deadlineLength);
        tasks[tasksCount] = new Deadline(deadlineName, by);
        System.out.println("------------------------------------------");
        System.out.println("Got it. I've added this task:\n" + tasks[tasksCount].toString() + "\n");
        tasksCount += 1;
        System.out.println("Now you have " + tasksCount + " tasks in the list.\n");
        System.out.println("------------------------------------------");
    }

    // Events of the task
    public static void eventTasks (String statements) {
        int eventLength = statements.length();
        String eventName = statements.substring(6, statements.lastIndexOf("/"));
        int index = statements.lastIndexOf("/") + 4;
        String to = statements.substring(index, eventLength);
        tasks[tasksCount] = new Event(eventName, to);
        System.out.println("------------------------------------------");
        System.out.println("Got it. I've added this task:\n" + tasks[tasksCount].toString() + "\n");
        tasksCount += 1;
        System.out.println("Now you have " + tasksCount + " tasks in the list.\n");
        System.out.println("------------------------------------------");
    }

    // Compilation
    public static void mySequence (String statements) {
        while (!statements.equals("bye")) {
            if (statements.equals("list")) {
                listTasks();
            } else if (statements.contains("done")) {
                markDone(statements);
            } else if (statements.contains("todo")) {
                toDoTasks(statements);
            } else if (statements.contains("deadline")) {
                deadlineTasks(statements);
            } else if (statements.contains("event")) {
                eventTasks(statements);
            }

            inputUser();
        }
        sayBye();
    }
    public static void main (String[] args) {
        System.out.println("Hello I'm IPChat, What can I do for you");
        System.out.println("------------------------------------------");
        inputUser();
    }
}
