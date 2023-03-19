package DukeFunctions;


import java.util.ArrayList;

public class TaskList {

    public ArrayList<Todo> TodoList;

    /**
     * Constructs an empty TaskList
     */
    public TaskList() {
        TodoList = new ArrayList<>();
    }

    /**
     * Constructs a TaskList from an arraylist. Called when constructing
     * a TaskList from a saved file
     *
     * @param savedList the arraylist used to construct the Tasklist
     */
    public TaskList(ArrayList<Todo> savedList) {
        this.TodoList = savedList;

    }

    /**
     * Adds a task to the task list.
     *
     * @param newTask task to be added
     */
    public void add(Todo newTask) {

        this.TodoList.add(newTask);
    }

    /**
     * Gets the number of tasks in the list.
     *
     * @return the number of tasks in the tsk list
     */
    public int size() {
        return TodoList.size();
    }

    /**
     * Gets a task at index i in the list
     *
     * @param i the index of the task to be retrieved
     * @return task at the specified index
     */
    public Todo get(int i) {
        return TodoList.get(i);
    }

    /**
     * Removes all tasks from the task list.
     */
    public void clear() {
        TodoList.clear();
    }


    /**
     * Removes a task from the task list.
     *
     * @param i the index of the task to be removed
     */
    public void remove(int i) {
        TodoList.remove(i);
    }

    public void find(Ui ui, String keyword) {

        TaskList searchResults = new TaskList();
        this.TodoList.forEach(todo -> {
            if (todo.getDescription().contains(keyword)) {
                searchResults.add(todo);
            }
        });
        ui.printSearchResults(searchResults);
    }

}
