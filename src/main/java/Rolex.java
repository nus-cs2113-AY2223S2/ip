import Commands.Command;
import Storage.Storage;
import Ui.Ui;
import Tasks.Task;
import java.io.IOException;

public class Rolex {

    public static void main(String[] args) throws IOException {

        String userInput;
        Ui.rolexGreetsUser();
        Command.getTaskList(Storage.createFile());

        do{
            userInput = Ui.takeUserInput();
            Task.processCommand(userInput);
        } while(!userInput.equalsIgnoreCase("bye"));

        Storage.fileWrite(Command.giveTaskList());
        Ui.rolexSaysBye();
    }

}