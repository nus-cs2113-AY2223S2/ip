package commands;

import enums.DialogueTypes;
import enums.ErrorDialogueTypes;
import managers.InputManager;
import managers.OutputDialogueManager;
import managers.SaveManager;
import managers.TaskManager;
import tasks.Task;

import java.io.IOException;

public abstract class Command {

    public abstract void execute(TaskManager taskList, SaveManager storage, OutputDialogueManager display);

    public void saveAllTasks (TaskManager taskList, SaveManager storage,OutputDialogueManager display) {
        try {
            storage.saveCurrentState(taskList);
        } catch (IOException e) {
            display.printErrorDialogue(ErrorDialogueTypes.ERROR_WHEN_SAVING);
        }
    }

    public void saveOneTasks (Task newTask, SaveManager storage, OutputDialogueManager display) {
        try {
            storage.saveNewTask(newTask);
        } catch (IOException e) {
            display.printErrorDialogue(ErrorDialogueTypes.ERROR_WHEN_SAVING);
        }
    }
}
