package com.ethanyidong.bunny;

import com.ethanyidong.bunny.arg.InvalidCommandException;
import com.ethanyidong.bunny.cmd.ExecutableCommand;
import com.ethanyidong.bunny.cmd.TokenizedCommand;

import java.nio.file.Paths;

public class BunnySession {
    private final BunnyTaskList tasks;
    private final BunnyUI ui;
    private final BunnyStorage storage;

    private boolean isQuit;
    private boolean isSuppressed;

    public BunnySession(boolean saveEnabled) {
        String home = System.getProperty("user.home");

        this.tasks = new BunnyTaskList();
        this.ui = new BunnyUI();
        this.storage = new BunnyStorage(saveEnabled, Paths.get(home, "bunny.aof"));
        this.isQuit = false;
        this.isSuppressed = false;
    }

    public boolean isQuit() {
        return this.isQuit;
    }

    public void quit() {
        this.isQuit = !this.isSuppressed;
    }

    public void setIsSuppressed(boolean isSuppressed) {
        this.isSuppressed = isSuppressed;
        this.ui.setIsSuppressed(isSuppressed);
    }

    public BunnyTaskList getTasks() {
        return this.tasks;
    }

    public BunnyUI getUI() {
        return this.ui;
    }

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
