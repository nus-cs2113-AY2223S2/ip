package duke.parser;

import duke.common.Common;
import duke.keycommands.*;

public class Parser {
    private String[] separatedKeyWordAndContent;
    private String keyword;
    private int taskNumber;

    private static final String EMPTY_TODO_DESCRIPTION = "OOPS!!! The description of a todo cannot be empty.";
    public static final String BYE_COMMAND = "bye";
    public static final String HELP_COMMAND = "help";
    public static final String LIST_COMMAND = "list";
    public static final String DELETE_COMMAND = "delete";
    public static final String TODO_COMMAND = "todo";
    public static final String DEADLINE_COMMAND = "deadline";
    public static final String EVENT_COMMAND = "event";
    public static final String MARK_COMMAND = "mark";
    public static final String UNMARK_COMMAND = "unmark";
    public static final String INCORRECT_KEYWORD = "OOPS!!! I'm sorry, but I don't know what that means :-(";
    public Parser() {
    }

    public void splitKeywordAndContent(String userInput) {
        this.separatedKeyWordAndContent = userInput.split(" ", 2);
        this.keyword = separatedKeyWordAndContent[0];
    }
    public boolean isOneWordInputInCorrectFormat() {
        if (separatedKeyWordAndContent.length > 1) {
            System.out.println("I don't know the meaning after the keyword");
            return false;
        }
        return true;
    }

    public boolean isLocateTaskNumberCommandInCorrectFormat() {
        try{
            taskNumber = Integer.parseInt(separatedKeyWordAndContent[1]);
            return true;
        } catch (NumberFormatException error) {
            System.out.println(Common.INSTRUCTION + "\n" + keyword + ": Number");
        } catch (IndexOutOfBoundsException error) {
            System.out.println("Please give me some content for your command");
        }
        return false;
    }

    public void executeDeleteCommand() {
        if (isLocateTaskNumberCommandInCorrectFormat()) {
            new DeleteCommand(taskNumber);
        }
    }


    public void executeChangeTaskStatusCommand() {
        if (isLocateTaskNumberCommandInCorrectFormat()) {
            new ChangeTaskStatusCommand(taskNumber, keyword);
        }
    }
    public void executeByeCommand() {
        if (isOneWordInputInCorrectFormat()) {
            new ByeCommand();
        }
    }

    public void executeHelpCommand() {
        if (isOneWordInputInCorrectFormat()) {
            new HelpCommand();
        }
    }

    public void executeListCommand() {
        if (isOneWordInputInCorrectFormat()) {
            new ListCommand();
        }
    }

    private boolean doesIndexOutOfBoundsOccur(String[] stringArray, int index, String outputMessage) {
        try {
            String test = stringArray[index];
            return false;
        } catch (ArrayIndexOutOfBoundsException error) {
            System.out.println(outputMessage);
            return true;
        }
    }

    public void executeTodoCommand() {
        if (doesIndexOutOfBoundsOccur(separatedKeyWordAndContent,1, EMPTY_TODO_DESCRIPTION)) {
            return;
        }
        new TodoCommand(separatedKeyWordAndContent[1]);
    }

    public void executeDeadlineCommand() {
        if (doesIndexOutOfBoundsOccur(separatedKeyWordAndContent,1, EMPTY_TODO_DESCRIPTION)) {
            return;
        }
        // split separatedKeyWordAndContent[1] into description and date
        String[] descriptionAndDate = separatedKeyWordAndContent[1].split(" /by ", 2);
        // check if description is empty
        if (doesIndexOutOfBoundsOccur(descriptionAndDate,0,"empty description")) {
            return;
        }
        if (doesIndexOutOfBoundsOccur(descriptionAndDate,1, "add date after /by")) {
            return;
        }
        new DeadlineCommand(descriptionAndDate[0], descriptionAndDate[1]);
    }
    //create a method executeEventCommand to handle event command in the format: event description /from beginning time /to end time
    public void executeEventCommand() {
        if (doesIndexOutOfBoundsOccur(separatedKeyWordAndContent,1, EMPTY_TODO_DESCRIPTION)) {
            return;
        }
        // split separatedKeyWordAndContent[1] into description and date
        String[] descriptionAndDate = separatedKeyWordAndContent[1].split(" /from ", 2);
        // check if description is empty
        if (doesIndexOutOfBoundsOccur(descriptionAndDate,0,"empty description")) {
            return;
        }
        if (doesIndexOutOfBoundsOccur(descriptionAndDate,1, "add date after /from")) {
            return;
        }
        //split descriptionAndDate[1] into beginning time and end time
        String[] beginningTimeAndEndTime = descriptionAndDate[1].split(" /to ", 2);
        //check if beginning time is empty
        if (doesIndexOutOfBoundsOccur(beginningTimeAndEndTime,0,"add beginning time after /from")) {
            return;
        }
        //check if end time is empty
        if (doesIndexOutOfBoundsOccur(beginningTimeAndEndTime,1,"add end time after /to")) {
            return;
        }
        new EventCommand(descriptionAndDate[0], beginningTimeAndEndTime[0], beginningTimeAndEndTime[1]);
    }


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
}
