import task.TaskList;

public class Ui {
    public static void print(String... strings) {
        System.out.println(Messages.LINE.MESSAGE);
        for (String s : strings) {
            System.out.println(s);
        }
        System.out.println(Messages.LINE.MESSAGE);
    }

    public static void printStartMessage() {
        print(Messages.START.MESSAGE);
    }

    public static void printExitMessage() {
        print(Messages.EXIT.MESSAGE);
    }

    public static void printTaskList(TaskList taskList) {
        print(Messages.LIST.MESSAGE, taskList.toString());
    }
}
