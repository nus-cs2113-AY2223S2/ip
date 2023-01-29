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
                index = Todo.addForToDo(line, list, index);
            } else if (type.matches("deadline")) {
                index = Deadline.addForDeadline(line, list, index);
            } else if (type.matches("event")) {
                index = Event.addForEvent(line, list, index);
            } else if (line.matches("list") || line.matches("bye") || line.matches("mark \\d") || line.matches("unmark \\d")) {
                Task.instructionLessAdd(line, list, index);
                if (line.equals("bye")) {
                    break;
                }
            }
        }


        System.out.println("    ____________________________________________________________\n");
    }
}


