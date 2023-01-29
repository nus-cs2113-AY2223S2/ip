import java.util.Scanner;

public class Todolist {

    public static final String LINE = "    ____________________________________________________________\n";
    public static final String GREET = "     Hello! I'm Duke\n" +
            "     Welcome to Tasks!\n" +
            "     Enter \"todo 'task-name'\" to add a task.\n" +
            "     Enter \"deadline 'task-name' /by 'deadline'\" to add a task with a deadline.\n" +
            "     Enter \"event 'task-name' /from 'start-date' /to 'end-date'\" to add a task with start and end dates.\n" +
            "     Enter \"mark 'task-index'\" to mark a task as done.\n" +
            "     Enter \"unmark 'task-index'\" to mark a task as not done yet.\n" +
            "     Enter \"list\" to obtain a list of all your tasks!.\n";
    public static final String BYE = "     Bye. Hope to see you again soon!\n";
    public static final String NOT_DONE = "    OK :(, I've marked this task as not done yet: \n    ";
    public static final String DONE = "    Nice! I've marked this task as done: \n    ";
    public static final String ERROR = "    Something went wrong :( Check your input and try again! \n";

    public static void printAddedTask(Task task, int total) {
        System.out.print(LINE + "    Got it. I've added this task:\n      " +
                task.toString() + "\n    Now you have " + total + " tasks in the list.\n" +
                LINE);
    }

    public static void main(String[] args) {
        Task[] tasks = new Task[100];
        String greet = LINE + GREET + LINE;
        System.out.print(greet);
        String line;
        int count = 0;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();

        while (!line.equals("bye")) {
            String[] words = line.split(" ", 2);
            String command = words[0];
            if (command.equals("list")) {
                System.out.println(LINE + "Here are the tasks in your list:");
                for (int i = 1; i <= count; i++) {
                    System.out.println("    " + i + "." + tasks[i-1].toString());
                }
                System.out.print(LINE);
            } else if (command.contains("mark")) {
                int index = Integer.parseInt(words[1]) - 1;
                if (command.equals("unmark")) {
                    tasks[index].setDone(false);
                    System.out.print(LINE + NOT_DONE);
                } else {
                    tasks[index].setDone(true);
                    System.out.print(LINE + DONE);
                }
                System.out.print(tasks[index].toString() + "\n" + LINE);
            } else {
                if (command.equals("todo")) {
                    String description = words[1];
                    tasks[count] = new Task(description, "T");
                } else if (command.equals("deadline")) {
                    String[] description = words[1].split(" /by ");
                    tasks[count] = new Deadline(description[0], "D", description[1]);
                } else if (command.equals("event")) {
                    String[] description = words[1].split(" /from ");
                    String[] dates = description[1].split(" /to ");
                    tasks[count] = new Event(description[0], "E", dates[0], dates[1]);
                } else {
                    System.out.print(LINE + ERROR + LINE);
                    line = in.nextLine();
                    continue;
                }
                count++;
                printAddedTask(tasks[count-1], count);
            }
            line = in.nextLine();
        }

        System.out.print(LINE + BYE + LINE);
    }
}
