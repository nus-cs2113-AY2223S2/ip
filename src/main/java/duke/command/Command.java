package duke.command;

import duke.exception.InvalidTaskException;
import duke.tasks.TaskList;


import java.text.ParseException;
import java.util.zip.DataFormatException;


/**
 * each command is represented one command object
 */
public abstract class Command {
    public void processCommand(TaskList tasksList, String line) throws InvalidTaskException, ParseException, DataFormatException {


    }
}
