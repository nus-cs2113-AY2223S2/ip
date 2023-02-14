package IO;

import java.util.InputMismatchException;
import java.util.Scanner;

import exception.PromptCannotBeEmptyException;
import Task.Deadline;
import Task.Event;
import Task.Task;
import Task.Todo;

public class Input {
    static Scanner sc = new Scanner(System.in);

    public static String scanWord() {
        String word = sc.next();
        return word;
    }

    public static String scanLine() {
        String line = sc.nextLine();
        return line;
    }

    public static String scanPrompt(String taskType) throws PromptCannotBeEmptyException {
        String prompt = "";
        try {
            prompt = sc.nextLine().trim();
            if (prompt.equals("")) {
                throw new PromptCannotBeEmptyException();
            } else {
                return prompt;
            }
        } catch (PromptCannotBeEmptyException e) {

            Output.printPromptEmptyError(taskType);
            return null;
        }
    }

    public static int scanTaskIndex(int tasksSize) {
        try {
            int indexTask = sc.nextInt() - 1;
            if (indexTask >= tasksSize) {
                throw new IndexOutOfBoundsException();
            } else {
                return indexTask;
            }

        } catch (InputMismatchException e) {
            Output.printTaskIndexNotIntegerError();
            scanLine();
            return -1;
        } catch (IndexOutOfBoundsException e) {
            Output.printTaskIndexOutOfBoundsError();
            return -1;
        }
    }

    public static String[] parseDeadline(String prompt) {
        try {
            int dividerPosition = prompt.indexOf("/by");
            String description = prompt.substring(0, dividerPosition).trim();
            String by = prompt.substring(dividerPosition + 3).trim();
            return new String[]{description, by};
        } catch (StringIndexOutOfBoundsException e) {
            Output.printIncorrectFormatError("event");
            return null;
        }
    }

    public static String[] parseEvent(String prompt) {
        try {
            int divider1Position = prompt.indexOf("/from");
            int divider2Position = prompt.indexOf("/to");
            String description = prompt.substring(0, divider1Position).trim();
            String from = prompt.substring(divider1Position + 5, divider2Position).trim();
            String to = prompt.substring(divider2Position + 3).trim();
            return new String[]{description, from, to};
        } catch (StringIndexOutOfBoundsException e) {
            Output.printIncorrectFormatError("event");
            return null;
        }
    }

    public static String parseTaskToWrite(Task task) {
        Boolean isDone = task.getStatus();
        String taskType = task.getTaskType();
        String encodedBooleanIsDone;
        String description = task.getTaskDescription();
        String startTime;
        String endTime;
        String parseResult = null;

        if (isDone) {
            encodedBooleanIsDone = "1";
        } else {
            encodedBooleanIsDone = "0";
        }

        switch (taskType) {
        case "T":
            parseResult = taskType + " | " + encodedBooleanIsDone + " | " + description;
            break;
        case "D":
            endTime = ((Deadline) task).getEndTime();
            parseResult = taskType + " | " + encodedBooleanIsDone + " | " + description + " | " + endTime;
            break;
        case "E":
            startTime = ((Event) task).getStartTime();
            endTime = ((Event) task).getEndTime();
            parseResult = taskType + " | " + encodedBooleanIsDone + " | " + description + " | " + startTime + " | " + endTime;
            break;
        default:
            parseResult = "Error";
        }

        return parseResult;
    }

    public static Task parseFileContent(String text) {
        Task readTask = null;
        Boolean isDone;
        String[] arrOfStr = text.split("[|]");
        String taskType = arrOfStr[0].trim();
        String doneString = arrOfStr[1].trim();
        String description = arrOfStr[2].trim();
        String startTime;
        String endTime;

        if (doneString.equals("1")) {
            isDone = true;
        } else {
            isDone = false;
        }

        switch (taskType) {
        case "T":
            readTask = new Todo(description);
            readTask.setMark(isDone);
            break;
        case "D":
            endTime = arrOfStr[3].trim();
            readTask = new Deadline(description, endTime);
            readTask.setMark(isDone);
            break;
        case "E":
            startTime = arrOfStr[3].trim();
            endTime = arrOfStr[4].trim();
            readTask = new Event(description, startTime, endTime);
            readTask.setMark(isDone);
            break;
        default:
            readTask = null;
        }

        return readTask;
    }
}