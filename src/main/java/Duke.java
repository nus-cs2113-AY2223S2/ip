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
            if (input.contains(StrIntLib.cmdBye)) {
                Ui.byeMessage();
                return;
            }
            Parser.parseCommand(input);
            try {
                Storage.writeSave();
                System.out.println(StrIntLib.saveDone);
            } catch (IOException e) {
                System.out.println(StrIntLib.saveError);
            }
        }
    }
}
