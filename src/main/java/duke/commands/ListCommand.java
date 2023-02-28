package duke.commands;

import duke.TaskList;
import duke.data.Task;

public class ListCommand extends Command{
    public static final String COMMAND_WORD = "list";

    public ListCommand() {}
    public void execute() {
        System.out.println("Here are the tasks in your list: ");
        int size = taskList.getSize();
        if(size > 0) {
            for (int i = 0; i < size; i++) {
                System.out.println(i + 1 + ". " + taskList.getTask(i).toString());
            }
        } else {
            System.out.println("Hooray! Your task list is empty.");
        }
    }
}
