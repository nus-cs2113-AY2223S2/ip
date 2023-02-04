
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    static ArrayList<Task> tasksList = new ArrayList<Task>();

    private static void addTasks(Task a) {
        tasksList.add(a);
    }

    private static void printMarking(int i) {
        System.out.println(String.format(" [%s] [%s] %s",
                tasksList.get(i).getTypeIcon(), tasksList.get(i).getStatusIcon(), tasksList.get(i).getDescription()));
    }

    private static void addMark(String remainingWords) {
        System.out.println("You are crushing it,1 task down!");
        Task.markAsDone(tasksList.get(Integer.parseInt(remainingWords) - 1));
        int i = Integer.parseInt(remainingWords) - 1;
        printMarking(i);
    }

    private static void unMark(String remainingWords) {
        System.out.println("I have unchecked it for you");
        Task.markAsNotDone(tasksList.get(Integer.parseInt(remainingWords) - 1));
        int i = Integer.parseInt(remainingWords) - 1;
        printMarking(i);
    }

    private static void printIntro() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello I am Duke your personal life assistant \n" + logo);
        System.out.println("What can I do for you today?");
    }

    private static void processList() {
        if (tasksList.size() == 0) {
            System.out.println("Nothing to do for now!Take a break!");
        } else {
            System.out.println("Here's your plan for a productive day!");
        }
        for (int i = 0; i < tasksList.size(); i++) {
            System.out.println(String.format("%d.[%s] [%s] %s", i + 1,
                    tasksList.get(i).getTypeIcon(), tasksList.get(i).getStatusIcon(), tasksList.get(i).getDescription()));
        }
    }

    public static void main(String[] args) {
        printIntro();
        String line;
        Scanner in = new Scanner(System.in);

        while (true) {
            line = in.nextLine();
            String arr[] = line.split(" ", 2);

            if (line.equals("bye")) {
                System.out.println("Bye see you again!");
                break;
            } else if (line.equals("list")) {
                processList();

            } else if (arr[0].equals("mark")) {
                addMark(arr[1]);

            } else if (arr[0].equals("unmark")) {
                unMark(arr[1]);

            } else if (arr[0].equals("todo")) {
                addTasks(new Todo(arr[1]));
                System.out.println(arr[1]);
                System.out.println("Now you have " + tasksList.size() + " tasks in your list");

            } else if (arr[0].equals("deadline")) {
                String[] parts = arr[1].split("/");
                addTasks(new Deadline(parts[0], parts[1]));
                System.out.println("Added!");
                System.out.println(String.format(" [%s] [%s] %s (%s)", 'D', " ", parts[0], parts[1]));
                System.out.println("Now you have " + tasksList.size() + " tasks in your list");

            } else if (arr[0].equals("event")) {
                String[] parts = arr[1].split("/");
                addTasks(new Event(parts[0], parts[1], parts[2]));
                System.out.println("Added!");
                System.out.println(String.format(" [%s] [%s] %s (%s %s)", 'E', " ", parts[0], parts[1], parts[2]));
                System.out.println("Now you have " + tasksList.size() + " tasks in your list");

            }
        }
    }

}
