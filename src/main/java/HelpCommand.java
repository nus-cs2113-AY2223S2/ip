public class HelpCommand extends Command{
    @Override
    public void executeCommand(TaskList taskList, String input) {
        System.out.println(Messages.DIVIDER);
        Ui.displayHelpMessage();
        System.out.println(Messages.DIVIDER);
    }
}
