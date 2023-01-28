import java.util.Scanner;

public class Mark {
    public static void main(String[] args) {
        Task[] tasks = new Task[100];

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
                    String status = tasks[i].getStatusIcon();
                    String item = tasks[i].description;
                    int ind = i + 1;
                    System.out.println("    " + ind + ".[" + status + "] " + item);
                }
                System.out.println("    ____________________________________________________________");
                line = in.nextLine();
            } else if (line.contains("mark")) {
                String[] words = line.split(" ");
                int ind = Integer.parseInt(words[1]) - 1;
                if (words[0].equals("unmark")) {
                    tasks[ind].setDone(false);
                    System.out.println("    ____________________________________________________________\n    " +
                            "OK, I've marked this task as not done yet: \n    [ ] " +
                            tasks[ind].description +
                            "\n    ____________________________________________________________");
                } else {
                    tasks[ind].setDone(true);
                    System.out.println("    ____________________________________________________________\n    " +
                            "Nice! I've marked this task as done: \n    [X] " +
                            tasks[ind].description +
                            "\n    ____________________________________________________________");
                }
                line = in.nextLine();
            } else {
                System.out.println("    ____________________________________________________________\n    " +
                        "You have added the following task: \n    " +
                        line +
                        "\n    ____________________________________________________________");
                tasks[total] = new Task(line, "T");
                line = in.nextLine();
                total++;
            }
        }

        System.out.println("    ____________________________________________________________\n" +
                "     Bye. Hope to see you again soon!\n" +
                "    ____________________________________________________________");
    }
}
