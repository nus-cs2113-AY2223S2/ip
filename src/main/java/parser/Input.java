package parser;

/**
 * Represents an enumeration for the CONSTANT valid String that the user can input.
 */
public enum Input {
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    LIST("list"),
    FIND("find"),
    BYE("bye"),
    MARK("mark"),
    UNMARK("unmark"),
    DELETE("delete");

    public final String input;
    Input(String input) {
        this.input = input;
    }
}
