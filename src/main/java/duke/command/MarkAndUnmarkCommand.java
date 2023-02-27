package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.io.IOException;

/**
 * MarkAndUnmarkCommand class that inherits from the Command class that handles both mark inputs and unmark inputs from
 * the user
 */
public class MarkAndUnmarkCommand extends Command {
    /**
     * The command type that can be either mark or unmark
     */
    private String commandType;
    /**
     * The user input String that is being split into its constituents
     */
    private String[] commandWords;
    /**
     * The index to be marked or unmarked by the user
     */
    private int indexOfMarking;

    /**
     * Constructor of the MarkAndUnmarkCommand that initialises the commandType, commandWords and indexOfMarking
     *
     * @param commandType    The command type that can be either mark or unmark
     * @param words          The user input String that is being split into its constituents
     * @param indexOfMarking The index to be marked or unmarked by the user
     */
    public MarkAndUnmarkCommand(String commandType, String[] words, int indexOfMarking) {
        super();
        this.commandType = commandType;
        this.commandWords = words;
        this.indexOfMarking = indexOfMarking;
    }

    /**
     * The execution of the MarkAndUnmarkCommand
     *
     * @param tasks    the TaskList that is being referred to
     * @param database the Storage that is being referred to
     */
    @Override
    public void execute(TaskList tasks, Storage database) {
        createMarkOrUnmark(tasks, database);
    }

    /**
     * Part of the execution step where it marks/unmarks the index targeted by the user, outputs a message after
     * marking/unmarking successfully, and updates the database
     *
     * @param tasks    the TaskList that makes use of the indexOfMarking to mark/unmark the correct task
     * @param database the Storage that would be updated after the marking/unmarking of task
     */
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
