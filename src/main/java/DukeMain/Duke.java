package DukeMain;

import Commands.Command;
import ErrorHandling.ErrorHandler;
import FileIO.DukeFile;
import TaskItems.Todos;
import UserInterface.UI;
import java.io.IOException;
import java.util.ArrayList;

public class Duke {

    public static void main(String[] args) throws IOException {

        UI.greet();

        String UserInput = UI.GetInput();

        ArrayList<Todos> todoItems = DukeFile.CreateFile();

        while (!UserInput.equalsIgnoreCase("bye")) {
            if (!ErrorHandler.isInputValid(UserInput, todoItems.size())) {
                UserInput = UI.GetInput();
                continue;
            }
            Command instruction = new Command(UserInput.split(" ")[0]);
            instruction.executeCommand(UserInput, todoItems);
            UserInput = UI.GetInput();
        }
        UI.sayBye();
    }
}
