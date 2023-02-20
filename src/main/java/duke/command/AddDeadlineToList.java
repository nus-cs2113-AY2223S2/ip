package duke.command;

import duke.data.TaskData;
import duke.exceptions.ListTooLarge;
import duke.task.Deadline;
import duke.task.Task;

public class AddDeadlineToList extends Command {
    private Deadline newTask;

    public AddDeadlineToList(String[] userInputArray) {
        setTasks(userInputArray);
    }

    @Override
    public void executeCommand(TaskData taskData) {
        taskData.add(newTask);
        System.out.println("Got it! Added \n"
                + "[D][ ] " + newTask.getDescription() + newTask.getDueDate() + "\n"
                + "to the list.");
        System.out.println("Now you have " + taskData.size() + " task(s) in the list.");
    }

    public void setTasks(String[] userInputArray) {
        try {
            String description = userInputArray[0];
            String dueBy = userInputArray[1];
            newTask = new Deadline(description, dueBy);
        } catch (IndexOutOfBoundsException outOfBounds) {
            System.out.println("Please input all the necessary details");
        }
    }


}
