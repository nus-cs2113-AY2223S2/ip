package wilsonoh.sagyo.commands;

public enum CommandType {
    LIST("^\\s*list\\s*$"),
    DELETE("^\\s*(delete|d)\\s*(?<idx>-?\\d+)?\\s*$"),
    FIND("^\\s*(find|f)\\s*(?<filter>.+)?$"),
    MARK("^\\s*mark\\s*(?<idx>-?\\d+)?\\s*$"),
    UNMARK("^\\s*unmark\\s*(?<idx>-?\\d+)?\\s*$"),
    BYE("^\\s*bye\\s*$"),
    EVENT("^\\s*(event|e)\\s*((?<name>.+?)\\s*(/from\\s*(?<from>.+?))?\\s*(/to\\s*(?<to>.+))?)?\\s*$"),
    DEADLINE("^\\s*(deadline|dl)\\s*((?<name>.+?)\\s*(/by\\s*(?<by>.+))?)?\\s*$"),
    TODO("^\\s*(todo|t)\\s*(?<name>.+)?$");

    private final String regexPattern;

    CommandType(String regexPattern) {
        this.regexPattern = regexPattern;
    }

    public String getRegexPattern() {
        return this.regexPattern;
    }
}
