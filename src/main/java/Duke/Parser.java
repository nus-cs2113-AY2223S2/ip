package Duke;

import Duke.Exception.MarkIndexException;
import Duke.Exception.NoTaskException;
import Duke.Exception.TaskInfoException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Parser {
    private static final String dividingLine = "\n————————————————————————————————————————————————————————\n";
    private static final DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    private static final DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");

    protected static String[] commands;

    public static String getCommand(String line) {
        commands = line.split(" ", 2);
        return commands[0];
    }

    public static boolean hasTask(TaskList tasks) {
        if (tasks.getSize() == 0) {
            return false;
        }
        return true;
    }

    public static boolean hasInfo() {
        if (commands.length == 1 || commands[1].equals("")) {
            return false;
        }
        return true;
    }

    public static boolean hasIndex(int index, TaskList tasks) {
        if (tasks.getSize() < index) {
            return false;
        }
        return true;
    }


    public static boolean processTask(String line, TaskList tasks, String path) {
        String command = Parser.getCommand(line);
        boolean isBye = false;

        try {
            switch (command) {
            case "list":
                if (!hasTask(tasks)) {
                    throw new NoTaskException();
                }
                Ui.listTasks(tasks.List());
                break;
            case "mark":
                int index = Integer.parseInt(commands[1]) - 1;
                if (!hasIndex(index, tasks)) {
                    throw new MarkIndexException();
                }
                if(!hasInfo()){
                    throw new TaskInfoException();
                }
                tasks.MarkTheTask(index);
                Ui.printMark(tasks.getDescription(index));
                Storage.saveFile(path, tasks.List());
                break;
            case "unmark":
                int index_2 = Integer.parseInt(commands[1]) - 1;
                if (!hasIndex(index_2, tasks)) {
                    throw new MarkIndexException();
                }
                tasks.UnmarkTheTask(index_2);
                Ui.printUnmark(tasks.getDescription(index_2));
                Storage.saveFile(path, tasks.List());
                break;
            case "delete":
                int index_3 = Integer.parseInt(commands[1]) - 1;
                if (!hasIndex(index_3, tasks)) {
                    throw new MarkIndexException();
                }
                Ui.printDelete(tasks.getDescription(index_3), tasks.getSize());
                tasks.DeleteTheTask(index_3);
                Storage.saveFile(path, tasks.List());
                break;
            case "find":
                String keyword = commands[1];
                Ui.listTasks(tasks.relevantTask(keyword));
                break;
            case "bye":
                isBye = true;
                break;
            case "todo":
                String todoDescription = commands[1];
                tasks.Todo(todoDescription);
                Ui.printAddTask(todoDescription, tasks.getSize());
                Storage.saveFile(path, tasks.List());
                break;
            case "deadline":
                String[] deadlineStr = commands[1].split(" /by ", 2);
                String deadlineDescription = deadlineStr[0];
                LocalDateTime deadlineTime = LocalDateTime.parse(deadlineStr[1], inputFormatter);
                String deadlineFormatted = deadlineTime.format(outputFormatter);
                tasks.Deadline(deadlineDescription, deadlineFormatted);
                Ui.printAddTask(deadlineDescription, tasks.getSize());
                Storage.saveFile(path, tasks.List());
                break;
            case "event":
                String[] eventStr = commands[1].split(" /from ", 2);
                String eventDescription = eventStr[0];
                String[] eventTime = eventStr[1].split(" /to ", 2);
                LocalDateTime eventStartTime = LocalDateTime.parse(eventTime[0], inputFormatter);
                LocalDateTime eventEndTime = LocalDateTime.parse(eventTime[1], inputFormatter);
                String eventStartFormatted = eventStartTime.format(outputFormatter);
                String eventEndFormatted = eventEndTime.format(outputFormatter);
                tasks.Event(eventDescription, eventStartFormatted, eventEndFormatted);
                Ui.printAddTask(eventDescription, tasks.getSize());
                Storage.saveFile(path, tasks.List());
                break;
            default:
                Ui.printUnrecognisableCommand();
                break;
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println(dividingLine + "☹ OOPS!!! The description of a " + line + " cannot be empty."
                    + dividingLine);
        } catch (TaskInfoException e) {
            System.out.println(dividingLine + "☹ OOPS!!! Missing information." + dividingLine);
        } catch (MarkIndexException e) {
            System.out.println(dividingLine + "☹ OOPS!!! Task index is unspecified." + dividingLine);
        } catch (NoTaskException e) {
            System.out.println(dividingLine + "☹ OOPS!!! No task in the list." + dividingLine);
        }
        return isBye;
    }
}
