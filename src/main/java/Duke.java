import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        String logo = "     ____        _        \n"
                + "    |  _ \\ _   _| | _____ \n"
                + "    | | | | | | | |/ / _ \\\n"
                + "    | |_| | |_| |   <  __/\n"
                + "    |____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String greet = "    ____________________________________________________________\n"
                + "     Hello! I'm Duke \n"
                + "     What can I do for you? \n"
                + "    ____________________________________________________________\n";
        System.out.println(greet);

        String line;
        Task[] list = new Task[100];
        int index = 0;

        while (true) {
            Scanner in = new Scanner(System.in);
            line = in.nextLine();
            String[] splitLine = line.split(" ", 2);
            String type = splitLine[0];

            if (type.matches("todo")) {
                System.out.print("    ____________________________________________________________\n");
                System.out.println("    Got it. I've added this task:");
                Todo newTask = new Todo(line);
                System.out.println("      " + newTask.label + newTask.getStatusIcon() + " " + newTask.description);
                list[index] = newTask;
                ++index;
                System.out.println("    Now you have " + index + " tasks in the list.");
                System.out.println("    ____________________________________________________________\n");
            } else if (type.matches("deadline")) {
                System.out.print("    ____________________________________________________________\n");
                System.out.println("    Got it. I've added this task:");
                Deadline newTask = new Deadline(line);
                System.out.println("      " + newTask.label + newTask.getStatusIcon() + " " + newTask.description + " " + newTask.whenDue);
                list[index] = newTask;
                ++index;
                System.out.println("    Now you have " + index + " tasks in the list.");
                System.out.println("    ____________________________________________________________\n");
            } else if (type.matches("event")) {
                System.out.print("    ____________________________________________________________\n");
                System.out.println("    Got it. I've added this task:");
                Event newTask = new Event(line);
                System.out.println("      "+ newTask.label + newTask.getStatusIcon() + " " + newTask.description + " " + newTask.start + newTask.end);
                list[index] = newTask;
                ++index;
                System.out.println("    Now you have " + index + " tasks in the list.");
                System.out.println("    ____________________________________________________________\n");
            } else if (line.matches("list") || line.matches("bye") || line.matches("mark \\d") || line.matches("unmark \\d")) {
                Task.conductInstruction(line, list, index);
                if (line.equals("bye")) {
                    break;
                }
            }
        }


        System.out.println("    ____________________________________________________________\n");
    }
}


