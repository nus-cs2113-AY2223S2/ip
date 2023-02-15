package app.tasks;

import app.save.FileManager;

import java.util.ArrayList;

public class Task {

    public static final String line = ("─".repeat(50));
    protected String taskDescription;
    protected boolean isDone;

    public Task(String taskDescription, boolean isDone) {
        setTaskDescription(taskDescription);
        setDone(isDone);
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (this.isDone ? "X" : " "); //Marks a task done with an X
    }

    public static void deleteTask(ArrayList<Task> tasks, String commandDescriptor) {
        try {
            int taskNumber = Integer.parseInt(commandDescriptor);
            System.out.println(line);
            System.out.println("Noted, I've removed this task:");
            System.out.println(tasks.get(taskNumber - 1));
            tasks.remove(taskNumber - 1);
            System.out.printf("Now you have %d tasks in the list.\n", tasks.size());
            try {
                FileManager.saveTasks(tasks);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } catch (NumberFormatException e) {
            System.out.println("ONO! Please choose a valid task to delete.");
        }
        System.out.println(line);
    }
    public static void taskStatusHandler(ArrayList<Task> tasks, String commandWord, String commandDescriptor) {
        System.out.println(line);
        int taskNumber = Integer.parseInt(commandDescriptor);
        if (commandWord.equals("mark")) {
            tasks.get(taskNumber - 1).setDone(true);
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(tasks.get(taskNumber - 1));
        } else {
            tasks.get(taskNumber - 1).setDone(false);
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(tasks.get(taskNumber - 1));
        }
        try {
            FileManager.saveTasks(tasks);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println(line);
    }

    public static void printTasks(ArrayList<Task> tasks) {
        System.out.println(line);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.print((i + 1) + ".");
            System.out.println(tasks.get(i));
        }
        System.out.println(line);
    }

    public String toString() {
        return null;
    }
}

