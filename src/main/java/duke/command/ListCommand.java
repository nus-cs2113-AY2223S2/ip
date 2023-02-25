package duke.command;

import duke.tasklist.task.Task;

public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";
    public static final String COMMAND_USAGE = COMMAND_WORD
            + ": Display all tasks in the list with their indices\n";
    public static final String LIST_HEADER = "Here are the tasks in your list:\n";

    public ListCommand() {}

    @Override
    public CommandResult execute() {
        if (taskList.size() == 0) {
            return new CommandResult("You don't have any task in the list!");
        }
        StringBuilder sb = new StringBuilder();
        int cnt = 0;
        for (Task task : taskList) {
            cnt += 1;
            sb.append(cnt).append(". ");
            sb.append(task.toString()).append(System.lineSeparator());
        }
        String listContent = sb.toString();
        return new CommandResult(LIST_HEADER+listContent);
    }
}
