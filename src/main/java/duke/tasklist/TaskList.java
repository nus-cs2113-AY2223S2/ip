package duke.tasklist;

import duke.exception.EmptyListError;
import duke.task.Task;
import duke.ui.UI;

import java.util.ArrayList;

/**
 * Array List that keep tracks of all the tasks
 */
public class TaskList {
    private ArrayList<Task> tasksList;

    public TaskList(ArrayList<Task> tasksList) {
        this.tasksList = tasksList;
    }

    public void addToList(Task task) {
        tasksList.add(task);
    }

    public int sizeOfList() {
        return tasksList.size();
    }

    public void mark(int index){
        tasksList.get(index).markAsDone();
    }

    public void unmark(int index){
        tasksList.get(index).markAsUndone();
    }

    public String getStatus(int index){
        return tasksList.get(index).getStatusIcon();
    }

    public String getDescription(int index){
        return tasksList.get(index).description;
    }

    public void removeTask(int index){
        tasksList.remove(index);
    }


    public String getString(int index){
        return tasksList.get(index).toString();
    }

    /**
     * Prints the current task-list
     *
     * @throws EmptyListError if error occurred due to having an empty list
     */
    public void printList() throws EmptyListError {
        if (tasksList.isEmpty()) {
            throw new EmptyListError();
        }
        System.out.println("Here are the tasks in your list:");
        UI.printList(returnTasks());
    }

    /**
     * Prints the list of tasks based on a keyword
     *
     * @param keyword the word that user use to search amongst the tasks
     * @throws EmptyListError if error occurred due to having an empty list
     */
    public void findInList(String keyword) throws EmptyListError{
        if (tasksList.isEmpty()) {
            throw new EmptyListError();
        }
        UI.printFindInList(tasksList, keyword);
    }

    public ArrayList<Task> returnTasks(){
        return tasksList;
    }
}
