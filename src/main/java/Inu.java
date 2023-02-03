import java.util.Scanner;

public class Inu {
    public static TaskList taskList;
    public static final int INDEX_MARK = 5;
    public static final int INDEX_UNMARK = 7;
    public static final int INDEX_TODO = 5;
    public static final int INDEX_DEADLINE = 9;
    public static final int INDEX_EVENT = 6;
    public final int INDEX_OFFSET_IN_COMMAND = 1;


    public Inu() {

        Ui.printGreeting();
        while
        //readCommand();
        parseCommand();
        Ui.printFarewell();

    }

    public String readCommand() {
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }

    public void parseCommand() {

        String line;
        String checkLine;

        taskList = new TaskList();

        Scanner input = new Scanner(System.in);

        line = input.nextLine();
        checkLine = line.toLowerCase();

        while (!checkLine.equals("bye")) {

            if (checkLine.equals("list")) {

                Ui.printTaskList(taskList);

            } else if (checkLine.startsWith("mark ")) {

                int taskIndex = Util.fetchMarkIndex(line);
                Util.markTask(taskList, taskIndex);
                Ui.printMessage(Messages.MESSAGE_MARK_TASK + "\n" + taskList.getTask(taskIndex).toString());

            } else if (checkLine.startsWith("unmark ")) {

                int taskIndex = Util.fetchUnMarkIndex(line);
                Util.unMarkTask(taskList, taskIndex);
                Ui.printMessage(Messages.MESSAGE_UNMARK_TASK + "\n" + taskList.getTask(taskIndex).toString());

            } else if (checkLine.startsWith("todo ")) {

                String todo = Util.fetchToDo(line);
                Todo todoTask = new Todo(todo);
                taskList.addTask(todoTask);
                Ui.printMessage("added: " + taskList.getLastTask().toString() + "\n"
                        + "Now you have " + taskList.getListIndex() + " tasks in your list.");

            } else if (checkLine.startsWith("deadline ")) {

                int byIndex = checkLine.indexOf("/");
                String[] deadLine = Util.fetchDeadLine(line, byIndex);
                DeadLine deadLineTask = new DeadLine(deadLine[0], deadLine[1]);
                taskList.addTask(deadLineTask);

                Ui.printMessage("added: " + taskList.getLastTask().toString() + "\n"
                        + "Now you have " + taskList.getListIndex() + " tasks in your list.");

            } else if (checkLine.startsWith("event ")) {

                int fromIndex = checkLine.indexOf("/");
                int toIndex = checkLine.lastIndexOf("/");
                String[] event = Util.fetchEvent(line, fromIndex, toIndex);
                Event eventTask = new Event(event[0], event[1], event[2]);
                taskList.addTask(eventTask);

                Ui.printMessage("added: " + taskList.getLastTask().toString() + "\n"
                        + "Now you have " + taskList.getListIndex() + " tasks in your list.");

            } else {
                Ui.printMessage(Messages.MESSAGE_INVALID);
            }

            line = input.nextLine();
            checkLine = line.toLowerCase();

        }

    }

    public static void main(String[] args) {
        new Inu();
    }
}
