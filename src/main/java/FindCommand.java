//import ArrayList
import java.util.ArrayList;

//FindCommand class that extends Command
public class FindCommand extends Command {
    protected String keyword;
    protected ArrayList<Task> matchingTasks;
    //constructor that takes in the keyword
    public FindCommand(String keyword){
        this.keyword = keyword;
    }
    //method that executes the find command
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException{
        matchingTasks = tasks.find(keyword);
        ui.printMatchingTasks(matchingTasks);
    }
    //method that checks if the command is an exit command
    public boolean isExit(){
        return false;
    }
}
