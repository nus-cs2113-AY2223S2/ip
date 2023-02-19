package duke.command;

public enum CommandWords {
    BYE("bye"),
    LIST("list"),
    MARK("mark"),
    UNMARK("unmark"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    DELETE("delete");
    public final String COMMAND;
    CommandWords(String command) {
        COMMAND = command;
    }
}
