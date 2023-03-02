package duke;

import java.io.IOException;

import duke.command.Command;
import duke.command.Parser;
import duke.command.Storage;
import duke.command.TodoList;
import duke.command.Ui;

/**
 * Main class that represents a Duke assistant.
 */
public class Duke {

    private TodoList todoList;
    private Storage storage;
    private Ui ui;

    /**
     * Constructor of <code>Duke</code>. It creates a <code>todoList</code>
     * that reads all tasks from <code>filePath</code> if the file exists.
     * @param filePath File that stores data(Tasks) that generated in last execution.
     * @throws IOException if any <code>IOException</code> was thrown in <code>Storage</code> and <code>TodoList</code>.
     * @throws DukeException if any <code>DukeException</code> was thrown in <code>TodoList</code>.
     */
    public Duke(String filePath) throws IOException, DukeException {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            todoList = new TodoList(storage.load());
        } catch (Exception e) {
            e.printStackTrace();
            todoList = new TodoList();
        }
    }

    /**
     * Main process running the <code>Duke</code> assistant.
     */
    public void run() {
        Ui.showWelcome();
        while(true){
            try {
                String line = ui.readCommand();
                Command cmd = Parser.parse(line);
                if(cmd.isExit()) {
                    break;
                }
                cmd.execute(todoList);
            } catch(DukeException e) {
                Ui.showError(e.getMessage());
            }
        }
        Ui.showExit();
    }

    /**
     * Main function of <code>Duke</code>.
     * @param args Arguments from CLI input[not used]
     */
    public static void main(String[] args) {
        try {
            new Duke("duke/data/duke.txt").run();
        } catch (IOException | DukeException e) {
            e.printStackTrace();
        }
    }
}
