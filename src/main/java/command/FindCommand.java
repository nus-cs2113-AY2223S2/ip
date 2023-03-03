package command;

import exception.DukeException;
import task.Task;
import taskList.TaskList;

import java.util.ArrayList;

public class FindCommand extends Command {
    private final int KEYWORD = 1;

    public FindCommand(ArrayList<String> commands) {
        super(commands);
    }

    /**
     * Display a list of tasks whose description contains a specific keyword.
     *
     * @param taskList The TaskList of Duke.
     * @return The list of Duke's tasks whose description contains aa specific keyword and their properties.
     * @throws DukeException if keyword argument is missing.
     */
    @Override
    public String doCommand(TaskList taskList) throws DukeException {
        String keyword = getCommands().get(KEYWORD);
        ArrayList<Task> tasksWithKeyword = new ArrayList<>();
        ArrayList<Task> tasks = taskList.getTasks();
        for (Task task: tasks) {
            String description = task.getDescription();
            if (description.contains(keyword)) {
                tasksWithKeyword.add(task);
            }
        }
        TaskList taskListWithKeyWord = new TaskList(tasksWithKeyword);
        StringBuilder result = new StringBuilder();
        result.append("Here are the matching tasks in your list:\n");
        for (int i = 0; i < taskListWithKeyWord.size(); i++) {
            result.append(i + 1);
            result.append(":").append(taskListWithKeyWord.get(i).getSummary()).append("\n");
        }
        return String.valueOf(result);
    }
}
