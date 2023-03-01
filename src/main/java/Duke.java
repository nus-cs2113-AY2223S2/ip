import java.io.FileNotFoundException;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private UI ui;
    private Parser parser=new Parser();

    public Duke(String filePath) {
        ui = new UI();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());

    }

    public void run() {
        ui.showWelcome();
        while (!parser.isExit()) {

            String fullCommand = ui.readCommand();
            ui.showLine();
            parser.parse(fullCommand);
        }
    }

    public static void main(String[] args) {
        new Duke("duke.txt").run();
    }

}
