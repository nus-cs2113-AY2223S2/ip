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
            return "todo " + task.getDescription();
        }
        if (task.getClass().equals(Deadline.class)) {
            return "deadline " + task.getDescription() + ((Deadline) task).getBy();
        }
        if (task.getClass().equals(Event.class)) {
            return "event " + task.getDescription() + " /" + ((Event) task).getFrom() + " /" + ((Event) task).getTo();
        }
        return "";
    }

    private static void writeSavedTasks(String filePath, ArrayList<Task> taskDataToWrite) throws IOException {
        FileWriter tasksDataWriter = new FileWriter(filePath);
        for (Task task : taskDataToWrite) {
            tasksDataWriter.write(getFileEntry(task));
        }
        tasksDataWriter.close();
    }
}
