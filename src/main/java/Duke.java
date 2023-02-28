import java.io.IOException;
import java.util.ArrayList;

public class Duke {

    public static void main(String[] args) throws IOException {

        Ui.loadDataMessage();
        ArrayList<Task> tasks = Storage.loadFromFile();
        Ui.welcomeMessage();
        String command = Ui.readCommand();

        while (!command.equals("bye")) {
            Parser.parseCommand(command, tasks);
            command = Ui.readCommand();

        }
        Ui.goodbyeMessage();
        Storage.writeToFile(tasks);
    }
}
