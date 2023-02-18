public class UnmarkCommand extends Command {
    public UnmarkCommand(String firstWord, String restOfCommand) {
        super(firstWord, restOfCommand);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        // Extract the index of the task to be unmarked
        int taskIndex;
        try {
            taskIndex = Integer.parseInt(restOfCommand) - 1;
        } catch (NumberFormatException e) {
            ui.showUnmarkError();
            return;
        }

        try {
            taskList.unmarkTask(taskIndex);
            ui.showTaskIsUnmarked();
            ui.showCurrTask(taskList, taskIndex);
        } catch (IndexOutOfBoundsException e) {
            ui.showInvalidTaskNumberError();
        }
    }
}
