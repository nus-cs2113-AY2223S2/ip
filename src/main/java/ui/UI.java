package ui;

import parser.Parser;
import storage.StorageFile;
import tasks.Task;
import todolist.TaskList;

public class UI {
    //Instructions Strings
    public static final String ACTION_LIST = "list";
    public static final String ACTION_FIND = "find";
    public static final String ACTION_DELETE = "delete";
    public static final String ACTION_MARK_COMPLETE = "mark";
    public static final String ACTION_MARK_INCOMPLETE = "unmark";
    public static final String ACTION_NEW_TODO = "todo";
    public static final String ACTION_NEW_DEADLINE = "deadline";
    public static final String ACTION_NEW_EVENT = "event";
    //Error Strings
    public static final String ERROR_UNKNOWN_INSTRUCTION = "What are you talking about?";
    //Message Strings
    public static final String COMPLETED_TASK_MESSAGE = "Nice! I've marked this task as done!";
    public static final String INCOMPLETE_TASK_MESSAGE = "Stop being lazy, get to work!";
    public static final String ADDED_TASK_MESSAGE = "Done! Added: ";
    public static final String TASK_REMOVED_MESSAGE_ONE = "Task removed:";
    public static final String TASK_REMOVED_MESSAGE_TWO = "Total tasks left: ";
    //Goodbye Strings
    public static final String GOODBYE_WITH_TOO_MUCH_TASKS = "You sure have a lot of things to do, Ningen";
    public static final String GOODBYE_MESSAGE = "Otsu-Nakiri!";
    public static final String GOODBYE_WITH_NOTHING_DONE = "You must think you're funny, Ningen";

    /**
     * Used after every user input
     * Depending on user input , execute an instruction and prints the relevant response
     * Saves the taskList to a text file if changes to the taskList are made.
     *
     * @param inputMessage the String Array containing the User Input
     * @param taskList the current state of the TaskList
     * @return true if instruction is valid, false otherwise
     */
    public static boolean executeUserInput(TaskList taskList, String[] inputMessage) {
        switch (inputMessage[0]) {
        case ACTION_LIST:
            taskList.listAllTasks();
            return true;
        case ACTION_DELETE:
            int taskIndex = Parser.checkActionInputValidity(inputMessage, taskList.getSize());
            if (taskIndex >= 0) {
                taskList.deleteATask(taskIndex);
                StorageFile.updateData(taskList);
                return true;
            }
            return false;
        case ACTION_FIND:
            String query = Parser.processQuery(inputMessage);
            if (!query.equals("")) {
                taskList.searchByKeyword(query);
                return true;
            }
            return false;
        case ACTION_MARK_COMPLETE:
            taskIndex = Parser.checkActionInputValidity(inputMessage, taskList.getSize());
            if (taskIndex >= 0) {
                taskList.markTaskComplete(taskIndex);
                StorageFile.updateData(taskList);
                return true;
            }
            return false;
        case ACTION_MARK_INCOMPLETE:
            taskIndex = Parser.checkActionInputValidity(inputMessage, taskList.getSize());
            if (taskIndex >= 0) {
                taskList.markTaskIncomplete(taskIndex);
                StorageFile.updateData(taskList);
                return true;
            }
            return false;
        case ACTION_NEW_TODO:
            String todoTask = Parser.processToDoMessage(inputMessage);
            if (!todoTask.equals("")) {
                taskList.addNewTodoTask(todoTask);
                StorageFile.updateData(taskList);
                return true;
            }
            return false;
        case ACTION_NEW_DEADLINE:
            String[] deadlineTask = Parser.processDeadlineMessage(inputMessage);
            if (deadlineTask.length == 2) {
                taskList.addNewDeadlineTask(deadlineTask);
                StorageFile.updateData(taskList);
                return true;
            }
            return false;
        case ACTION_NEW_EVENT:
            String[] eventTask = Parser.processEventMessage(inputMessage);
            if (eventTask.length == 3) {
                taskList.addNewEventTask(eventTask);
                StorageFile.updateData(taskList);
                return true;
            }
            return false;
        default:
            System.out.println(ERROR_UNKNOWN_INSTRUCTION);
            return false;
        }
    }

    public static void printAddedNewTaskMessage(Task currentTask) {
        System.out.println(ADDED_TASK_MESSAGE);
        System.out.println(currentTask);
    }

    public static void printMarkedTaskIncompleteMessage(Task currentTask) {
        System.out.println(INCOMPLETE_TASK_MESSAGE);
        System.out.println(currentTask);
    }

    public static void printMarkedTaskCompleteMessage(Task currentTask) {
        System.out.println(COMPLETED_TASK_MESSAGE);
        System.out.println(currentTask);
    }

    public static void printDeletedTaskMessage (Task currentTask, int tasksLeft) {
        System.out.println(TASK_REMOVED_MESSAGE_ONE);
        System.out.println(currentTask);
        System.out.println(TASK_REMOVED_MESSAGE_TWO + tasksLeft);
    }

    /**
     * Prints the goodbye message
     * Special messages are printed if TaskList exceeds 20 tasks or if nothing valid was done during runtime
     *
     * @param didSomethingUseful True if any valid task was done during runtime, False otherwise
     * @param sizeOfTaskList     Size of the TaskList at exit
     */
    public static void printGoodbyeMessage(boolean didSomethingUseful, int sizeOfTaskList) {
        if (!didSomethingUseful) {
            System.out.println(GOODBYE_WITH_NOTHING_DONE);
        } else if (sizeOfTaskList > 20) {
            System.out.println(GOODBYE_WITH_TOO_MUCH_TASKS);
        } else {
            System.out.println(GOODBYE_MESSAGE);
        }
    }
}
