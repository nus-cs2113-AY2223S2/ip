import java.util.Scanner;

public class Todolist {

    public static void printAddedTask(Task task, int total) {
        total ++;
        System.out.println("    ____________________________________________________________\n" +
                "    Got it. I've added this task:\n      " +
                task.toString() +
                "\n    Now you have " + total + " tasks in the list.\n" +
                "    ____________________________________________________________");
    }
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
                //list all tasks
                System.out.println("    ____________________________________________________________\n    " +
                        "Here are the tasks in your list:");
                for (int i = 1; i <= total; i++) {
                    System.out.println("    " + i + "." + tasks[i-1].toString());
                }
                System.out.println("    ____________________________________________________________");
                line = in.nextLine();
            }
            String[] words = line.split(" ", 2);
            if (words[0].contains("mark")) {
                //mark or unmark
                int ind = Integer.parseInt(words[1]) - 1;
                if (words[0].equals("unmark")) {
                    tasks[ind].setDone(false);
                    System.out.println("    ____________________________________________________________\n    " +
                            "OK, I've marked this task as not done yet: \n    " +
                            tasks[ind].toString() +
                            "\n    ____________________________________________________________");
                } else {
                    tasks[ind].setDone(true);
                    System.out.println("    ____________________________________________________________\n    " +
                            "Nice! I've marked this task as done: \n    " +
                            tasks[ind].toString() +
                            "\n    ____________________________________________________________");
                }
            } else {
                if (words[0].equals("todo")) {
                    tasks[total] = new Task(words[1], "T");
                } else if (words[0].equals("deadline")) {
                    String[] terms = words[1].split(" /by ");
                    tasks[total] = new Deadline(terms[0], "D", terms[1]);
                } else {
                    String[] name = words[1].split(" /from ");
                    String[] from = name[1].split(" /to ");
                    tasks[total] = new Event(name[0], "E", from[0], from[1]);
                }
                printAddedTask(tasks[total], total);
                total++;
            }
            line = in.nextLine();
        }

        System.out.println("    ____________________________________________________________\n" +
                "     Bye. Hope to see you again soon!\n" +
                "    ____________________________________________________________");
    }
}
