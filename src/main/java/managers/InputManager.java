package managers;

import enums.DialogueTypes;
import tasks.Deadlines;
import tasks.Events;
import tasks.Task;
import tasks.ToDos;

import java.util.ArrayList;

public class InputManager {
    private static final ArrayList<Task> todoList = new ArrayList<Task>();
    public static boolean processInput(String line) {
        if (line.equals("bye")) {
            return true;
        }
        if (line.equals("list")) {
            OutputDialogueManager.printInteraction(DialogueTypes.LIST_TASKS);
            for (Task item: todoList) {
                item.printTask();
            }
        } else {
            String[] words = line.split(" ");
            switch (words[0]) {
            case "mark": {
                int indexToMark = Integer.parseInt(words[1]) - 1;
                todoList.get(indexToMark).setAsDone();
                OutputDialogueManager.printInteraction(DialogueTypes.MARK_TASK);
                todoList.get(indexToMark).printTask();
                break;
            }
            case "unmark": {
                int indexToMark = Integer.parseInt(words[1]) - 1;
                todoList.get(indexToMark).setAsUndone();
                OutputDialogueManager.printInteraction(DialogueTypes.UNMARK_TASK);
                todoList.get(indexToMark).printTask();
                break;
            }
            case "todo": {
                Task newTask = new ToDos(line.substring(5));
                todoList.add(newTask);
                OutputDialogueManager.printInteraction(DialogueTypes.ADD_TASK);
                newTask.printTaskWithoutId();
                break;
            }
            case "deadline": {
                Task newTask = new Deadlines(line.substring(9));
                todoList.add(newTask);
                OutputDialogueManager.printInteraction(DialogueTypes.ADD_TASK);
                newTask.printTaskWithoutId();
                break;
            }
            case "event": {
                Task newTask = new Events(line.substring(6));
                todoList.add(newTask);
                OutputDialogueManager.printInteraction(DialogueTypes.ADD_TASK);
                newTask.printTaskWithoutId();
                break;
            }
            default:
                OutputDialogueManager.printInteraction(DialogueTypes.UNKNOWN_TYPE);
                OutputDialogueManager.printInteraction(DialogueTypes.HELP);
                break;
            }
        }
        return false;
    }
}
