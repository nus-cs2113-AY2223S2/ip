package Duke;

import Duke.DukeCommandLine.DukeCommandLineInput;
import Duke.DukeCommandLine.DukeParser;
import Duke.DukeCommandLine.DukeException;
import Duke.DukeFunction.DukeList;
import Duke.DukeFunction.DukeStorage;
import Duke.DukeFunction.DukeUI;

public class DukeRobot {
    private static String LOADING_MESSAGE = "Loading tasks from file...";
    private DukeUI ui;
    private DukeList tasks;
    private DukeParser parser;
    private DukeStorage storage;

    /**
     * Constructor for DukeRobot.
     * Prepares the DukeRobot for use:
     * initialises the ui, tasks, parser and storage.
     * loads the existing tasks from the storage.
     * prints all the greetings.
     */
    public DukeRobot() {
        this.ui = new DukeUI();
        this.tasks = new DukeList();
        this.parser = new DukeParser();
        this.storage = new DukeStorage();
        ui.printGreeting();
        try {
            System.out.println(LOADING_MESSAGE);
            storage.loadTask(tasks);
        } catch (DukeException e) {
            ui.printString(e.getMessage());
        }
        ui.printLine();
    }

    /**
     * Runs the program until termination (user types "bye").
     */
    public void run() {
        boolean isExit = false;
        while(!isExit) {
            try {
                String fullCommand  = ui.readCommand();
                DukeCommandLineInput command = parser.parse(fullCommand);
                command.execute(tasks, ui, parser, storage);
                isExit = command.isExit();
            } catch (DukeException e) {
                ui.printError(e.getMessage());
            } finally {
                ui.printLine();
            }
        }
    }

    public static void main(String[] args) {
        new DukeRobot().run();
    }
}
