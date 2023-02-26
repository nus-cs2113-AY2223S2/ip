package duke.parser;

import duke.common.Common;
import duke.keycommands.*;

public class Parser {
    private String[] separatedKeyWordAndContent;
    private String keyword;
    private int taskNumber;

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
            new TodoCommand(separatedKeyWordAndContent);
            break;
        case DEADLINE_COMMAND:
            new DeadlineCommand(separatedKeyWordAndContent);
            break;
        case EVENT_COMMAND:
            new EventCommand(separatedKeyWordAndContent);
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
