package duke.common;
import duke.file.FileClass;
import duke.tasktypes.Task;

import java.util.ArrayList;

public class Common {
    public static final String HORIZONTAL_LINE = "____________________________________________________________";
    public static final String FILE_PATH = "data.txt";
    public static final String BIG_NUMBER = "The task number is bigger than the number of tasks";
    public static final String WRITEFILE_EXCEPTION_MESSAGE = "OPPS!!! Something went wrong when you write to data file";
    public static final String INSTRUCTION = "Please give your command in the following format";
    public static ArrayList<Task> tasks = new ArrayList<>();
    public static FileClass dataFile = new FileClass(FILE_PATH, tasks);
}
