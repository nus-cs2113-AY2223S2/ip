package duke;

import commands.Greeting;
import commands.FileIO;
import commands.HandleUserCommand;
import exceptions.InvalidTaskException;
import tasks.Task;

import java.util.ArrayList;


public class Duke {
    public static void main(String[] args) throws InvalidTaskException {
        Greeting.greeting();
        ArrayList<Task> list = new ArrayList<>();
        FileIO.runFileIO(list);
        String userCommand = "";
        HandleUserCommand.handleCommand(userCommand, list);
    }
}