package duke;

import java.io.IOException;

import duke.command.Command;
import duke.command.Parser;
import duke.command.Storage;
import duke.command.TodoList;
import duke.command.Ui;

public class Duke {

    private TodoList todoList;
    private Storage storage;
    private Ui ui;

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

    public static void main(String[] args) {
        try {
            new Duke("duke/data/duke.txt").run();
        } catch (IOException | DukeException e) {
            e.printStackTrace();
        }
    }
}
