package com.ethanyidong.bunny;

import com.ethanyidong.bunny.arg.InvalidCommandException;
import com.ethanyidong.bunny.cmd.ExecutableCommand;
import com.ethanyidong.bunny.cmd.TokenizedCommand;

import java.nio.file.Paths;

/**
 * Handles control flow of the Bunny app.
 */
public class BunnySession {
    private final BunnyTaskList tasks;
    private final BunnyUI ui;
    private final BunnyStorage storage;

    private boolean isQuit;
    private boolean isSuppressed;

    /**
     * @param saveEnabled whether to load and save commands to a save file (disabled for testing purposes)
     */
    public BunnySession(boolean saveEnabled) {
        String home = System.getProperty("user.home");

        this.tasks = new BunnyTaskList();
        this.ui = new BunnyUI();
        this.storage = new BunnyStorage(saveEnabled, Paths.get(home, "bunny.aof"));
        this.isQuit = false;
        this.isSuppressed = false;
    }

    /**
     * @return <code>true</code> the user has quit the session, <code>false</code> otherwise
     */
    public boolean isQuit() {
        return this.isQuit;
    }

    /**
     * Quits the session if control is not currently suppressed
     * @see BunnySession#setIsSuppressed(boolean)
     */
    public void quit() {
        this.isQuit = !this.isSuppressed;
    }

    /**
     * Suppresses or unsuppresses output and control
     * @param isSuppressed new suppression state
     */
    public void setIsSuppressed(boolean isSuppressed) {
        this.isSuppressed = isSuppressed;
        this.ui.setIsSuppressed(isSuppressed);
    }

    /**
     * @return The reference to the list of tasks
     */
    public BunnyTaskList getTasks() {
        return this.tasks;
    }

    /**
     * @return A reference to the user interaction handler
     */
    public BunnyUI getUI() {
        return this.ui;
    }

    /**
     * Parses raw input into a command executes the command on the current session
     * @param commandString the raw (unparsed) string of the command to run
     */
    public void runCommandString(String commandString) {
        TokenizedCommand inputCommand = new TokenizedCommand(commandString);
        ExecutableCommand executableCommand;
        try {
            executableCommand = ExecutableCommand.validateAndParse(this, inputCommand);
        } catch (InvalidCommandException ice) {
            this.ui.printMessage(ice.toString());
            return;
        }
        executableCommand.execute(this);
    }

    /**
     * Loads the existing save file if saving is enabled and it exists, then runs the REPL of the app.
     * All commands entered are saved to the file if saving is enabled
     */
    public void runBunny() {
        this.storage.loadSave(this);

        this.ui.printMessage("Hello! I'm Bunny.\nWhat can I do for you?");

        try {
            this.storage.beginSave();
            while (!this.isQuit()) {
                String input = this.ui.getNextCommandString();
                this.storage.save(input);
                this.runCommandString(input);
            }
            this.ui.printMessage("Bye. Hope to see you again soon!");
            this.storage.endSave();
        } catch (Exception _ex) {
            System.out.println("Error writing save file! Quitting...");
        }
    }
}
