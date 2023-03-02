import java.io.IOException;

public class Duke {
    private final Storage storage;
    private final TaskList tasks;
    private final UI ui;
    private final Parser parser = new Parser();

    /**
     * Create the Duke Object
     * @param filePath an String giving the base location of the file
     */
    public Duke(String filePath) throws IOException {
        ui = new UI();
        storage = new Storage(filePath);
        this.tasks = new TaskList(storage.load());
    }

    /**
     * Start to run the Duke application
     */
    public void run() throws IOException{
        ui.showWelcome();
        while (!parser.isExit()) {
            String fullCommand = ui.readCommand();
            ui.showLine();
            parser.parse(fullCommand);
        }
    }

    public static void main(String[] args) throws IOException {
        new Duke("duke.txt").run();
    }

}
