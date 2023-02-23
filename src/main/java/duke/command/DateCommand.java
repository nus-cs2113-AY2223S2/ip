package duke.command;

import duke.Ui;
import duke.exception.InvalidDateTime;
import duke.task.Storage;
import duke.task.TaskList;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class DateCommand extends Command {
    LocalDate date;

    public DateCommand (String dateString) throws InvalidDateTime {
        try {
            this.date = LocalDate.parse(dateString);
        } catch (DateTimeParseException e) {
            throw new InvalidDateTime();
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printDateList(tasks.getTasksOnDate(date), date);
    }
}
