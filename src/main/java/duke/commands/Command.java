package duke.commands;

import duke.task.TaskList;

public class Command {

    public void handleCommand(String line, TaskList list){
        String lineBreak = UI.getLineBreak();
        System.out.println("Please input a task!\n" + lineBreak);
    }
}
