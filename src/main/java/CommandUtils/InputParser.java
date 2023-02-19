package CommandUtils;

import Exceptions.DukeException;

/**
 * Interface to ensure that commands will parse user inputs
 */
public interface InputParser {
    public void parseInput(String command, String input) throws DukeException;
}
