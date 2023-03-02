package Duke;

import Duke.Exception.DukeException;
import Duke.Exception.MarkIndexException;
import Duke.Exception.NoTaskException;
import Duke.Exception.TaskInfoException;
import Duke.commands.Deadline;
import Duke.commands.Event;
import Duke.commands.Task;
import Duke.commands.Todo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;



public class ExecuteCommand {
    private static DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    private static DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
    public static void processTask(String line, Command tasks) throws Duke.Exception.DukeException, Duke.Exception.TaskInfoException, Duke.Exception.MarkIndexException, Duke.Exception.NoTaskException {
        if (line.equals("list")) {
            tasks.List();
        } else if (line.contains("mark") && !line.contains("unmark")) {
            if (tasks.getSize() == 0) {
                throw new Duke.Exception.NoTaskException();
            }
            if (line.length() == 4) {
                throw new Duke.Exception.MarkIndexException();
            }
            int taskNum = Integer.parseInt(line.substring(5)) - 1;
            tasks.MarkTheTask(taskNum);
            Ui.printMark(tasks.getDescription(taskNum));
        } else if (line.contains("delete")) {
            if (line.length() == 6) {
                throw new Duke.Exception.MarkIndexException();
            }
            int taskNum = Integer.parseInt(line.substring(7)) - 1;
            Ui.printDelete(tasks.getDescription(taskNum), tasks.getSize());
            tasks.DeleteTheTask(taskNum);

        } else if (line.contains("unmark")) {
            if (tasks.getSize() == 0) {
                throw new NoTaskException();
            }
            if (line.length() == 6) {
                throw new MarkIndexException();
            }
            int unmarkNum = Integer.parseInt(line.substring(7)) - 1;
            tasks.UnmarkTheTask(unmarkNum);
            Ui.printMark(tasks.getDescription(unmarkNum));
        } else if (line.contains("todo")) {
            tasks.Todo(line.substring(5));

        } else if (line.contains("deadline")) {
            line = line.replace("deadline ", "");
            if (line.substring(line.indexOf("/by ") + 4).length() == 0) {
                throw new Duke.Exception.TaskInfoException();
            }
            String deadlineTimeStr = line.split("/by ")[1];
            LocalDateTime deadlineTime = LocalDateTime.parse(deadlineTimeStr, inputFormatter);
            String deadlineFormatted = deadlineTime.format(outputFormatter);
            tasks.Deadline((line.substring(0, line.indexOf(" /"))), deadlineFormatted);
        } else if (line.contains("event")) {
            line = line.replace("event ", "");
            if (line.substring(line.indexOf("/to ") + 4).length() == 0) {
                throw new TaskInfoException();
            }
            String eventFromStr = line.split("/from ")[1].split(" /to ")[0];
            LocalDateTime eventFrom = LocalDateTime.parse(eventFromStr, inputFormatter);
            String eventFromFormatted = eventFrom.format(outputFormatter);
            String eventToStr = line.split("/from ")[1].split(" /to ")[1];
            LocalDateTime eventToTime = LocalDateTime.parse(eventToStr, inputFormatter);
            String eventToFormatted = eventToTime.format(outputFormatter);
            tasks.Event((line.substring(0, line.indexOf(" /"))), eventFromFormatted, eventToFormatted);
        } else if (line.contains("find")) {
            line = line.replace("find ", "");
            String keyword = line;
            tasks.relevantTask(keyword);
        } else {
//            tasks[taskCount] = new Task(line);
            throw new DukeException();
        }
    }
}
