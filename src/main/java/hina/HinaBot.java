package hina;

import hina.exceptions.HinaException;
import hina.helper.Parser;
import hina.helper.Storage;
import hina.helper.TaskList;
import hina.helper.Ui;
import hina.task.Task;

import java.io.FileNotFoundException;
import java.util.ArrayList;


public class HinaBot {
    protected static ArrayList<Task> taskList = new ArrayList<Task>();
    private static Ui ui;
    private static Storage storage;
    private static TaskList tasks;
    public HinaBot(String savePath, String dataDir) {
        ui = new Ui();
        storage = new Storage();
        Ui.showGreeting();
        try {
            tasks = new TaskList(Storage.readSaveFile(savePath));
        } catch (FileNotFoundException exception) {
            Storage.createSaveFile(savePath, dataDir);
        }
    }

    public static void main(String[] args) {
        new HinaBot("data/savedlist.txt", "data");

        while (true) {
            try {
                Parser.readCommand();
            } catch (HinaException cmdException) {
                Ui.invalidCommandMessage();
            } catch (StringIndexOutOfBoundsException argException) {
                Ui.notEnoughDetails();
            }
        }
    }
}
