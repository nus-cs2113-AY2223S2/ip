package duke;

import exceptions.InvalidTaskException;
import tasks.Task;

import java.util.ArrayList;

import static commands.FileIO.runFileIO;
import static commands.Greeting.greeting;
import static commands.HandleUserCommand.handleCommand;


/**
 *This is the main class that runs the program.
 */
public class Duke {
    public static void main(String[] args) throws InvalidTaskException {
        ArrayList<Task> list = new ArrayList<>();
        runFileIO(list);
        greeting();
        handleCommand(list);
    }
}