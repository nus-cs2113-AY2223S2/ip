package command;

import exception.DukeException;
import taskList.TaskList;

import java.util.ArrayList;

public abstract class Command {
    private final ArrayList<String> COMMANDS;

    public Command(ArrayList<String> commands) {
        this.COMMANDS = commands;
    }

    public ArrayList<String> getCommands() {
        return COMMANDS;
    }

    public abstract String doCommand(TaskList taskList) throws DukeException;
}

