package parser;

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
