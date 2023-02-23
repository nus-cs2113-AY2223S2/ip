package duke.command;

import duke.exception.InvalidTaskException;
import duke.tasks.TaskList;

<<<<<<< HEAD
<<<<<<< HEAD
=======
import java.text.ParseException;
import java.util.ArrayList;
import java.util.zip.DataFormatException;
>>>>>>> master
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
<<<<<<< HEAD
    public void processCommand(TaskList tasksList, String line) throws InvalidTaskException {
=======
=======
>>>>>>> master
    public void processCommand(TaskList tasksList, String line) throws InvalidTaskException, ParseException, DataFormatException {

>>>>>>> master
    }
}
