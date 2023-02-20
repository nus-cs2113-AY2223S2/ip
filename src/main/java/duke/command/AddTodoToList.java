package duke.command;

import duke.data.TaskData;
import duke.task.Task;

public class AddTodoToList extends Command {
    private Task newTask;

    public AddTodoToList(String description) {
        setTasks(description);
    }

    @Override
    public void executeCommand(TaskData taskData) {
        taskData.add(newTask);
        System.out.println("Got it! Added \n"
                + "[T][ ] " + newTask.getDescription() + "\n"
                + "to the list.");
        System.out.println("Now you have " + taskData.size() + " task(s) in the list.");
    }

    public void setTasks(String description) {
        this.newTask = new Task(description);
    }


}
