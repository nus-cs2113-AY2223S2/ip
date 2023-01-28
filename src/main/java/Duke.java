import java.util.Scanner;
public class Duke {

    public static void main(String[] args) {
        String logo = "    ____________________________________________________________\n" +
                "     Hello! I'm Duke\n" +
                "     What can I do for you?\n" +
                "    ____________________________________________________________\n";

        System.out.println(logo);

        String line = "    ____________________________________________________________\n";

        Task[] tasks = new Task[100];
        Scanner in = new Scanner(System.in);
        String action = in.nextLine();
        int index = 0;
        String bye = "bye";

        while (!action.equals(bye)) {
            System.out.println(line);

            if (action.equals("list")) {
                System.out.println("     Here are the tasks in your list:\n");
                for (int i = 0; i < index; i = i + 1) {
                    int num = i + 1;
                    // System.out.println("     " + num + ":[" + tasks[i].getStatusIcon() + "] " + tasks[i].getDescription());
                    System.out.println("     " + num
                                        + ".[" + tasks[i].getTypeOfTask()
                                        + "][" + tasks[i].getStatusIcon()
                                        + "] " + tasks[i].getDescription());
                }
            } else if (action.startsWith("mark")) {
                String[] inputs = action.split(" ");
                int ind = Integer.parseInt(inputs[1]) - 1;
                tasks[ind].mark();
                System.out.println("     Nice! I've marked this task as done:\n");
                System.out.println("       " + "[" + tasks[ind].getStatusIcon() + "] " + tasks[ind].getDescription());

            } else if (action.startsWith("unmark")) {
                String[] inputs = action.split(" ");
                int ind = Integer.parseInt(inputs[1]) - 1;
                tasks[ind].unmark();
                System.out.println("     OK, I've marked this task as not done yet:\n");
                System.out.println("       " + "[" + tasks[ind].getStatusIcon() + "] " + tasks[ind].getDescription());

            } else if (action.startsWith("todo")) {
                String[] inputs = action.split(" ", 2);
                String todoTask = inputs[1];
                tasks[index] = new Todos(todoTask);
                index = index + 1;
                System.out.println("     Got it. I've added this task:");
                System.out.println("       [T][ ] " + todoTask);
                System.out.println("     Now you have " + index + (index > 1 ? " tasks " : " task ") + "in the list.");

            } else if (action.startsWith("deadline")) {
                String[] inputs = action.split(" ", 2);
                String todoTask = inputs[1];
                String[] taskAndDeadline = todoTask.split(" /by ");
                String theTask = taskAndDeadline[0];
                String dueBy = taskAndDeadline[1];
                tasks[index] = new Deadlines(theTask, dueBy);
                index = index + 1;
                System.out.println("     Got it. I've added this task:");
                System.out.println("       [D][ ] " + theTask + " (by: " + dueBy + ")");
                System.out.println("     Now you have " + index + (index > 1 ? " tasks " : " task ") + "in the list.");

            } else if (action.startsWith("event")) {
                String[] inputs = action.split(" ", 2);
                String todoTask = inputs[1];
                String[] taskAndDeadline = todoTask.split(" /from ");
                String theTask = taskAndDeadline[0];
                String dueBy = taskAndDeadline[1];
                String[] startAndEnd = dueBy.split(" /to ");
                String start = startAndEnd[0];
                String end = startAndEnd[1];
                tasks[index] = new Events(theTask, start, end);
                index = index + 1;
                System.out.println("     Got it. I've added this task:");
                System.out.println("       [E][ ] " + theTask + " (from: " + start + " to: " + end + ")");
                System.out.println("     Now you have " + index + (index > 1 ? " tasks " : " task ") + "in the list.");

            } else {
                tasks[index] = new Task(action);
                index = index + 1;
                System.out.println("     added: " + action);
            }

            System.out.println(line);
            action = in.nextLine();
        }

        System.out.println(line);
        System.out.println("     " + "Bye. Hope to see you again soon!");
        System.out.println(line);

    }

}
