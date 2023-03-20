package Arsdorint;
import Arsdorint.UI.TextUI;
import Arsdorint.command.*;
import Arsdorint.data.StorageClass;
import Arsdorint.data.TaskList;
import Arsdorint.parser.TaskParser;

import static Arsdorint.MessageList.MESSAGE_NEW_FILE;

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
    private StorageClass storage;
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
     * Set up the required objects, print hello message
     */
    private void start() {
        this.UI = new TextUI();
        UI.showHelloMessage();
        this.storage = new StorageClass();
        try {
            this.taskList = storage.load();
            UI.showToUser(MESSAGE_NEW_FILE);
        } catch (StorageClass.StorageException e) {
            UI.showToUser(e.getMessage());
            this.taskList = new TaskList();
        }
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
            save();
        } while (!CommandExit.isExit(command));
    }

    /**
     * Save and catch error
     */
    private void save() {
        try {
            storage.save(taskList);
        } catch (StorageClass.StorageException e) {
            UI.showToUser(e.getMessage());
        }
    }

    /**
     * Execute the command
     *
     * @param command the input command that the user typed in
     */
    private CommandRes executeCommand(Command command) {
        try {
            command.setTaskList(taskList);
            return command.execute();
        } catch (Exception err) {
            UI.showToUser(err.getMessage());
            throw new RuntimeException(err);
        }
    }
}

