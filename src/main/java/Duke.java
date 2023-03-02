import duke.storage.DataManager;
import duke.storage.FileManager;
import duke.utils.DukeException;
import duke.utils.Parser;
import duke.utils.TaskList;
import duke.utils.Ui;
import java.io.IOException;
import java.util.Scanner;

public class Duke {
    public TaskList taskList;
    public Ui ui;
    public Parser parser;
    public FileManager originalFile;
    public Duke () {
        this.taskList = new TaskList();
        this.ui = new Ui();
        this.parser = new Parser(this.taskList, this.ui);
        this.originalFile = new FileManager(FILE_LOCATION);
    }
    public static Scanner input = new Scanner(System.in);
    public static String FILE_LOCATION = "./data/duke.txt";

    public void runCommands() {

    }
    public static void main(String[] args) throws DukeException, IOException {
        Duke ashy = new Duke();
        ashy.ui.printGreetingMessage();
        ashy.taskList.list = ashy.originalFile.loadData();
        ashy.taskList.currentTaskNum = ashy.taskList.list.size();

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

