import java.util.Scanner;
public class Duke {
    public static void printDash() {
        System.out.println("____________________________________________________________");
    }
    public static void listTask(int num, Task[] todos) {
        printDash();
        System.out.println("Tasks in list:");
        int k;
        for (k = 1; num >= k; k += 1) {
            System.out.println(k + "." + todos[k - 1]);
        }
        printDash();
    }

    public static void addTask(String cmd, Task[] todos, int num) {
        Task command;
        command = new Task(cmd);
        todos[num] = command;
        printDash();
        System.out.println("added: " + command.description);
        printDash();
    }

    public static void unmarkTask(int k,Task[] todos, String[] list) {
        int pt;
        pt = Integer.parseInt(list[k]);
        Task command;
        command = todos[pt - 1];
        command.markNotDone();
        printDash();
        System.out.println("Marking task as undone:\n" + command);
        printDash();
    }

    public static void markTask(int k, Task[] todos, String[] list) {
        int pt;
        pt = Integer.parseInt(list[k]);
        Task command;
        command = todos[pt - 1];
        command.markAsDone();
        printDash();
        System.out.println("Marking task as done:\n" + command);
        printDash();
    }
    public static void makeDeadline(String[] list, Task[] todos, int num, int k, int j) {
        Task command;
        command = new Deadline(list[k], list[j]);
        todos[num] = command;
        num += 1;
        printDash();
        System.out.println("Adding this task:\n" + command);
        System.out.println("You currently have " + num + " task(s)");
        printDash();
    }

    public static void makeToDo(Task[] todos, int num, String[] list, int k) {
        Task command;
        command = new Todo(list[k]);
        todos[num] = command;
        num += 1;
        printDash();
        System.out.println("Adding this task:\n" + command);
        System.out.println("You currently have " + num + " task(s)");
        printDash();
    }
    public static void makeEvent(Task[] todos, String[] beg, String[] end, int num, int k, int j, int l) {
        Task command = new Event(beg[k], end[j], end[l]);
        todos[num] = command;
        num += 1;
        printDash();
        System.out.println("Adding this task:\n" + command);
        System.out.println("You currently have " + num + " task(s)");
        printDash();
    }
    public static void main(String[] args) {
        printDash();
        System.out.println("Hola! I'm Duke");
        System.out.println("Let me know your tasks for the day!");
        printDash();

        Scanner in = new Scanner(System.in);

        int num;
        num = 0;

        String cmd;
        cmd = in .nextLine();

        Task[] todos;
        todos = new Task[100];

        while (!(cmd.equals("bye"))) {
            if (cmd.equals("list")) {
                listTask(num, todos);
            } else {
                String[] list = cmd.split(" ", 2);

                if (list[0].equals("mark")) {
                    markTask(1, todos, list);
                } else if (list[0].equals("unmark")) {
                    unmarkTask(1, todos, list);
                } else if (list[0].equals("todo")) {
                    makeToDo(todos, num, list, 1);
                    num += 1;
                } else if (list[0].equals("event")) {
                    String[] beg;
                    beg = list[1].split("/from ", 2);
                    String[] end;
                    end = beg[1].split("/to ", 2);
                    makeEvent(todos, beg, end, num,0, 0, 1);
                    num += 1;
                } else if (list[0].equals("deadline")) {
                    String[] due;
                    due = list[1].split("/by ", 2);
                    makeDeadline(due, todos, num, 0, 1);
                    num += 1;
                } else {
                    addTask(cmd, todos, num);
                    num += 1;
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