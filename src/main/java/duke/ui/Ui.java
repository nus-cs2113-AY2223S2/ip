package duke.ui;

import static duke.common.Messages.*;

import duke.Task;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
public class Ui {
    public Ui(){
    }

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

    public void showTaskList(TaskList tasks){
        tasks.printTasks();
    }

    public void showMarkedTask(TaskList taskList, int indexMark){
        System.out.println(MARK_DONE_MESSAGE);
        System.out.println("  [" + taskList.getTask(indexMark-1).getStatusIcon() + "] " + taskList.getTask(indexMark-1).getDescription());
    }
    public void showUnmarkedTask(TaskList taskList, int indexUnmark){
        System.out.println(MARK_UNDONE_MESSAGE);
        System.out.println("  [" + taskList.getTask(indexUnmark-1).getStatusIcon() + "] " + taskList.getTask(indexUnmark-1).getDescription());
    }
    public void showTodoTask(TaskList taskList, int numOfTask){
        System.out.println(taskList.getTask(numOfTask - 1));
        System.out.println("Now you have " + numOfTask + " task in the list.");
    }
    public void showDeadlineTask(TaskList taskList, int numOfTask){
        System.out.println(taskList.getTask(numOfTask - 1));
        System.out.println("Now you have " + numOfTask + " task in the list.");
    }
    public void showEventTask(TaskList taskList, int numOfTask){
        System.out.println(taskList.getTask(numOfTask - 1));
        System.out.println("Now you have " + numOfTask + " task in the list.");
    }
    public void showDeletedTask(TaskList taskList, int indexDelete,int numOfTask){
        numOfTask -= 1;
        System.out.println(TASK_REMOVED_MESSAGE);
        System.out.println(indexDelete + "." +"[" + taskList.getTask(indexDelete-1).getType() + "]" + "[" + taskList.getTask(indexDelete-1).getStatusIcon() + "] "+ taskList.getTask(indexDelete-1).getDescription()
                + taskList.getTask(indexDelete-1).getDeadline() + taskList.getTask(indexDelete-1).getPeriod());
        System.out.println("Now you have " + numOfTask + " task in the list.");
    }

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
