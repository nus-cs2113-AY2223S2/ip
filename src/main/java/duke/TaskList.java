package duke;

import duke.exception.EmptyListError;

import java.util.ArrayList;

public class TaskList {
    public ArrayList<Task> tasksList;
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

    public boolean isEmpty(){
        return tasksList.isEmpty();
    }

    public void printList() throws EmptyListError {
        if (tasksList.isEmpty()) {
            throw new EmptyListError();
        }
        UI.printMessage("Here are the tasks in your list:");
        for (int i = 0; i < tasksList.size(); i++) {
            System.out.println((i + 1) + "." + tasksList.get(i).toString());
        }
    }

    public ArrayList<Task> returnTasks(){
        return tasksList;
    }
}
