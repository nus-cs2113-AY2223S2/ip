package ui;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public enum Command {

    EXIT("bye"),
    LIST("list"),
    MARK("mark"),
    UNMARK("unmark"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event");

    // All commands recognized to create a new task
    public static final Set<String> TASKS = new HashSet<>(Arrays.asList(TODO.label, DEADLINE.label, EVENT.label));

    public final String label;

    Command(String label) {
        this.label = label;
    }

    /**
     * Expected command syntax
     */
    public static class Syntax {

        public static final String MARK = "mark <task #>";
        public static final String UNMARK = "unmark <task #>";
        public static final String TODO = "todo <description>";
        public static final String DEADLINE = "deadline <description> /by <deadline>";
        public static final String EVENT = "event <description> /from <start> /to <end>";

    }

}
