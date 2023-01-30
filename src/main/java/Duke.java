import java.util.Scanner;

public class Duke {

    static final Task[] TASKS = new Task[101];

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        runBot();
    }

    public static void runBot() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        Scanner scan = new Scanner(System.in);
        String[] command = scan.nextLine().split(" ");
        int commandI = 0;
        int tasksI = 1;
        while (!command[0].equals("bye")) {
            if (command[0].equals("list")) {    // not a task --> want to see the tasks
                System.out.println("Here are the tasks in your list: ");
                for (int i = 1; i < tasksI; i++) {
                    Task currTask = TASKS[i];
                    // System.out.println(i + ".[" + currTask.getStatusIcon() + "] " + currTask.getDescription());
                    System.out.println(i + ". " + currTask.toString());
                }
            } else if (command[0].contains("mark")) {   // not a task --> want to mark/unmark a task
                int taskNum = Integer.parseInt(command[1]);
                Task currTask = TASKS[taskNum];
                if (command[0].equals("mark")) {
                    currTask.mark();
                    System.out.println("Nice! I've marked this task as done: ");
                    System.out.println(currTask.toString());
                } else {
                    currTask.unmark();
                    System.out.println("OK, I've marked this task as not done yet: ");
                    System.out.println(currTask.toString());
                }
            } else {    // task
                if (command[0].equals("deadline")) {
                    String description = String.join(" ", command).substring(8);
                    String by = description.substring(description.indexOf("by") + 3);
                    Deadline deadline = new Deadline(description, by);
                    TASKS[tasksI] = deadline;
                    System.out.println("Got it. I've added this task: ");
                    System.out.println(deadline.toString());
                } else if (command[0].equals("todo")) {
                    String description = String.join(" ", command).substring(4);
                    Todo todo = new Todo(description);
                    TASKS[tasksI] = todo;
                    System.out.println("Got it. I've added this task: ");
                    System.out.println(todo.toString());
                } else if (command[0].equals("event")) {
                    String description = String.join(" ", command).substring(5);
                    Event event = new Event(description);
                    TASKS[tasksI] = event;
                    System.out.println("Got it. I've added this task:");
                    System.out.println(event.toString());
                } else {
                    Task task = new Task(String.join(" ", command));
                    TASKS[tasksI] = task;
                    System.out.println(task.toString());
                }
                System.out.println("Now you have " + tasksI + " tasks in the list.");
                tasksI++;
            }
            command = scan.nextLine().split(" ");
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}



