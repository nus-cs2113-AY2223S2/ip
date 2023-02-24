package duke;

import duke.keycommands.*;

import java.util.ArrayList;
import java.util.List;

public class CommandCenter {
    private String userInput;
    private String[] separatedKeyWordAndContent;
    private String keyword;

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
    public static final ArrayList<String> KEYWORDS = new ArrayList<>(
            List.of(TODO_COMMAND, DEADLINE_COMMAND, EVENT_COMMAND, LIST_COMMAND,
                    BYE_COMMAND, MARK_COMMAND, UNMARK_COMMAND, HELP_COMMAND, DELETE_COMMAND)
    );
    public CommandCenter() {
    }

    public void setVariables(String userInput) {
        this.userInput = userInput;
        this.separatedKeyWordAndContent = userInput.split(" ", 2);
        this.keyword = separatedKeyWordAndContent[0];
    }

    public void handleInput() {
        switch (this.keyword) {
        case BYE_COMMAND:
            new ByeCommand();
            break;
        case HELP_COMMAND:
            new HelpCommand();
            break;
        case LIST_COMMAND:
            new ListCommand();
            break;
        case DELETE_COMMAND:
            new DeleteCommand(userInput,separatedKeyWordAndContent);
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
            new ChangeTaskStatusCommand(userInput,separatedKeyWordAndContent,MARK_COMMAND);
            break;
        case UNMARK_COMMAND:
            new ChangeTaskStatusCommand(userInput,separatedKeyWordAndContent,UNMARK_COMMAND);
            break;
        default:
            System.out.println(INCORRECT_KEYWORD);
        }
    }
}
