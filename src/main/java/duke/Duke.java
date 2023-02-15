package duke;

import duke.exceptions.DukeException;
import duke.ui.Ui;
import duke.storage.FileManager;
import duke.tasks.TaskList;
import duke.commands.Command;

public class Duke {
    public static void main(String[] args) {
        
        FileManager fileManager = new FileManager();
        TaskList taskList = new TaskList(fileManager.retrieve());
        Command commands = new Command(taskList);

        Ui.printWelcome();
        try {
            commands.getInput();
            fileManager.save(taskList);
            Ui.printExit();
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
        
        
        
        
    }
}
