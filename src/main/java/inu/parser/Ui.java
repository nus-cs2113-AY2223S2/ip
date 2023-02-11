package inu.parser;

import inu.tasklist.TaskList;

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
                + "Now you have " + taskList.getTaskListSize() + " tasks in your list.");
        System.out.println(Messages.MESSAGE_DIVIDER);

    }

    public static void printInvalid() {

        System.out.println(Messages.MESSAGE_DIVIDER);
        System.out.println(Messages.MESSAGE_INVALID);
        System.out.println(Messages.MESSAGE_DIVIDER);

    }

    public static void printPromptValidInput() {

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

    public static void printPromptValidByDateEntryAfterSlash() {

        System.out.println(Messages.MESSAGE_DIVIDER);
        System.out.println(Messages.MESSAGE_PROMPT_VALID_BY_DATE_ENTRY_AFTER_SLASH);
        System.out.println(Messages.MESSAGE_DIVIDER);

    }

    public static void printPromptValidFromAndToEntryAfterSlash() {

        System.out.println(Messages.MESSAGE_DIVIDER);
        System.out.println(Messages.MESSAGE_PROMPT_VALID_FROM_AND_TO_ENTRY_AFTER_SLASH);
        System.out.println(Messages.MESSAGE_DIVIDER);

    }

    public static void printPromptValidTaskIndexEntry() {

        System.out.println(Messages.MESSAGE_DIVIDER);
        System.out.println(Messages.MESSAGE_PROMPT_VALID_TASK_INDEX_ENTRY);
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

    public static void printPromptValidDeleteEntry() {

        System.out.println(Messages.MESSAGE_DIVIDER);
        System.out.println(Messages.MESSAGE_PROMPT_VALID_DELETE_ENTRY);
        System.out.println(Messages.MESSAGE_DIVIDER);

    }

    public static void printPromptValidTaskIndex() {

        System.out.println(Messages.MESSAGE_DIVIDER);
        System.out.println(Messages.MESSAGE_PROMPT_VALID_TASK_INDEX);
        System.out.println(Messages.MESSAGE_DIVIDER);

    }

    public static void printPromptEmptyTaskList() {

        System.out.println(Messages.MESSAGE_DIVIDER);
        System.out.println(Messages.MESSAGE_PROMPT_EMPTY_TASK_LIST);
        System.out.println(Messages.MESSAGE_DIVIDER);

    }

    public static void printDelete(TaskList taskList, int deleteTaskNumber) {

        System.out.println(Messages.MESSAGE_DIVIDER);
        System.out.println(Messages.MESSAGE_DELETE_TASK + "\n" + taskList.getTask(deleteTaskNumber).toString());
        System.out.println(Messages.MESSAGE_DIVIDER);

    }

}