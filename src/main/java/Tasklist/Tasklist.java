package Tasklist;

import UI.UserInterface;

import java.util.ArrayList;


/**
 * this class provides methods to read,add,delete and update tasks from the taskArray
 * it is the superclass of event, todo and deadline
 */
public class Tasklist {
    //class level attributes
    private static Tasklist[] taskArray = new Tasklist[100];
    public static int lastIndex = 0;

    //instance level attributes
    protected String type = new String();
    protected String taskName = new String();
    protected int taskNumber;
    public String description = new String();
    public boolean isDone = false;


    public Tasklist(String taskName, int taskNumber) {
        this.taskName = taskName;
        this.taskNumber = taskNumber;
    }

    public static void addToTaskArrayList(Tasklist taskToBeAdded) {
        taskArray[lastIndex] = taskToBeAdded;
        lastIndex++;
    }

    /**
     * this function toggles the isDone status of tasks
     *
     * @param taskIndex the identifying number of the task to be marked (or unmarked)
     */
    public static void markOrUnmark(int taskIndex) {
        taskArray[taskIndex - 1].isDone = (!taskArray[taskIndex - 1].isDone);
        taskArray[taskIndex - 1].updateTaskDescription();
        UserInterface.markMessage(taskIndex);
    }

    public static Tasklist get(int index) {
        return taskArray[index];
    }

    public static ArrayList<String> find(String queryString) {
        ArrayList<String> foundTaskList = new ArrayList<String>();
        for (int index = 0; index < lastIndex; index++) {
            if (taskArray[index].taskName.contains(queryString)) {
                foundTaskList.add(taskArray[index].description);
            }
        }
        return foundTaskList;
    }

    public static void deleteFromTaskArray(int indexToDelete) {
        UserInterface.deleteMessage(indexToDelete);
        if (lastIndex > 1) {
            for (int index = indexToDelete - 1; index < lastIndex - 1; index++) {
                taskArray[index] = taskArray[index + 1];
                taskArray[index].taskNumber -= 1;
            }
        }
        if (lastIndex == 1) {
            taskArray[0] = null;
        }

        lastIndex--;
        taskArray[lastIndex] = null;
    }

    /**
     * @return either returns "[X]" or "[ ]" string depends on the isDone status of the task.
     */
    public String getDoneString() {
        return this.isDone ? "[X]" : "[ ]";
    }

    /**
     * this method is used to update the task description string of all subclasses (todo,deadline,event) of TaskList.
     * it is called whenever entries in the file is loaded into the taskArray, and whenever the isDone status of a -
     * task is toggled.
     */
    public void updateTaskDescription() {
    }

    /**
     * this method converts TaskList objects into text file formats that can be saved into the file
     *
     * @return a string in the format of taskNumber.taskType.isDone.taskName.otherTaskDetails
     */
    public String createEntry() {
        return Integer.toString(this.taskNumber) + (".") + this.type + "." + this.getDoneString() + "." +
                (this.taskName) + "\n";
    }

}
