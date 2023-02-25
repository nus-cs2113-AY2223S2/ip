package duke;

import java.io.IOException;

import duke.commands.Command;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.TextUi;

public class Duke {
	private Storage storage;
    private TaskList tasks;
    private TextUi ui;

    public Duke(String filePath){
        ui = new TextUi();
        storage = new Storage(filePath);
        try {
			tasks = new TaskList(storage.readFileContents());
		} catch (IOException e) {
			ui.showLoadingError();
            tasks = new TaskList();
		}
    }

    public void run() {
    	ui.showIntroduction();
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.scanLine();
            Command c = Parser.parseCommand(fullCommand);
            c.execute(tasks, ui, storage);
                
            try {
    			storage.writeToFile(tasks);
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
            
            isExit = c.isExit(); 
        }
    }

    public static void main(String[] args){
        new Duke("data.txt").run();
    }
}
