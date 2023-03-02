package duke.commands;

import duke.data.Task;

/**
 * Find the task that contains keyword
 */
public class FindCommand extends Command{
    public static final String COMMAND_WORD = "find";
    public static final String MESSAGE_USAGE = "OOPS!! The correct format of finding task is: find keyword";
    protected String keyWord;

    /**
     * Convenience constructor using raw values
     * @param keyWord the keyword which the description contains
     */
    public FindCommand(String keyWord) {
        this.keyWord = keyWord;
    }

    public void execute() {
        try {
            System.out.println("Here are the matching tasks in your list: ");
            int size = taskList.getSize();
            if(size > 0) {
                for (int i = 0; i < size; i++) {
                    Task target= taskList.getTask(i);
                    if(target.getDescription().contains(keyWord)){
                        System.out.println(target);
                    }
                }
            } else {
                System.out.println("No matching tasks found.");
            }
        } catch (IndexOutOfBoundsException ie) {
            System.out.println("OOPS! Your task index exceeds the maximum.");
        }
    }
}
