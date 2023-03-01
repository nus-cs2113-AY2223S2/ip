public class Duke {

    private final static String LOAD_PATH = "save/tasks.txt";
    private final Parser parser;
    private Storage storage;
    private Ui ui;
    private TaskList tasks;

    /**
     * Constructs a Duke object for program to run by
     * Initialising the ui, storage, parser and task list
     * to be used for the program.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage(LOAD_PATH);
        ui.showGreetingMessage();
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadErrorMessage();
            tasks = new TaskList();
        }
        parser = new Parser(ui, storage, tasks);
    }

    public static void main(String[] args) {
        new Duke().run();
    }

    /**
     * Runs the Duke program
     */
    public void run() {
        try {
            String input = ui.requestUserInput();
            parser.run(tasks, input);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
