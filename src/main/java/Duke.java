import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void printList(List<Task> l1) {
        int index;
        for (int i = 0; i < l1.size(); i += 1) {
            index = i + 1;
            System.out.println(index + ". [" + l1.get(i).getStatusIcon() + "] " + l1.get(i).getDescription());
        }
        System.out.println();
    }
    public static void main(String[] args) {
        /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);*/
        System.out.println("Hello! I'm a Robot");
        System.out.println("What can I do for you?\n");
        List<Task> list = new ArrayList<>();
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        while(!line.equals("bye")) {
            if (line.equals("list")) {
                if (!list.isEmpty()) {
                    System.out.println();
                    printList(list);
                } else {
                    System.out.println('\n' + "List is empty!" + '\n');
                }
            } else if (line.startsWith("mark")) {
                int list_index = Integer.parseInt(line.substring(5)) - 1;
                if (list.get(list_index).getIsDone()) {
                    System.out.println('\n' + "Task is originally marked as done." + '\n');
                } else {
                    list.get(list_index).markAsDone();
                    System.out.println('\n' + "Nice! I've marked this task as done:");
                    System.out.println("  [" + list.get(list_index).getStatusIcon() + "] " + list.get(list_index).getDescription() + '\n');
                }
            } else if (line.startsWith("unmark")) {
                int list_index = Integer.parseInt(line.substring(7)) - 1;
                if (!list.get(list_index).getIsDone()) {
                    System.out.println('\n' + "Task is originally marked as not done." + '\n');
                } else {
                    list.get(list_index).markAsUndone();
                    System.out.println('\n' + "OK, I've marked this task as not done yet:");
                    System.out.println("  [" + list.get(list_index).getStatusIcon() + "] " + list.get(list_index).getDescription() + '\n');
                }
            } else {
                System.out.println('\n' + "added: " + line + " to the task list!" + '\n');
                Task temp_task = new Task(line);
                list.add(temp_task);
            }
            line = in.nextLine();
        }
        System.out.println('\n' + "Bye. Hope to see you again soon!");
    }
}
