package duke;

import duke.command.Command;
import duke.exception.EmptyDeadlineDescriptionException;
import duke.exception.EmptyEventDescriptionException;
import duke.exception.EmptyKeywordException;
import duke.exception.EmptyToDoDescriptionException;
import duke.exception.UnknownCommandException;
import duke.parse.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.io.IOException;

public class Duke {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    public static void main(String[] args) {
        new Duke().run();
    }

    public Duke() {
        ui = new Ui();
        storage = new Storage("./data/duke.txt");
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showLoadFileErr();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcomeMessage();
        boolean isExit = false;

        while (!isExit) {
            try {
                String input = ui.getInput();
                Command c = Parser.parse(input);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (UnknownCommandException e) {
                ui.showUnknownCmdErr();
            } catch (NumberFormatException e) {
                ui.showWrongTaskNoFormatErr();
            } catch (EmptyToDoDescriptionException e) {
                ui.showEmptyTodoDescErr();
            } catch (EmptyDeadlineDescriptionException e) {
                ui.showEmptyDeadlineDescErr();
            } catch (EmptyEventDescriptionException e) {
                ui.showEmptyEventDescErr();
            } catch (IOException e) {
                ui.showWriteFileErr();
            } catch (ArrayIndexOutOfBoundsException e) {
                ui.showEmptyTaskNoErr();
            } catch (IndexOutOfBoundsException e) {
                ui.showTaskNoOutOfRangeErr();
            } catch (EmptyKeywordException e) {
                ui.showEmptyKeywordErr();
            }
        }

        ui.showExitMessage();
    }
}