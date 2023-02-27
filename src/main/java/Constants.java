public class Constants {
    public static final String CHANGE_COMMAND = "change";
    public static final String BYE_COMMAND = "bye";
    public static final String LANG_COMMAND = "lang";
    public static final String LIST_COMMAND = "list";
    public static final String MARK_COMMAND = "mark";
    public static final String UNMARK_COMMAND = "unmark";
    public static final String DELETE_COMMAND = "delete";
    public static final String TODO_COMMAND = "todo";
    public static final String DEADLINE_COMMAND = "deadline";
    public static final String DEADLINE_BY = "/by";
    public static final String EVENT_COMMAND = "event";
    public static final String EVENT_START_FROM = "/from";
    public static final String EVENT_END_TO = "/to";
    /** A fixed sized buffer to trim strings at the right index */
    public static final int COMMAND_BUFFER = 1;
    /** The return result when a substring is not found */
    public static final int OUT_OF_BOUNDS = -1;
    /** Name of the text file that stores all the list of tasks */
    public static final String STORAGE_INFO_TXT = "storage-info.txt";
    /** Name of the folder that stores the text file that stores all the list of tasks */
    public static final String STORE_DIR = "store";
    /** The buffer used to separate the various information containing in a task */
    public static final String STORAGE_BUFFER = "~;~";
}
