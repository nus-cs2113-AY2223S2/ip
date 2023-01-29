
import java.util.Scanner;

public class Duke {


    public static void main(String[] args) {
        String divider = "\t____________________________________________________________";
        Scanner in = new Scanner(System.in);
       // ArrayList<Tasks> taskList = new ArrayList<>();
        String line;
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.print(divider + '\n'
                + " \t Hello! I'm Duke\n\t What can I do for you?\n" + divider + "\n\n");
        line = in.nextLine(); //first input
        while (!line.equals("bye")) {
            String[] userInput = line.split(" ", 2);
            String command = userInput[0];
            switch (command) {
            case "mark":
                Tasks markTask =  Tasks.getTaskList().get(Integer.parseInt(userInput[1]) - 1);
                markTask.setMarked(true);
                System.out.println(divider + "\n\t Nice! I've marked this task as done:\n\t  " +
                        "[X] " +  markTask.getItem());
                System.out.println(divider);
                break;
            case "unmark":
                Tasks unMarkTask = Tasks.getTaskList().get(Integer.parseInt(userInput[1]) - 1);
                unMarkTask.setMarked(false);
                System.out.println(divider + "\n\t Nice! I've marked this task as not done yet:\n\t  " +
                        "[ ] " +  unMarkTask.getItem());
                System.out.println(divider);
                break;
            case "list":
                int timer = Tasks.getNumberOfTasks();
                int num = 1;
                System.out.println(divider + "\n\t Here are the tasks in your list:");
                while (num <= timer) {
                    Tasks thisTask = Tasks.getTaskList().get(num - 1);
                    if (thisTask.isMarked()) {
                        System.out.println("\t " + Integer.toString(num) + ".[X] " + thisTask.getItem());
                    } else {
                        System.out.println("\t " + Integer.toString(num) + ".[ ] " + thisTask.getItem());
                    }
                    ++num;
                }
                System.out.println(divider);
                break;
            default:
                Tasks.addToList(new Tasks(line, false));
            }
            line = in.nextLine(); //new user input
        }
        System.out.println(divider + "\n\t Bye. Hope to see you again soon!\n" + divider);
    }
}
