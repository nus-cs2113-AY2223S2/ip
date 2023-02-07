package app.tasks;

import app.exceptions.IncompleteCommandException;

public class Event extends Task{
    public static final String line = ("─".repeat(50));
    protected String startTime;
    protected String endTime;

    public Event(String taskDescription, String startTime, String endTime) {
        super(taskDescription);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString(){
        return "[E][" + getStatusIcon() + "] " + taskDescription + " (from: " + startTime  + " to: " + endTime + ")" ;
    }

    public static void eventHandler(Task[] tasks, int[] index, String commandDescriptor) {
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
            tasks[index[0]] = new Event(taskDescription, startTime, endTime);
            System.out.println("Got it. I've added this task:");
            System.out.println(tasks[index[0]]);
            System.out.printf("Now you have %d tasks in the list.\n", index[0] + 1);
            System.out.println(line);
            index[0]++;
        } catch (IncompleteCommandException e){
            e.printErrorMessage("event");
            System.out.println(line);
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println("ONO! Please enter a valid command.");
            System.out.println(line);
        }
    }
}
