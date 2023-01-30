import java.util.Scanner;

public class Inu {
    public static TaskList taskList;
    public static final String MESSAGE_GREETING = " Woof Woof! I'm Inu! Your personal Shib-Assistant ^.^\n";
    public static final String MESSAGE_PROMPT = " What can I do for you today?\n";
    public static final String MESSAGE_FAREWELL = " Bye. Hope to see you again soon ^.^!\n";
    public static final String MESSAGE_LIST_HEADER = " Woof! Here are your current tasks ^.^:\n";

    public static final String MESSAGE_MARK_TASK = " Woof! I've marked this task as completed for you:\n";
    public static final String MESSAGE_UNMARK_TASK = " Woof! I've marked this task as incompleted for you:\n";
    public static final String MESSAGE_DIVIDER = "____________________________________________________________\n";
    public static final String MESSAGE_INVALID = " Sorry! Please type a valid command! ^.^\n";
    public static final int INDEX_MARK = 5;
    public static final int INDEX_UNMARK = 7;
    public static final int INDEX_TODO = 5;
    public static final int INDEX_DEADLINE = 9;
    public static final int INDEX_EVENT = 6;


    public Inu() {

        System.out.println(MESSAGE_DIVIDER + MESSAGE_GREETING);
        System.out.println(MESSAGE_PROMPT + MESSAGE_DIVIDER);

        taskList = new TaskList();
        readCommand();

        System.out.println(MESSAGE_DIVIDER + MESSAGE_FAREWELL + MESSAGE_DIVIDER);

    }

    public void readCommand() {

        String line;
        String checkLine;

        Scanner in = new Scanner(System.in);

        line = in.nextLine();
        checkLine = line.toLowerCase();

        while (!checkLine.equals("bye")) {

            if (checkLine.equals("list")) {

                System.out.println(MESSAGE_DIVIDER + MESSAGE_LIST_HEADER);
                taskList.printList();
                System.out.println("\n" + MESSAGE_DIVIDER);

            } else if (checkLine.startsWith("mark ")) {

                String index = checkLine.substring(INDEX_MARK);
                int taskIndex = Integer.parseInt(index) - 1;
                taskList.getTask(taskIndex).setDone();

                System.out.println(MESSAGE_DIVIDER + MESSAGE_MARK_TASK
                        + taskList.getTask(taskIndex).toString() + "\n" + MESSAGE_DIVIDER);

            } else if (checkLine.startsWith("unmark ")) {

                String index = checkLine.substring(INDEX_UNMARK);
                int taskIndex = Integer.parseInt(index) - 1;
                taskList.getTask(taskIndex).resetDone();

                System.out.println(MESSAGE_DIVIDER + MESSAGE_UNMARK_TASK
                        + taskList.getTask(taskIndex).toString() + "\n" + MESSAGE_DIVIDER);

            } else if (checkLine.startsWith("todo ")) {

                String todo = checkLine.substring(INDEX_TODO);
                taskList.addTask(new Todo(todo));

                System.out.println(MESSAGE_DIVIDER + "added: " + taskList.getLastTask().toString()
                        + '\n' + "Now you have " + taskList.getListIndex() + " tasks in your list." + "\n"
                        + MESSAGE_DIVIDER);

            } else if (checkLine.startsWith("deadline ")) {

                int firstTextBreak = checkLine.indexOf("/");

                String deadLine = checkLine.substring(INDEX_DEADLINE, firstTextBreak);
                String by = checkLine.substring(firstTextBreak + 1);
                taskList.addTask(new DeadLine(deadLine, by));

                System.out.println(MESSAGE_DIVIDER + "added: " + taskList.getLastTask().toString()
                        + '\n' + "Now you have " + taskList.getListIndex() + " tasks in your list." + "\n"
                        + MESSAGE_DIVIDER);

            } else if (checkLine.startsWith("event ")) {

                int firstTextBreak = checkLine.indexOf("/");
                int lastTextBreak = checkLine.lastIndexOf("/");

                String event = checkLine.substring(INDEX_EVENT, firstTextBreak);
                String from = checkLine.substring(firstTextBreak + 1, lastTextBreak);
                String to = checkLine.substring(lastTextBreak + 1);
                taskList.addTask(new Event(event, from, to));

                System.out.println(MESSAGE_DIVIDER + "added: " + taskList.getLastTask().toString()
                        + '\n' + "Now you have " + taskList.getListIndex() + " tasks in your list." + "\n"
                        + MESSAGE_DIVIDER);

            } else {
                System.out.println(MESSAGE_INVALID);
            }

            line = in.nextLine();
            checkLine = line.toLowerCase();

        }

    }

    public static void main(String[] args) {
        new Inu();
    }
}
