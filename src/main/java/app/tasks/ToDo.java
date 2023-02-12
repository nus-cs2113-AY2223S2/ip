package app.tasks;

import app.exceptions.IncompleteCommandException;

import java.util.ArrayList;

public class ToDo extends Task {
    public static final String line = ("─".repeat(50));

    public ToDo(String taskDescription) {
        super(taskDescription);
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
            tasks.add(tasks.size(), new ToDo(commandDescriptor));
            System.out.println("Got it. I've added this task:");
            System.out.println(tasks.get(tasks.size() - 1));
            System.out.printf("Now you have %d tasks in the list.\n", tasks.size());
            System.out.println(line);
        } catch (IncompleteCommandException e) {
            e.printErrorMessage("todo");
            System.out.println(line);
        }
    }
}
