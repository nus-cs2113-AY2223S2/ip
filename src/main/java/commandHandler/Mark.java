package commandHandler;

import userInputParser.Parser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import data.ProcessStorageTasks;
import data.tasksList;
import duke.Task;
import ui.Display;

/**
 * Represents the mark feature in Duke program. Users may specify the task index
 * they would like to mark done/undone e.g., <code>/mark 3</code> or
 * <code>/unmark 3</code>
 */
public class Mark {

    /**
     * Marks user specified task to be done/undone.
     * 
     * @param index  Task index to be marked.
     * @param action Desired action to executed.
     */
    public static void markTask(String index, Parser.MarkType action) {
        try {
            int taskIndex = Integer.parseInt(index);
            taskIndex--;
            Task task = tasksList.getTaskArrayList().get(taskIndex);
            if (action.equals(Parser.MarkType.MARK)) {
                if (task.getDoneStatus()) {
                    Display.warnUser("Task is already marked!");
                    return;
                }
                task.markAsDone();
                Display.notifyUser("The following task has been marked done: [X] " + task.description);
            } else {
                if (!task.getDoneStatus()) {
                    Display.warnUser("Task is already unmarked!");
                    return;
                }
                task.markAsUndone();
                Display.notifyUser("The following task has been unmarked: [ ] " + task.description);
            }
            markStorageTask(taskIndex, action);
        } catch (Exception e) {
            Display.warnUser("Please enter a valid numerical index of the task!");
            return;
        }
    }

    /**
     * Marks corresponding task in storage text file to be done/undone.
     * 
     * @param index  Task index to be marked.
     * @param action Desired action to executed.
     */
    public static void markStorageTask(int index, Parser.MarkType action) {
        String indexToRemove = Integer.toString(index);
        ArrayList<String> savedTasksList = new ArrayList<>();
        /* copy all saved tasks into ArrayList */
        try {
            savedTasksList.addAll(Files.readAllLines(Paths.get(ProcessStorageTasks.FILE_PATH)));
        } catch (IOException e) {
            Display.warnUser(e.getMessage());
        }
        /* edit the selected task */
        try {
            File file = new File(ProcessStorageTasks.FILE_PATH);
            Scanner s = new Scanner(file);
            while (s.hasNextLine()) {
                String line = s.nextLine();
                String[] lineArray = line.split(":");
                String storageTaskIndex = lineArray[0];
                if (storageTaskIndex.equals(indexToRemove)) {
                    String storageTaskMarkIndication = lineArray[2];
                    if (action == Parser.MarkType.MARK) {
                        storageTaskMarkIndication = "true";
                    } else {
                        storageTaskMarkIndication = "false";
                    }
                    savedTasksList.set(Integer.parseInt(indexToRemove), String.join(":", lineArray));
                }
            }
            s.close();
        } catch (IOException e) {
            Display.warnUser(e.getMessage());
        }
        /* rewrite tasks into tasks.txt */
        try {
            FileWriter writer = new FileWriter(ProcessStorageTasks.FILE_PATH);
            for (String task : savedTasksList) {
                writer.write(task + "\n");
            }
            writer.close();
        } catch (IOException e) {
            Display.warnUser(e.getMessage());
        }
    }
}
