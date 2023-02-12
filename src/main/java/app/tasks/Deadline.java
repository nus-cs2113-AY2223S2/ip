package app.tasks;

import app.exceptions.IncompleteCommandException;

import java.util.ArrayList;

public class Deadline extends Task{
    public static final String line = ("─".repeat(50));
    protected String by;
    public Deadline(String taskDescription, String by) {
        super(taskDescription);
        this.by = by;
    }

    @Override
    public String toString(){
        return " [D][" + getStatusIcon() + "] " + taskDescription + "(by:" + by + ")";
    }

    public static void deadlineHandler(ArrayList<Task> tasks, String commandDescriptor) {
        try {
            System.out.println(line);
            if (commandDescriptor.length() == 0) {
                throw new IncompleteCommandException();
            }
            String[] parts = commandDescriptor.split("/by");
            String taskDescription = parts[0];
            String deadline = parts[1];
            tasks.add(tasks.size(), new Deadline(taskDescription, deadline));
            System.out.println("Got it. I've added this task:");
            System.out.println(tasks.get(tasks.size() - 1));
            System.out.printf("Now you have %d tasks in the list.\n", tasks.size());
            System.out.println(line);
        } catch (IncompleteCommandException e){
            e.printErrorMessage("deadline");
            System.out.println(line);
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println("ONO! Please enter a valid command.");
            System.out.println(line);
        }
    }
}
