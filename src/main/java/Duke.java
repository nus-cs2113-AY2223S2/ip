public class Duke {

    //define the file path
    public static final String FILE_PATH = "data/duke.txt";

    //Initialize the storage, tasklist and ui
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.readFile(filePath));
        } catch (Exception e) {
            ui.printErrorMessage(e.getMessage());
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.printWelcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.printLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.printErrorMessage(e.getMessage());
            } finally {
                ui.printLine();
            }
        }
        //Write the tasks to the file using the writeToFile method
        storage.writeFile(tasks.getTasks(), FILE_PATH);
    }

    public static void main(String[] args) {
        new Duke(FILE_PATH).run();
    }

}