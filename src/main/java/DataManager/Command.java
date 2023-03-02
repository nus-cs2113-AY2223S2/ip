package DataManager;

import UI.Ui;
import task.Storage;
import task.Task;
import task.TaskList;

import java.util.ArrayList;

/**
 * This class manages all the commands to execute, and executes corresponding methods in other classes
 */
public class Command {
    private String commandAction;
    private boolean isExit = false;

    public Command(String action) {
        this.commandAction = action;
    }

    public boolean getExitStatus() {
        return isExit;
    }

    public void setExit(boolean exit) {
        isExit = exit;
    }

    public void markTask(int index) {
        TaskList.list.get(index).markDone();
        Ui.markResponse(index);
    }

    public void unmarkTask(int index) {
        TaskList.list.get(index).unmarkDone();
        Ui.unmarkResponse(index);
    }

    public static void createTodoTask(String taskDescription) {
        TaskList.addTodo(taskDescription, "");
    }

    public static void createDeadlineTask(String taskDescription, String deadline) {
        TaskList.addDeadline(taskDescription, deadline, "");
    }

    public static void createEventTask(String taskDescription, String from, String to) {
        TaskList.addEvent(taskDescription, from, to, "");
    }

    public static void deleteTask(int index) {
        TaskList.deleteTask(index);
    }

    public static void updateFileData(String taskDescription) {
        Storage.storeFileData(taskDescription);
    }

    public static void findItems(String keyword) {
        ArrayList<Task> itemMatch = new ArrayList<>();
        for (Task item : TaskList.list) {
            String taskDescription = item.getTask();
            if(taskDescription.contains(keyword)) {
                itemMatch.add(item);
            }
        }
        Ui.printList(itemMatch);
    }
}
