package duke.storage;

import duke.task.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class StorageMaster {

    private StorageSearcher storageSearcher;
    private StorageEnumerator storageEnumerator;
    private ArrayList<String> tasks;

    public StorageMaster(StorageSearcher storageSearcher, StorageEnumerator storageEnumerator) {
        this.storageSearcher = storageSearcher;
        this.storageEnumerator = storageEnumerator;
        this.tasks = storageSearcher.getTasks();
    }

    public StorageSearcher getStorageSearcher() {
        return storageSearcher;
    }

    public StorageEnumerator getStorageEnumerator() {
        return storageEnumerator;
    }

    public Task getByIndex(int index) {
        return storageEnumerator.get(index);
    }

    public ArrayList<Task> listTasks() {
        return storageEnumerator.getList();
    }

    public Task getByName(String taskName) {
        if (!tasks.contains(taskName)) {
            //throw exception
        }
        return storageSearcher.get(taskName);
    }

    public void addTask(Task task) {

        tasks.add(task.getName());
        storageEnumerator.add(task);
        storageSearcher.add(task);
    }


    public void removeTask(int uid) {
        if (tasks.size() > uid) {
            //throw exception if array list out of bounds
        }
        String task = tasks.get(uid);
        tasks.remove(uid);
        storageEnumerator.delete(task);
        storageSearcher.delete(task);
    }

    public ArrayList<String> listTasks() {
        ArrayList<String> tasksList = new ArrayList<String>();
        for (Task task : storageEnumerator.getList()) {
            tasksList.add(task.getDescription());
        }
        return tasksList;
    }
}
