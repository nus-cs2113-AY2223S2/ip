import java.util.Scanner;

public class Mark {
    public static void main(String[] args) {
        Task[] list = new Task[100];

        String greet = "    ____________________________________________________________\n" +
                "     Hello! I'm Duke\n" +
                "     What can I do for you?\n" +
                "    ____________________________________________________________";
        System.out.println(greet);
        String line;
        int total = 0;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        while (!line.equals("bye")) {
            if (line.equals("list")) {
                System.out.println("    ____________________________________________________________\n    Here are the tasks in your list:");
                for (int i = 0; i < total; i++) {
                    String status = list[i].getStatusIcon();
                    String item = list[i].description;
                    int ind = i + 1;
                    System.out.println("    " + ind + ".[" + status + "] " + item);
                }
                System.out.println("    ____________________________________________________________");
                line = in.nextLine();
                continue;
            } else if (line.contains("mark")) {
                String[] words = line.split(" ");
                int ind = Integer.parseInt(words[1]) - 1;
                if (words[0].equals("unmark")) {
                    list[ind].setStatus(false);
                    System.out.println("    ____________________________________________________________\n    " +
                            "OK, I've marked this task as not done yet: \n    [ ] " +
                            list[ind].description +
                            "\n    ____________________________________________________________");
                } else {
                    list[ind].setStatus(true);
                    System.out.println("    ____________________________________________________________\n    " +
                            "Nice! I've marked this task as done: \n    [X] " +
                            list[ind].description +
                            "\n    ____________________________________________________________");
                }
                line = in.nextLine();
                continue;
            } else {
                System.out.println("    ____________________________________________________________\n    You have added the following task: \n    " +
                        line +
                        "\n    ____________________________________________________________");
                list[total] = new Task(line);
                line = in.nextLine();
                total++;
            }
        }

        System.out.println("    ____________________________________________________________\n" +
                "     Bye. Hope to see you again soon!\n" +
                "    ____________________________________________________________");
    }
}
