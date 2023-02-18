public class MarkCommand extends Command {
    public MarkCommand(String firstWord, String restOfCommand) {
        super(firstWord, restOfCommand);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        // Extract the index of the task to be marked
        int taskIndex;
        try {
            taskIndex = Integer.parseInt(restOfCommand) - 1;
        } catch (NumberFormatException e) {
            ui.showMarkError();
            return;
        }

        try {
            taskList.markTask(taskIndex);
            ui.showTaskIsMarked();
            ui.showCurrTask(taskList, taskIndex);
        } catch (IndexOutOfBoundsException e) {
            ui.showInvalidTaskNumberError();
        }
    }
}
