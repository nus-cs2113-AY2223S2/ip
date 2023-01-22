
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
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
        ArrayList<Task> list = new ArrayList<Task>();
        while (true) {
            line = in.nextLine();
            String arr[] = line.split(" ", 2);
            if (line.equals("bye")) {
                System.out.println("Bye see you again!");
                break;
            } else if (line.equals("list")) {
                System.out.println("Here's your plan for a productive day!");
                for (int i = 0; i < list.size(); i++) {
                    System.out.println(String.format("%d.[%s] %s", i + 1, list.get(i).getStatusIcon(), list.get(i).getDescription()));
                }
            } else if (arr[0].equals("mark")) {
                System.out.println("You are crushing it,1 task down!");
                Task.markAsDone(list.get(Integer.parseInt(arr[1]) - 1));
                int i = Integer.parseInt(arr[1]) - 1;
                System.out.println(String.format(" [%s] %s", list.get(i).getStatusIcon(), list.get(i).getDescription()));

            } else if (arr[0].equals("unmark")) {
                System.out.println("I have unchecked it for you");
                Task.markAsNotDone(list.get(Integer.parseInt(arr[1]) - 1));
                int i = Integer.parseInt(arr[1]) - 1;
                System.out.println(String.format(" [%s] %s", list.get(i).getStatusIcon(), list.get(i).getDescription()));

            } else {
                System.out.println("added: " + line);
                Task task = new Task(line);
                list.add(task);
            }


        }


    }


}
