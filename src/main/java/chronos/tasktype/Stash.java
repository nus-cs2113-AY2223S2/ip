package chronos.tasktype;

import chronos.savehandler.Save;
import chronos.savehandler.Storage;

import java.util.ArrayList;
import java.util.stream.Collectors;

//this class stores all the tasks
public class Stash {

    private ArrayList<Task> tasks;

    /**
     * Constructs a new, empty {@code Stash} object.
     */
    public Stash() {
        this.tasks = new ArrayList<Task>();;
    }

    /**
     * Adds a new task to this {@code Stash}.
     *
     * @param task the task to add
     */
    public void addNewTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Adds a specified number of copies of the given task to this {@code Stash}.
     *
     * @param count the number of copies to add
     * @param task the task to add
     */
    public void generateTask(int count, Task task){
        this.tasks.add(task);
    }

    /**
     * Returns the task at the specified index in this {@code Stash}.
     *
     * @param index the index of the task to retrieve
     * @return the task at the specified index
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size())
     */
    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    /**
     * Deletes the task at the specified index from this {@code Stash}.
     *
     * @param index the index of the task to delete
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size())
     */
    public void deleteTask(int index){
        this.tasks.remove(index);
    }

    /**
     * Returns the number of tasks in this {@code Stash}.
     *
     * @return the number of tasks in this {@code Stash}
     */
    public int ObtainTaskCount() {
        int curr = this.tasks.size();
        return curr;
    }

    /**
     * Prints all the tasks in this {@code Stash} to the console, followed by the
     * number of tasks in the Stash.
     */
    public void printTasks() {
        for (Task task : tasks) {
            System.out.printf(" %s", task.toString());
            System.out.print("\n");
        }
        System.out.printf("Currently, you have %d tasks(s) in your ToDo list.", ObtainTaskCount());
        System.out.print("\n");
    }
    /**
     * Searches for all tasks in this {@code Stash} whose description contains the
     * specified keyword, and prints their string representations to the console.
     *
     * @param keyword the keyword to search for in task descriptions
     */
    public void searchTask(String keyword){
        for (Task task: tasks){
            if (task.getDescription().contains(keyword)){
                System.out.printf(" %s", task.toString());
            }
        }
    }

    /**
     * Saves the list of tasks to a file. Each task is converted to a Save object and stored in an ArrayList.
     * The Save objects are then passed to the Storage class for saving to file.
     */
    public void saveTasksToFile() {
        ArrayList<Save> saveTasks = new ArrayList<>();
        for (Task task : this.tasks) {
            if (task instanceof Todo) {
                Todo todo = (Todo) task;
                saveTasks.add(new Save("[T]", todo.isDone(), todo.getDescription(), "", "", ""));
            } else if (task instanceof Event) {
                Event event = (Event) task;
                saveTasks.add(new Save("[E]", event.isDone(), event.getDescription(), "", event.getStart(), event.getEnd()));
            } else if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                saveTasks.add(new Save("[D]", deadline.isDone(), deadline.getDescription(), deadline.getDue(), "", ""));
            }
        }
        Storage.saveTasks(saveTasks.stream().map(Save::bucketConverter).collect(Collectors.toCollection(ArrayList::new)));
    }



}
