package ui;

import java.util.Set;
import task.DeadlineTask;
import task.EventTask;

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
    public static final Set<String> ADD_TASK_COMMANDS = Set.of(TODO.label, DEADLINE.label, EVENT.label);

    // All command syntax that modify an existing task
    public static final Set<String> MODIFY_TASK_COMMANDS = Set.of(MARK.label, UNMARK.label, DELETE.label);

    public final String label;
    public final String expectedSyntax;

    Syntax(String label, String expectedSyntax) {
        this.label = label;
        this.expectedSyntax = expectedSyntax;
    }

    Syntax(String label) {
        this.label = label;
        this.expectedSyntax = "";
    }

}
