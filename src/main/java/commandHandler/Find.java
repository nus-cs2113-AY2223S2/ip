package commandHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import data.tasksList;
import duke.Task;
import ui.Display;

/**
 * Represents the Find feature in the Duke program. Users may specify the
 * keyword(s) to be searched. e.g., <code>/find lunch</code>/
 */
public class Find {
    private final Set<String> keywords;

    public Find(String keywords) {
        String[] keywordsArray = keywords.toLowerCase().split(" ");
        this.keywords = new HashSet<>(Arrays.asList(keywordsArray));
        searchTasksList();
    }

    public void searchTasksList() {
        ArrayList<Task> matchingTasksList = new ArrayList<>();
        ArrayList<Task> userTasksList = tasksList.getTaskArrayList();
        for (Task task : userTasksList) {
            Set<String> taskDescription = new HashSet<String>(Arrays.asList(task.description.toLowerCase().split(" ")));
            if (!Collections.disjoint(taskDescription, keywords)) {
                matchingTasksList.add(task);
            }
        }
        listMatchingTasks(matchingTasksList);

    }

    /**
     * Displays the list of matching tasks to users.
     * 
     * @param list List containing matching tasks to be printed.
     */
    public void listMatchingTasks(ArrayList<Task> list) {
        if (list.size() == 0) {
            Display.warnUser("No matching tasks found!");
            return;
        }
        Display.notifyUser("These are the tasks that matched your keyword!");
        for (int i = 0; i < list.size(); i++) {
            Display.printLine();
            int index = i;
            index++;
            System.out.print(index + ".");
            System.out.println(list.get(i));
            Display.printLine();
        }
    }

}
