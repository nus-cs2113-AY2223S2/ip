package duke;

import duke.exceptions.DukeException;
import duke.ui.Ui;
import duke.storage.FileManager;
import duke.tasks.TaskList;
import duke.parser.CommandParser;

public class Duke {
    public static void main(String[] args) {
        
        FileManager fileManager = new FileManager();
        TaskList taskList = new TaskList(fileManager.retrieve());
        CommandParser parser = new CommandParser(taskList);

        Ui.printWelcome();
        try {
            parser.getInput(fileManager);
            fileManager.save(taskList);
            Ui.printExit();
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
        
        
        
        
    }
}
