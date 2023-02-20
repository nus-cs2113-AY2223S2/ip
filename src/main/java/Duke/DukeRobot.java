package Duke;

import Duke.DukeCommandLine.DukeCommandLineInput;
import Duke.DukeCommandLine.DukeParser;
import Duke.DukeCommandLine.DukeTaskInputException;
import Duke.DukeFunction.DukeList;
import Duke.DukeFunction.DukeUI;

public class DukeRobot {
    private static final DukeUI ui = new DukeUI();
    private static final DukeList tasks = new DukeList();
    private static final DukeParser parser = new DukeParser();
    public void run() {
        ui.printGreeting();
        try {
            tasks.loadTask();
        } catch (DukeTaskInputException e) {
            ui.printError(e.getMessage());
        }
        boolean isExit = false;
        while(!isExit) {
            try {
                String fullCommand  = ui.readCommand();
                DukeCommandLineInput command = parser.parse(fullCommand);
                command.execute(tasks, ui, parser);
                isExit = command.isExit();
            } catch (DukeTaskInputException e) {
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
