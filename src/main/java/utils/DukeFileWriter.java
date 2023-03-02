package utils;

import task.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * A tool for writing the task list to the local file.
 * The Format of the local file:
 * Normal task:   A | isDone | [description]
 * Todo task:     T | isDone | [description]
 * Deadline task: D | isDone | [description] | [Deadline]
 * Event task:    E | isDone | [description] | [from] | [to]
 */
public class DukeFileWriter {
    protected static String filePath;

    public DukeFileWriter(String filePath){
        this.filePath = filePath;
    }

    private static void addLineToFile(String line) throws IOException {
        FileWriter fw = new FileWriter(filePath,true);
        fw.write(line);
        fw.close();
    }

    /**
     * Called when adding a todo / deadline / event object. Input: new task object
     * @param newObject The new task object the user added.
     * @throws IOException
     */
    public static void addNewObjectToFile(Task newObject) throws IOException {
        String line = getObjectLineString(newObject);
        addLineToFile(line);
    }

    /**
     * Receive a Todo / Deadline / Event Object and turn it into a new LineString in the file.
     * @param newObject A task object to write to local files.
     * @return The line string of the object in the file.
     */
    private static String getObjectLineString(Task newObject){
        String line = null;
        String lineType = newObject.getClass().getTypeName();
        switch (lineType) {
            case("task.Task"):
            case("task.Todo"):{
                line = newObject.getLetter()+ "|" + newObject.getIntStatus() +
                        "|" + newObject.getDescription() + "\n";
                break;
            } case("task.Deadline"):{
                Deadline newDeadline = (Deadline) newObject;
                line = newDeadline.getLetter()+ "|" + newDeadline.getIntStatus() +
                        "|" + newDeadline.getDescription() + "|" + newDeadline.getBy() + "\n";
                break;
            } case("task.Event"): {
                Event newEvent = (Event) newObject;
                line = newEvent.getLetter() + "|" + newEvent.getIntStatus() +
                        "|" + newEvent.getDescription() + "|" + newEvent.getFrom() +
                        "|" + newEvent.getTo() + "\n";
                break;
            }
        }
        return line;
    }

    /**
     * Rewrite the whole task list in the memory to the disk.
     * @param tasks The whole task list.
     * @throws IOException
     */
    public void rewriteAllToFile(TaskList tasks) throws IOException {
        ArrayList<Task> tasksList = tasks.getTasks();
        FileWriter fw = new FileWriter(filePath, false);fw.close();
        for(Task task : tasksList){
            addNewObjectToFile(task);
        }
    }

}
