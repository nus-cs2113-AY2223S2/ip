public enum Command {
    LIST("list"),
    MARK("mark"),
    UNMARK("unmark"),
    REMIND("remind"),
    DEADLINE("deadline"),
    EVENT("event"),
    EXIT("bye");

    private final String name;

    private Command(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
