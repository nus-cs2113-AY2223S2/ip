package king;

import king.exceptions.KingException;
import king.parser.CommandParser;
import king.storage.FileManager;
import king.tasks.TaskList;
import king.ui.Ui;

public class King {
    public static void main(String[] args) {
        FileManager fileManager = new FileManager();
        TaskList taskList = new TaskList(fileManager.retrieve());
        CommandParser parser = new CommandParser(taskList);
        Ui.printWelcome();
        
        try {
            parser.getInput(fileManager);
            fileManager.save(taskList);
            Ui.printExit();
        } catch (KingException e) {
            System.out.println(e.getMessage());
        }
    }
}
