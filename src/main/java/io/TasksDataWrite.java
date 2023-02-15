package io;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class TasksDataWrite {
    private static String getFileEntry(Task task) {
        if (task.getClass().equals(Todo.class)) {
            return "todo " + task.getDescription() + '/' + task.isDone();
        }
        if (task.getClass().equals(Deadline.class)) {
            return "deadline " + task.getDescription() + '/' + ((Deadline) task).getBy() + '/' + task.isDone();
        }
        if (task.getClass().equals(Event.class)) {
            return "event " + task.getDescription() + '/' + ((Event) task).getFrom() + '/' + ((Event) task).getTo() + '/' + task.isDone();
        }
        return "";
    }

    public static void writeSavedTasks(String filePath, ArrayList<Task> taskDataToWrite) throws IOException {
        //TODO: Account for '/' in entries.
        FileWriter tasksDataWriter = new FileWriter(filePath);
        for (Task task : taskDataToWrite) {
            tasksDataWriter.write(getFileEntry(task) + '\n');
        }
        tasksDataWriter.close();
    }
}
