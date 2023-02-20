package duke.command;

import duke.data.TaskData;
import duke.exceptions.ListTooLarge;
import duke.task.Event;
import duke.task.Task;

public class AddEventToList extends Command {
    private Event newTask;

    public AddEventToList(String description) {
        setTasks(description);
    }

    @Override
    public void executeCommand(TaskData taskData) {
        taskData.add(newTask);
        System.out.println("Got it! Added \n"
                + "[E][ ] " + newTask.getDescription() + newTask.getDuration() + "\n"
                + "to the list.");
        System.out.println("Now you have " + taskData.size() + " task(s) in the list.");
    }

    public void setTasks(String userInput) {
        final String[] userInputArray = userInput.trim().split("/from|/to");
        String description = userInputArray[0];
        String startTime = userInputArray[1];
        String endTime = userInputArray[2];
        this.newTask = new Event(description, startTime, endTime);
    }

}
