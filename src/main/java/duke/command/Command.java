package duke.command;

import duke.DukeException;
import duke.output.Printer;
import duke.output.StandardOutput;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

public class Command {
    private static final int VALID_MARK_LENGTH = 2;
    private static final int VALID_UNMARK_LENGTH = 2;
    private static final int MINIMUM_TODO_LENGTH = 2;
    private static final int MINIMUM_DEADLINE_LENGTH = 4;
    private static final int MINIMUM_EVENT_LENGTH = 6;
    public static void evaluate(String input, Task[] tasks) {
        try {
            String[] arrayOfInput = input.split(" ");
            boolean isInputList = input.equals(CommandWords.LIST.COMMAND);
            if (isInputList) {
                Printer.listTasks(tasks);
            } else if (isMark(arrayOfInput)) {
                // if command is "mark <int>"
                markTask(tasks, arrayOfInput);
            } else if (isUnmark(arrayOfInput)) {
                // if command is "unmark <int>"
                unmarkTask(tasks, arrayOfInput);
            } else {
                // command is to add task
                decideTaskGroup(input, tasks, arrayOfInput);
            }
        } catch (DukeException e) {
            System.out.println(StandardOutput.SEGMENT_LINE.STANDARD_OUTPUT + System.lineSeparator());
        }
    }

    public static boolean isMark(String[] input) throws DukeException {
        boolean isTwoWordInput = (input.length == VALID_MARK_LENGTH);
        boolean isFirstWordMark = input[0].equals(CommandWords.MARK.COMMAND);
        if (!isFirstWordMark) {
            return false;
        }
        if (!isTwoWordInput) {
            // user only provided "mark"
            System.out.println(StandardOutput.SEGMENT_LINE.STANDARD_OUTPUT);
            System.out.println(StandardOutput.INVALID_MARK_MESSAGE.STANDARD_OUTPUT);
            throw new DukeException();
        }
        boolean isSecondWordNumber = isStringOfInteger(input[1]);
        if (!isSecondWordNumber) {
            // user provided "mark <non digit chara>"
            System.out.println(StandardOutput.SEGMENT_LINE.STANDARD_OUTPUT);
            System.out.println(StandardOutput.INVALID_MARK_MESSAGE.STANDARD_OUTPUT);
            throw new DukeException();
        } // todo also check whether the number !> than no of task
        return true;
    }

    public static boolean isUnmark(String[] input) throws DukeException {
        // if input length (separated by " ") == 2 && first word == "unmark" && second word only contains numbers
        boolean isTwoWordInput = (input.length == VALID_UNMARK_LENGTH);
        boolean isFirstWordUnmark = input[0].equals(CommandWords.UNMARK.COMMAND);
        if (!isFirstWordUnmark) {
            return false;
        }
        if (!isTwoWordInput) {
            // user only provided "unmark"
            System.out.println(StandardOutput.SEGMENT_LINE.STANDARD_OUTPUT);
            System.out.println(StandardOutput.INVALID_UNMARK_MESSAGE.STANDARD_OUTPUT);
            throw new DukeException();
        }
        boolean isSecondWordNumber = isStringOfInteger(input[1]);
        if (!isSecondWordNumber) {
            // user provided "unmark <non digit chara>"
            System.out.println(StandardOutput.SEGMENT_LINE.STANDARD_OUTPUT);
            System.out.println(StandardOutput.INVALID_UNMARK_MESSAGE.STANDARD_OUTPUT);
            throw new DukeException();
        }
        // todo also check whether the number !> than no of task
        return true;
    }

    public static boolean isStringOfInteger(String input) {
        // takes in a string and checks whether the string only contains digits characters
        char[] inputInArray = input.toCharArray();
        for (char c : inputInArray) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    public static void markTask(Task[] tasks, String[] arrayOfInput) {
        tasks[Integer.parseInt(arrayOfInput[1]) - 1].markAsDone();
        Printer.markOrUnmark(tasks, Integer.parseInt(arrayOfInput[1]) - 1);
    }

    public static void unmarkTask(Task[] tasks, String[] arrayOfInput) {
        tasks[Integer.parseInt(arrayOfInput[1]) - 1].markAsNotDone();
        Printer.markOrUnmark(tasks, Integer.parseInt(arrayOfInput[1]) - 1);
    }

    public static void decideTaskGroup(String input, Task[] tasks, String[] arrayOfInput) throws DukeException {
        boolean isInputTodo = arrayOfInput[0].equals(CommandWords.TODO.COMMAND);
        boolean isInputDeadline = arrayOfInput[0].equals(CommandWords.DEADLINE.COMMAND);
        boolean isInputEvent = arrayOfInput[0].equals(CommandWords.EVENT.COMMAND);
        if (isInputTodo) {
            String[] todoTaskNameArray = input.split(" ", 2);
            checkValidTodo(todoTaskNameArray);
            addTodoTask(tasks, todoTaskNameArray);
        } else if (isInputDeadline) {
            checkValidDeadline(input, arrayOfInput);
            addDeadlineTask(tasks, input);
        } else if (isInputEvent) {
            checkValidEvent(input, arrayOfInput);
            addEventTask(tasks, input);
        } else {
            // if input doesn't contain any keywords
            System.out.println(StandardOutput.SEGMENT_LINE.STANDARD_OUTPUT);
            System.out.println(StandardOutput.INVALID_INPUT_MESSAGE.STANDARD_OUTPUT);
            throw new DukeException();
        }
        Printer.echoAddTasks(tasks);
        Task.incrementTotalTasks();
    }

    public static void checkValidTodo(String[] input) throws DukeException {
        if (input.length < MINIMUM_TODO_LENGTH) {
            System.out.println(StandardOutput.SEGMENT_LINE.STANDARD_OUTPUT);
            System.out.println(StandardOutput.INVALID_TODO_MESSAGE.STANDARD_OUTPUT);
            throw new DukeException();
        }
    }

    public static void addTodoTask(Task[] tasks, String[] input) {
        tasks[Task.totalTasks] = new Todo(input[1]);
    }

    public static void checkValidDeadline(String input, String[] arrayOfInput) throws DukeException {
        if (!input.contains("/by")) {
            System.out.println(StandardOutput.SEGMENT_LINE.STANDARD_OUTPUT);
            System.out.println(StandardOutput.MISSING_DEADLINE_KEYWORD_MESSAGE.STANDARD_OUTPUT);
            throw new DukeException();
        }
        if (arrayOfInput.length < MINIMUM_DEADLINE_LENGTH) {
            System.out.println(StandardOutput.SEGMENT_LINE.STANDARD_OUTPUT);
            System.out.println(StandardOutput.INSUFFICIENT_DEADLINE_FIELD_MESSAGE.STANDARD_OUTPUT);
            throw new DukeException();
        }
    }

    public static void addDeadlineTask(Task[] tasks, String input) {
        String[] commandInformation = input.split(" ", 2);
        String[] taskNameAndDate = commandInformation[1].split("/by", 2);
        tasks[Task.totalTasks] = new Deadline(taskNameAndDate[0].trim(), taskNameAndDate[1].trim());
    }

    public static void checkValidEvent(String input, String[] arrayOfInput) throws DukeException {
        if (!input.contains("/from") || !input.contains("/to")) {
            System.out.println(StandardOutput.SEGMENT_LINE.STANDARD_OUTPUT);
            System.out.println(StandardOutput.INVALID_EVENT_FORMAT_MESSAGE.STANDARD_OUTPUT);
            throw new DukeException();
        }
        if (input.indexOf("/from") > input.indexOf("/to")) {
            System.out.println(StandardOutput.SEGMENT_LINE.STANDARD_OUTPUT);
            System.out.println(StandardOutput.INVALID_EVENT_FORMAT_MESSAGE.STANDARD_OUTPUT);
            throw new DukeException();
        }
        if (arrayOfInput.length < MINIMUM_EVENT_LENGTH) {
            System.out.println(StandardOutput.SEGMENT_LINE.STANDARD_OUTPUT);
            System.out.println(StandardOutput.INSUFFICIENT_EVENT_FIELD_MESSAGE.STANDARD_OUTPUT);
            throw new DukeException();
        }
    }

    public static void addEventTask(Task[] tasks, String input) {
        String taskNameInformation = input.split(" ", 2)[1];
        String[] taskNameAndDate = taskNameInformation.split("/from", 2); // name fromTo
        String[] fromAndTo = taskNameAndDate[1].split("/to", 2); // from to
        tasks[Task.totalTasks] = new Event(taskNameAndDate[0].trim(), fromAndTo[0].trim(), fromAndTo[1].trim());
    }
}
