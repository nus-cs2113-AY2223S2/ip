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

            String[] inputs = action.split(" ", 2);
            String cmd = inputs[0];
            boolean hasDescription = (inputs.length > 1);

            switch (cmd) {
                case "list":

                    listTask(tasks, index);

                    break;
                case "mark":

                    if (hasDescription) {

                        try {

                            markTask(tasks, inputs[1], index);

                        } catch (DukeException e) {

                            System.out.println("       " + e.getMessage());

                        }

                    } else {

                        System.out.println("       ☹ OOPS!!! The task number cannot be empty.");

                    }

                    break;
                case "unmark":

                    if (hasDescription) {

                        try {

                            unmarkTask(tasks, inputs[1], index);

                        } catch (DukeException e) {

                            System.out.println("       " + e.getMessage());

                        }

                    } else {

                        System.out.println("       ☹ OOPS!!! The task number cannot be empty.");

                    }

                    break;
                case "todo":

                    if (hasDescription) {

                        try {

                            index = addTodo(tasks, inputs[1], index);

                        } catch (DukeException e) {

                            System.out.println("       " + e.getMessage());

                        }

                    } else {

                        System.out.println("       ☹ OOPS!!! The description of a todo cannot be empty.");

                    }

                    break;
                case "deadline":

                    if (hasDescription) {

                        try {

                            index = addDeadline(tasks, inputs[1], index);

                        } catch (DukeException e) {

                            System.out.println("       " + e.getMessage());

                        }

                    } else {

                        System.out.println("       ☹ OOPS!!! The description of a deadline cannot be empty.");

                    }

                    break;
                case "event":

                    if (hasDescription) {

                        try {

                            index = addEvent(tasks, inputs[1], index);

                        } catch (DukeException e) {

                            System.out.println("       " + e.getMessage());

                        }

                    } else {

                        System.out.println("       ☹ OOPS!!! The description of an event cannot be empty.");

                    }

                    break;
                default:

                    System.out.println("       ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    break;
            }

            System.out.println(line);
            action = in.nextLine();
        }

        System.out.println(line);
        System.out.println("     " + "Bye. Hope to see you again soon!");
        System.out.println(line);

    }

    private static int addEvent(Task[] tasks, String todoTask, int index) throws DukeException {

        String[] taskAndDeadline = todoTask.split(" /from ");

        if(taskAndDeadline.length < 2) {

            throw new DukeException("The start date/time cannot be empty.");

        }

        String theTask = taskAndDeadline[0];
        String dueBy = taskAndDeadline[1];
        String[] startAndEnd = dueBy.split(" /to ");

        if(startAndEnd.length < 2) {

            throw new DukeException("The end date/time cannot be empty.");

        }

        String start = startAndEnd[0];
        String end = startAndEnd[1];
        tasks[index] = new Event(theTask, start, end);
        index = index + 1;
        System.out.println("     Got it. I've added this task:");
        System.out.println("       [E][ ] " + theTask + " (from: " + start + " to: " + end + ")");
        System.out.println("     Now you have "
                            + index + (index > 1 ? " tasks " : " task ")
                            + "in the list.");
        return index;
    }

    private static int addDeadline(Task[] tasks, String todoTask, int index) throws DukeException {

        String[] taskAndDeadline = todoTask.split(" /by ");

        if(taskAndDeadline.length < 2) {

            throw new DukeException("The due date/time cannot be empty.");

        }

        String theTask = taskAndDeadline[0];
        String dueBy = taskAndDeadline[1];
        tasks[index] = new Deadline(theTask, dueBy);
        index = index + 1;
        System.out.println("     Got it. I've added this task:");
        System.out.println("       [D][ ] " + theTask + " (by: " + dueBy + ")");
        System.out.println("     Now you have "
                            + index + (index > 1 ? " tasks " : " task ")
                            + "in the list.");
        return index;
    }

    private static int addTodo(Task[] tasks, String todoTask, int index) throws DukeException {

        tasks[index] = new Todo(todoTask);
        index = index + 1;
        System.out.println("     Got it. I've added this task:");
        System.out.println("       [T][ ] " + todoTask);
        System.out.println("     Now you have "
                            + index + (index > 1 ? " tasks " : " task ")
                            + "in the list.");
        return index;
    }

    private static void unmarkTask(Task[] tasks, String taskNumber, int index) throws DukeException {

        int ind = Integer.parseInt(taskNumber) - 1;

        if(ind >= index || ind < 0) {
            throw new DukeException("Task " + (ind + 1) + " does not exist.");
        }

        tasks[ind].unmark();
        System.out.println("     OK, I've marked this task as not done yet:\n");
        System.out.println("       "
                            + "[" + tasks[ind].getStatusIcon() + "] "
                            + tasks[ind].getDescription());
    }

    private static void markTask(Task[] tasks, String taskNumber, int index) throws DukeException {

        int ind = Integer.parseInt(taskNumber) - 1;

        if(ind >= index || ind < 0) {
            throw new DukeException("Task " + (ind + 1) + " does not exist.");
        }

        tasks[ind].mark();
        System.out.println("     Nice! I've marked this task as done:\n");
        System.out.println("       "
                            + "[" + tasks[ind].getStatusIcon() + "] "
                            + tasks[ind].getDescription());
    }

    private static void listTask(Task[] tasks, int index) {
        System.out.println("     Here are the tasks in your list:\n");
        for (int i = 0; i < index; i = i + 1) {
            int num = i + 1;
            System.out.println("     " + num
                                + ".[" + tasks[i].getTypeOfTask()
                                + "][" + tasks[i].getStatusIcon()
                                + "] " + tasks[i].getDescription());
        }
    }

}
