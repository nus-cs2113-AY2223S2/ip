package util;

import java.util.ArrayList;
import java.util.Scanner;

import errors.DukeException;
import errors.ErrorMessages;
import tasks.Task;


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
    * Stores the list of tasks
    * */
    private static ArrayList<Task> listOfTasks = new ArrayList<>();
    /**
     * Starts the main function
     * */
    public void Parser() {
        Storage storage = new Storage();
        storage.loadData(listOfTasks);

        Scanner newScanner = new Scanner(System.in);
        OutputUI outPutUI = new OutputUI();
        ErrorMessages errMsgs = new ErrorMessages();
        Marker marker = new Marker();
        TaskDeleter taskDeleter = new TaskDeleter();
        TaskAdder taskAdder = new TaskAdder();
        Storage storageManager = new Storage();
        Finder finder = new Finder();
        while (true) {
            try {
                String input = newScanner.nextLine();
                String firstWord = input.split(CHAR_SPACE)[0];
                switch (firstWord) {
                case LIST_COMMAND:
                    outPutUI.printList(listOfTasks, listOfTasks.size());
                    break;
                case EXIT_COMMAND:
                    outPutUI.printByeByeMessage();
                    newScanner.close();
                    storageManager.saveList(listOfTasks);
                    System.exit((0));
                    break;
                case TODO_COMMAND:
                case DEADLINE_COMMAND:
                case EVENT_COMMAND:
                    //
                    taskAdder.addTaskToList(listOfTasks, input, NOT_FROM_SAVE_DATA);
                    break;
                case MARK_COMMAND:
                case UNMARK_COMMAND:
                    //
                    marker.markOrUnamrkTask(listOfTasks, input, NOT_FROM_SAVE_DATA);
                    break;
                case DELETE_COMMAND:
                    //
                    taskDeleter.attemptToDeleteTask(listOfTasks, input);
                    break;
                case FIND_COMMAND:
                    finder.findTaskFromList(listOfTasks, input);
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
