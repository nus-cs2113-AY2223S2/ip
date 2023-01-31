public class Ui {

    public static void printGreeting() {
        System.out.println(Messages.MESSAGE_DIVIDER);
        System.out.println(Messages.MESSAGE_GREETING);
        System.out.println(Messages.MESSAGE_PROMPT);
        System.out.println(Messages.MESSAGE_DIVIDER);
    }

    public static void printFarewell() {
        System.out.println(Messages.MESSAGE_DIVIDER);
        System.out.println(Messages.MESSAGE_FAREWELL);
        System.out.println(Messages.MESSAGE_DIVIDER);
    }

    public static void printTaskList(TaskList taskList) {
        System.out.println(Messages.MESSAGE_DIVIDER);
        System.out.println(Messages.MESSAGE_LIST_HEADER);
        taskList.printList();
        System.out.println(Messages.MESSAGE_DIVIDER);
    }

    public static void printMessage(String message) {
        System.out.println(Messages.MESSAGE_DIVIDER);
        System.out.println(message);
        System.out.println(Messages.MESSAGE_DIVIDER);
    }

}
