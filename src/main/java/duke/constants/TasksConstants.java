package duke.constants;

import duke.parser.datetime.DateTimeParser;

public final class TasksConstants {
    private TasksConstants() {
    }

    public static final String FORMAT_EVENT = "Event - event <task details> "
            + "/from <" + DateTimeParser.getFormatDateTime() + "> "
            + "/to <" + DateTimeParser.getFormatDateTime() + ">"
            + System.lineSeparator();
    public static final String FORMAT_DEADLINE = "Deadline - deadline <task details> "
                    + "/by <" + DateTimeParser.getFormatDateTime() + ">"
                    + System.lineSeparator();
    public static final String FORMAT_TODO = "Todo - todo <task details>" + System.lineSeparator();
    public static final String FORMAT_DATE_TIME = "yyyy-MM-dd HHmm";
    public static final String TASK_EVENT = "EVENT";
    public static final String TASK_DEADLINE = "DEADLINE";
    public static final String TASK_TODO = "TODO";
}
