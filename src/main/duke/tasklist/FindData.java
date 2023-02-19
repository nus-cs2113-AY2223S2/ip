package duke.tasklist;

import duke.task.Task;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;

public class FindData {
    private final HashMap<String, LinkedHashSet<Integer>> findTable;

    public FindData() {
        this.findTable = new HashMap<>();
    }

    public void addTask(Task task, int index) {
        String[] split = task.getTask().split("%");
        String name = split[0];
        String[] keywords = name.split(" ");
        String extraKeyWord = "";
        for (String str : keywords) {
            if (findTable.get(str) == null) {
                LinkedHashSet<Integer> temp = new LinkedHashSet<>();
                temp.add(index);
                findTable.put(str, temp);
            } else {
                findTable.get(str).add(index);
            }
            extraKeyWord = extraKeyWord.concat(str + " ");
        }
        if (findTable.get(extraKeyWord) == null) {
            LinkedHashSet<Integer> temp = new LinkedHashSet<>();
            temp.add(index);
            findTable.put(extraKeyWord, temp);
        } else {
            findTable.get(extraKeyWord).add(index);
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

    public LinkedHashSet<Integer> findKeyword(String keyword) {
        if (findTable.get(keyword.trim()) == null) {
            return new LinkedHashSet<>();
        } else {
            return findTable.get(keyword.trim());
        }
    }
}
