package duke.command.taskCommands;

import duke.command.Command;
import duke.exception.InvalidTaskException;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.TaskList;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import static duke.main.Duke.printHorizontalLine;
import static duke.main.Duke.taskCount;
import static duke.tasks.Task.dateTimeFormatter;

public class EventTask extends Command {
    @Override
    public void processCommand(TaskList tasksList, String input) throws InvalidTaskException {
        //ex: event meeting /from 2pm /to 4pm
        String[] task = input.split("/(from|to)", 3);
        if (task[1].trim().isEmpty() || task[2].trim().isEmpty()) {
            throw new InvalidTaskException();
        }
        try {
            LocalDateTime startTime = LocalDateTime.parse(task[1].trim(), dateTimeFormatter);
            LocalDateTime endTime = LocalDateTime.parse(task[2].trim(), dateTimeFormatter);
            tasksList.addTask(new Event(task[0].trim(), startTime, endTime));
            taskCount++;
        } catch (DateTimeParseException e) {
            System.out.println("Please enter all command parameter!");
            printHorizontalLine();
        }
    }
}
