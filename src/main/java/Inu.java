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

    public int fetchMarkIndex(String userString) {
        final int INDEX_OFFSET = 1;
        String index = userString.substring(INDEX_MARK);
        return Integer.parseInt(index) - 1;
    }

    public int fetchUnMarkIndex(String userString) {
        final int INDEX_OFFSET = 1;
        String index = userString.substring(INDEX_UNMARK);
        return Integer.parseInt(index) - 1;
    }

    public void markTask(int taskIndex) {
        taskList.getTask(taskIndex).setDone();
    }

    public void unMarkTask(int taskIndex) {
        taskList.getTask(taskIndex).resetDone();
    }

    public String fetchToDo(int indexOfStartOfToDo, String userString) {
        return userString.substring(indexOfStartOfToDo);
    }

    public String[] fetchDeadLine(
            int indexOfStartOfToDo, int indexOfStartOfDeadLine, String userString) {
        final int INDEX_OFFSET = 1;
        final int INDEX_TODO_INFORMATION = 0;
        final int INDEX_DEADLINE_INFORMATION = 1;
        String[] deadLine = new String[2];
        deadLine[INDEX_TODO_INFORMATION] = userString.substring(indexOfStartOfToDo, indexOfStartOfDeadLine);
        deadLine[INDEX_DEADLINE_INFORMATION] = userString.substring(indexOfStartOfDeadLine + INDEX_OFFSET);
        return deadLine;
    }

    public String[] fetchEvent(
            int indexOfStartOfToDo, int indexOfStartOfFrom, int indexOfStartOfTo, String userString) {
        final int INDEX_OFFSET = 1;
        final int INDEX_TODO_INFORMATION = 0;
        final int INDEX_EVENT_FROM_INFORMATION = 1;
        final int INDEX_EVENT_TO_INFORMATION = 2;
        String[] event = new String[3];
        event[INDEX_TODO_INFORMATION] = userString.substring(indexOfStartOfToDo, indexOfStartOfFrom);
        event[INDEX_EVENT_FROM_INFORMATION] = userString.substring(indexOfStartOfFrom + INDEX_OFFSET, indexOfStartOfTo);
        event[INDEX_EVENT_TO_INFORMATION] = userString.substring(indexOfStartOfTo + INDEX_OFFSET);
        return event;
    }

    public void readCommand() {

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

                int taskIndex = fetchMarkIndex(checkLine);
                markTask(taskIndex);
                Ui.printMessage(Messages.MESSAGE_MARK_TASK + "\n" + taskList.getTask(taskIndex).toString());

            } else if (checkLine.startsWith("unmark ")) {

                int taskIndex = fetchUnMarkIndex(checkLine);
                unMarkTask(taskIndex);
                Ui.printMessage(Messages.MESSAGE_UNMARK_TASK + "\n" + taskList.getTask(taskIndex).toString());

            } else if (checkLine.startsWith("todo ")) {

                String todo = fetchToDo(INDEX_TODO, checkLine);
                Todo todoTask = new Todo(todo);
                taskList.addTask(todoTask);
                Ui.printMessage("added: " + taskList.getLastTask().toString() + "\n"
                        + "Now you have " + taskList.getListIndex() + " tasks in your list.");

            } else if (checkLine.startsWith("deadline ")) {

                int byIndex = checkLine.indexOf("/");
                String[] deadLine = fetchDeadLine(INDEX_DEADLINE, byIndex, checkLine);
                DeadLine deadLineTask = new DeadLine(deadLine[0], deadLine[1]);
                taskList.addTask(deadLineTask);

                Ui.printMessage("added: " + taskList.getLastTask().toString() + "\n"
                        + "Now you have " + taskList.getListIndex() + " tasks in your list.");

            } else if (checkLine.startsWith("event ")) {

                int fromIndex = checkLine.indexOf("/");
                int toIndex = checkLine.lastIndexOf("/");
                String[] event = fetchEvent(INDEX_EVENT, fromIndex, toIndex, checkLine);
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
