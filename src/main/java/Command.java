import java.util.ArrayList;
import java.text.ParseException;
import java.util.zip.DataFormatException;


public abstract class Command {
    public abstract void executeCommand(TaskList taskList, String input); // executes the commands differently based on the various types of commands
}
