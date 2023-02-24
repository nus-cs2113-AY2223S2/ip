public class ListCommand extends Command{
    @Override
    public void runCommand(String input, TaskList tasks, UI ui) {
        ui.printAllTasks(tasks);
    }
}
