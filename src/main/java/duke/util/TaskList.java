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
                DeleteTask(itemNumber);
            }
            Storage.saveDataFromInput(lists);
        } catch (IndexOutOfBoundsException e) {
            ui.printError();
        }
    }

    public void DeleteTask(int itemNumber) {
        int index = itemNumber - 1;
        Task item = lists.get(index);
        ui.printRemoveTask(item);
        lists.remove(index);
        ui.printListSize(lists.size());
    }
}
