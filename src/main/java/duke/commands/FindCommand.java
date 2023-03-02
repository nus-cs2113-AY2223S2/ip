package duke.commands;

import duke.commands.task.Task;

public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";
    public static final String MESSAGE_FIND_SUCCESS = "All matching tasks have been listed!";
    public static final String MESSAGE_FIND_FAIL = "There are no matching tasks!";

    public String keyword;
    public boolean isFound = false;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public CommandResult execute() {
        for (int x = 0; x < taskList.getSizeOfList(); x++) {
            containsKeyword(taskList.getTask(x), keyword);
        }
        if (isFound) {
            isFound = false;
            return new CommandResult(MESSAGE_FIND_SUCCESS);
        } else {
            return new CommandResult(MESSAGE_FIND_FAIL);
        }
    }

    public void containsKeyword(Task task, String keyword) {
        if (task.taskDescription.contains(keyword)) {
            task.print();
            isFound = true;
        }
    }
}
