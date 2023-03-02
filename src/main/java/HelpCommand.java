public class HelpCommand extends Command{
    @Override
    public void executeCommand(TaskList taskList, String input) {
        System.out.println(Messages.DIVIDER);
        System.out.println(Messages.HELPMESSAGE);
        System.out.println(Messages.DIVIDER);
    }
}
