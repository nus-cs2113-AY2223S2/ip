
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    static ArrayList<Task> list = new ArrayList<Task>();

    private static void addTasks(Task a) {
        list.add(a);
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello I am Baymax your personal life assistant \n" + logo);
        System.out.println("What can I do for you today?");
        String line;
        Scanner in = new Scanner(System.in);


        while (true) {
            line = in.nextLine();
            String arr[] = line.split(" ", 2);
            if (line.equals("bye")) {
                System.out.println("Bye see you again!");
                break;
            } else if (line.equals("list")) {
                if (list.size() == 0) {
                    System.out.println("Nothing to do for now!Take a break!");
                } else {
                    System.out.println("Here's your plan for a productive day!");
                }
                for (int i = 0; i < list.size(); i++) {
                    System.out.println(String.format("%d.[%s] [%s] %s", i + 1,
                            list.get(i).getTypeIcon(), list.get(i).getStatusIcon(), list.get(i).getDescription()));
                }
            } else if (arr[0].equals("mark")) {
                System.out.println("You are crushing it,1 task down!");
                Task.markAsDone(list.get(Integer.parseInt(arr[1]) - 1));
                int i = Integer.parseInt(arr[1]) - 1;
                System.out.println(String.format(" [%s] [%s] %s",
                        list.get(i).getTypeIcon(), list.get(i).getStatusIcon(), list.get(i).getDescription()));

            } else if (arr[0].equals("unmark")) {
                System.out.println("I have unchecked it for you");
                Task.markAsNotDone(list.get(Integer.parseInt(arr[1]) - 1));
                int i = Integer.parseInt(arr[1]) - 1;
                System.out.println(String.format(" [%s] [%s] %s",
                        list.get(i).getTypeIcon(), list.get(i).getStatusIcon(), list.get(i).getDescription()));

            } else if (arr[0].equals("todo")) {
                addTasks(new Todo(arr[1]));
                System.out.println("Added!");
                System.out.println(String.format(" [%s] [%s] %s", 'T', " ", arr[1]));
                System.out.println("Now you have " + list.size() + " tasks in your list");
            } else if (arr[0].equals("deadline")) {
                String[] parts = arr[1].split("/");
                addTasks(new Deadline(parts[0], parts[1]));
                System.out.println("Added!");
                System.out.println(String.format(" [%s] [%s] %s (%s)", 'D', " ", parts[0], parts[1]));
                System.out.println("Now you have " + list.size() + " tasks in your list");
            } else if (arr[0].equals("event")) {
                String[] parts = arr[1].split("/");
                addTasks(new Event(parts[0], parts[1], parts[2]));
                System.out.println("Added!");
                System.out.println(String.format(" [%s] [%s] %s (%s %s)", 'E', " ", parts[0], parts[1], parts[2]));
                System.out.println("Now you have " + list.size() + " tasks in your list");

            }


        }


    }


}
