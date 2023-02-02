import java.util.Scanner;

public class Duke {

    static final Task[] TASKS = new Task[101];
    static int tasksI = 1;

    public static void main(String[] args) {
        // print intro message
        start();
        // run bot (decode task)
        run();
    }

    public static void start() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    public static void list() {
        System.out.println("Here are the tasks in your list: ");
        for (int i = 1; i < tasksI; i++) {
            Task currTask = TASKS[i];
            System.out.println(i + ". " + currTask.toString());
        }
    }

    public static void markUnmark(String word, Task task) {
        if (word.equals("mark")) {
            task.mark();
            System.out.println("Nice! I've marked this task as done: ");
            System.out.println(task.toString());
        } else {
            task.unmark();
            System.out.println("OK, I've marked this task as not done yet: ");
            System.out.println(task.toString());
        }
    }

    public static void deadline(String taskDescript) {
        int bySize = 3;
        // System.out.println("begin index: " + taskDescript.indexOf("by"));
        // System.out.println("end index: " + taskDescript.indexOf("by") + bySize);
        String by = taskDescript.substring(taskDescript.indexOf("by") + bySize);
        Deadline deadline = new Deadline(taskDescript, by);
        TASKS[tasksI] = deadline;
        System.out.println("Got it. I've added this task: ");
        System.out.println(deadline.toString());
    }

    public static void todo(String taskDescript) {
        Todo todo = new Todo(taskDescript);
        TASKS[tasksI] = todo;
        System.out.println("Got it. I've added this task: ");
        System.out.println(todo.toString());
    }

    public static void event(String taskDescript) {
        Event event = new Event(taskDescript);
        TASKS[tasksI] = event;
        System.out.println("Got it. I've added this task:");
        System.out.println(event.toString());
    }

    public static void task(String taskType, String[] taskDescript) {
        if (taskType.equals("deadline") || taskType.equals("todo") || taskType.equals("event")) {
            String descript = String.join(" ", taskDescript).substring(taskType.length());
            if (taskType.equals("deadline")) {
                deadline(descript);
            } else if (taskType.equals("todo")) {
                todo(descript);
            } else if (taskType.equals("event")) {
                event(descript);
            }
        } else {
            Task task = new Task(String.join(" ", taskType));
            TASKS[tasksI] = task;
            System.out.println(task.toString());
        }
        System.out.println("Now you have " + tasksI + " tasks in the list.");
    }

    public static void run() {
        Scanner scan = new Scanner(System.in);
        String[] input = scan.nextLine().split(" ");
        while (!input[0].equals("bye")) {
            // want to see all the tasks in a list
            if (input[0].equals("list")) {
                list();
            // mark/unmark a task
            } else if (input[0].contains("mark")) {
                Task taskNum = TASKS[Integer.parseInt(input[1])];
                markUnmark(input[0], taskNum);
            // a task
            } else {
                task(input[0], input);
                tasksI++;
            }
            input = scan.nextLine().split(" ");
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}



