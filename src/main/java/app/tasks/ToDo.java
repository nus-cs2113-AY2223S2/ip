package app.tasks;

import app.exceptions.IncompleteCommandException;
import app.save.FileManager;
import java.io.IOException;

import java.util.ArrayList;

public class ToDo extends Task {
    public static final String line = ("─".repeat(50));

    public ToDo(String taskDescription, boolean isDone) {
        super(taskDescription, isDone);
    }

    @Override
    public String toString() {
        return " [T][" + getStatusIcon() + "] " + taskDescription;
    }

    public static void todoHandler(ArrayList<Task> tasks, String commandDescriptor) {
        try {
            System.out.println(line);
            if (commandDescriptor.length() == 0) {
                throw new IncompleteCommandException();
            }
            System.out.println(line);
            tasks.add(tasks.size(), new ToDo(commandDescriptor, false));
            System.out.println("Got it. I've added this task:");
            System.out.println(tasks.get(tasks.size() - 1));
            System.out.printf("Now you have %d tasks in the list.\n", tasks.size());
            System.out.println(line);
            try {
                FileManager.saveTasks(tasks);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } catch (IncompleteCommandException e) {
            e.printErrorMessage("todo");
            System.out.println(line);
        }
    }
}
