import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        String command;
        Scanner in = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        boolean isRunning = true;

        while (isRunning) {
            command = in.nextLine();

            if (command.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                isRunning = false;
            }

            else if (command.equals("list")) {
                //list down stuff
                int count = 1;
                System.out.println("Here are the tasks in your list:");
                for (Task task: tasks) {


                    System.out.println(count + ".[" + task.getStatusIcon() + "] " + task.description);
                    count += 1;
                }
            }

            else if (command.matches("mark \\d+")) {

               // char n = command.charAt(5)p
                int n = Character.getNumericValue(command.charAt(5)); //convert to char than get numeric value
                Task m = tasks.get(n-1);
                m.markAsDone();

                System.out.println("Nice! I've marked this task as done:");
                //print the task
                System.out.println("[" + m.getStatusIcon() + "] " + m.description);

            }

            else if (command.matches("unmark \\d+")) {
                int n = Character.getNumericValue(command.charAt(7)); //convert to char than get numeric value
                Task m = tasks.get(n-1);
                m.unmarkAsDone();

                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("[" + m.getStatusIcon() + "] " + m.description);

            }

            else {
                Task t = new Task(command); //make new task
                tasks.add(t); //add task to list with description = t, isDone = false
                System.out.println("added: " + t.description);
            }


        }


        //if string == list: print in loop starting from 1
        //if string has "mark": find the number in the string (String[5]) and then get(index) from AL
        //but what if we have something like "mark A?"
        //if string has "unmark": find the number in the string (String[5]) and then get(index) from AL to remove it





    }
}
