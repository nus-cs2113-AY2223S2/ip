import duke.task.Deadline;

public class DeadlineCommand extends Command {
    private Deadline deadline;

    public void setDeadline(String input) {
        String[] temp = input.split("deadline | /by "); //separates deadline description and time
        String description = temp[1];
        String by = temp[2];
        this.deadline = new Deadline(description, by);
    }

    @Override
    public void runCommand(String input, TaskList tasks, UI ui) {
        try {
            setDeadline(input);
            tasks.addTask(deadline);
            ui.printTaskAddedStatement(tasks, deadline);
        } catch (ArrayIndexOutOfBoundsException exception) {
            Messages.emptyDeadlineMessage();
        }
    }
}
