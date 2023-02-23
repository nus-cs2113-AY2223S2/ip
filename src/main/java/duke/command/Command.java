package duke.command;

import duke.exception.InvalidTaskException;
import duke.tasks.TaskList;

<<<<<<< HEAD
=======
import java.text.ParseException;
import java.util.ArrayList;
import java.util.zip.DataFormatException;
>>>>>>> master

/**
 * each command is represented one command object
 */
public abstract class Command {
<<<<<<< HEAD
    public void processCommand(TaskList tasksList, String line) throws InvalidTaskException {
=======
    public void processCommand(TaskList tasksList, String line) throws InvalidTaskException, ParseException, DataFormatException {

>>>>>>> master
    }
}
