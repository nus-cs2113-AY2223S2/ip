// Java program to read data of various types using Scanner class.
package duke;

import duke.command.Command;
import duke.TaskList;
import duke.Storage;
import duke.task.Task;

/**
 * @author : Steven A. O. Waskito
 * @mailto : e0851459@u.nus.edu
 * @created : 3 February 2023
 *
 * This is the main Java driver and has most of the important methods
 *
 **/
public class Duke extends Throwable{
    private Storage storage;
    private TaskList taskList;

    public Duke(String filePath) {
        storage = new Storage(filePath);
        taskList = new TaskList();
        try {
            taskList = new TaskList(storage.readFile());
        } catch (DukeException e) {
            Ui.printErrorMessage("filepath error");
            taskList = new TaskList();
        }
    }
    public void run() {
        Ui.printWelcomeMessage();
        String inputCommand = "";
        boolean isBye = false;

        do {
            inputCommand = Ui.getCommand();
            Ui.printLine();
            try {
                Command command = Parser.parse(inputCommand);
                if (command == null) continue;
                isBye = command.execute(taskList);
                storage.saveFile(taskList);
            } catch (DukeException e) {
                Ui.printErrorMessage(e.getMessage());
            }

        } while(!isBye);

        Ui.printByeMessage();
    }
    public static void main(String[] args) {
        new Duke("data/input.txt").run();
    }
}
