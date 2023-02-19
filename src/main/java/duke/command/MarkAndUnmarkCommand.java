package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.IllegalCommandException;

import java.io.IOException;

public class MarkAndUnmarkCommand extends Command {

    private String commandType;
    private String[] commandWords;
    private int indexOfMarking;

    public MarkAndUnmarkCommand(String commandType, String[] words, int indexOfMarking) {
        super();
        this.commandType = commandType;
        this.commandWords = words;
        this.indexOfMarking = indexOfMarking;
    }

    @Override
    public void execute(TaskList tasks, Storage database) {
        createMarkOrUnmark(tasks, database);
    }


    private void createMarkOrUnmark(TaskList tasks, Storage database) {
        tasks.getTaskFromIndex(indexOfMarking).setDone(commandWords[0]);
        Ui.markChangeMessage(commandType, indexOfMarking, tasks);
        try {
            database.updateDatabaseTask();
        } catch (IOException e) {
            Ui.updateDatabaseFailureMessage();
        }
    }

}
