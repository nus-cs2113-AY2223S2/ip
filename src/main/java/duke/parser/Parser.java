package duke.parser;

import duke.common.Common;
import duke.exception.InvalidTaskNumberException;
import duke.keycommands.ChangeTaskStatusCommand;
import duke.keycommands.DeleteCommand;
import duke.keycommands.HelpCommand;
import duke.keycommands.ListCommand;
import duke.keycommands.TodoCommand;
import duke.keycommands.DeadlineCommand;
import duke.keycommands.EventCommand;
import duke.keycommands.ByeCommand;
import duke.keycommands.FindCommand;

/**
 * Handles the parsing of user input commands for Duke.
 */
public class Parser {

    private static final String BYE_COMMAND = "bye";

    private static final String HELP_COMMAND = "help";

    private static final String LIST_COMMAND = "list";

    private static final String DELETE_COMMAND = "delete";

    private static final String FIND_COMMAND = "find";

    private static final String TODO_COMMAND = "todo";

    private static final String DEADLINE_COMMAND = "deadline";

    private static final String EVENT_COMMAND = "event";

    private static final String MARK_COMMAND = "mark";

    private static final String UNMARK_COMMAND = "unmark";

    private static final String INCORRECT_KEYWORD = "OOPS!!! I'm sorry, but I don't know what that means :-(";

    private static final String NEEDLESS_SENTENCE_AFTER_ONE_WORD_COMMAND = "I don't know the meaning of the sentence after the keyword";

    private static final String REMINDING_MESSAGE_ABOUT_GIVING_TASK_NUMBER = "Please give me the task number";

    private static final String THE_TASK_NUMBER_NOT_WITHIN_THE_LIST = "The task number is out of range";

    private static final String REMINDING_MESSAGE_ABOUT_GIVING_TASK_CONTENT = "Please give me the task content";

    private static final String REMINDING_MESSAGE_ABOUT_GIVING_KEYWORD_TO_FIND = "Please give me the keyword to find";

    private static final String DEADLINE_FORMAT = "Format: deadline {your task} /by {deadline date}\n";

    private static final String EVENT_FORMAT = "Format: event {your task} /from {begin time} /to {end time}\n";

    public static final String REMINDING_MESSAGE_TO_FOLLOW_THE_FORMAT = "Please follow the format below:\n";

    public static final String REMINDING_MESSAGE_ABOUT_NOT_LETTING_ANY_FIELD_EMPTY = "Remember to not leave the field inside the curly brackets empty!";

    public static final String REMINDING_MESSAGE_ABOUT_FOLLOWING_DEADLINE_FORMAT = REMINDING_MESSAGE_TO_FOLLOW_THE_FORMAT+
            DEADLINE_FORMAT + REMINDING_MESSAGE_ABOUT_NOT_LETTING_ANY_FIELD_EMPTY;

    public static final String REMINDING_MESSAGE_ABOUT_FOLLOWING_EVENT_FORMAT = REMINDING_MESSAGE_TO_FOLLOW_THE_FORMAT+
            EVENT_FORMAT + REMINDING_MESSAGE_ABOUT_NOT_LETTING_ANY_FIELD_EMPTY;

    private String[] separatedKeywordAndDescription;
    private String keyword;
    private int taskNumber;

    /**
     * Execute the user input.
     */
    public void executeUserInput() {
        switch (this.keyword) {
        case BYE_COMMAND:
            executeByeCommand();
            break;
        case HELP_COMMAND:
            executeHelpCommand();
            break;
        case LIST_COMMAND:
            executeListCommand();
            break;
        case DELETE_COMMAND:
            executeDeleteCommand();
            break;
        case FIND_COMMAND:
            executeFindCommand();
            break;
        case TODO_COMMAND:
            executeTodoCommand();
            break;
        case DEADLINE_COMMAND:
            executeDeadlineCommand();
            break;
        case EVENT_COMMAND:
            executeEventCommand();
            break;
        case MARK_COMMAND:
        case UNMARK_COMMAND:
            executeChangeTaskStatusCommand();
            break;
        default:
            System.out.println(INCORRECT_KEYWORD);
        }
    }

    /**
     * Splits a user input into keyword and description.
     * @param userInput a string representing the user input to be split.
     */
    public void splitKeywordAndDescription(String userInput) {
        this.separatedKeywordAndDescription = userInput.split(" ", 2);
        this.keyword = separatedKeywordAndDescription[0];
    }

    private void executeByeCommand() {
        if (isOneWordInputInCorrectFormat()) {
            new ByeCommand();
        }
    }

    private void executeHelpCommand() {
        if (isOneWordInputInCorrectFormat()) {
            new HelpCommand();
        }
    }

    private void executeListCommand() {
        if (isOneWordInputInCorrectFormat()) {
            new ListCommand();
        }
    }

    /**
     * Checks if the command with only one word like "bye", "help" is in the correct format.
     * @return true if the user input with only one word is in the correct format, false otherwise
     */
    private boolean isOneWordInputInCorrectFormat() {
        if (separatedKeywordAndDescription.length > 1) {
            System.out.println(NEEDLESS_SENTENCE_AFTER_ONE_WORD_COMMAND);
            return false;
        }
        return true;
    }

    private void executeDeleteCommand() {
        if (isLocateTaskNumberCommandInCorrectFormat()) {
            new DeleteCommand(taskNumber);
        }
    }

    private void executeChangeTaskStatusCommand() {
        if (isLocateTaskNumberCommandInCorrectFormat()) {
            new ChangeTaskStatusCommand(taskNumber, keyword);
        }
    }

    /**
     * Checks if the "locate task number" commands such as "delete", "mark" are in the correct format and valid.
     * @return true if the command is in the correct format and valid, false otherwise
     */
    private boolean isLocateTaskNumberCommandInCorrectFormat() {
        try{
            taskNumber = Integer.parseInt(separatedKeywordAndDescription[1]);
            testTaskNumber();
            return true;
        } catch (NumberFormatException error) {
            System.out.println(Common.INSTRUCTION + "\n" + keyword + ": Number");
        } catch (IndexOutOfBoundsException error) {
            System.out.println(REMINDING_MESSAGE_ABOUT_GIVING_TASK_NUMBER);
        } catch (InvalidTaskNumberException error) {
            System.out.println(THE_TASK_NUMBER_NOT_WITHIN_THE_LIST);
        }
        return false;
    }

    /**
     * Tests if the given task number is within the range of list size.
     * If the task number is invalid, an InvalidTaskNumberException is thrown.
     * @throws InvalidTaskNumberException if the task number is greater than the number of tasks or less than or equal to 0.
     */
    private void testTaskNumber() throws InvalidTaskNumberException {
        if (taskNumber > Common.tasks.size() || taskNumber <= 0) {
            throw new InvalidTaskNumberException();
        }
    }

    private void executeFindCommand() {
        if (isTheDescriptionEmpty(separatedKeywordAndDescription,1, REMINDING_MESSAGE_ABOUT_GIVING_KEYWORD_TO_FIND)){
            return;
        }
        new FindCommand(separatedKeywordAndDescription[1]);
    }

    /**
     * Checks if the description after an important word like "/from", "by", "todo" is empty.
     * @param stringArray the array of strings to be checked
     * @param index the index of the string to be checked
     * @param outputMessage the message to be printed if the description is empty
     * @return true if the description is empty, false otherwise
     */
    private boolean isTheDescriptionEmpty(String[] stringArray, int index, String outputMessage) {
        try {
            String test = stringArray[index];
            if (stringArray[index].isEmpty()) {
                return true;
            }
            return false;
        } catch (ArrayIndexOutOfBoundsException error) {
            System.out.println(outputMessage);
            return true;
        }
    }

    private void executeTodoCommand() {
        if (isTheDescriptionEmpty(separatedKeywordAndDescription,1, REMINDING_MESSAGE_ABOUT_GIVING_TASK_CONTENT)) {
            return;
        }
        new TodoCommand(separatedKeywordAndDescription[1]);
    }

    private void executeDeadlineCommand() {
        try {
            String[] taskContentAndDate = separatedKeywordAndDescription[1].split(" /by ", 2);
            new DeadlineCommand(taskContentAndDate[0], taskContentAndDate[1]);
        } catch (Exception error) {
            System.out.println(REMINDING_MESSAGE_ABOUT_FOLLOWING_DEADLINE_FORMAT);
        }
    }

    private void executeEventCommand() {
        try {
            String[] taskContentAndDate = separatedKeywordAndDescription[1].split(" /from ", 2);
            String[] beginningTimeAndEndTime = taskContentAndDate[1].split(" /to ", 2);
            new EventCommand(taskContentAndDate[0], beginningTimeAndEndTime[0], beginningTimeAndEndTime[1]);
        } catch (Exception error) {
            System.out.println(REMINDING_MESSAGE_ABOUT_FOLLOWING_EVENT_FORMAT);
        }
    }

}
