package ui;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public enum Command {

    EXIT("bye"),
    LIST("list"),
    MARK("mark", "mark <task #>"),
    UNMARK("unmark", "unmark <task #>"),
    DELETE("delete", "delete <task #>"),
    TODO("todo", "todo <description>"),
    DEADLINE("deadline", "deadline <description> /by <deadline>"),
    EVENT("event", "event <description> /from <start> /to <end>");

    // All commands that create a new task
    public static final Set<String> ADD_TASK_COMMANDS = new HashSet<>(
        Arrays.asList(TODO.label, DEADLINE.label, EVENT.label));

    // All commands that modify an existing task
    public static final Set<String> MODIFY_TASK_COMMANDS = new HashSet<>(
        Arrays.asList(MARK.label, UNMARK.label, DELETE.label));

    public final String label;
    public final String expectedSyntax;

    Command(String label, String expectedSyntax) {
        this.label = label;
        this.expectedSyntax = expectedSyntax;
    }

    Command(String label) {
        this.label = label;
        this.expectedSyntax = "";
    }

}
