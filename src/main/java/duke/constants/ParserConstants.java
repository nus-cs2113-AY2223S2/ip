package duke.constants;

import java.util.regex.Pattern;

public final class ParserConstants {
    private ParserConstants() {
    }

    public static final String COMMAND_MARK = "mark";
    public static final String COMMAND_BYE = "bye";
    public static final String COMMAND_DEADLINE = "deadline";
    public static final String COMMAND_DELETE = "delete";
    public static final String COMMAND_EVENT = "event";
    public static final String COMMAND_EXIT = "exit";
    public static final String COMMAND_FIND = "find";
    public static final String COMMAND_HELP = "help";
    public static final String COMMAND_LIST = "list";
    public static final String COMMAND_TODO = "todo";
    public static final String COMMAND_UNMARK = "unmark";
    public static final String JSON_KEY_TYPE = "type";
    public static final String KEYWORD_BY = "/by";
    public static final String KEYWORD_FROM = "/from";
    public static final String KEYWORD_TO = "/to";
    public static final Pattern PATTERN_DEADLINE = Pattern.compile(
            "^(\\S+[\\S\\s]*)(\\s+/by\\s+)(\\S+[\\S\\s]*)$",
            Pattern.CASE_INSENSITIVE);
    public static final Pattern PATTERN_EVENT = Pattern.compile(
            "^(\\S+[\\S\\s]*)(\\s+/from\\s+)(\\S+[\\S\\s]*)(\\s+/to\\s+)(\\S+[\\S\\s]*)$",
            Pattern.CASE_INSENSITIVE);
    public static final Pattern PATTERN_TODO = Pattern.compile(
            "^(\\S+[\\S\\s]*)$",
            Pattern.CASE_INSENSITIVE);
}
