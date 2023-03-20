package Arsdorint.command;

import Arsdorint.MessageList;
import Arsdorint.task.Task;

import javax.crypto.spec.OAEPParameterSpec;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

/**
 * Represent the result of the command
 */
public class CommandRes {
    public String messageTop = "";
    public String messageBottom = "";
    public List<? extends Task> task;

    /**
     * Initialization without task
     *
     * @param strTop the first string to be shown
     */
    public CommandRes(String strTop) {
        this.messageTop = strTop;
    }

    /**
     * Initialization with task
     *
     * @param strTop the first string to be shown
     * @param task the list of all tasks
     * @param strBottom the last string to be shown
     */
    public CommandRes(String strTop, ArrayList task, String strBottom) {
        this.messageTop = strTop;
        this.messageBottom = strBottom;
        this.task = task;
    }

    /**
     * Print the task
     */
    public String[] printTask() {
        if (task == null) {
            return new String[]{};
        } else if (messageTop.equals(MessageList.MESSAGE_DIVIDER_LIST)) {
            return IntStream.range(0, task.size()).mapToObj(i ->
                    (i + MessageList.OFFSET + "." + task.get(i))).toArray(String[]::new);
        } else {
            return task.stream().map(Task::toString).toArray(String[]::new);
        }
    }

    public Optional<List<? extends Task>> getTask() {
        return Optional.ofNullable(task);
    }
}
