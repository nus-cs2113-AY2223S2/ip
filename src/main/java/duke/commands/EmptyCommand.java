package duke.commands;

import duke.tasks.TaskList;

public class EmptyCommand extends Command{

    @Override
    public void handleCommand(String line, TaskList taskList){
        System.out.println("I'm sorry, but I don't know what that means :-(");
    }
}
