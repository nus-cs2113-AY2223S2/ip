/**
 * Represents the Duke chatbot. Allows user to utilise features such as loading
 * and saving their data in every instance of Duke, listing tasks, finding
 * specific tasks, marking and unmarking tasks, deleting tasks, as well as adding
 * three different types of tasks : To-do, Deadline or Event.
 */
public class Duke {
    protected Storage storage;
    protected Parser parser;
    protected Ui ui;

    /**
     * Creates new Ui, Storage and Parser objects.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage();
        parser = new Parser();
    }

    /**
     * Greets the user and loads data from text file at the start of the program.
     * Handles user commands until user exits the program.
     * When user exits program, saves current task list inside text file and says bye.
     */
    public void run() {
        ui.greetUser();
        storage.load();
        parser.handleCommands();
        storage.saveData();
        ui.sayBye();
    }

    /**
     * Runs Duke.
     * @param args The user's inputs.
     */
    public static void main(String[] args) {
        new Duke().run();
    }
}

