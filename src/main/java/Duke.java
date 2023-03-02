import java.util.Scanner;
public class Duke {
    public static void printDash() {
        System.out.println("____________________________________________________________");
    }

    public static void listTask() {
        printDash();
        System.out.println("Tasks in list:");
        int k;
        for (k = 1; Task.taskNum >= k; k += 1) {
            System.out.println(k + "." + Task.tasks[k - 1]);
        }
        printDash();
    }
    public static void unmarkTask(String[] list) {
        try {
            int pt;
            pt = Integer.parseInt(list[1]);
            Task command;
            command = Task.tasks[pt - 1];
            command.markNotDone();
            printDash();
            System.out.println("Marking task as undone:\n" + command);
            printDash();
        } catch (NullPointerException err) {
            printDash();
            System.out.println("☹ OOPS!!! Not enough tasks here. Try again.");
            printDash();
        } catch (IndexOutOfBoundsException err) {
            printDash();
            System.out.println("☹ OOPS!!! Task number is missing. Try again.");
            printDash();
        } catch (NumberFormatException err) {
            printDash();
            System.out.println("☹ OOPS!!! Invalid task number. Try again.");
            printDash();
        }
    }

    public static void markTask(String[] list) {
        try {
            int pt;
            pt = Integer.parseInt(list[1]);
            Task command;
            command = Task.tasks[pt - 1];
            command.markAsDone();
            printDash();
            System.out.println("Marking task as done:\n" + command);
            printDash();
        } catch (NullPointerException err) {
            printDash();
            System.out.println("☹ OOPS!!! Not enough tasks here. Try again.");
            printDash();
        } catch (IndexOutOfBoundsException err) {
            printDash();
            System.out.println("☹ OOPS!!! Task number is missing. Try again.");
            printDash();
        } catch (NumberFormatException err) {
            printDash();
            System.out.println("☹ OOPS!!! Invalid task number. Try again.");
            printDash();
        }
    }
    public static void makeDeadline(String[] list) {
        try {
            String[] due;
            due = list[1].split("/by ", 2);
            Task command;
            command = new Deadline(due[0], due[1]);
            Task.tasks[Task.taskNum] = command;
            Task.taskNum += 1;
            printDash();
            System.out.println("Adding this task:\n" + command);
            System.out.println("You currently have " + Task.taskNum + " task(s)");
            printDash();
        } catch (IndexOutOfBoundsException err) {
            printDash();
            System.out.println("☹ OOPS!!! Due date of deadline or description cannot be empty. Try again.\nFollow the following format: deadline [description] /by [due date]");
            printDash();
        }
    }

    public static void makeToDo(String[] list) {
        try {
            Task command;
            command = new Todo(list[1]);
            Task.tasks[Task.taskNum] = command;
            Task.taskNum += 1;
            printDash();
            System.out.println("Adding this task:\n" + command);
            System.out.println("You currently have " + Task.taskNum + " task(s)");
            printDash();
        } catch (IndexOutOfBoundsException err) {
            printDash();
            System.out.println("☹ OOPS!!! Description of a to do cannot be empty. Try again.");
            printDash();
        }
    }
    public static void makeEvent(String[] list) {
        try {
            String[] beg;
            beg = list[1].split("/from ", 2);
            String[] end;
            end = beg[1].split("/to ", 2);
            Task command = new Event(end[0], end[1], beg[0]);
            Task.tasks[Task.taskNum] = command;
            Task.taskNum += 1;
            printDash();
            System.out.println("Adding this task:\n" + command);
            System.out.println("You currently have " + Task.taskNum + " task(s)");
            printDash();
        } catch (IndexOutOfBoundsException err) {
            printDash();
            System.out.println("☹ OOPS!!! Date of event or description cannot be empty. Try again.\nFollow the following format: event [description] /from [start] /to [end]");
            printDash();
        }
    }
    public static void main(String[] args) {
        printDash();
        System.out.println("Hola! I'm Duke");
        System.out.println("Let me know your tasks for the day!");
        printDash();

        Scanner in = new Scanner(System.in);

        String cmd;
        cmd = in .nextLine();

        while (!(cmd.equals("bye"))) {
            if (cmd.equals("list")) {
                listTask();
            } else {
                String[] list = cmd.split(" ", 2);

                if (list[0].equals("mark")) {
                    markTask(list);
                } else if (list[0].equals("unmark")) {
                    unmarkTask(list);
                } else if (list[0].equals("todo")) {
                    makeToDo(list);
                } else if (list[0].equals("event")) {
                    makeEvent(list);
                } else if (list[0].equals("deadline")) {
                    makeDeadline(list);
                } else {
                    printDash();
                    System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    printDash();
                }
            }
            cmd = in.nextLine();
        }

        // exit
        printDash();
        System.out.println("Bye, cya soon!");
        printDash();
    }
}