package command;

import exception.DukeException;
import task.Task;

import java.util.ArrayList;

public abstract class Command {
    private final ArrayList<String> commands;

    public Command(ArrayList<String> commands) {
        this.commands = commands;
    }

    public ArrayList<String> getCommands() {
        return commands;
    }

    public abstract String doCommand(ArrayList<Task> tasks) throws DukeException;
}
