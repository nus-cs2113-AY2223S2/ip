import java.util.ArrayList;
import tasks.Deadline;
import tasks.Event;
import tasks.Todo;
import java.util.Iterator;
import java.lang.Iterable;

public class TaskList {
    private ArrayList<Todo> itemList = new ArrayList<Todo>(0);

    /**
     * Adds the given item to the tasklist Instance
     * 
     * @param item The Task instance (Todo/ Deadline/ Event) to be added into the array of tasks
     */
    public void addItem(Todo item) {
        itemList.add(item);
    }

    /**
     * Retrieves the item in the array of tasks that has the given index
     * Indices start from 1 (not 0)
     * 
     * @param index The index of the Task instance (Todo/ Deadline/ Event) to be retrieved
     * @return The Task Instance (Todo/ Deadline/ Event) retrieved
     */
    public Todo get(int index) {
        return itemList.get(index-1);
    }

    /**
     * Removes the item in the array of tasks that has the given index
     * Indices start from 1 (not 0)
     * 
     * @param index The index of the Task instance (Todo/ Deadline/ Event) to be removed
     * @return The Task Instance (Todo/ Deadline/ Event) removed
     */
    public Todo remove(int index) {
        index--;
        Todo itemToDelete = itemList.get(index);
        itemList.remove(index);
        return itemToDelete;
    }

    /**
     * Gets the length of the array of tasks
     */
    public int size() {
        return itemList.size();
    }

    /**
     * Gets the TaskList as an ArrayList instance of <Todo>s
     * Typically used for for-each loops in the programme like:
     * for (Todo item: itemList) {
     * }
     */
    public ArrayList<Todo> asList() {
        return itemList;
    }

    /**
     * Finds the jobs that seem like the given input
     * 
     * @param nameLike The name that is used as reference for search. 
     * The actual name returned should be in the format "*${nameLike}*" where each * can represent any number of 
     * character, including the case of 0 characters
     * @return A new array of tasks that are like the given input
     */
    public ArrayList<Todo> findLike(String nameLike) {
        ArrayList<Todo> results = new ArrayList<Todo>(0);
        for (Todo item: itemList) {
            if (item.getDescription().contains(nameLike)) {
                results.add(item);
            }
        }
        return results;
    }
}
