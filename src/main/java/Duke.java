import commands.Handler;
import java.io.IOException;
import parser.Parser;
import storage.Storage;
import TaskManager.TaskManager;
import UI.TextUI;

public class Duke {
	private Storage storage;
    private TaskManager tasks;
    private TextUI ui;

    public Duke(String filePath){
        ui = new TextUI();
        storage = new Storage(filePath);
        try {
			tasks = new TaskManager(storage.readFileContents());
		} catch (IOException e) {
			ui.showLoadingError();
            tasks = new TaskManager();
		}
    }

    public void run() {
    	ui.showIntroduction();
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.scanLine();
            Handler c = Parser.parseCommand(fullCommand);
            c.execute(tasks, ui, storage);
            try {
    			storage.writeToFile(tasks);
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
            
            isExit = c.isExit(); 
        }
    }

    public static void main(String[] args){
        new Duke("data.txt").run();
    }
}
