package ui;

import java.util.Set;
import task.DeadlineTask;
import task.EventTask;

public enum Command {

    EXIT("bye"),
    LIST("list"),
    MARK("mark", "mark <task #>"),
    UNMARK("unmark", "unmark <task #>"),
    DELETE("delete", "delete <task #>"),
    TODO("todo", "todo <description>"),
    DEADLINE("deadline", "deadline <description> " + DeadlineTask.BY_DELIMITER  + " <deadline>"),
    EVENT("event", "event <description> " + EventTask.START_DELIMITER + " <start> " + EventTask.END_DELIMITER+ " <end>");

    // All commands that create a new task
    public static final Set<String> ADD_TASK_COMMANDS = Set.of(TODO.label, DEADLINE.label, EVENT.label);

    // All commands that modify an existing task
    public static final Set<String> MODIFY_TASK_COMMANDS = Set.of(MARK.label, UNMARK.label, DELETE.label);

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
