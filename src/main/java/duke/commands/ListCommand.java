package duke.commands;


/**
 * The command to list all the tasks.
 */
public class ListCommand extends Command{
    public static final String COMMAND_WORD = "list";

    /**
     * Convenience constructor using raw values
     */
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
