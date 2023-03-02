import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class AddDeadlineCommand extends Command{

    @Override
    public void executeCommand(TaskList taskList, String input) {
        String[] deadlineSplit = input.split("/by", 2);
        String deadlineBy = deadlineSplit[1].trim();
        LocalDate toFormat = LocalDate.parse(deadlineBy);
        String deadlineByFormatted = toFormat.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String[] deadlineAndName = deadlineSplit[0].split(" ", 2);
        String deadlineName = deadlineAndName[1].trim();

        Deadline deadlineBeingAdded = new Deadline(deadlineName, deadlineByFormatted);
        taskList.addTask(deadlineBeingAdded);
    }
}