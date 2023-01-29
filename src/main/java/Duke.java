import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?\n");
        ArrayList<Task> tasks = new ArrayList<Task>();
        Scanner scan = new Scanner(System.in);
        String desc = scan.nextLine();
        while (!desc.equals("bye")) {
            if (desc.equals("list")) {
                int a = 1;
                System.out.println("Here are the tasks in the list:");
                for (Task i : tasks) {
                    System.out.println(a + ". [" + i.getStatusIcon() + "] " + i.getDescription());
                    a++;
                }
            } else if (desc.matches("mark [0-9]{1,2}")) {
                String[] marks = desc.split(" ");
                tasks.get(Integer.parseInt(marks[1]) - 1).markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("[" + tasks.get(Integer.parseInt(marks[1]) - 1).getStatusIcon() + "] " +
                        tasks.get(Integer.parseInt(marks[1]) - 1).getDescription());
            } else if (desc.matches("unmark [0-9]{1,2}")) {
                String[] marks = desc.split(" ");
                tasks.get(Integer.parseInt(marks[1]) - 1).markAsNotDone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("[" + tasks.get(Integer.parseInt(marks[1]) - 1).getStatusIcon() + "] " +
                        tasks.get(Integer.parseInt(marks[1]) - 1).getDescription());
            } else {
                Task task = new Task(desc);
                System.out.println("added: " + desc);
                tasks.add(task);
            }
            desc = scan.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");

    }

}
