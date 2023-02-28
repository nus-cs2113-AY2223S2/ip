package duke.common;
import duke.storage.Storage;
import duke.tasktypes.Task;

import java.util.ArrayList;

/**
 * Represents the common attributes and methods using for the whole application.
 */
public class Common {
    public static final String WHITE_SPACE = " ";
    public static final String VERTICAL_BAR = " | ";
    public static final String HORIZONTAL_LINE = "____________________________________________________________";
    public static final String FILE_PATH = "data.txt";
    public static final String WRITEFILE_EXCEPTION_MESSAGE = "OPPS!!! Something went wrong when you write to data file";
    public static final String INSTRUCTION = "Please give your command in the following format";

    public static ArrayList<Task> tasks = new ArrayList<>();
    public static Storage dataFile = new Storage(FILE_PATH, tasks);
}
