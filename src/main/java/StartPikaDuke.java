import errors.DukeException;
import errors.ErrorMessages;
import tasks.Task;
import util.*;

import java.util.ArrayList;
import java.util.Scanner;

public class StartPikaDuke {
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
    static ArrayList<Task> listOfTasks = new ArrayList<>();

    public void startPikaDuke() {
        Storage storage = new Storage();
        storage.loadData(listOfTasks);

        Scanner newScanner = new Scanner(System.in);
        OutputUI outPutUI = new OutputUI();
        ErrorMessages errMsgs = new ErrorMessages();
        Marker marker = new Marker();
        TaskDeleter taskDeleter = new TaskDeleter();
        TaskAdder taskAdder = new TaskAdder();
        Storage storageManager = new Storage();
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
                case TODO_COMMAND:
                case DEADLINE_COMMAND:
                case EVENT_COMMAND:
                    taskAdder.addTaskToList(listOfTasks, input, NOT_FROM_SAVE_DATA);
                    break;
                case MARK_COMMAND:
                case UNMARK_COMMAND:
                    marker.handleMarkUnmarkAction(listOfTasks, input, NOT_FROM_SAVE_DATA);
                    break;
                case DELETE_COMMAND:
                    taskDeleter.handleDeleteAction(listOfTasks, input);
                    break;
                default:
                    throw new DukeException(errMsgs.provideWrongCommandText());
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
