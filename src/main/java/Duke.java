import task.Task;
import task.ToDo;
import task.Event;
import task.Deadline;

import java.util.Scanner;
import java.util.StringJoiner;

public class Duke {
    private static final Task[] taskList = new Task[100];
    private static int taskCount = 0;

    private static String getTaskCountString() {
        return String.format("Now you have %d task%s in the list.", taskCount, taskCount == 1 ? "" : "s");
    }

    private static void print(String... strings) {
        System.out.println(Messages.LINE.MESSAGE);
        for (String s : strings) {
            System.out.println(s);
        }
        System.out.println(Messages.LINE.MESSAGE);
    }

    private static void printTaskList() {
        StringJoiner taskListString = new StringJoiner(System.lineSeparator());
        for (int i = 0; i < taskCount; i++) {
            taskListString.add((i + 1) + "." + taskList[i].toString());
        }
        print(Messages.LIST.MESSAGE, taskListString.toString());
    }

    public static void main(String[] args) {
        print(Messages.START.MESSAGE);

        try (Scanner in = new Scanner(System.in)) {
            boolean isRunning = true;

            while (isRunning) {
                String[] command = in.nextLine().trim().split(" ", 2);

                switch (command[0]) {
                case "todo":
                    taskList[taskCount++] = new ToDo(command[1]);
                    print(Messages.ADD.MESSAGE, taskList[taskCount - 1].toString(), getTaskCountString());
                    break;
                case "event": {
                    String[] arguments = command[1].split(Event.DELIMITER);
                    taskList[taskCount++] = new Event(arguments[0], arguments[1], arguments[2]);
                    print(Messages.ADD.MESSAGE, taskList[taskCount - 1].toString(), getTaskCountString());
                    break;
                }
                case "deadline": {
                    String[] arguments = command[1].split(Deadline.DELIMITER);
                    taskList[taskCount++] = new Deadline(arguments[0], arguments[1]);
                    print(Messages.ADD.MESSAGE, taskList[taskCount - 1].toString(), getTaskCountString());
                    break;
                }
                case "list":
                    printTaskList();
                    break;
                case "mark": {
                    int index = Integer.parseInt(command[1]) - 1;
                    taskList[index].setDone(true);
                    print(Messages.MARK.MESSAGE, taskList[index].toString());
                    break;
                }
                case "unmark": {
                    int index = Integer.parseInt(command[1]) - 1;
                    taskList[index].setDone(false);
                    print(Messages.UNMARK.MESSAGE, taskList[index].toString());
                    break;
                }
                case "bye":
                    isRunning = false;
                    break;
                default:
                    // do nothing
                }
            }
        }

        print(Messages.EXIT.MESSAGE);
    }
}
