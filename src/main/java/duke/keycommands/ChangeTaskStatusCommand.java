package duke.keycommands;

import duke.common.Common;

import java.io.IOException;

public class ChangeTaskStatusCommand {
    private static final String FINISH_UNMARKING_MESSAGE = "Ok! I've marked this task as not done yet:";
    private static final String FINISH_MARKING_MESSAGE = "Nice! I've marked this task as done:";
    private int taskNumber;
    private String keyword;

    public ChangeTaskStatusCommand(int taskNumber, String keyword) {
        this.taskNumber = taskNumber;
        this.keyword = keyword;
        changeTaskStatus();
    }


    private void changeTaskStatus() {
        if (taskNumber > Common.tasks.size()) {
            System.out.println(Common.BIG_NUMBER);
            return;
        } else if (taskNumber <= 0) {
            System.out.println("Please give me a positive task number");
            return;
        }
        boolean doesCommandContainsUnmark = keyword.equals("unmark");
        if (doesCommandContainsUnmark) {
            Common.tasks.get(taskNumber - 1).unMark();
            System.out.println(FINISH_UNMARKING_MESSAGE);
        } else {
            Common.tasks.get(taskNumber - 1).mark();
            System.out.println(FINISH_MARKING_MESSAGE);
        }
        System.out.println(taskNumber + ". " + Common.tasks.get(taskNumber - 1).getMarkingStatus()
                + " " + Common.tasks.get(taskNumber - 1).getContent());
        try {
            Common.dataFile.updateTask(Common.FILE_PATH);
        } catch (IOException error) {
            System.out.println(Common.WRITEFILE_EXCEPTION_MESSAGE);
        }
    }

}
