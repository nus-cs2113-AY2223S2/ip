public enum Messages {
    LINE ("_".repeat(60)),
    START("Hello! I'm Duke" + System.lineSeparator() + "What can I do for you?"),
    EXIT ("Bye. Hope to see you again soon!"),
    ADD  ("added: ");

    public final String TEXT;

    Messages(String text) {
        TEXT = text;
    }
}
