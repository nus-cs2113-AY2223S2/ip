package duke.commands;

import duke.tasks.Tasklist;

public abstract class TaskCommand extends Command {
    /** The task list that the command will interact with */
    protected Tasklist tasklist;

    public TaskCommand(String commandWord) {
        super(commandWord);
    }


    public void createList(Tasklist tasklist) {

        this.tasklist = tasklist;
    }
}

