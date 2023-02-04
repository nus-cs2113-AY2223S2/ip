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

    public static void printPromptValidTask() {
        System.out.println(Messages.MESSAGE_DIVIDER);
        System.out.println(Messages.MESSAGE_PROMPT_VALID_INPUT);
        System.out.println(Messages.MESSAGE_DIVIDER);
    }
    public static void printPromptValidDeadLine() {
        System.out.println(Messages.MESSAGE_DIVIDER);
        System.out.println(Messages.MESSAGE_PROMPT_VALID_DEADLINE);
        System.out.println(Messages.MESSAGE_DIVIDER);
    }

    public static void printPromptValidEvent() {
        System.out.println(Messages.MESSAGE_DIVIDER);
        System.out.println(Messages.MESSAGE_PROMPT_VALID_EVENT);
        System.out.println(Messages.MESSAGE_DIVIDER);
    }
    public static void printPromptValidTaskEntry() {
        System.out.println(Messages.MESSAGE_DIVIDER);
        System.out.println(Messages.MESSAGE_PROMPT_VALID_TASK_ENTRY);
        System.out.println(Messages.MESSAGE_DIVIDER);
    }

    public static void printPromptValidDeadLineEntry() {
        System.out.println(Messages.MESSAGE_DIVIDER);
        System.out.println(Messages.MESSAGE_PROMPT_VALID_DEAD_LINE_ENTRY_AFTER_SLASH);
        System.out.println(Messages.MESSAGE_DIVIDER);
    }
    public static void printPromptValidEventEntry() {
        System.out.println(Messages.MESSAGE_DIVIDER);
        System.out.println(Messages.MESSAGE_PROMPT_VALID_EVENT_ENTRY_AFTER_SLASH);
        System.out.println(Messages.MESSAGE_DIVIDER);
    }

    public static void printPromptValidMarkAndUnMark() {
        System.out.println(Messages.MESSAGE_DIVIDER);
        System.out.println(Messages.MESSAGE_PROMPT_VALID_MARK_AND_UN_MARK);
        System.out.println(Messages.MESSAGE_DIVIDER);
    }
    public static void printPromptValidMarkEntry() {
        System.out.println(Messages.MESSAGE_DIVIDER);
        System.out.println(Messages.MESSAGE_PROMPT_VALID_MARK_ENTRY);
        System.out.println(Messages.MESSAGE_DIVIDER);
    }

    public static void printPromptValidUnMarkEntry() {
        System.out.println(Messages.MESSAGE_DIVIDER);
        System.out.println(Messages.MESSAGE_PROMPT_VALID_UN_MARK_ENTRY);
        System.out.println(Messages.MESSAGE_DIVIDER);
    }

    public static void printPromptValidMarkAndUnMarkIndex() {
        System.out.println(Messages.MESSAGE_DIVIDER);
        System.out.println(Messages.MESSAGE_PROMPT_VALID_MARK_AND_UN_MARK_INDEX);
        System.out.println(Messages.MESSAGE_DIVIDER);
    }

    public static void printPromptEmptyTaskList() {
        System.out.println(Messages.MESSAGE_DIVIDER);
        System.out.println(Messages.MESSAGE_PROMPT_EMPTY_TASK_LIST);
        System.out.println(Messages.MESSAGE_DIVIDER);
    }

}