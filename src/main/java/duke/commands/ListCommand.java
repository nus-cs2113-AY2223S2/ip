package duke.commands;
import java.util.ArrayList;
import java.util.List;
import duke.outputs.Messages;

public class ListCommand extends TaskCommand{
    public ListCommand(){
        super("list");
    }
    public CommandResult execute() {
        ArrayList<String> messages = new ArrayList<>();
        messages.add(String.format(Messages.MESSAGE_TASK_LIST_SIZE, tasklist.getNumberOfTasks()));
        messages.addAll(List.of(tasklist.listOfTasks()));
        return new CommandResult(messages.toArray(new String[0]));
    }
}
