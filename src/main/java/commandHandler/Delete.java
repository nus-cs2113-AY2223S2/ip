package commandHandler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import data.ProcessStorageTasks;
import data.tasksList;
import duke.Task;
import ui.Display;

/**
 * Represents the Delete feature in the Duke program. Users may specify the
 * index of the task to be deleted e.g., <code>/delete 3</code>/
 */
public class Delete {

    /**
     * Deletes user specified task from Duke program task list.
     * 
     * @param taskIndex index of task to be deleted.
     */
    public static void deleteTask(int taskIndex) {
        try {
            ArrayList<Task> taskArrayList = tasksList.getTaskArrayList();
            taskIndex--;
            Display.notifyUser("The following task has been removed: [ ] "
                    + taskArrayList.get(taskIndex).description);
            tasksList.deleteTask(taskIndex);
            deleteSavedTask(taskIndex);
            tasksList.userTaskCount--;
        } catch (Exception e) {
            Display.warnUser("Please enter a valid numerical index of the task!");
        }
    }

    /**
     * Deletes user specified task from savedTasks.txt file.
     * 
     * @param taskIndex index of task to be deleted.
     */
    public static void deleteSavedTask(int taskIndex) {
        String indexToRemove = Integer.toString(taskIndex);
        ArrayList<String> savedTasksList = new ArrayList<>();
        int newTaskIndex = 0;
        /* Add all tasks to ArrayList except task to be deleted */
        try {
            File file = new File(ProcessStorageTasks.FILE_PATH);
            Scanner s = new Scanner(file);
            while (s.hasNextLine()) {
                String line = s.nextLine();
                String[] lineArray = line.split(":");
                if (!(lineArray[0].equals(indexToRemove))) {
                    lineArray[0] = Integer.toString(newTaskIndex);
                    String newTaskString = String.join(":", lineArray);
                    savedTasksList.add(newTaskString);
                    newTaskIndex++;
                }
            }
            s.close();
        } catch (IOException e) {
            Display.warnUser("Error accessing file!");
        }
        /* rewrite tasks into savedTasks.txt */
        try {
            FileWriter writer = new FileWriter(ProcessStorageTasks.FILE_PATH);
            for (String task : savedTasksList) {
                writer.write(task + "\n");
            }
            writer.close();
        } catch (IOException e) {
            Display.warnUser("Error accessing file!");
        }
    }
}
