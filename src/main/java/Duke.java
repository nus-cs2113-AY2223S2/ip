import java.io.IOException;

public class Duke {
    private TaskList taskList;
    public Duke(String filePath) {
        Storage.createSave(filePath);
        taskList = new TaskList(Storage.readSave());
    }
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
