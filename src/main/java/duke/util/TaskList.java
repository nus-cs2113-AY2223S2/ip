package duke.util;

import duke.task.Task;

import java.util.ArrayList;

public class TaskList {
    public static ArrayList<Task> lists;
    private Ui ui;

    public TaskList (ArrayList<Task> lists) {
        this.lists = lists;
    }


    public void listTasks() {
        System.out.println("Here are the tasks in your list:");
        int itemNumber = 1;
        for (Task item : lists) {
            if ((itemNumber - 1) == lists.size()) {
                break;
            }
            item.printTask(itemNumber);
            itemNumber++;
        }
    }

    public void markOrDeleteTask(String[] words, String line) {
        try {
            int itemNumber = Integer.parseInt(words[1]);
            if (line.startsWith("mark")) {
                lists.get(itemNumber - 1).markAsDone();
            } else if (line.startsWith("unmark")) {
                lists.get(itemNumber - 1).markAsUndone();
            } else {
                deleteTask(itemNumber);
            }
            Storage.saveDataFromInput(lists);
        } catch (IndexOutOfBoundsException e) {
            ui.printError();
        }
    }

    public void deleteTask(int itemNumber) {
        int index = itemNumber - 1;
        Task item = lists.get(index);
        ui.printRemoveTask(item);
        lists.remove(index);
        ui.printListSize(lists.size());
    }

    public void searchForTask(String inputLine) throws IndexOutOfBoundsException {
        try {
            String[] lines = inputLine.split(" ", 2);
            String searchTerm = lines[1];
            ArrayList<Task> searchResults = new ArrayList<>();
            for (Task item : lists) {
                if (item.description.contains(searchTerm)) {
                    searchResults.add(item);
                }
            }
            ui.printSearchResults(searchResults);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("You must include a search term for your search.");
        }
    }
}
