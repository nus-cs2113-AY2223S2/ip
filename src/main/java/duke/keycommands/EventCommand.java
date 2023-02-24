package duke.keycommands;

import duke.Common;
import duke.Task;

public class EventCommand {
    public static final String INVALID_INPUT = "This is an invalid input, please follow this input format\n";
    private static final String EVENT_FORMAT = "Format: event {your task} /from {begin date} /to {end date}\n";
    public static final String EVENT_INVALID_INPUT = INVALID_INPUT + EVENT_FORMAT;
    private static final String ADDING_TASK = "Got it. I've added this task:";
    public static final String EMPTY_EVENT_DESCRIPTION = "OOPS!!! The description of a event cannot be empty.";
    private String[] separatedKeyWordAndContent;

    public EventCommand(String[] separatedKeyWordAndContent) {
        this.separatedKeyWordAndContent = separatedKeyWordAndContent;
        doEventCommand();
    }

    public void doEventCommand() {
        if (doesIndexOutOfBoundsOccur(separatedKeyWordAndContent,1, EMPTY_EVENT_DESCRIPTION)) {
            return;
        }
        addEventTask(separatedKeyWordAndContent[1]);
    }
    private boolean doesIndexOutOfBoundsOccur(String[] stringArray, int index, String outputMessage) {
        try {
            String test = stringArray[index];
            return false;
        } catch (ArrayIndexOutOfBoundsException error) {
            System.out.println(outputMessage);
            return true;
        }
    }
    public void addEventTask(String content)  {
        String[] seperatedWordsInContent = content.split(" /");
        if (doesIndexOutOfBoundsOccur(seperatedWordsInContent,2,EVENT_INVALID_INPUT)) {
            return;
        }
        if (doesIndexOutOfBoundsOccur(seperatedWordsInContent[1].split(" ", 2),1,EVENT_INVALID_INPUT)) {
            return;
        }
        String beginDate = seperatedWordsInContent[1].split(" ", 2)[1];
        if (doesIndexOutOfBoundsOccur(seperatedWordsInContent[2].split(" ", 2),1,EVENT_INVALID_INPUT)) {
            return;
        }
        String endDate = seperatedWordsInContent[2].split(" ", 2)[1];

        if (seperatedWordsInContent[1].startsWith("from ") && seperatedWordsInContent[2].startsWith("to ")) {
            String taskName = seperatedWordsInContent[0];
            Task task = new Event(taskName,beginDate,endDate);
            Common.tasks.add(task);
            System.out.println(ADDING_TASK);
            System.out.println("[E][] " + taskName + " (from: " + beginDate + " to: " + endDate + ")");
            System.out.println("Now you have " + Common.tasks.size() + " tasks in the list.");
            String inputToDataFile = "E | " + convertMarkingStatusToNumber(task) + " | " + task.getContent()
                    + " | " + task.getBeginDate() + " | " + task.getEndDate() + "\n";
            Common.dataFile.appendTaskToDataFile(inputToDataFile);
        } else {
            System.out.println(EVENT_INVALID_INPUT);
        }
    }

    private String convertMarkingStatusToNumber(Task task) {
        if (task.getMarkingStatus().equals("[ ]")) {
            return "0";
        } else {
            return "1";
        }
    }
}
