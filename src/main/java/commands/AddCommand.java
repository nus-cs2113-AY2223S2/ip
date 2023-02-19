package commands;

import enums.DialogueTypes;
import enums.ErrorDialogueTypes;
import errors.InvalidDeadlineException;
import errors.InvalidEventException;
import managers.OutputDialogueManager;
import managers.SaveManager;
import managers.TaskManager;
import tasks.Task;
import static tasks.Task.getItemCount;

/**
 * Represents a command to add a new Task in to the list of Tasks.
 */
public class AddCommand extends Command{
    public String commandLine;
    private String content;

    /**
     * Constructs a new AddCommand, specifying the type and the content of the Task to add.
     *
     * @param commandLine A String containing the type of Task to be added, <code>Todo, Deadline</code>
     *                    or <code>Event</code>.
     * @param content A String containing the content that is used to construct the new Task.
     */
    public AddCommand(String commandLine, String content) {
        this.commandLine = commandLine;
        this.content = content;
    }

    @Override
    public void execute(TaskManager taskList, SaveManager storage, OutputDialogueManager display){
        try {
            Task newTask = addNewTask(taskList);
            printNewlyAddedTask(display, newTask);
            super.saveOneTasks(newTask, storage, display);
        } catch (InvalidDeadlineException e) {
            display.printErrorDialogue(ErrorDialogueTypes.DEADLINE_WRONG_FORMAT);
        } catch (InvalidEventException e) {
            display.printErrorDialogue(ErrorDialogueTypes.EVENT_WRONG_FORMAT);
        }
    }

    private void printNewlyAddedTask(OutputDialogueManager display, Task newTask) {
        display.printInteraction(DialogueTypes.ADD_TASK);
        newTask.printTaskWithoutId();
        display.printInteraction(DialogueTypes.COUNT_OF_TASKS);
        System.out.println(getItemCount());
    }

    private Task addNewTask(TaskManager taskList) throws InvalidDeadlineException, InvalidEventException {
        Task newTask = null;
        switch (this.commandLine) {
        case "todo" :
            newTask = taskList.addNewTodoTask(this.content);
            break;
        case "deadline":
            newTask = taskList.addNewDeadlineTask(this.content);
            break;
        case "event":
            newTask = taskList.addNewEventTask(this.content);
            break;
        }
        return newTask;
    }
}
