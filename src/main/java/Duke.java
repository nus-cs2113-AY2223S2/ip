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
            Task newTask = new Task(line);

            if (line.equals("list") || line.equals("bye") || line.matches("mark \\d") || line.startsWith("mark \\d")) {
                Task.conductInstruction(line, list, index);
                if (line.equals("bye")) {
                    System.out.println("    ____________________________________________________________\n");
                    break;
                }
            } else {
                System.out.println("    " + "added: " + line);
                list[index] = newTask;
                ++index;
            }
            System.out.println("    ____________________________________________________________\n");
        }
    }
}
