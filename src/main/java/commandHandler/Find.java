package commandHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import data.tasksList;
import duke.Task;
import ui.Display;
import ui.exceptions.MissingCommandException;

public class Find {
    private final Set<String> keywords;

    public Find(String userInput) throws MissingCommandException {
        String[] userInputArray = userInput.split(" ");
        if (userInputArray.length < 2) {
            throw new MissingCommandException("Please specify at least 1 keyword!");
        }
        String[] keywords = userInput.split(" ", 2)[1].split(" ");
        this.keywords = new HashSet<>(Arrays.asList(keywords));
        searchTasksList();
    }

    public void searchTasksList() {
        ArrayList<Task> matchingTasksList = new ArrayList<>();
        ArrayList<Task> userTasksList = tasksList.getTaskArrayList();
        for (Task task : userTasksList) {
            Set<String> taskDescription = new HashSet<String>(Arrays.asList(task.description.split(" ")));
            if (!Collections.disjoint(taskDescription, keywords)) {
                matchingTasksList.add(task);
            }
        }
        listMatchingTasks(matchingTasksList);

    }

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
