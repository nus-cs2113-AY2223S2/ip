package Duke.DukeFunction;

import Duke.DukeCommandLine.DukeException;
import Duke.DukeTask.DukeDeadline;
import Duke.DukeTask.DukeEvent;
import Duke.DukeTask.DukeTask;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class DukeStorage {
    private static final String FILE_PATH = "data/list.txt";
    private static final int TASK_TYPE_INDEX = 0;
    private static final int TASK_STATUS_INDEX = 1;
    private static final int TASK_NAME_INDEX = 2;
    private static final int TASK_BY_INDEX = 3;
    private static final int TASK_FROM_INDEX = 3;
    private static final int TASK_TO_INDEX = 4;
    private static final String TASK_TYPE_DEADLINE = "D";
    private static final String TASK_TYPE_EVENT = "E";
    private static final String TASK_TYPE_TODO = "T";
    private static final String TASK_STATUS_DONE = "1";

    /**
     * Saves the task list to the file.
     * @param tasks the task list to be saved.
     * @throws DukeException if the file cannot be saved.
     */
    public void saveTask(DukeList tasks) throws DukeException {
        File file = new File(FILE_PATH);
        try {
            if(!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(FILE_PATH);
            tasks.storeTask(fileWriter);
            fileWriter.close();
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
    }

    /**
     * Loads the task list from the file.
     * @param tasks the task list to be loaded.
     * @throws DukeException if the file cannot be loaded or there is no such file.
     */
    public void loadTask(DukeList tasks) throws DukeException {
        File file = new File(FILE_PATH);
        try {
            if(!file.exists()) {
                throw new DukeException("No file to load!");
            }
            Scanner fileReader = new Scanner(file);
            String TaskLine;
            while(fileReader.hasNextLine()) {
                TaskLine = fileReader.nextLine();
                String[] taskInfo = TaskLine.split(" \\| ");
                String taskType = taskInfo[TASK_TYPE_INDEX].trim();
                String taskStatus = taskInfo[TASK_STATUS_INDEX].trim();
                String taskName = taskInfo[TASK_NAME_INDEX].trim();
                switch (taskType) {
                case TASK_TYPE_TODO:
                    DukeTask task = new DukeTask(taskName);
                    if(taskStatus.equals(TASK_STATUS_DONE)) {
                        task.markAsDone();
                    }
                    tasks.addTask(task);
                    break;
                case TASK_TYPE_DEADLINE:
                    String taskBy = taskInfo[TASK_BY_INDEX].trim();
                    DukeTask deadline = new DukeDeadline(taskName, taskBy);
                    if(taskStatus.equals(TASK_STATUS_DONE)) {
                        deadline.markAsDone();
                    }
                    tasks.addTask(deadline);
                    break;
                case TASK_TYPE_EVENT:
                    String taskFrom = taskInfo[TASK_FROM_INDEX].trim();
                    String taskTo = taskInfo[TASK_TO_INDEX].trim();
                    DukeTask event = new DukeEvent(taskName, taskFrom, taskTo);
                    if(taskStatus.equals(TASK_STATUS_DONE)) {
                        event.markAsDone();
                    }
                    tasks.addTask(event);
                    break;
                default:
                    throw new DukeException("Sorry, I can't read the file, the format is wrong");
                }
            }
        } catch (IOException e) {
            throw new DukeException("[IOException] Sorry, I can't load the file, " + e.getMessage());
        }
    }

}
