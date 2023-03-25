package commands;
import parser.Parser;
import storage.Storage;
import Task.Event;
import Task.Task;
import TaskManager.TaskManager;
import UI.TextUI;

public class AddEventCommand implements Handler {
    private String arguments;
    public static final String COMMAND_WORD = "event";
    private static final int INDEX_DESCRIPTION_PARSED = 0;
    private static final int INDEX_STARTTIME_PARSED = 1;
    private static final int INDEX_ENDTIME_PARSED = 2;

    public AddEventCommand(String arguments) {
        this.arguments = arguments;
    }

    public void execute(TaskManager tasks, TextUI ui, Storage storage) {
        if (!arguments.isBlank()) {
            Parser parser = new Parser();
            String description = null;
            String startTime = null;
            String endTime = null;
            String[] parsedString = parser.parseEvent(arguments);

            if (parsedString != null) {
                description = parsedString[INDEX_DESCRIPTION_PARSED];
                startTime = parsedString[INDEX_STARTTIME_PARSED];
                endTime = parsedString[INDEX_ENDTIME_PARSED];
            }

            if (description != null & startTime != null & endTime != null) {
                Task newTask = new Event(description, startTime, endTime);
                tasks.addTask(newTask);
                ui.showNewTaskMessage(tasks, newTask);
            }
        } else {
            ui.showPromptEmptyErrorMessage(COMMAND_WORD);
        }
    }

    public boolean isExit() {
        return false;
    }
}