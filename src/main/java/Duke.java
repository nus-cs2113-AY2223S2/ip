public class Duke {

    //Define the file path
    public static final String FILE_PATH = "data/duke.txt";

    //Initialize the storage, tasklist and ui
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for the Duke class.
     * @param filePath the file path of the file to be read
     * @throws DukeException if there is an error reading the file
     * @throws IOException if there is an error reading the file
     * @throws FileNotFoundException if there is an error reading the file
     */

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

    /**
     * This method runs the Duke program.
     * It prints the welcome message.
     * It reads the command from the user.
     * It parses the command and executes it.
     * It checks if the command is an exit command.
     * It writes the tasks to the file.
     * @param fullCommand the command entered by the user
     * @param isExit boolean to check if the command is an exit command
     * @param c the command to be executed
     * @throws DukeException if there is an error executing the command
     */
    public void run() {
        ui.printWelcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.printLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui);
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

    /**
     * This method runs the Duke program.
     * @param args the arguments
     */
    public static void main(String[] args) {
        new Duke(FILE_PATH).run();
    }

}