package duke.commands;

import duke.data.Deadline;

public class AddDeadlineCommand extends Command{
    public static final String COMMAND_WORD = "deadline";
    public static final String MESSAGE_USAGE = "OOPS!! The correct format of adding deadline is: deadline task /by time";
    private final Deadline toAdd;

    public AddDeadlineCommand(String content, String byTime){
        this.toAdd = new Deadline(content, byTime);
    }

    public void execute() {
        taskList.addTask(toAdd);
        System.out.println("Got it. I've added this task: ");
        System.out.println(toAdd);
        taskList.printSize();
    }
}
