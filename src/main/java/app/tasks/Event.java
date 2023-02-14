package app.tasks;

import app.exceptions.IncompleteCommandException;
import app.save.FileManager;

import java.io.IOException;

import java.util.ArrayList;

public class Event extends Task {
    public static final String line = ("─".repeat(50));
    protected String startTime;
    protected String endTime;

    public Event(String taskDescription, String startTime, String endTime) {
        super(taskDescription);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    @Override
    public String toString() {
        return " [E][" + getStatusIcon() + "] " + taskDescription + " (from: " + startTime + " to: " + endTime + ")";
    }

    public static void eventHandler(ArrayList<Task> tasks, String commandDescriptor) {
        try {
            System.out.println(line);
            if (commandDescriptor.length() == 0) {
                throw new IncompleteCommandException();
            }
            String[] fromParts = commandDescriptor.split("/from");
            String taskDescription = fromParts[0].trim();
            String[] toParts = fromParts[1].split("/to");
            String startTime = toParts[0].trim();
            String endTime = toParts[1].trim();
            tasks.add(tasks.size(), new Event(taskDescription, startTime, endTime));
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
            e.printErrorMessage("event");
            System.out.println(line);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("ONO! Please enter a valid command.");
            System.out.println(line);
        }
    }
}
