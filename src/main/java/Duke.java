import command.Command;
import exceptions.FileLineParseException;
import task.*;
import parser.Parser;
import ui.Ui;
import utils.Storage;


import java.io.FileNotFoundException;

public class Duke {

    private Ui ui;
    private TaskList tasks;
    private Storage storage;

    /**
     * Initialize the task list from the local file. If there's no local file,
     * or cannot parse the local file, create a new empty task list.
     * @param filePath The path of the local file which saves the past tasks.
     */
    private Duke(String filePath){
        ui = new Ui();
        storage = new Storage(filePath);
        try{
            tasks = new TaskList(storage.load());
        }catch(FileLineParseException e){
            ui.showFileLineParseException();
            tasks = new TaskList();
        }catch (FileNotFoundException e){
            ui.showFileNotFoundException();
            tasks = new TaskList();
        }
    }

    /**
     * Read user's command, parse them into command objects and execute them.
     */
    private void run(){
        ui.showWelcome();
        boolean isExit = false;
        while(!isExit){
            try{
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            }
            finally{
                ui.showLine();
            }
        }
    }

    public static void main(String[] args){
        new Duke("Data/duke.txt").run();
    }

}
