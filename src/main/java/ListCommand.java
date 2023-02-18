public class ListCommand extends Command {
    public ListCommand(String firstWord, String restOfCommand) {
        super(firstWord, restOfCommand);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        // There are no tasks in the list
        if (taskList.numOfTasks == 0) {
            System.out.println("\tThere are no tasks in your list currently!");
            return;
        }

        String output = "\tHere are the tasks in your list:" + System.lineSeparator();
        for (int i = 0; i < taskList.numOfTasks; i++) {
            output += "\t" + (i + 1) + "." + taskList.tasks.get(i) + System.lineSeparator();
        }
        System.out.print(output);
    }
}
