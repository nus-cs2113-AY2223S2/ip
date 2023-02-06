import errors.InvalidDeadlineException;
import errors.InvalidEventException;
import errors.TaskNumberOutOfRangeException;

import java.util.ArrayList;

public class InputManager {
    private static final ArrayList<Task> todoList = new ArrayList<Task>();
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
        case "mark": {
            markTaskAsState(words, true);
            break;
        }
        case "unmark": {
            markTaskAsState(words, false);
            break;
        }
        case "todo": {
            addNewTodoTask(line);
            break;
        }
        case "deadline": {
            addNewDeadlineTask(line);
            break;
        }
        case "event": {
            addNewEventTask(line);
            break;
        }
        case "help" : {
            displayHelpMenu();
            break;
        }
        default:
            System.out.println("default error");
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
        Task newTask = new Events(line.substring(EVENT_BEGIN_INDEX));
        todoList.add(newTask);
        OutputDialogueManager.printInteraction(DialogueTypes.ADD_TASK);
        newTask.printTaskWithoutId();
    }

    private static void addNewDeadlineTask(String line) throws InvalidDeadlineException {
        Task newTask = new Deadlines(line.substring(DEADLINE_BEGIN_INDEX));
        todoList.add(newTask);
        OutputDialogueManager.printInteraction(DialogueTypes.ADD_TASK);
        newTask.printTaskWithoutId();
    }

    private static void addNewTodoTask(String line) {
        Task newTask = new ToDos(line.substring(TODO_BEGIN_INDEX));
        todoList.add(newTask);
        OutputDialogueManager.printInteraction(DialogueTypes.ADD_TASK);
        newTask.printTaskWithoutId();
    }

    private static void markTaskAsState(String[] words, boolean state)
            throws TaskNumberOutOfRangeException {
        int indexToMark = Integer.parseInt(words[1]) - 1;
        if (indexToMark > Task.getItemCount() || indexToMark <= 0) {
            throw new TaskNumberOutOfRangeException();
        }
        todoList.get(indexToMark).markAsState(state);
        if (state) {
            OutputDialogueManager.printInteraction(DialogueTypes.MARK_TASK);
        } else {
            OutputDialogueManager.printInteraction(DialogueTypes.UNMARK_TASK);
        }
        todoList.get(indexToMark).printTask();
    }
    private static void listAllItems(){
        if (Task.getItemCount() < 1) {
            OutputDialogueManager.printErrorDialogue(DialogueTypes.NO_TASK_IN_LIST);
            return;
        }
        OutputDialogueManager.printInteraction(DialogueTypes.LIST_TASKS);
        for (Task item: todoList) {
            item.printTask();
        }
    }
}
