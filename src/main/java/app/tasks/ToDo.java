package app.tasks;

import app.exceptions.IncompleteCommandException;
import app.save.FileManager;
import java.io.IOException;

public class ToDo extends Task{
    public static final String line = ("─".repeat(50));
    public ToDo(String taskDescription) {
        super(taskDescription);
    }

    @Override
    public String toString(){
        return "[T][" + getStatusIcon() + "] " + taskDescription;
    }

    public static void todoHandler(Task[] tasks, int[] index, String commandDescriptor) {
        try {
            System.out.println(line);
            if (commandDescriptor.length() == 0) {
                throw new IncompleteCommandException();
            }
            System.out.println(line);
            tasks[index[0]] = new ToDo(commandDescriptor);
            System.out.println("Got it. I've added this task:");
            System.out.println(tasks[index[0]]);
            System.out.printf("Now you have %d tasks in the list.\n", index[0] + 1);
            System.out.println(line);
            try {
                FileManager.saveTasks(tasks);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            index[0]++;
        } catch (IncompleteCommandException e){
            e.printErrorMessage("todo");
            System.out.println(line);
        }
    }
}
