import duke.storage.DataManager;
import duke.storage.Storage;
import duke.utils.DukeException;
import duke.utils.Parser;
import duke.utils.TaskList;
import duke.utils.Ui;
import java.io.IOException;
import java.util.Scanner;

/**
 * Initializes the application.
 * Reads the datafile and loops commands.
 * Main class for the application.
 */
public class Duke {
    public TaskList taskList;
    public Ui ui;
    public Parser parser;
    public Storage originalFile;
    public Duke () {
        this.taskList = new TaskList();
        this.ui = new Ui();
        this.parser = new Parser(this.taskList, this.ui);
        this.originalFile = new Storage(FILE_LOCATION);
    }
    public static Scanner input = new Scanner(System.in);

    /**The file location to store the task list data.*/
    public static String FILE_LOCATION = "./data/duke.txt";

    /**
     * Interacts with user by loading existing data and loop commands.
     * Passes the user inputs into Parser object.
     * Prints exit message when user types <code>bye</code>.
     * Rewrites the local file with the updated task list and then ends application.
     *
     *  @param args The configuration parameters passed into the main function in Java.
     *  @throws DukeException If user input does not follow the guideline.
     *  @throws IOException If I/O errors occur when reading or writing file.
     */
    public static void main(String[] args) throws DukeException, IOException {
        Duke ashy = new Duke();
        ashy.ui.printGreetingMessage();
        ashy.taskList.list = ashy.originalFile.loadData();
        ashy.taskList.matchingTasksNum = ashy.taskList.list.size();

        String userInput = input.nextLine();
        while (!userInput.equals("bye")) {
            ashy.parser.doCommand(userInput);
            userInput = input.nextLine();
        }
        ashy.ui.printFarewellMessage();
        DataManager dataManager= new DataManager(FILE_LOCATION, ashy.taskList.list);
        dataManager.writeToFileWithErrorHandler();
    }
}

