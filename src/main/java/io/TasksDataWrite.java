package io;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This class handles the writing of the list of tasks of the current session to a persistent txt file to be read in
 * future sessions.
 */
public class TasksDataWrite {
    /**
     * @param task The task to be formatted.
     * @return A string formatted with delimiting keywords to be accepted by the reader.
     */
    private static String getFileEntry(Task task) {
        if (task.getClass().equals(Todo.class)) {
            return "todo " + task.getDescription() + "/marked" + task.isDone();
        }
        if (task.getClass().equals(Deadline.class)) {
            return "deadline " + task.getDescription() + "/by" + ((Deadline) task).getBy() + "/marked" + task.isDone();
        }
        if (task.getClass().equals(Event.class)) {
            return "event " + task.getDescription() + "/from" + ((Event) task).getFrom() + "/to" + ((Event) task).getTo() + "/marked" + task.isDone();
        }
        return "";
    }

    /**
     * @param filePath A string representing the relative path towards the data txt file stored between sessions.
     * @param taskDataToWrite The ArrayList of tasks that was filled by the user in the current session.
     * @throws IOException Thrown when the string cannot be written to the file.
     */
    public static void writeSavedTasks(String filePath, ArrayList<Task> taskDataToWrite) throws IOException {
        //TODO: Account for '/' in entries.
        FileWriter tasksDataWriter = new FileWriter(filePath);
        for (Task task : taskDataToWrite) {
            tasksDataWriter.write(getFileEntry(task) + '\n');
        }
        tasksDataWriter.close();
    }
}
