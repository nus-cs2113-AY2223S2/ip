package duke.task;

import duke.exception.EmptyTaskException;

import duke.Storage.Storage;
import duke.ui.Ui;

import java.util.ArrayList;


/**
 * Contains the list of Tasks which consists of ToDo, Deadline, and Event Tasks.
 * Also contains the operations to manage the list of Tasks.
 */
public class TaskList {
    protected ArrayList<Task> tasks;
    private final Storage storage = new Storage();
    private static Ui ui;

    /**
     * Class constructor which fetches the array list of saved tasks if there is a save file present.
     */
    public TaskList(){
        this.tasks = storage.loadTasks();
        ui = new Ui();
    }

    /**
     * Adds the specified task to the tasks array list.
     * @param upcomingTask This is the task that will be added.
     */
    public void addTask(Task upcomingTask){
        tasks.add(upcomingTask);
    }

    /**
     * Sets the status of the task at specified index within the task array list to true if index is valid.
     * @param taskIndex This is the index of the task which will be marked as undone.
     * @exception IndexOutOfBoundsException on invalid input.
     */
    public void markTaskAsDone(int taskIndex){

        try {
            tasks.get(taskIndex).markAsDone();
            storage.saveToFile(tasks);
            ui.showSuccessfulMark(tasks.get(taskIndex));
        } catch (IndexOutOfBoundsException e){
            ui.showException("Invalid ID");
        }
    }

    /**
     * Sets the status of the task at specified index within the task array list to false if index is valid.
     * @param taskIndex This is the index of the task which will be marked as undone.
     * @exception IndexOutOfBoundsException on invalid input.
     */
    public void markTaskAsUndone(int taskIndex){
        try {
            tasks.get(taskIndex).markAsUndone();
            ui.showSuccessfulUnmark(tasks.get(taskIndex));
            storage.saveToFile(tasks);
        } catch (IndexOutOfBoundsException e){
            ui.showException("Invalid ID");
        }
    }

    /**
     * Prints tasks in the tasks array list.
     * @see package.duke.ui.Ui#listTasks()
     */
    public void listTasks(){
        ui.listTasks(this.tasks);
    }

    /**
     * Deletes task at specified index if it exists.
     * @param taskIndex This is the index of the task that will be deleted.
     * @exception IndexOutOfBoundsException on invalid input.
     */
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

    /**
     * Creates and adds a ToDo object with input as the description to the task array list.
     * @param input This is the description parameter for the ToDo object.
     * @throws EmptyTaskException where description is only "todo".
     * @see
     */
    public void generateToDo(String input) throws EmptyTaskException{
        if (input.equals("todo")){
            throw new EmptyTaskException();
        }
        ToDo newTask = new ToDo(input);
        this.addTask(newTask);
        storage.saveToFile(tasks);
        ui.showSuccessfulAdd(newTask);
    }

    /**
     * Creates and adds a Deadline object to the task array list.
     * The Deadline object will split the input string into description and deadline.
     * @param input This is the input string that will be converted into description and deadline.
     * @throws EmptyTaskException where the description is equals to "deadline".
     * @exception StringIndexOutOfBoundsException where the input does not have "/from" in it.
     */
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

    /**
     * Creates and adds an Event object to the tasks array list.
     * Input it converted into description, start and end fields to create the event object.
     * @param input This is the user input that used to create the event object
     * @throws EmptyTaskException when the user input string is "event"
     * @exception StringIndexOutOfBoundsException where either "/from" or "/to" are not present in the input
     */
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
