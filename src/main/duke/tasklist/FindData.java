package duke.tasklist;

import duke.task.Task;

import java.util.HashMap;
import java.util.HashSet;

public class FindData {
    private final HashMap<String, HashSet<Integer>> findTable;

    public FindData() {
        this.findTable = new HashMap<>();
    }

    public void addTask(Task task, int index) {
        String[] split = task.getTask().split("%", 3);
        String name = split[0];
        String[] keywords = name.split(" ");
        for (String str : keywords) {
            if (findTable.get(str) == null) {
                HashSet<Integer> temp = new HashSet<>();
                temp.add(index);
                findTable.put(str, temp);
            } else {
                findTable.get(str).add(index);
            }
        }
    }

    public void handleDelete (int deletedIndex) {
        for (String str: findTable.keySet()) {
            findTable.get(str).remove(deletedIndex);
            for (int i : findTable.get(str)) {
                if (i > deletedIndex) {
                    findTable.get(str).remove(i);
                    findTable.get(str).add(i + 1);
                }
            }
        }
    }

    public HashSet<Integer> findKeyword(String keyword) {
        if (findTable.get(keyword) == null) {
            return new HashSet<>();
        } else {
            return findTable.get(keyword);
        }
    }
}
