import parser.Parser;
import storage.StorageFile;
import todolist.TaskList;
import ui.UI;

import java.util.Scanner;

public class NakiriAyame {
    //Instructions Strings
    public static final String ACTION_GOODBYE = "bye";

    public static void main(String[] args) {
        TaskList taskList = new TaskList();
        StorageFile.initialiseData(taskList);
        Scanner in = new Scanner(System.in);

        boolean madeAnyValidInstruction = false;
        String[] inputMessage = Parser.processInputMessage(in);

        while (!inputMessage[0].equals(ACTION_GOODBYE)) {
            boolean instructionIsValid = UI.executeUserInput(taskList, inputMessage);
            if(instructionIsValid) {
                madeAnyValidInstruction = true;
            }
            inputMessage = Parser.processInputMessage(in);
        }

        UI.printGoodbyeMessage(madeAnyValidInstruction, taskList.getSize());
    }
}

