import java.util.Scanner;

public class Inu {

    private Task[] todoList;
    public static final String MESSAGE_GREETING = " Woof Woof! I'm Inu! Your personal Shib-Assistant ^.^\n";
    public static final String MESSAGE_PROMPT = " What can I do for you today?\n";
    public static final String MESSAGE_FAREWELL = " Bye. Hope to see you again soon ^.^!\n";
    public static final String MESSAGE_LIST_HEADER = " Woof! Here are your current tasks ^.^:\n";
    public static final String MESSAGE_MARK_TASK = " Woof! I've marked this task as completed for you:\n";
    public static final String MESSAGE_UNMARK_TASK = " Woof! I've marked this task as incompleted for you:\n";
    public static final String MESSAGE_DIVIDER = "____________________________________________________________\n";

    public Inu() {

        System.out.println(MESSAGE_DIVIDER + MESSAGE_GREETING);
        System.out.println(MESSAGE_PROMPT + MESSAGE_DIVIDER);

        setTodoList();
        readCommand();

        System.out.println(MESSAGE_DIVIDER + MESSAGE_FAREWELL + MESSAGE_DIVIDER);

    }

    public void getTodoList(int listIndex) {

        for (int i = 0; i < listIndex; ++i) {

            System.out.println((i + 1) + ". " + "[" + todoList[i].getStatusIcon() + "] "
                    + todoList[i].getDescription());

        }

    }

    public void setTodoList() {

        this.todoList = new Task[100];

    }

    public void readCommand() {

        String line;
        String checkLine;

        int listIndex = 0;

        Scanner in = new Scanner(System.in);

        line = in.nextLine();
        checkLine = line.toLowerCase();

        while (!checkLine.equals("bye")) {

            if (checkLine.equals("list")) {

                System.out.println(MESSAGE_DIVIDER + MESSAGE_LIST_HEADER);
                getTodoList(listIndex);
                System.out.println("\n" + MESSAGE_DIVIDER);

            } else if (checkLine.startsWith("mark ")) {

                // This ensures that the mark command is read correctly only if an index follows it

                String index = checkLine.substring(5, checkLine.length());
                int taskIndex = Integer.parseInt(index) - 1;
                todoList[taskIndex].setDone();

                System.out.println(MESSAGE_DIVIDER + MESSAGE_MARK_TASK + "[" + todoList[taskIndex].getStatusIcon()
                        + "] " + todoList[taskIndex].getDescription() + "\n" + MESSAGE_DIVIDER);

            } else if (checkLine.startsWith("unmark ")) {

                String index = checkLine.substring(7, checkLine.length());
                int taskIndex = Integer.parseInt(index) - 1;
                todoList[taskIndex].resetDone();

                System.out.println(MESSAGE_DIVIDER + MESSAGE_UNMARK_TASK + "[" + todoList[taskIndex].getStatusIcon()
                        + "] " + todoList[taskIndex].getDescription() + "\n" + MESSAGE_DIVIDER);

            } else {

                Task task = new Task(line);
                todoList[listIndex] = task;
                ++listIndex;

                System.out.println(MESSAGE_DIVIDER + "added: " + task.getDescription()
                        + '\n' + MESSAGE_DIVIDER);

            }

            line = in.nextLine();
            checkLine = line.toLowerCase();

        }

    }

    public static void main(String[] args) {
        new Inu();
    }
}
