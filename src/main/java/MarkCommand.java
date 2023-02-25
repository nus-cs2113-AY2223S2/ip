import duke.task.Task;

public class MarkCommand extends Command {
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
            task.setAsDone();
            ui.printTaskStatusStatement(task, "mark");
        } catch (ArrayIndexOutOfBoundsException exception) {
            Messages.emptyMarkMessage();
        } catch (IndexOutOfBoundsException exception) {
            Messages.invalidMarkMessage();
        } catch (NumberFormatException exception) {
            Messages.notNumberMarkMessage();
        }
    }
}
