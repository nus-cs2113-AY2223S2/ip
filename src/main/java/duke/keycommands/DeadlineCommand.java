package duke.keycommands;

import duke.Common;
import duke.Task;
import duke.exception.EmptyDescription;

public class DeadlineCommand {
    private String[] separatedKeyWordAndContent;
    public static final String EMPTY_DEADLINE_DESCRIPTION = "OOPS!!! The description of a deadline cannot be empty.";
    private static final String ADDING_TASK = "Got it. I've added this task:";
    public static final String EMPTY_DEADLINE_TASK = "OOPS!!! the deadline can not be empty";
    public static final String INVALID_INPUT = "This is an invalid input, please follow this input format\n";
    private static final String DEADLINE_FORMAT = "Format: deadline {your task} /by {deadline date}\n";
    public static final String DEADLINE_INVALID_INPUT = INVALID_INPUT + DEADLINE_FORMAT;
    public DeadlineCommand(String[] separatedKeyWordAndContent) {
        this.separatedKeyWordAndContent = separatedKeyWordAndContent;
        doDeadlineCommand();
    }
    public void doDeadlineCommand() {
        if (doesIndexOutOfBoundsOccur(separatedKeyWordAndContent,1, EMPTY_DEADLINE_DESCRIPTION)) {
            return;
        }
        try {
            addDeadlineTask(separatedKeyWordAndContent[1]);
        } catch(EmptyDescription e) {
            System.out.println(EMPTY_DEADLINE_TASK);
        }
    }
    private void addDeadlineTask(String content) throws EmptyDescription{
        String[] seperatedWordsInContent = content.split(" /");
        if (doesIndexOutOfBoundsOccur(seperatedWordsInContent, 1, DEADLINE_INVALID_INPUT)) {
            return;
        }
        if (seperatedWordsInContent[1].startsWith("by ")) {
            String date = seperatedWordsInContent[1].split(" ", 2)[1];
            if (date.trim().isEmpty()) {
                throw new EmptyDescription();
            }
            String taskName = seperatedWordsInContent[0];
            Task task = new Deadline(taskName,date);
            Common.tasks.add(task);
            System.out.println(ADDING_TASK);
            System.out.println("[D][] " + taskName + " (by: " + date + ")");
            System.out.println("Now you have " + Common.tasks.size() + " tasks in the list.");
            String inputToDataFile = "D | " + convertMarkingStatusToNumber(task) + " | " + task.getContent()
                    + " | " + task.getDate() + "\n";
            Common.dataFile.appendTaskToDataFile(inputToDataFile);
        } else {
            System.out.println(DEADLINE_INVALID_INPUT);
        }
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


    private String convertMarkingStatusToNumber(Task task) {
        if (task.getMarkingStatus().equals("[ ]")) {
            return "0";
        } else {
            return "1";
        }
    }
}
