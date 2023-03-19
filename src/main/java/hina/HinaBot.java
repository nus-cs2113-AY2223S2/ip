package hina;

import hina.exceptions.HinaException;
import hina.helper.Parser;
import hina.helper.Storage;
import hina.helper.TaskList;
import hina.helper.Ui;

import java.io.FileNotFoundException;

public class HinaBot {
    private static Ui ui;
    private static Storage storage;
    private static TaskList tasks;

    /**
     * Class constructor specifying the path to a file containing save data. Tries to
     * access the save file and attempts to create a new one if it does not exist.
     *
     * @param savePath path to the save file.
     * @param dataDir directory the save file is stored in.
     */
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
