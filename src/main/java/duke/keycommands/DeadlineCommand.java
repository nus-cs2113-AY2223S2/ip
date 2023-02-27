package duke.keycommands;

import duke.common.Common;
import duke.tasktypes.Deadline;
import duke.tasktypes.Task;

public class DeadlineCommand {

    private static final String ADDING_TASK = "Got it. I've added this task:";

    private String content;
    private String date;


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
