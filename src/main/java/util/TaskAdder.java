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

    private static final String TODO_TASK = "todo";
    private static final String DEADLINE_TASK = "deadline";
    private static final String EVENT_TASK = "event";
    private static final String CHAR_SPACE = " ";
    private static final String BY_SPLIT = "/by";
    private static final String FROM_SPLIT = "/from";
    private static final String TO_SPLIT = "/to";
    private static final String[] EVENT_PARAMS = {FROM_SPLIT, TO_SPLIT};

    private final OutputUI outputUI = new OutputUI();

    /**
     * @param listOfTasks TaskList to add task to
     * @param description Description of new task
     * @param loadFromSaveData Whether to output the new task or not,
     *                         Mainly to be differentiated when saving or during a Duke session
     */
    public void addTaskToList(ArrayList<Task> listOfTasks, String description, boolean loadFromSaveData) {
        String[] taskDescription = description.split(CHAR_SPACE, 2);
        try {
            if (taskDescription.length != 2) {
                throw new TaskAddError(provideTaskEmptyDescriptionText());
            }
            switch (taskDescription[0]) {
            case TODO_TASK:
                addTodoTask(listOfTasks, taskDescription[1], loadFromSaveData);
                break;
            case DEADLINE_TASK:
                addDeadlineTask(listOfTasks, taskDescription[1], loadFromSaveData);
                break;
            case EVENT_TASK:
                addEventTask(listOfTasks, taskDescription[1], loadFromSaveData);
                break;
            default:
                System.out.println(provideWrongTaskNameText());
            }
        } catch (TaskAddError e) {
            System.out.println(e.getMessage());
        }

    }

    private void addTodoTask(ArrayList<Task> listOfTasks, String description, boolean hideOutput)
            throws TaskAddError {
        String descriptionBlankCheck = clearBlankSpaces(description);
        if (descriptionBlankCheck.equals(BLANK)) {
            throw new TaskAddError(provideTaskEmptyDescriptionText());
        }
        Todo todoTask = new Todo(" " + description.trim());
        listOfTasks.add(todoTask);
        if (!hideOutput) {
            outputUI.addToListMessage(todoTask, listOfTasks.size());
        }
    }


    private void addDeadlineTask(ArrayList<Task> listOfTasks, String description, boolean hideOutput)
            throws TaskAddError {
        if (!description.contains(BY_SPLIT)) {
            throw new TaskAddError(provideTaskNoDeadlineParamsText());
        }
        String descriptionBlankCheck = clearBlankSpaces(description);
        if (descriptionBlankCheck.equals(BLANK)) {
            throw new TaskAddError(provideTaskEmptyDescriptionText());
        }
        String[] deadlineInput = description.split(BY_SPLIT);
        if (deadlineInput.length != 2) {
            throw new TaskAddError(provideTaskEmptyDescriptionText());
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
        if (!containsEventParams(description, EVENT_PARAMS)) {
            throw new TaskAddError(provideTaskWrongEventParamsText());
        }
        String descriptionBlankCheck = clearBlankSpaces(description);
        if (descriptionBlankCheck.equals(BLANK)) {
            throw new TaskAddError(provideTaskEmptyDescriptionText());
        }
        String[] eventData = description.split(FROM_SPLIT + "|" + TO_SPLIT);

        String eventDescription;
        String eventFrom;
        String eventTo;
        if (eventData.length == 3) {
            eventDescription = eventData[0].trim();
            eventFrom = eventData[1].trim();
            eventTo = eventData[2].trim();
        } else {
            throw new TaskAddError(provideTaskWrongEventFormatText());
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
            throw new TaskAddError(provideTaskEmptyDescriptionForDeadlineText());
        }
        if (deadline.equals(BLANK)) {
            throw new TaskAddError(provideTaskEmptyDueDateDeadlineText());
        }
        return true;
    }

    private static boolean containsEventParams(String inputString, String[] items) {
        boolean found = true;
        for (String item : items) {
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
            throw new TaskAddError(provideTaskEmptyDescriptionText());
        }
        if (eventFrom.equals(BLANK)) {
            throw new TaskAddError(provideTaskEmptyStartDateEventText());
        }
        if (eventTo.equals(BLANK)) {
            throw new TaskAddError(provideTaskEmptyEndDateEventText());
        }
        return true;
    }

    private static String clearBlankSpaces(String inputString) {
        return inputString.replaceAll(" ", "");
    }
}
