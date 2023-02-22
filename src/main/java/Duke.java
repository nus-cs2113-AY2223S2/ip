import duke.task.Task;
import duke.userinterface.Ui;
import duke.datahandling.Storage;
import duke.datahandling.Parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    /**ArrayList to store a list of tasks inputted by the user of the Duke Program.*/
    public static ArrayList<Task> list = new ArrayList<>();

    /**Stores the size of the ArrayList after addition/deletion of tasks.*/
    public static int listSize = 0;

    /**Instance to handle all user interface requirements for the Duke program.*/
    public static final Ui dukeUserInterface = new Ui();

    /**Instance to parse all user inputs.*/
    public static final Parser dukeUserInputParser = new Parser(list, listSize);

    /**Instance to handle all data storage related operations in the Duke Program.*/
    public static final Storage dukeDataStorage = new Storage(list, listSize);

    /**
     * Executes the "Duke" program.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        retrieveFileData();
        dukeUserInterface.greetUser();
        dukeUserInputParser.obtainUserInputs(list, listSize, userInput);
        saveDataToFile();
    }

    /**
     * Retrieves data stored in the .txt file and writes the data to an ArrayList found
     * in the Duke Program.
     */
    private static void retrieveFileData() {
        try {
            listSize = dukeDataStorage.readFileData(list);
        } catch (IOException ie) {
            dukeUserInterface.printFileCreationError();
        }
    }

    /**
     * Writes data stored in the ArrayList in the Duke Program to a .txt file
     * for future usage and reference.
     */
    private static void saveDataToFile() {
        try {
            dukeDataStorage.writeFileData(list);
        } catch (IOException ie) {
            dukeUserInterface.printFileSavingError();
        }
    }
}
