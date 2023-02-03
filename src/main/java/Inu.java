import java.util.Scanner;

public class Inu {
    public static TaskList taskList;
    public static final String TODO = "todo";
    public static final String DEADLINE = "deadline";
    public static final String EVENT = "event";
    public static final String MARK = "mark";
    public static final String UNMARK = "unmark";
    public static final String LIST = "list";
    public static final int USER_STRING_SPLIT_LIMIT = 2;


    public Inu() {

        Ui.printGreeting();
        parseCommand();
        Ui.printFarewell();

    }

    public String[] readCommand() {
        String userString;
        String[] userCommand;

        Scanner input = new Scanner(System.in);
        userString = input.nextLine();
        userCommand = userString.split(" ", USER_STRING_SPLIT_LIMIT);

        return userCommand;
    }

    public void parseCommand() {

        taskList = new TaskList();

        String[] userString;
        String command;
        String entry;
        String task;
        String from;
        String to;

        do {

            userString = readCommand();
            command = userString[0];

            switch (command) {

            case TODO:

                entry = userString[1];
                Todo todoTask = new Todo(entry);
                taskList.addTask(todoTask);

                Ui.printMessage("added: " + taskList.getLastTask().toString() + "\n"
                        + "Now you have " + taskList.getListIndex() + " tasks in your list.");
                break;

            case DEADLINE:

                entry = userString[1];
                task = Util.fetchTask(entry);
                String deadLine = Util.fetchDeadLine(entry);
                DeadLine deadLineTask = new DeadLine(task, deadLine);
                taskList.addTask(deadLineTask);

                Ui.printMessage("added: " + taskList.getLastTask().toString() + "\n"
                        + "Now you have " + taskList.getListIndex() + " tasks in your list.");
                break;

            case EVENT:

                entry = userString[1];
                task = Util.fetchTask(entry);
                from = Util.fetchFrom(entry);
                to = Util.fetchTo(entry);
                Event eventTask = new Event(task, from, to);
                taskList.addTask(eventTask);

                Ui.printMessage("added: " + taskList.getLastTask().toString() + "\n"
                        + "Now you have " + taskList.getListIndex() + " tasks in your list.");
                break;

            case LIST:

                Ui.printTaskList(taskList);
                break;

            case MARK:

                entry = userString[1];
                int markIndex = Util.fetchMarkIndex(entry);
                Util.markTask(taskList, markIndex);
                Ui.printMessage(Messages.MESSAGE_MARK_TASK + "\n" + taskList.getTask(markIndex).toString());
                break;

            case UNMARK:

                entry = userString[1];
                int unMarkIndex = Util.fetchUnMarkIndex(entry);
                Util.unMarkTask(taskList, unMarkIndex);
                Ui.printMessage(Messages.MESSAGE_UNMARK_TASK + "\n" + taskList.getTask(unMarkIndex).toString());
                break;

            default:
                Ui.printMessage(Messages.MESSAGE_INVALID);
                break;
            }
        } while (!command.equals("bye"));

    }

    public static void main(String[] args) {
        new Inu();
    }
}
