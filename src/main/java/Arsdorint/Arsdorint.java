package Arsdorint;
import Arsdorint.UI.TextUI;
import Arsdorint.command.*;
import Arsdorint.data.Storage;
import Arsdorint.data.TaskList;
import Arsdorint.parser.TaskParser;

/**
 * Start of Arsdorint application
 * Initializes the application and interact with the users
 */
public class Arsdorint {
    public static String logo = "    ___                         _                                 _\n"
            + "   / _ \\     _____   _____  ___| |   ___    _____   _   _____   _| |_\n"
            + "  / /_\\ \\   /  ___| /  __/ /  _  |  / _ \\  /  ___| | | |  _  \\ |_   _|\n"
            + " / _____ \\  | /    __\\ \\   | |_| | | |_| | | /     | | | | | |   | |\n"
            + "/_/     \\_\\ |_|   /____/   \\_____|  \\___/  |_|     |_| |_| |_|   |_|\n";

    private TextUI UI;
    private Storage storage;
    private TaskList taskList;

    /**
     * Print exit message and exits
     */
    public void exit() {
        UI.showExitMessage();
        UI.input.close();
        System.exit(0);
    }

    public static void main(String[] args) {
        new Arsdorint().run();
    }

    /**
     * Run the program until termination / exit
     */
    public void run() {
        start();
        mainLoop();
        exit();
    }


    /**
     * Setup the required objects, print hello message
     */
    private void start() {
        this.UI = new TextUI();
        UI.showHelloMessage();
        UI.showToUser(Storage.load());
    }


    /**
     * Read user command and execute until exit command is detected
     */
    private void mainLoop() {
        Command command;
        do {
            String userCommandText = UI.getUserCommand();
            command = new TaskParser(taskList).parsedCommand(userCommandText);
            CommandRes res = executeCommand(command);
            UI.showResToUser(res);
            Storage.save();
        } while (!CommandExit.isExit(command));
    }

    /**
     * Execute the command
     */
    private CommandRes executeCommand(Command command) {
        return command.execute();
    }
}
