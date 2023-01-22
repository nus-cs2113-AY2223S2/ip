import java.util.Scanner;
import java.util.StringJoiner;

public class Duke {
    private static final Task[] taskList = new Task[100];
    private static int taskCount = 0;

    private static void print(String... strings) {
        System.out.println(Messages.LINE.TEXT);
        for (String s : strings) {
            System.out.println(s);
        }
        System.out.println(Messages.LINE.TEXT);
    }

    private static void printTaskList() {
        StringJoiner taskListString = new StringJoiner(System.lineSeparator());
        for (int i = 0; i < taskCount; i++) {
            taskListString.add((i + 1) + "." + taskList[i].toString());
        }
        print(Messages.LIST.TEXT, taskListString.toString());
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        boolean isRunning = true;

        print(Messages.START.TEXT);
        while (isRunning) {
            String original = in.nextLine().trim();
            String[] command = original.split(" ", 2);
			
            switch (command[0]) {
            case "list":
                printTaskList();
                break;
            case "mark": {
                int index = Integer.parseInt(command[1]) - 1;
                taskList[index].mark(true);
                print(Messages.MARK.TEXT, taskList[index].toString());
                break;
            }
            case "unmark": {
                int index = Integer.parseInt(command[1]) - 1;
                taskList[index].mark(false);
                print(Messages.UNMARK.TEXT, taskList[index].toString());
                break;
            }
            case "bye":
                isRunning = false;
                break;
            default:
                taskList[taskCount++] = new Task(original);
                print(Messages.ADD.TEXT + original);
            }
        }
        print(Messages.EXIT.TEXT);
    }
}
