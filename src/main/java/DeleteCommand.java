public class DeleteCommand extends Command {
    private int index;

    public void setIndex(String input) {
        try {
            String[] temp = input.split(" ", 2);
            int index = Integer.parseInt(temp[1]);
            this.index = index;
        } catch (NumberFormatException exception) {
            Messages.invalidTaskMessage();
        }
    }

    @Override
    public void runCommand(String input, TaskList tasks, UI ui) {
        try {
            setIndex(input);
            ui.printTaskDeletedStatement(tasks, tasks.getTask(index - 1));
            tasks.deleteTask(index - 1);
        } catch (ArrayIndexOutOfBoundsException exception) {
            System.out.println("☹ OOPS!!! The description of a delete cannot be empty");
        }
    }
}
