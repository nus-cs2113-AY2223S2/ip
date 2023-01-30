package duke.commands;

import duke.task.TaskList;

public class Command {

    /**
     * Handles the command based on user input
     *
     * @param line User input represented as a String
     * @param taskList List containing the tasks input by user
     */
    public void handleCommand(String line, TaskList taskList){
        String lineBreak = UI.getLineBreak();
        System.out.println("Please input a task!\n" + lineBreak);
    }
}
