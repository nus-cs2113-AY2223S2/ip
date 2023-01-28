import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Duke");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");

        String line;
        Scanner in = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int taskCount = 0;
        while (true) {
            line = in.nextLine();
            if ("list".equalsIgnoreCase(line)) {
                System.out.println("____________________________________________________________");
                System.out.println(" Here are the tasks in your list:");
                int index = 1;
                for (int i = 0; i < taskCount; i++) {
                    System.out.println("  " + index + "." + tasks[i].toString());
                    index++;
                }
                System.out.println("____________________________________________________________");
            } else if ((line.substring(0,4)).equalsIgnoreCase("mark")) {
                System.out.println("____________________________________________________________");
                System.out.println(" Nice! I've marked this task as done:");
                int index = Integer.parseInt(line.substring(5));
                tasks[index-1].markAsDone();
                System.out.println(tasks[index-1].toString());
                System.out.println("____________________________________________________________");
            } else if ((line.substring(0,6)).equalsIgnoreCase("unmark")) {
                System.out.println("____________________________________________________________");
                System.out.println(" Okay, I've marked this task as not done yet:");
                int index = Integer.parseInt(line.substring(7));
                tasks[index-1].markAsNotDone();
                System.out.println(tasks[index-1].toString());
                System.out.println("____________________________________________________________");
            } else if((line.substring(0,4)).equalsIgnoreCase("todo")) {
                tasks[taskCount] = new Todo(line.substring(5));
                System.out.println("____________________________________________________________");
                System.out.println(" Got it. I've added this task:");
                System.out.println(tasks[taskCount].toString());
                taskCount++;
                System.out.println(" Now you have " + taskCount + " tasks in your list.");
                System.out.println("____________________________________________________________");
            } else if((line.substring(0,8)).equalsIgnoreCase("deadline")) {
                int dividerPosition = line.indexOf("/by");
                String taskDescription = line.substring(9, dividerPosition);
                String deadline = line.substring(dividerPosition+4);
                tasks[taskCount] = new Deadline(taskDescription, deadline);
                System.out.println("____________________________________________________________");
                System.out.println(" Got it. I've added this task:");
                System.out.println(tasks[taskCount].toString());
                taskCount++;
                System.out.println(" Now you have " + taskCount + " tasks in your list.");
                System.out.println("____________________________________________________________");
            } else if((line.substring(0,5)).equalsIgnoreCase("event")) {
                int divider1 = line.indexOf("/from");
                String event = line.substring(6, divider1);
                int divider2 = line.indexOf("/to");
                String eventStart = line.substring(divider1+6, divider2);
                String eventEnd = line.substring(divider2+4);
                tasks[taskCount] = new Event(event, eventStart, eventEnd);
                System.out.println("____________________________________________________________");
                System.out.println(" Got it. I've added this task:");
                System.out.println(tasks[taskCount].toString());
                taskCount++;
                System.out.println(" Now you have " + taskCount + " tasks in your list.");
                System.out.println("____________________________________________________________");
            } else if ("bye".equalsIgnoreCase(line)) {
                System.out.println("____________________________________________________________");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            }
        }
    }
}