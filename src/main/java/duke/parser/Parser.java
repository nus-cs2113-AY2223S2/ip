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

public class Parser {

    public static final String BYE_COMMAND = "bye";
    public static final String HELP_COMMAND = "help";
    public static final String LIST_COMMAND = "list";
    public static final String DELETE_COMMAND = "delete";
    public static final String FIND_COMMAND = "find";
    public static final String TODO_COMMAND = "todo";
    public static final String DEADLINE_COMMAND = "deadline";
    public static final String EVENT_COMMAND = "event";
    public static final String MARK_COMMAND = "mark";
    public static final String UNMARK_COMMAND = "unmark";
    public static final String INCORRECT_KEYWORD = "OOPS!!! I'm sorry, but I don't know what that means :-(";
    public static final String NEEDLESS_SENTENCE_AFTER_ONE_WORD_COMMAND = "I don't know the meaning of the sentence after the keyword";
    public static final String REMINDING_MESSAGE_ABOUT_GIVING_TASK_NUMBER = "Please give me the task number";
    public static final String THE_TASK_NUMBER_NOT_WITHIN_THE_LIST = "The task number is out of range";
    public static final String REMINDING_MESSAGE_ABOUT_GIVING_TASK_CONTENT = "Please give me the task content";
    public static final String REMINDING_MESSAGE_ABOUT_GIVING_DEADLINE_TIME = "Please give me the deadline time";
    public static final String REMINDING_MESSAGE_ABOUT_GIVING_DESCRIPTION = "Please give me some description to perform the command";
    public static final String REMINDING_MESSAGE_ABOUT_GIVING_TIME_TO_THE_EVENT = "Please give me the time for the event";
    public static final String REMINDING_MESSAGE_ABOUT_GIVING_THE_BEGINNING_TIME = "Please give me the beginning time";
    public static final String REMINDING_MESSAGE_ABOUT_GIVING_THE_END_TIME = "Please give me the end time";
    public static final String REMINDING_MESSAGE_ABOUT_GIVING_KEYWORD_TO_FIND = "Please give me the keyword to find";

    private String[] separatedKeyWordAndContent;
    private String keyword;
    private int taskNumber;

    public void handleInput() {
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

    public void splitKeywordAndContent(String userInput) {
        this.separatedKeyWordAndContent = userInput.split(" ", 2);
        this.keyword = separatedKeyWordAndContent[0];
    }

    private boolean isOneWordInputInCorrectFormat() {
        if (separatedKeyWordAndContent.length > 1) {
            System.out.println(NEEDLESS_SENTENCE_AFTER_ONE_WORD_COMMAND);
            return false;
        }
        return true;
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

    private boolean isLocateTaskNumberCommandInCorrectFormat() {
        try{
            taskNumber = Integer.parseInt(separatedKeyWordAndContent[1]);
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
    
    // create a method to throw InvalidTaskNumberException when the task number is bigger than the number of tasks
    private void testTaskNumber() throws InvalidTaskNumberException {
        if (taskNumber > Common.tasks.size() || taskNumber <= 0) {
            throw new InvalidTaskNumberException();
        }
    }

    // create a method to parse the input of find command
    private void executeFindCommand() {
        if (isTheDescriptionEmpty(separatedKeyWordAndContent,1, REMINDING_MESSAGE_ABOUT_GIVING_KEYWORD_TO_FIND)){
            return;
        }
        new FindCommand(separatedKeyWordAndContent[1]);
    }


    private boolean isTheDescriptionEmpty(String[] stringArray, int index, String outputMessage) {
        try {
            String test = stringArray[index];
            return false;
        } catch (ArrayIndexOutOfBoundsException error) {
            System.out.println(outputMessage);
            return true;
        }
    }

    private void executeTodoCommand() {
        if (isTheDescriptionEmpty(separatedKeyWordAndContent,1, REMINDING_MESSAGE_ABOUT_GIVING_TASK_CONTENT)) {
            return;
        }
        new TodoCommand(separatedKeyWordAndContent[1]);
    }

    private void executeDeadlineCommand() {
        if (isTheDescriptionEmpty(separatedKeyWordAndContent,1, REMINDING_MESSAGE_ABOUT_GIVING_DESCRIPTION)) {
            return;
        }
        // split separatedKeyWordAndContent[1] into description and date
        String[] taskContentAndDate = separatedKeyWordAndContent[1].split(" /by ", 2);
        // check if description is empty
        if (isTheDescriptionEmpty(taskContentAndDate,0, REMINDING_MESSAGE_ABOUT_GIVING_TASK_CONTENT)) {
            return;
        }
        if (isTheDescriptionEmpty(taskContentAndDate,1, REMINDING_MESSAGE_ABOUT_GIVING_DEADLINE_TIME)) {
            return;
        }
        new DeadlineCommand(taskContentAndDate[0], taskContentAndDate[1]);
    }

    //create a method executeEventCommand to handle event command in the format: event description /from beginning time /to end time
    private void executeEventCommand() {
        if (isTheDescriptionEmpty(separatedKeyWordAndContent,1, REMINDING_MESSAGE_ABOUT_GIVING_DESCRIPTION)) {
            return;
        }
        // split separatedKeyWordAndContent[1] into description and date
        String[] taskContentAndDate = separatedKeyWordAndContent[1].split(" /from ", 2);
        // check if description is empty
        if (isTheDescriptionEmpty(taskContentAndDate,0,REMINDING_MESSAGE_ABOUT_GIVING_TASK_CONTENT)) {
            return;
        }
        if (isTheDescriptionEmpty(taskContentAndDate,1, REMINDING_MESSAGE_ABOUT_GIVING_TIME_TO_THE_EVENT)) {
            return;
        }
        //split descriptionAndDate[1] into beginning time and end time
        String[] beginningTimeAndEndTime = taskContentAndDate[1].split(" /to ", 2);
        //check if beginning time is empty
        if (isTheDescriptionEmpty(beginningTimeAndEndTime,0, REMINDING_MESSAGE_ABOUT_GIVING_THE_BEGINNING_TIME)) {
            return;
        }
        //check if end time is empty
        if (isTheDescriptionEmpty(beginningTimeAndEndTime,1, REMINDING_MESSAGE_ABOUT_GIVING_THE_END_TIME)) {
            return;
        }
        new EventCommand(taskContentAndDate[0], beginningTimeAndEndTime[0], beginningTimeAndEndTime[1]);
    }

}
