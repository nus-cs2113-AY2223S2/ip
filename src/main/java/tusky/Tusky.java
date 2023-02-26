package tusky;

import tusky.commands.Command;
import tusky.exceptions.EmptyDescriptionException;
import tusky.exceptions.KeyNotFoundException;
import tusky.parser.Parser;
import tusky.storage.Storage;

import tusky.tasks.TaskList;
import tusky.ui.Ui;

import java.nio.file.NoSuchFileException;

import java.io.FileNotFoundException;

public class Tusky {
    private static TaskList tasks;

    private static Storage storage;
    private static Ui ui;

    public static void run () {
        boolean isExit = false;
        while (!isExit) {

            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (IllegalArgumentException e) {
                ui.showUnknownCommand();
            } catch (ArrayIndexOutOfBoundsException e) {
                ui.showInvalidParameters();
            } catch (KeyNotFoundException e) {
                ui.showKeyNotFound();
            } catch (EmptyDescriptionException e) {
                ui.showEmptyDescription();
            } catch (IndexOutOfBoundsException e) {
                ui.showInvalidIndex();
            } catch (Exception e) {
                ui.showUnknownException(e);
            } finally {
                ui.showLine();
            }

        }
    }


    public static void main (String[] args) {
        ui = new Ui();
        storage = new Storage("data/tusky.json");

        ui.showLine();
        ui.showWelcome();
        try {
            tasks = new TaskList(storage.readFile());
            ui.showFileLoaded();
        } catch (FileNotFoundException | NoSuchFileException e) {
            tasks = new TaskList();
            storage.writeFile(tasks);
            ui.showFileCreated();
        }
        ui.showLine();

        run();

    }
}
