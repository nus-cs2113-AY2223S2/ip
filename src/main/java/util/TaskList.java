package util;

import java.util.ArrayList;

import tasks.Task;

public class TaskList {
    private static ArrayList<Task> listOfTasks = new ArrayList<>();
    private static final TaskDeleter taskDeleter = new TaskDeleter();
    private static final Marker marker = new Marker();
    private static final TaskAdder taskAdder = new TaskAdder();

    private static final Storage storage = new Storage();



    public void addNewTask(String input, boolean doIPrintOutput){
        taskAdder.addTaskToList(listOfTasks, input, doIPrintOutput);
    }

    public ArrayList<Task> accessTaskList(){
        return listOfTasks;
    }

    public void printList(){
        OutputUI outputUI = new OutputUI();
        outputUI.printList(listOfTasks, listOfTasks.size());
    }

    public void handleMarkUnmarkAction(String input, boolean doIPrintOutput){
        marker.markOrUnamrkTask(listOfTasks, input, doIPrintOutput);
    }

    public void handleDeleteAction(String input){
        taskDeleter.attemptToDeleteTask(listOfTasks, input);
    }

    public void saveData(){
        storage.saveList(listOfTasks);
    }

    public void handleFindTaskAction(String input){
        Finder finder = new Finder();
        finder.findTaskFromList(listOfTasks, input);
    }


}
