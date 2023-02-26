package DukeFunctions;


import java.util.ArrayList;

public class TaskList {

    public ArrayList<Todo> TodoList;

    public TaskList() {
        TodoList = new ArrayList<>();
    }

    public TaskList(ArrayList<Todo> savedList) {
        TodoList = savedList;

    }

    public void add(Todo newTask) {

        TodoList.add(newTask);
    }

    public int size() {
        return TodoList.size();
    }

    public Todo get(int i) {
        return TodoList.get(i);
    }

    public void clear() {
        TodoList.clear();
    }

    public void remove(int i) {
        TodoList.remove(i);
    }

    public void find(Ui ui, String keyword) {

        TaskList searchResults = new TaskList();
        this.TodoList.forEach(todo -> {
            if (todo.description.contains(keyword)) {
                searchResults.add(todo);
            }
        });
        ui.printSearchResults(searchResults);
    }

}
