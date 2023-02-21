package duke.task;

import duke.exception.EmptyTaskException;

import duke.Storage.Storage;
import duke.ui.Ui;

import java.util.ArrayList;


public class TaskList {
    protected ArrayList<Task> tasks;
    private final Storage storage = new Storage();
    private static Ui ui;
    public TaskList(){
        this.tasks = storage.loadTasks();
        ui = new Ui();
    }
    public void addTask(Task upcomingTask){
        tasks.add(upcomingTask);
    }

    public void markTaskAsDone(int taskIndex){

        try {
            tasks.get(taskIndex).markAsDone();
            storage.saveToFile(tasks);
            ui.showSuccessfulMark(tasks.get(taskIndex));
        } catch (IndexOutOfBoundsException e){
            ui.showException("Invalid ID");
        }
    }
    public void markTaskAsUndone(int taskIndex){
        try {
            tasks.get(taskIndex).markAsUndone();
            ui.showSuccessfulUnmark(tasks.get(taskIndex));
            storage.saveToFile(tasks);
        } catch (IndexOutOfBoundsException e){
            ui.showException("Invalid ID");
        }
    }
    public void listTasks(){
        ui.listTasks(this.tasks);
    }

    public void deleteTask(int taskIndex){
        try {
            Task temp = tasks.get(taskIndex);
            tasks.remove(taskIndex);
            storage.saveToFile(tasks);
            ui.showSuccessfulDelete(temp, tasks.size());
        } catch (IndexOutOfBoundsException e){
            ui.showException("Invalid ID");
        }
    }

    public void generateToDo(String input) throws EmptyTaskException{
        if (input.equals("todo")){
            throw new EmptyTaskException();
        }
        ToDo newTask = new ToDo(input);
        this.addTask(newTask);
        storage.saveToFile(tasks);
        ui.showSuccessfulAdd(newTask);
    }
    public void generateDeadline(String input) throws EmptyTaskException{
        if (input.equals("deadline")){
            throw new EmptyTaskException();
        }
        int indexSeparator = input.indexOf("/");
        String taskDescription;
        String taskDue;
        try {
            taskDescription = input.substring(0, indexSeparator);
            taskDue = input.substring(indexSeparator + 4);
        }catch(StringIndexOutOfBoundsException a){
            ui.showException("Invalid Deadline or Event");
            return;
        }
        Deadline newTask = new Deadline(taskDescription, taskDue);
        this.addTask(newTask);
        storage.saveToFile(tasks);
        ui.showSuccessfulAdd(newTask);
    }

    public void generateEvent(String input) throws EmptyTaskException{
        if (input.equals("event")){
            throw new EmptyTaskException();
        }
        int indexSeparator = input.indexOf("/");
        String taskDescription = "";
        String taskDates = "";
        try {
            taskDescription = input.substring(0, indexSeparator); //locates location of first / for "from"
            taskDates = input.substring(indexSeparator + 6); //creates "from" substring
        }catch (StringIndexOutOfBoundsException a){
            ui.showException("Invalid Deadline or Event");
            return;
        }
        indexSeparator = taskDates.indexOf("/"); //locates location of second / for "by"
        String taskStart = "";
        String taskEnd = "";
        try {
            taskStart = taskDates.substring(0, indexSeparator - 1); //creates "by" substring
            taskEnd = taskDates.substring(indexSeparator + 4);
        }catch(StringIndexOutOfBoundsException a){
            ui.showException("Invalid Deadline or Event");
            return;
        }
        Event newTask = new Event(taskDescription, taskStart, taskEnd);
        this.addTask(newTask);
        storage.saveToFile(tasks);
        ui.showSuccessfulAdd(newTask);
    }

    public void find(String description){
        ArrayList<Task> temp = new ArrayList<>();
        for (Task task : tasks){
            if (task.getDescription().contains(description)){
                temp.add(task);
            }
        }
        ui.searchResults(temp);
        temp.clear();
    }
}
