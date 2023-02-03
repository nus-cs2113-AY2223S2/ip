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
    public static void printMark(TaskList taskList, int markIndex) {
        System.out.println(Messages.MESSAGE_DIVIDER);
        System.out.println(Messages.MESSAGE_MARK_TASK + "\n" + taskList.getTask(markIndex).toString());
        System.out.println(Messages.MESSAGE_DIVIDER);
    }
    public static void printUnMark(TaskList taskList, int unMarkIndex) {
        System.out.println(Messages.MESSAGE_DIVIDER);
        System.out.println(Messages.MESSAGE_UNMARK_TASK + "\n" + taskList.getTask(unMarkIndex).toString());
        System.out.println(Messages.MESSAGE_DIVIDER);
    }

    public static void printAcknowledgment(TaskList taskList) {
        System.out.println(Messages.MESSAGE_DIVIDER);
        System.out.println("added: " + taskList.getLastTask().toString() + "\n"
                + "Now you have " + taskList.getListIndex() + " tasks in your list.");
        System.out.println(Messages.MESSAGE_DIVIDER);
    }

    public static void printInvalid() {
        System.out.println(Messages.MESSAGE_DIVIDER);
        System.out.println(Messages.MESSAGE_INVALID);
        System.out.println(Messages.MESSAGE_DIVIDER);
    }

}
