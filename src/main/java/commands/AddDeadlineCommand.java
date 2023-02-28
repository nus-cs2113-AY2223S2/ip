package commands;


import parser.Parser;
import storage.Storage;
import Task.Deadline;
import Task.Task;
import TaskManager.TaskManager;
import UI.TextUI;

public class AddDeadlineCommand implements Handler{
    private String arguments;
    private static int INDEX_DESCRIPTION_PARSED = 0;
    private static int INDEX_ENDTIME_PARSED = 1;
    public static final String COMMAND_WORD = "deadline";

    public AddDeadlineCommand(String arguments) {
        this.arguments = arguments;
    }

    public void execute(TaskManager taskManager, TextUI ui, Storage storage) {
        if (!arguments.isBlank()) {
            Parser parser = new Parser();
            String description = null;
            String endTime = null;
            String[] parsedString = parser.parseDeadline(arguments);

            if (parsedString != null) {
                description = parsedString[INDEX_DESCRIPTION_PARSED];
                endTime = parsedString[INDEX_ENDTIME_PARSED];
            }

            if (description != null & endTime != null) {
                Task newTask = new Deadline(description, endTime);
                taskManager.addTask(newTask);
                ui.showNewTaskMessage(taskManager, newTask);
            }
        } else {
            ui.showPromptEmptyErrorMessage(COMMAND_WORD);
        }
    }

    public boolean isExit() {
        return false;
    }
}