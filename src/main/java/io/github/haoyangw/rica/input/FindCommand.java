package io.github.haoyangw.rica.input;

import io.github.haoyangw.rica.task.Task;
import io.github.haoyangw.rica.task.TaskManager;

import java.util.List;

public class FindCommand extends Command {
    private static final String MATCHING_TASKS_MSG = " I found some matching tasks for '%s':";
    private static final String NO_MATCHING_TASKS_MSG = " Hmm, none of your tasks match '%s' :( Ever tried 'Hello world'? ;)";

    public FindCommand(String command, TaskManager taskManager) {
        super(command, taskManager);
    }

    private static String getKeywordFrom(String command) {
        final int NUM_PARAMETERS = 2;
        final int INDEX_OF_KEYWORD = 1;
        return command.split(" ", NUM_PARAMETERS)[INDEX_OF_KEYWORD];
    }

    @Override
    public void run() {
        String keyword = FindCommand.getKeywordFrom(super.getCommand());
        List<Task> matchingTasks = super.getTaskManager().getMatchingTasks(keyword);
        super.getTextUi().printTasks(matchingTasks,
                String.format(FindCommand.MATCHING_TASKS_MSG, keyword),
                String.format(FindCommand.NO_MATCHING_TASKS_MSG, keyword));
    }

}
