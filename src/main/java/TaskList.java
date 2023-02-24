import java.util.ArrayList;
import tasks.Deadline;
import tasks.Event;
import tasks.Todo;
import java.util.Iterator;
import java.lang.Iterable;

public class TaskList {
    private ArrayList<Todo> itemList = new ArrayList<Todo>(0);

    public void addItem(Todo item) {
        itemList.add(item);
    }

    public Todo get(int index) {
        return itemList.get(index-1);
    }

    public Todo remove(int index) {
        index--;
        Todo itemToDelete = itemList.get(index);
        itemList.remove(index);
        return itemToDelete;
    }

    public int size() {
        return itemList.size();
    }

    public Iterator<Todo> iterator() {
        return itemList.iterator();
    }

    public ArrayList<Todo> asList() {
        return itemList;
    }

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
