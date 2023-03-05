import java.io.IOException;

/**
 * Duke is the main class in the duke application.
 */
public class Duke {
    private TaskList taskList;

    /**
     * This method sets up the application state.
     *
     * @param filePath
     */
    public Duke(String filePath) {
        Storage.createSave(filePath);
        taskList = new TaskList(Storage.readSave());
    }

    /**
     * This is the main method.
     *
     * @param args Unused.
     */
    public static void main(String[] args) {
        Duke duke = new Duke("data/duke.txt");
        Ui.welcomeMessage();
        String input;
        while (true) {
            input = Ui.takeInput();
            boolean terminate = input.toLowerCase().equals(StrIntLib.cmdBye);
            if (terminate) {
                Ui.byeMessage();
                return;
            }
            Parser.parseCommand(input);
            System.out.println("");
        }
    }
}
