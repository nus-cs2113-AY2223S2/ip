package ui;

import java.util.Set;
import task.DeadlineTask;
import task.EventTask;

/**
 * Holds the root command and expected syntax of all commands
 */
public enum Syntax {

    EXIT("bye"),
    LIST("list"),
    FIND("find", "find <description to be found>"),
    MARK("mark", "mark <task #>"),
    UNMARK("unmark", "unmark <task #>"),
    DELETE("delete", "delete <task #>"),
    TODO("todo", "todo <description>"),
    DEADLINE("deadline", "deadline <description> " + DeadlineTask.BY_DELIMITER + " <deadline>"),
    EVENT("event",
            "event <description> " + EventTask.START_DELIMITER + " <start> " + EventTask.END_DELIMITER + " <end>");

    // All command syntax that create a new task
    public static final Set<String> ADD_TASK_COMMANDS = Set.of(TODO.root, DEADLINE.root, EVENT.root);

    // All command syntax that modify an existing task
    public static final Set<String> MODIFY_TASK_COMMANDS = Set.of(MARK.root, UNMARK.root, DELETE.root);

    public final String root;
    public final String expectedSyntax;

    Syntax(String label, String expectedSyntax) {
        this.root = label;
        this.expectedSyntax = expectedSyntax;
    }

    Syntax(String label) {
        this.root = label;
        this.expectedSyntax = "";
    }

}
