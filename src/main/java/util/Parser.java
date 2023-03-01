package util;

import errors.DukeException;
import errors.ErrorMessages;


/**
* Starts the main processing of input
* */
public class Parser {

    private static final boolean NOT_FROM_SAVE_DATA = false;
    private static final String CHAR_SPACE = " ";
    private static final String LIST_COMMAND = "list";
    private static final String EXIT_COMMAND = "bye";
    private static final String TODO_COMMAND = "todo";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String EVENT_COMMAND = "event";
    private static final String MARK_COMMAND = "mark";
    private static final String UNMARK_COMMAND = "unmark";
    private static final String DELETE_COMMAND = "delete";
    private static final String FIND_COMMAND = "find";


    /**
     * Starts the main function
     * */
    public void Parser( TaskList taskListHandler) {
        Ui ui = new Ui();
        OutputUI outPutUI = new OutputUI();
        ErrorMessages errMsgs = new ErrorMessages();
        while (true) {
            try {
                String input = ui.getInput();
                String firstWord = input.split(CHAR_SPACE)[0];
                switch (firstWord) {
                case LIST_COMMAND:
                    taskListHandler.printList();
                    break;
                case EXIT_COMMAND:
                    outPutUI.printByeByeMessage();
                    taskListHandler.saveData();
                    System.exit((0));
                    break;
                case TODO_COMMAND:
                case DEADLINE_COMMAND:
                case EVENT_COMMAND:
                    taskListHandler.addNewTask(input, NOT_FROM_SAVE_DATA);
                    break;
                case MARK_COMMAND:
                case UNMARK_COMMAND:
                    taskListHandler.handleMarkUnmarkAction(input, NOT_FROM_SAVE_DATA);
                    break;
                case DELETE_COMMAND:
                    taskListHandler.handleDeleteAction(input);
                    break;
                case FIND_COMMAND:
                    taskListHandler.handleFindTaskAction(input);
                    break;
                default:
                    throw new DukeException(errMsgs.errorWrongCommandText());
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
