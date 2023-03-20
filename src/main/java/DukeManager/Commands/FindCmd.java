package DukeManager.Commands;

import DukeManager.Ui.TextUi;
import DukeManager.data.DukeErrors.BlankListException;
import DukeManager.data.TaskList;
import DukeManager.data.Tasks.Task;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class FindCmd extends Cmd {
    public static final String MSG_USAGE =
            "find: Displays all tasks in the taskList as a list that contains tha keyword used in command.\n"
                    + "Example: find sweep";

    private static final String MSG_FIND_HEADER = "\t  These are the task(s) in your list containing %s: ";

    private static String keyword;

    public FindCmd(String keyword) {
        FindCmd.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, TextUi ui) throws BlankListException {
        this.taskList = tasks;
        int counter = 1;
        String[] taskPrintList = new String[taskList.size() + 1];
        if (taskList.size() == 0) {
            throw new BlankListException();
        }

        taskPrintList[0] = String.format(MSG_FIND_HEADER, keyword);
        for (int i = 1; i < taskList.size() + 1; i++) {
            if (taskList.getTask(i - 1).getDescription().contains(keyword.toLowerCase())) {
                taskPrintList[counter] = String.format("%d. %s\n", counter, taskList.getTask(i - 1));
                counter++;
            }
        }
        ui.showToUser(Arrays.copyOf(taskPrintList, counter));
    }
}
