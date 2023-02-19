package CommandUtils;

import Exceptions.DukeException;

public interface InputParser {
    public void parseInput(String command, String input) throws DukeException;
}
