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

public class AddCommand extends Command{
    public String commandLine;
    private String content;
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

    private static void printNewlyAddedTask(OutputDialogueManager display, Task newTask) {
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
