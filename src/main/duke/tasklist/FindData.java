package duke.tasklist;

import duke.task.Task;

import java.util.HashMap;
import java.util.LinkedHashSet;

/**
 * Represents all data required for <code>find</code> function.
 */
public class FindData {
    private final HashMap<String, LinkedHashSet<Integer>> findTable;

    /**
     * Initializes data for <code>find</code> functions.
     */
    public FindData() {
        this.findTable = new HashMap<>();
    }

    /**
     * Function to add a task object into this object.
     * Adds individual strings separated by whitespace <code>" "</code> as individual keywords.
     *
     * @param task The task object involved.
     * @param index The number id of the task object involved.
     */
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
            if (findTable.get(extraKeyWord.trim()) == null) {
                LinkedHashSet<Integer> temp = new LinkedHashSet<>();
                temp.add(index);
                findTable.put(extraKeyWord.trim(), temp);
            } else {
                findTable.get(extraKeyWord.trim()).add(index);
            }
        }
    }

    /**
     * Deletes the task object involved when <code>delete</code> is called.
     * Shifts all the entries such that the number id of each task object is in line with their number id inside
     * <code>TaskData</code>.
     *
     * @param deletedIndex The number id of the task object deleted.
     */
    public void handleDelete (int deletedIndex) {
        for (String str: findTable.keySet()) {
            findTable.get(str).remove(deletedIndex);
            for (int i : findTable.get(str)) {
                if (i > deletedIndex) {
                    findTable.get(str).remove(i);
                    findTable.get(str).add(i - 1);
                }
            }
        }
    }

    /**
     * Finds all the task objects with the same keyword as the user input.
     *
     * @param keyword The user input keyword.
     * @return Returns a <code>LinkedHashSet</code> of all the number id of the task objects involved.
     * Returns an empty <code>LinkedHashSet</code> if no matching task objects.
     */
    public LinkedHashSet<Integer> findKeyword(String keyword) {
        if (findTable.get(keyword.trim()) == null) {
            return new LinkedHashSet<>();
        } else {
            return findTable.get(keyword.trim());
        }
    }
}
