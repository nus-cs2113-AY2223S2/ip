import java.util.Scanner;

public class UserCommands {
    public static String[] userString;
    public static String command;
    public static String entry;
    public static String task;
    public static String from;
    public static String to;
    public static final String TODO = "todo";
    public static final String DEADLINE = "deadline";
    public static final String EVENT = "event";
    public static final String MARK = "mark";
    public static final String UNMARK = "unmark";
    public static final String LIST = "list";
    public static final int USER_STRING_SPLIT_LIMIT = 2;

    public static String[] readCommand() {

        String userString;
        String[] userCommand;

        Scanner input = new Scanner(System.in);
        userString = input.nextLine();
        userCommand = userString.split(" ", USER_STRING_SPLIT_LIMIT);

        return userCommand;

    }

    public static void parseCommand(TaskList taskList) {

        do {

            userString = readCommand();
            command = userString[0];

            switch (command) {

            case TODO:
                
                    entry = userString[1];
                    Todo todoTask = new Todo(entry);
                    taskList.addTask(todoTask);
                    Ui.printAcknowledgment(taskList);
                    break;

            case DEADLINE:

                entry = userString[1];
                task = Util.fetchTask(entry);
                String deadLine = Util.fetchDeadLine(entry);
                DeadLine deadLineTask = new DeadLine(task, deadLine);
                taskList.addTask(deadLineTask);
                Ui.printAcknowledgment(taskList);
                break;

            case EVENT:

                entry = userString[1];
                task = Util.fetchTask(entry);
                from = Util.fetchFrom(entry);
                to = Util.fetchTo(entry);
                Event eventTask = new Event(task, from, to);
                taskList.addTask(eventTask);
                Ui.printAcknowledgment(taskList);
                break;

            case LIST:

                Ui.printTaskList(taskList);
                break;

            case MARK:

                entry = userString[1];
                int markIndex = Util.fetchMarkIndex(entry);
                Util.markTask(taskList, markIndex);
                Ui.printMark(taskList, markIndex);
                break;

            case UNMARK:

                entry = userString[1];
                int unMarkIndex = Util.fetchUnMarkIndex(entry);
                Util.unMarkTask(taskList, unMarkIndex);
                Ui.printUnMark(taskList, unMarkIndex);
                break;

            default:

                Ui.printInvalid();
                break;

            }
        } while (!command.equals("bye"));

    }

}
