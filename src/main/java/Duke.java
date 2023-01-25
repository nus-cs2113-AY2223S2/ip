import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        //System.out.println("Hello from\n" + logo);
        Scanner in = new Scanner(System.in);
        
        String line = "____________________________________________________________";

        System.out.println(line);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        System.out.println(line);
        
        Task[] tasks = new Task[100];
        int numTasks = 0;

        while (true) {
            System.out.println();
            String cmd = in.nextLine();
            String[] splitIntoArgs = cmd.split(" ");

            if (cmd.equals("bye")) {
                System.out.println(line);
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(line);
                break;
            } else if (cmd.equals("list")) {
                System.out.println(line);
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < numTasks; i++) {
                    System.out.println(Integer.toString(i+1) + "." + tasks[i].getStatusIcon() + 
                        " " + tasks[i].getDescription());
                }
                System.out.println(line);
            } else if (splitIntoArgs[0].equals("mark")){
                int toMark = Integer.parseInt(splitIntoArgs[1]) - 1;
                tasks[toMark].markDone();
                System.out.println(line);
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(tasks[toMark].getStatusIcon() + " " + tasks[toMark].getDescription());
                System.out.println(line);
            } else if (splitIntoArgs[0].equals("unmark")){
                int toUnmark = Integer.parseInt(splitIntoArgs[1]) - 1;
                tasks[toUnmark].unMarkDone();
                System.out.println(line);
                System.out.println("Ok, I've marked this task as not done yet:");
                System.out.println(tasks[toUnmark].getStatusIcon() + " " + tasks[toUnmark].getDescription());
                System.out.println(line);
            } else{
                System.out.println(line);
                System.out.println("added: " + cmd);
                System.out.println(line);
                tasks[numTasks] = new Task(cmd);
                numTasks++;
            }
        }     
    }
}
