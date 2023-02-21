/**
 * This class represents the find command.
 * It is a subclass of the Command class.
 * It contains the execute method to find the tasks that match the keyword.
 * It also contains the isExit method to return false.
 */

import java.util.ArrayList;

//FindCommand class that extends Command
public class FindCommand extends Command {
    protected String keyword;
    protected ArrayList<Task> matchingTasks;

    /**
     * This constructor takes in the keyword.
     * @param keyword the keyword to be matched
     */
    public FindCommand(String keyword){
        this.keyword = keyword;
    }

    //method that executes the find command
    /**
     * This method executes the find command.
     * @param tasks the task list
     * @param ui the user interface
     */
    public void execute(TaskList tasks, Ui ui) throws DukeException{
        matchingTasks = tasks.find(keyword);
        ui.printMatchingTasks(matchingTasks);
    }
    //method that checks if the command is an exit command
    public boolean isExit(){
        return false;
    }
}
