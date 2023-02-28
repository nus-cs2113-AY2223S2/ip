package duke.commands;
import java.util.ArrayList;
import java.util.List;
import duke.outputs.Messages;

public class FindCommand extends TaskCommand{
    private String keyword;

    public FindCommand(String keyword) {
        super("find");
        this.keyword = keyword;
    }

    public CommandResult execute() {
        String[] taskList = this.tasklist.findTasks(keyword);
        if (taskList.length == 0) {
            return new CommandResult(Messages.ERROR_MESSAGE_KEYWORD_NOT_FOUND);
        } else {
            ArrayList<String> messages = new ArrayList<>();
            messages.add(String.format(Messages.MESSAGE_KEYWORD_FOUND, taskList.length));
            messages.addAll(List.of(taskList));
            return new CommandResult(messages.toArray(new String[0]));
        }

    }
}
