import errors.DukeException;
import errors.ErrorMessages;
import tasks.Task;
import util.Marker;
import util.OutputUI;
import util.TaskAdder;
import util.Storage;

import java.util.ArrayList;
import java.util.Scanner;

public class StartPikaDuke extends TaskAdder {
    private static boolean NOT_FROM_SAVE_DATA = false;
    private static final String CHAR_SPACE = " ";
    static ArrayList<Task> listOfTasks = new ArrayList<>();

    public void startPikaDuke() {
        Storage storage = new Storage();
        storage.loadData(listOfTasks);

        Scanner newScanner = new Scanner(System.in);
        OutputUI outPutUI = new OutputUI();
        ErrorMessages errMsgs = new ErrorMessages();
        Marker marker = new Marker();
        while (true) {
            try {
                String input = newScanner.nextLine();
                String firstWord = input.split(CHAR_SPACE)[0];
                switch (firstWord) {
                    case "list":
                        outPutUI.printList(listOfTasks, listOfTasks.size());
                        break;
                    case "bye":
                        outPutUI.printByeByeMessage();
                        newScanner.close();
                        storage.saveListDate(listOfTasks);
                        System.exit((0));
                    case "todo":
                    case "deadline":
                    case "event":
                        addTaskToList(listOfTasks, input, NOT_FROM_SAVE_DATA);
                        break;
                    case "mark":
                    case "unmark":
                        marker.handleMarkUnmarkAction(listOfTasks, input, NOT_FROM_SAVE_DATA);
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
