import java.util.Scanner;

public class Inu {
    public static TaskList taskList;
    public static final int INDEX_MARK = 5;
    public static final int INDEX_UNMARK = 7;
    public static final int INDEX_TODO = 5;
    public static final int INDEX_DEADLINE = 9;
    public static final int INDEX_EVENT = 6;


    public Inu() {

        Ui.printGreeting();
        readCommand();
        Ui.printFarewell();

    }

    public void readCommand() {

        String line;
        String checkLine;

        taskList = new TaskList();

        Scanner in = new Scanner(System.in);

        line = in.nextLine();
        checkLine = line.toLowerCase();

        while (!checkLine.equals("bye")) {

            if (checkLine.equals("list")) {

                Ui.printTaskList(taskList);

            } else if (checkLine.startsWith("mark ")) {

                String index = checkLine.substring(INDEX_MARK);
                int taskIndex = Integer.parseInt(index) - 1;
                taskList.getTask(taskIndex).setDone();

                Ui.printMessage(Messages.MESSAGE_MARK_TASK + "\n" + taskList.getTask(taskIndex).toString());

            } else if (checkLine.startsWith("unmark ")) {

                String index = checkLine.substring(INDEX_UNMARK);
                int taskIndex = Integer.parseInt(index) - 1;
                taskList.getTask(taskIndex).resetDone();

                Ui.printMessage(Messages.MESSAGE_UNMARK_TASK + "\n" + taskList.getTask(taskIndex).toString());

            } else if (checkLine.startsWith("todo ")) {

                String todo = checkLine.substring(INDEX_TODO);
                taskList.addTask(new Todo(todo));
                Ui.printMessage("added: " + taskList.getLastTask().toString() + "\n"
                        + "Now you have " + taskList.getListIndex() + " tasks in your list.");

            } else if (checkLine.startsWith("deadline ")) {

                int firstTextBreak = checkLine.indexOf("/");

                String deadLine = checkLine.substring(INDEX_DEADLINE, firstTextBreak);
                String by = checkLine.substring(firstTextBreak + 1);
                taskList.addTask(new DeadLine(deadLine, by));

                Ui.printMessage("added: " + taskList.getLastTask().toString() + "\n"
                        + "Now you have " + taskList.getListIndex() + " tasks in your list.");

            } else if (checkLine.startsWith("event ")) {

                int firstTextBreak = checkLine.indexOf("/");
                int lastTextBreak = checkLine.lastIndexOf("/");

                String event = checkLine.substring(INDEX_EVENT, firstTextBreak);
                String from = checkLine.substring(firstTextBreak + 1, lastTextBreak);
                String to = checkLine.substring(lastTextBreak + 1);
                taskList.addTask(new Event(event, from, to));

                Ui.printMessage("added: " + taskList.getLastTask().toString() + "\n"
                        + "Now you have " + taskList.getListIndex() + " tasks in your list.");

            } else {
                Ui.printMessage(Messages.MESSAGE_INVALID);
            }

            line = in.nextLine();
            checkLine = line.toLowerCase();

        }

    }

    public static void main(String[] args) {
        new Inu();
    }
}
