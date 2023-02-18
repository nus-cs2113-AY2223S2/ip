package CommandUtils;

import Exceptions.DukeException;

public interface ParseInput {
    public void parseInput(String command, String input) throws DukeException;
}
