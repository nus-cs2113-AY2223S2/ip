package Duke;

public class Duke {
    private Ui ui; 
    private Storage storage;
    private TaskList tasklist;
    private Parser parser;
    private final int PARSERINDEX = 1;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage();
        tasklist = new TaskList(filePath);
        parser = new Parser();
    }

    public void run() {
        ui.greet();
        while (parser.getIsRunning()) {
            String commandToBeParsed = ui.readCommand();
            String commandParsed = parser.parseCommand(commandToBeParsed);
            if (commandParsed.startsWith("B")) { // bye from user, terminate program
                parser.setIsRunning(false);
            } else if (commandParsed.startsWith("L")) { // print list
                tasklist.printCurrentList();
            } else if (commandParsed.startsWith("F")) { // find tasks in list
                tasklist.printTasksFound(commandParsed.substring(PARSERINDEX));
            } else if (commandParsed.startsWith("M")) { // mark task
                tasklist.mark(commandParsed.substring(PARSERINDEX));
            } else if (commandParsed.startsWith("U")) { // unmark task
                tasklist.unmark(commandParsed.substring(PARSERINDEX));
            } else if (commandParsed.startsWith("D")) { // delete task
                tasklist.delete(commandParsed.substring(PARSERINDEX));
            } else if (commandParsed.startsWith("XNC")) { // null command error
                ui.printErrorMessage("Sire, I am not trained to understand gibberish.");
            } else { // task keyed in by users
                tasklist.addTask(commandParsed.substring(PARSERINDEX));
            }
        }
        ui.bye();
    }

    public static void main(String[] args) {
        new Duke("dukeData.txt").run();
    }
}
