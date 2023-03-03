package duke.constants;

import java.nio.file.Path;
import java.nio.file.Paths;

public final class Config {
    private Config() {
    }

    public static final String CHAR_SPACE = " ";
    public static final String CHECKBOX_LEFT = "[";
    public static final String CHECKBOX_RIGHT = "]";
    public static final String INDENT = "    ";
    public static final String LINE = "____________________________________________________________";
    public static final String LOGO = " ____        _        " + System.lineSeparator()
            + "|  _ \\ _   _| | _____ " + System.lineSeparator()
            + "| | | | | | | |/ / _ \\" + System.lineSeparator()
            + "| |_| | |_| |   <  __/" + System.lineSeparator()
            + "|____/ \\__,_|_|\\_\\___|" + System.lineSeparator();
    public static final String MARKER_CHECKBOX = "X";
    public static final String MARKER_DEADLINE = "D";
    public static final String MARKER_EVENT = "E";
    public static final String MARKER_TODO = "T";
    public static final String MESSAGE_EXIT = "Bye. Hope to see you again soon!";
    public static final String MESSAGE_HELP = "Welcome to Duke." + System.lineSeparator()
            + "Duke is a task managing application optimised for the Command-Line Interface." + System.lineSeparator()
            + "Commands: bye, deadline, event, exit, find, help, list, mark, todo, unmark" + System.lineSeparator()
            + "To learn more about each command and view the full user guide, "
            + "visit https://jinxuan-owyong.github.io/ip/" + System.lineSeparator();
    public static final String MESSAGE_SAVE_FAILED = "Warning: Save operation failed." + System.lineSeparator()
            + "Any data added from the most recent command will not be saved.";
    public static final String MESSAGE_TASKS_AVAILABLE = "Here are the tasks in your list:";
    public static final String MESSAGE_TASKS_FOUND = "Showing matches for query: ";
    public static final String MESSAGE_TASKS_MARKED = "Nice! I've marked this task as done:";
    public static final String MESSAGE_TASKS_UNMARKED = "OK, I've marked this task as not done yet:";
    public static final String MESSAGE_WELCOME = "Hello! I'm Duke" + System.lineSeparator() + "What can I do for you?";
    public static final String MESSAGE_LOGO = "Hello from";
    private static final String PATH_HOME = System.getProperty("user.dir");
    public static final Path PATH_SAVE_DIR = Paths.get(PATH_HOME, "data");
    public static final Path PATH_SAVE_FILE = Paths.get(PATH_SAVE_DIR.toString(), "save.txt");
}
