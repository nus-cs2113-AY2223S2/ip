package duke.ui;

import static duke.common.Messages.*;
import duke.tasklist.TaskList;

/**
 * Ui of Duke Application
 */
public class Ui {
    public Ui(){
    }


    /**
     * Generates and prints out the welcome message and task list upon the start of the application.
     *
     * @param tasks task list containing the tasks from the loaded storage file.
     */
    public void showWelcomeMessage(TaskList tasks){
        System.out.println(WELCOME_MESSAGE);
        System.out.println(LOADING_FILE_MESSAGE);
        tasks.printTasks();
    }

    public void showGoodByeMessage(){
        System.out.println(GOODBYE_MESSAGE);
    }

    public void showCreatingFileMessage(){
        System.out.println(CREATING_NEW_FILE);
    }

    /**
     * Shows the user the task list.
     *
     * @param tasks an Array List containing Tasks added by the user.
     */
    public void showTaskList(TaskList tasks){
        tasks.printTasks();
    }

    /**
     * Shows the user the task that has been marked.
     *
     * @param taskList an Array List containing Tasks added by the user.
     * @param indexMark number of the task that needs to be marked.
     */
    public void showMarkedTask(TaskList taskList, int indexMark){
        System.out.println(MARK_DONE_MESSAGE);
        System.out.println("  [" + taskList.getTask(indexMark-1).getStatusIcon() + "] " + taskList.getTask(indexMark-1).getDescription());
    }

    /**
     * Shows the user the task that has been unmarked.
     *
     * @param taskList an Array List containing Tasks added by the user.
     * @param indexUnmark number of the task that needs to be unmarked.
     */
    public void showUnmarkedTask(TaskList taskList, int indexUnmark){
        System.out.println(MARK_UNDONE_MESSAGE);
        System.out.println("  [" + taskList.getTask(indexUnmark-1).getStatusIcon() + "] " + taskList.getTask(indexUnmark-1).getDescription());
    }

    /**
     * Shows the user the to-do task that has been added and the updated total number of tasks in their list.
     *
     * @param taskList an Array List containing Tasks added by the user.
     * @param numOfTask number of tasks currently in the list.
     */
    public void showTodoTask(TaskList taskList, int numOfTask){
        System.out.println(taskList.getTask(numOfTask - 1));
        System.out.println("Now you have " + numOfTask + " task(s) in the list.");
    }
    /**
     * Shows the user the deadline that has been added and the updated total number of tasks in their list.
     *
     * @param taskList an Array List containing Tasks added by the user.
     * @param numOfTask number of tasks currently in the list.
     */
    public void showDeadlineTask(TaskList taskList, int numOfTask){
        System.out.println(taskList.getTask(numOfTask - 1));
        System.out.println("Now you have " + numOfTask + " task(s) in the list.");
    }

    /**
     * Shows the user the event that has been added and the updated total number of tasks in their list.
     *
     * @param taskList an Array List containing Tasks added by the user.
     * @param numOfTask number of tasks currently in the list.
     */
    public void showEventTask(TaskList taskList, int numOfTask){
        System.out.println(taskList.getTask(numOfTask - 1));
        System.out.println("Now you have " + numOfTask + " task(s) in the list.");
    }

    /**
     * Shows the user the task that has been deleted and the updated total number of tasks in the list.
     *
     * @param taskList an Array List containing Tasks added by the user.
     * @param indexDelete number of the task that needs to be deleted.
     * @param numOfTask number of tasks currently in the list.
     */
    public void showDeletedTask(TaskList taskList, int indexDelete,int numOfTask){
        numOfTask -= 1;
        System.out.println(TASK_REMOVED_MESSAGE);
        System.out.println(indexDelete + "." +"[" + taskList.getTask(indexDelete-1).getType() + "]" + "[" + taskList.getTask(indexDelete-1).getStatusIcon() + "] "+ taskList.getTask(indexDelete-1).getDescription()
                + taskList.getTask(indexDelete-1).getDeadline() + taskList.getTask(indexDelete-1).getPeriod());
        System.out.println("Now you have " + numOfTask + " task(s) in the list.");
    }
    public void showMatchingTasksMessage(){System.out.println(MATCHING_TASKS_MESSAGE);}
    public void showInvalidTodoFormatMessage(){
        System.out.println(INVALID_TODO_FORMAT_MESSAGE);
    }
    public void showInvalidDeadlineFormatMessage(){
        System.out.println(INVALID_DEADLINE_FORMAT_MESSAGE);
    }
    public void showInvalidEventFormatMessage(){
        System.out.println(INVALID_EVENT_FORMAT_MESSAGE);
    }
    public void showEmptyToFieldMessage(){
        System.out.println(EMPTY_TO_FIELD_MESSAGE);
    }
    public void showInvalidTaskNumberMessage(){ System.out.println(INVALID_TASK_NUMBER_MESSAGE);}
    public void showDukeExceptionMessage(){ System.out.println(DUKE_EXCEPTION_MESSAGE);}
}
