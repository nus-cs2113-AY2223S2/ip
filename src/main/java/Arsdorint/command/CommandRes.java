package Arsdorint.command;

import Arsdorint.MessageList;
import Arsdorint.task.Task;

import java.util.ArrayList;
import java.util.stream.IntStream;

public class CommandRes {
    public String messageTop = "";
    public String messageBottom = "";
    public ArrayList<Task> task = null;

    public CommandRes(String strTop) {
        this.messageTop = strTop;
    }
    public CommandRes(String strTop, ArrayList task, String strBottom) {
        this.messageTop = strTop;
        this.messageBottom = strBottom;
        this.task = task;
    }

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
}
