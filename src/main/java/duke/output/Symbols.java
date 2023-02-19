package duke.output;

public enum Symbols {
    TODO("T"),
    DEADLINE("D"),
    EVENT("E"),
    PROGRAM_MARK("X"),
    PROGRAM_UNMARK(" "),
    DATA_MARK("1"),
    DATA_UNMARK("0"),
    DATA_DELIMITER(" / "),
    EVENT_DATE_DELIMITER(" to ");

    public final String SYMBOL;
    Symbols(String symbol) {
        SYMBOL = symbol;
    }
}
