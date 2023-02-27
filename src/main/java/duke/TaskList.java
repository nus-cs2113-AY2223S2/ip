package duke;

import duke.command.Deadline;
import duke.command.Event;
import duke.command.Todo;
import duke.exception.IllegalCommandException;
import duke.task.Task;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Execute command specified by the command string.
     *
     * @param inputLine Input command from user.
     * @param command String containing the task type.
     */
    public void executeCommand(String[] inputLine, String command, Storage storage) {
        if (command.equals("bye")) {
            Ui.farewell();
            // exit while loop and end program
            System.exit(0);
        } else if (command.equals("list")) {
            // list out the tasks and status
            if (tasks.isEmpty()) {
                // empty list -> print help
                Ui.printException("There is nothing in your list right now.");
            } else {
                printList(tasks);
            }
            return;
        }

        Parser.taskType currTaskType = Parser.getTaskType(command);
        try {
            switch (currTaskType) {
            case DELETE:
                deleteTask(inputLine, storage);
                break;
            case MARK:
                markTask(inputLine, storage);
                break;
            case UNMARK:
                unmarkTask(inputLine, storage);
                break;
            case TODO:
                // command todo
                makeTodo(inputLine, storage);
                break;
            case DEADLINE:
                makeDeadline(inputLine, storage);
                break;
            case EVENT:
                makeEvent(inputLine, storage);
                break;
            case HELP:
                System.out.println(Ui.HELP_PAGE);
                break;
            case FIND:
                ArrayList<Task> matchTasks = new ArrayList<>();
                for (Task t : tasks) {
                    if (t.getTaskDescription().contains(inputLine[1])) {
                        matchTasks.add(t);
                    }
                }
                printList(matchTasks);
                break;
            default:
                throw new IllegalCommandException(command);
            }

        } catch (IllegalCommandException e) {
            Ui.printException("INVALID COMMAND!");
        }
    }

    /**
     * Prints all tasks.
     *
     * @param t List containing tasks to be printed.
     */
    public static void printList(ArrayList<Task> t) {
        System.out.println(Ui.LINE_BREAK);
        Ui.printlnWithIndentation("Here are the tasks in your list: ");
        for (int i = 0; i < t.size(); ++i) {
            int taskNumber = i + 1;
            System.out.println(Ui.INDENTATION + taskNumber + "." +
                    t.get(i).toString());
        }
        System.out.println(Ui.LINE_BREAK);
    }

    private static void makeEvent(String[] inputLine, Storage storage) {
        try {
            String action = inputLine[1];
            if (action.contains("/from") & action.contains("/to")) {
                int fromIndex = action.indexOf("/from");
                int toIndex = action.indexOf("/to");
                String toAddEvent = action.substring(0, fromIndex - 1);
                String fromTime = action.substring(fromIndex + 5, toIndex - 1);
                String toTime = action.substring(toIndex + 3);
                toAddEvent = toAddEvent.trim();
                fromTime = fromTime.trim();
                toTime = toTime.trim();
                Event addEvent = new Event(toAddEvent, fromTime, toTime);
                tasks.add(addEvent);
                System.out.println(Ui.LINE_BREAK);
                Ui.printlnWithIndentation("Got it. I've added this task: ");
                System.out.println(Ui.INDENTATION + "  " + addEvent);
                printTaskCount();
                storage.save();
            } else {
                throw new IllegalCommandException(action);
            }
        } catch (IllegalCommandException e) {
            Ui.printException("INVALID COMMAND! Missing '/from' or '/to'");
        } catch (ArrayIndexOutOfBoundsException e) {
            Ui.printException("event cannot be empty");
        } catch (IOException e) {
            Ui.printException("Error saving file!");
        }
    }

    private static void printTaskCount() {
        // print out the number of task user has
        System.out.println(Ui.INDENTATION + "Now you have " + tasks.size() + " tasks in the list. \n" + Ui.LINE_BREAK);
    }

    private static void makeDeadline(String[] inputLine, Storage storage) {
        try {
            String action = inputLine[1];
            if (action.contains("/by")) {
                int byIndex = action.indexOf("/by");
                String toAddDeadline = action.substring(0, byIndex - 1);
                String by = action.substring(byIndex + 3);
                toAddDeadline = toAddDeadline.trim();
                by = by.trim();
                Deadline addDeadline = new Deadline(toAddDeadline, by);
                tasks.add(addDeadline);
                System.out.println(Ui.LINE_BREAK);
                Ui.printlnWithIndentation("Got it. I've added this task: ");
                System.out.println(Ui.INDENTATION + "  " + addDeadline);
                printTaskCount();
                storage.save();
            } else {
                throw new IllegalCommandException(action);
            }
        } catch (IllegalCommandException e) {
            Ui.printException("INVALID COMMAND! Missing '/by'");
        } catch (ArrayIndexOutOfBoundsException e) {
            Ui.printException("deadline cannot be empty");
        } catch (IOException e) {
            Ui.printException("Error saving file!");
        }
    }

    private static void makeTodo(String[] inputLine, Storage storage) {
        try {
            String action = inputLine[1];
            action = action.trim();
            Todo addTodo = new Todo(action);
            tasks.add(addTodo);
            System.out.println(Ui.LINE_BREAK);
            Ui.printlnWithIndentation("Got it. I've added this task: ");
            System.out.println(Ui.INDENTATION + "  " + addTodo);
            printTaskCount();
            storage.save();
        } catch (ArrayIndexOutOfBoundsException e) {
            Ui.printException("todo cannot be empty");
        } catch (IOException e) {
            Ui.printException("Error saving file!");
        }
    }

    private static void unmarkTask(String[] inputLine, Storage storage) {
        try {
            String action = inputLine[1];
            action = action.trim();
            int indexToMark = Integer.parseInt(action) - 1;
            tasks.get(indexToMark).setDone(false);
            Ui.printlnWithIndentation("OK, I've marked this task as not done yet: ");
            System.out.println(Ui.INDENTATION + "  [ ] " + tasks.get(indexToMark).getTaskDescription() + '\n' + Ui.LINE_BREAK);
            storage.save();
        } catch (ArrayIndexOutOfBoundsException e) {
            Ui.printException("unmark cannot be empty");
        } catch (IndexOutOfBoundsException e) {
            Ui.printException("Invalid task number! Task number does not exist!");
        } catch (NumberFormatException e) {
            Ui.printException("Please input numeric number to unmark task!");
        } catch (IOException e) {
            Ui.printException("Error saving file!");
        }
    }

    private static void markTask(String[] inputLine, Storage storage) {
        try {
            String action = inputLine[1];
            action = action.trim();
            int indexToMark = Integer.parseInt(action) - 1;
            tasks.get(indexToMark).setDone(true);
            Ui.printlnWithIndentation("Nice! I've marked this task as done:");
            System.out.println(Ui.INDENTATION + "  [X] " + tasks.get(indexToMark).getTaskDescription() + '\n' + Ui.LINE_BREAK);
            storage.save();
        } catch (ArrayIndexOutOfBoundsException e) {
            Ui.printException("mark cannot be empty");
        } catch (IndexOutOfBoundsException e) {
            Ui.printException("Invalid task number! Task number does not exist!");
        } catch (NumberFormatException e) {
            Ui.printException("Please input numeric number to mark task!");
        } catch (IOException e) {
            Ui.printException("Error saving file!");
        }
    }

    private static void deleteTask(String[] inputLine, Storage storage) {
        try {
            String action = inputLine[1];
            action = action.trim();
            int indexToDelete = Integer.parseInt(action) - 1;
            String toPrint = tasks.get(indexToDelete).toString();
            tasks.remove(indexToDelete);
            Ui.printlnWithIndentation("Nice! I've deleted this task:");
            System.out.println(Ui.INDENTATION + toPrint +
                    '\n' + Ui.LINE_BREAK);
            printTaskCount();
            storage.save();
        } catch (ArrayIndexOutOfBoundsException e) {
            Ui.printException("delete cannot be empty");
        } catch (IndexOutOfBoundsException e) {
            Ui.printException("Invalid task number! Task number does not exist!");
        } catch (NumberFormatException e) {
            Ui.printException("Please input numeric number to delete task!");
        } catch (IOException e) {
            Ui.printException("Error saving file!");
        }
    }

    /**
     * Adds task from .txt file without outputs.
     *
     * @param fileLine String of action command.
     * @param command Command string from the saved file.
     * @throws IllegalCommandException if command is invalid.
     */
    public static void addTaskFromFile(String fileLine, String command) throws IllegalCommandException {
        switch (command) {
        case "todo":
            Todo addTodo = new Todo(fileLine);
            tasks.add(addTodo);
            break;
        case "deadline":
            int byIndex = fileLine.indexOf("/by");
            String toAddDeadline = fileLine.substring(0, byIndex - 1);
            String by = fileLine.substring(byIndex + 3);
            toAddDeadline = toAddDeadline.trim();
            by = by.trim();
            Deadline addDeadline = new Deadline(toAddDeadline, by);
            tasks.add(addDeadline);
            break;
        case "event":
            int fromIndex = fileLine.indexOf("/from");
            int toIndex = fileLine.indexOf("/to");
            String toAddEvent = fileLine.substring(0, fromIndex - 1);
            String fromTime = fileLine.substring(fromIndex + 5, toIndex - 1);
            String toTime = fileLine.substring(toIndex + 3);
            toAddEvent = toAddEvent.trim();
            fromTime = fromTime.trim();
            toTime = toTime.trim();
            Event addEvent = new Event(toAddEvent, fromTime, toTime);
            tasks.add(addEvent);
            break;
        default: throw new IllegalCommandException("Parsing error!");
        }
    }

    /**
     * Sets the last task in the task list as done.
     *
     * @param isDone When true, task is set to done, else not done.
     */
    public static void lastTaskSetDone(boolean isDone) {
        tasks.get(tasks.size() - 1).setDone(isDone);
    }

    /**
     * Get an arraylist of commands used to initialise tasks from all tasks.
     *
     * @return Arraylist of strings to save.
     */
    public static ArrayList<String> getSaveString() {
        ArrayList<String> saveStrings = new ArrayList<>();
        for (Task t : tasks) {
            saveStrings.add(t.getSave());
        }
        return saveStrings;
    }


}
