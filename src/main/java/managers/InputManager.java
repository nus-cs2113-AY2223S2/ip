package managers;

import enums.DialogueTypes;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;
import errors.InvalidDeadlineException;
import errors.InvalidEventException;
import errors.TaskNumberOutOfRangeException;

import java.util.ArrayList;

public class InputManager {
    private static final ArrayList<Task> tasksList = SaveManager.initialiseData();
    public static final int TODO_BEGIN_INDEX = 5;
    public static final int DEADLINE_BEGIN_INDEX = 9;
    public static final int EVENT_BEGIN_INDEX = 6;

    public static boolean processInput(String line) {
        if (line.equals("bye")) {
            return true;
        }
        if (line.equals("list")) {
            listAllItems();
        } else {
            String[] words = line.split(" ");
            try {
                processComplicatedCommands(line, words);
                SaveManager.saveCurrentState(tasksList);
            } catch (NumberFormatException e) {
                OutputDialogueManager.printErrorDialogue(DialogueTypes.INVALID_TASK_NUMBER);
                listAllItems();
            } catch (IndexOutOfBoundsException e){
                OutputDialogueManager.printErrorDialogue(DialogueTypes.EMPTY_TASK_NAME);
            } catch (InvalidDeadlineException e) {
                OutputDialogueManager.printErrorDialogue(DialogueTypes.DEADLINE_WRONG_FORMAT);
            } catch (InvalidEventException e) {
                OutputDialogueManager.printErrorDialogue(DialogueTypes.EVENT_WRONG_FORMAT);
            } catch (TaskNumberOutOfRangeException e) {
                OutputDialogueManager.printErrorDialogue(DialogueTypes.TASK_NUMBER_OUT_OF_RANGE);
                listAllItems();
            }
        }
        return false;
    }

    private static void processComplicatedCommands(String line, String[] words)
            throws InvalidDeadlineException, InvalidEventException, TaskNumberOutOfRangeException {
        switch (words[0]) {
        case "mark":
            changeStateOfTask(words, true);
            break;
        case "unmark":
            changeStateOfTask(words, false);
            break;
        case "todo":
            addNewTodoTask(line);
            break;
        case "deadline":
            addNewDeadlineTask(line);
            break;
        case "event":
            addNewEventTask(line);
            break;
        case "help":
            displayHelpMenu();
            break;
        case "delete":
            deleteItem(words);
            break;
        default:
            processErrorInput();
            break;
        }
    }

    private static void displayHelpMenu() {
        OutputDialogueManager.printInteraction(DialogueTypes.HELP_MENU);
    }

    private static void processErrorInput() {
        OutputDialogueManager.printInteraction(DialogueTypes.UNKNOWN_TYPE);
        displayHelpMenu();
    }

    private static void addNewEventTask(String line) throws InvalidEventException {
        Task newTask = new Event(line.substring(EVENT_BEGIN_INDEX));
        tasksList.add(newTask);
        OutputDialogueManager.printInteraction(DialogueTypes.ADD_TASK);
        newTask.printTaskWithoutId();
    }

    private static void addNewDeadlineTask(String line) throws InvalidDeadlineException {
        Task newTask = new Deadline(line.substring(DEADLINE_BEGIN_INDEX));
        tasksList.add(newTask);
        OutputDialogueManager.printInteraction(DialogueTypes.ADD_TASK);
        newTask.printTaskWithoutId();
    }

    private static void addNewTodoTask(String line) {
        Task newTask = new ToDo(line.substring(TODO_BEGIN_INDEX));
        tasksList.add(newTask);
        OutputDialogueManager.printInteraction(DialogueTypes.ADD_TASK);
        newTask.printTaskWithoutId();
    }

    private static void changeStateOfTask(String[] words, boolean isDone)
            throws TaskNumberOutOfRangeException, NumberFormatException {
        int indexToMark = Integer.parseInt(words[1]) - 1;
        if (indexToMark >= Task.getItemCount() || indexToMark < 0) {
            throw new TaskNumberOutOfRangeException();
        }
        tasksList.get(indexToMark).markAsState(isDone);
        if (isDone) {
            OutputDialogueManager.printInteraction(DialogueTypes.MARK_TASK);
        } else {
            OutputDialogueManager.printInteraction(DialogueTypes.UNMARK_TASK);
        }
        System.out.print(indexToMark + 1);
        tasksList.get(indexToMark).printTask();
    }
    private static void listAllItems() {
        if (Task.getItemCount() < 1) {
            OutputDialogueManager.printErrorDialogue(DialogueTypes.NO_TASK_IN_LIST);
            return;
        }
        OutputDialogueManager.printInteraction(DialogueTypes.LIST_TASKS);
        int taskCount = 1;
        for (Task item: tasksList) {
            System.out.print(taskCount);
            item.printTask();
            taskCount++;
        }
    }

    private static void deleteItem(String[] words) throws TaskNumberOutOfRangeException {
        int indexToDelete = Integer.parseInt(words[1]) - 1;
        if (indexToDelete >= Task.getItemCount() || indexToDelete < 0) {
            throw new TaskNumberOutOfRangeException();
        }
        Task toBeDeleted = tasksList.get(indexToDelete);
        OutputDialogueManager.printInteraction(DialogueTypes.DELETE_TASK);
        tasksList.remove(indexToDelete);
        Task.decreaseItemCount();
        toBeDeleted.printTaskWithoutId();
    }
}
