import duke.task.Task;

public class UnmarkCommand extends Command {
    private int index;

    public void setIndex(String input) {
        String[] temp = input.split(" ", 2);
        int index = Integer.parseInt(temp[1]);
        this.index = index;
    }

    @Override
    public void runCommand(String input, TaskList tasks, UI ui) {
        try {
            setIndex(input);
            Task task = tasks.getTask(index - 1);
            task.setAsUndone();
            ui.printTaskStatusStatement(task, "unmark");
        } catch (ArrayIndexOutOfBoundsException exception) {
            Messages.emptyUnmarkMessage();
        } catch (IndexOutOfBoundsException exception) {
            Messages.invalidUnmarkMessage();
        } catch (NumberFormatException exception) {
            Messages.notNumberUnmarkMessage();
        }
    }
}