package util;

import java.util.ArrayList;

import errors.ErrorMessages;
import errors.TaskAddError;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;


/**
 * Handles Adding tasks to a list
 * */
public class TaskAdder extends ErrorMessages {
    private static final String BLANK = "";
    private static final String SPACE = " ";
    private static final String OR_SYMBOL = "|";
    private static final String TODO_TASK = "todo";
    private static final String DEADLINE_TASK = "deadline";
    private static final String EVENT_TASK = "event";
    private static final String CHAR_SPACE = " ";
    private static final String BY_SPLIT = "/by";
    private static final String FROM_SPLIT = "/from";
    private static final String TO_SPLIT = "/to";
    private static final Integer CORRECT_TASK_INPUT_LENGTH = 2;
    private static final Integer CORRECT_DEADLINE_INPUT_LENGTH = 2;
    private static final Integer CORRECT_EVENT_INPUT_LENGTH = 3;
    private static final String[] EVENT_PARAMS = {FROM_SPLIT, TO_SPLIT};

    private final OutputUI outputUI = new OutputUI();

    /**
     * @param listOfTasks TaskList to add task to
     * @param description Description of new task
     * @param loadedFromSaveData Whether to output the new task or not,
     *                         Mainly to be differentiated when saving or during a Duke session
     */
    public void addTaskToList(ArrayList<Task> listOfTasks, String description, boolean loadedFromSaveData) {
        String[] taskDescription = description.split(CHAR_SPACE, 2);
        try {
            if (taskDescription.length != CORRECT_TASK_INPUT_LENGTH) {
                throw new TaskAddError(errorTaskEmptyDescriptionText());
            }
            switch (taskDescription[0]) {
            case TODO_TASK:
                addTodoTask(listOfTasks, taskDescription[1], loadedFromSaveData);
                break;
            case DEADLINE_TASK:
                addDeadlineTask(listOfTasks, taskDescription[1], loadedFromSaveData);
                break;
            case EVENT_TASK:
                addEventTask(listOfTasks, taskDescription[1], loadedFromSaveData);
                break;
            default:
                if (!loadedFromSaveData){
                    throw new TaskAddError(errorWrongTaskNameText());
                }

            }
        } catch (TaskAddError e) {
            System.out.println(e.getMessage());
        }

    }

    private void addTodoTask(ArrayList<Task> listOfTasks, String description, boolean hideOutput)
            throws TaskAddError {
        String descriptionBlankCheck = clearBlankSpaces(description);
        if (descriptionBlankCheck.equals(BLANK)) {
            throw new TaskAddError(errorTaskEmptyDescriptionText());
        }
        Todo todoTask = new Todo(BLANK + description.trim());
        listOfTasks.add(todoTask);
        if (!hideOutput) {
            outputUI.addToListMessage(todoTask, listOfTasks.size());
        }
    }


    private void addDeadlineTask(ArrayList<Task> listOfTasks, String description, boolean hideOutput)
            throws TaskAddError {
        if (!description.contains(BY_SPLIT)) {
            throw new TaskAddError(errorTaskNoDeadlineParamsText());
        }
        String descriptionBlankCheck = clearBlankSpaces(description);
        if (descriptionBlankCheck.equals(BLANK)) {
            throw new TaskAddError(errorTaskEmptyDescriptionText());
        }
        String[] deadlineInput = description.split(BY_SPLIT);
        if (deadlineInput.length != CORRECT_DEADLINE_INPUT_LENGTH) {
            throw new TaskAddError(errorDeadlineMissingDescriptionOrDeadline());
        }
        String deadlineDescription = deadlineInput[0].trim();
        String deadlineFinishByDate = deadlineInput[1].trim();
        if (deadlineFieldsAreNotBlank(deadlineDescription, deadlineFinishByDate)) {
            Deadline deadLineTask = new Deadline(deadlineInput[0], deadlineInput[1]);
            listOfTasks.add(deadLineTask);
            if (!hideOutput) {
                outputUI.addToListMessage(deadLineTask, listOfTasks.size());
            }
        }
    }


    private void addEventTask(ArrayList<Task> listOfTasks, String description, boolean hideOutput) throws TaskAddError {
        if (!containsEventParams(description)) {
            throw new TaskAddError(errorTaskWrongEventParamsText());
        }
        String descriptionBlankCheck = clearBlankSpaces(description);
        if (descriptionBlankCheck.equals(BLANK)) {
            throw new TaskAddError(errorTaskEmptyDescriptionText());
        }
        String[] eventData = description.split(FROM_SPLIT + OR_SYMBOL + TO_SPLIT);

        String eventDescription;
        String eventFrom;
        String eventTo;
        if (eventData.length == CORRECT_EVENT_INPUT_LENGTH) {
            eventDescription = eventData[0].trim();
            eventFrom = eventData[1].trim();
            eventTo = eventData[2].trim();
        } else {
            throw new TaskAddError(errorTaskWrongEventFormatText());
        }
        if (eventFieldsAreNotBlank(eventDescription, eventFrom, eventTo)) {
            Event newEvent = new Event(eventDescription, eventFrom, eventTo);
            listOfTasks.add(newEvent);
            if (!hideOutput) {
                outputUI.addToListMessage(newEvent, listOfTasks.size());
            }
        }
    }

    private boolean deadlineFieldsAreNotBlank(String description, String deadline) throws TaskAddError {
        if (description.equals(BLANK)) {
            throw new TaskAddError(errorTaskEmptyDescriptionForDeadlineText());
        }
        if (deadline.equals(BLANK)) {
            throw new TaskAddError(errorTaskEmptyDueDateDeadlineText());
        }
        return true;
    }

    private static boolean containsEventParams(String inputString) {
        boolean found = true;
        for (String item : EVENT_PARAMS) {
            if (!inputString.contains(item)) {
                found = false;
                break;
            }
        }
        return found;
    }

    private boolean eventFieldsAreNotBlank(String eventDescription, String eventFrom, String eventTo)
            throws TaskAddError {
        if (eventDescription.equals(BLANK)) {
            throw new TaskAddError(errorTaskEmptyDescriptionText());
        }
        if (eventFrom.equals(BLANK)) {
            throw new TaskAddError(errorTaskEmptyStartDateEventText());
        }
        if (eventTo.equals(BLANK)) {
            throw new TaskAddError(errorTaskEmptyEndDateEventText());
        }
        return true;
    }

    private static String clearBlankSpaces(String inputString) {
        return inputString.replaceAll(SPACE, BLANK);
    }
}
