package commands;

import enums.ErrorDialogueTypes;
import managers.OutputDialogueManager;
import managers.SaveManager;
import managers.TaskManager;
import tasks.Task;
import java.io.IOException;

/**
 * Represents a command that is instructed for the program to conduct. A Command
 * Object specifies what the type of command is with its subclasses.
 */
public abstract class Command {
    /**
     * Executes the command depending on the type of command it is, using the various managers.
     *
     * @param taskList A TaskManager that controls the list of Tasks and how it is manipulated.
     * @param storage A SaveManager that controls how the list of Tasks can be saved.
     * @param display An OutputDialogueManager that controls what to display for the user to see.
     */
    public abstract void execute(TaskManager taskList, SaveManager storage, OutputDialogueManager display);

    /**
     * Calls the SaveManager to save all the tasks in the current list of Tasks.
     *
     * @param taskList A TaskManager that controls the list of Tasks and how it is manipulated.
     * @param storage A SaveManager that controls how the list of Tasks can be saved.
     * @param display An OutputDialogueManager that controls what to display for the user to see.
     */
    public void saveAllTasks (TaskManager taskList, SaveManager storage,OutputDialogueManager display) {
        try {
            storage.saveCurrentState(taskList);
        } catch (IOException e) {
            display.printErrorDialogue(ErrorDialogueTypes.ERROR_WHEN_SAVING);
        }
    }

    /**
     * Calls the SaveManager to save one more Task in to the current list of Tasks.
     *
     * @param newTask A Task that is going to be saved.
     * @param storage A SaveManager that controls how the list of Tasks can be saved.
     * @param display An OutputDialogueManager that controls what to display for the user to see.
     */
    public void saveOneTasks (Task newTask, SaveManager storage, OutputDialogueManager display) {
        try {
            storage.saveNewTask(newTask);
        } catch (IOException e) {
            display.printErrorDialogue(ErrorDialogueTypes.ERROR_WHEN_SAVING);
        }
    }
}
