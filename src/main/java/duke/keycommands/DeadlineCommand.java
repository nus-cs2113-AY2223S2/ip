package duke.keycommands;

import duke.common.Common;
import duke.tasktypes.Deadline;
import duke.tasktypes.Task;
import duke.exception.EmptyDescription;

public class DeadlineCommand {
    private String content;
    private String date;
    public static final String EMPTY_DEADLINE_DESCRIPTION = "OOPS!!! The description of a deadline cannot be empty.";
    private static final String ADDING_TASK = "Got it. I've added this task:";
    public static final String EMPTY_DEADLINE_TASK = "OOPS!!! the deadline can not be empty";
    public static final String INVALID_INPUT = "This is an invalid input, please follow this input format\n";
    private static final String DEADLINE_FORMAT = "Format: deadline {your task} /by {deadline date}\n";
    public static final String DEADLINE_INVALID_INPUT = INVALID_INPUT + DEADLINE_FORMAT;

    public DeadlineCommand(String content, String date) {
        this.content = content;
        this.date = date;
        addDeadlineTask();
    }

    private void addDeadlineTask() {
        Task task = new Deadline(content,date);
        Common.tasks.add(task);
        System.out.println(ADDING_TASK);
        task.printTask();
        System.out.println("Now you have " + Common.tasks.size() + " tasks in the list.");
        Common.dataFile.appendTaskToDataFile(task.putInputToDataFile());
    }
}
