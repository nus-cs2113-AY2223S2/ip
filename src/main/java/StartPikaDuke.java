import errors.DukeException;
import errors.ErrorMessages;
import tasks.Task;
import util.Marker;
import util.OutputUI;

import java.util.ArrayList;
import java.util.Scanner;
public class StartPikaDuke extends ListAdder{
    private static final String CHAR_SPACE = " ";
    static ArrayList<Task> listOfTask = new ArrayList<>();
    public void startPikaDuke() {
        Scanner newScanner = new Scanner(System.in);
        OutputUI outPutUI = new OutputUI();
        ErrorMessages errMsgs  = new ErrorMessages();
        Marker marker = new Marker();
        while (true) {
            try {
                String input = newScanner.nextLine();
                String firstWord = input.split(CHAR_SPACE)[0];
                switch (firstWord) {
                case "list":
                    outPutUI.printList(listOfTask, listOfTask.size());
                    break;
                case "bye":
                    outPutUI.printByeByeMessage();
                    System.exit((0));
                case "todo":
                case "deadline":
                case "event":
                    addTaskToList(listOfTask, input);
                    break;
                case "mark":
                case "unmark":
                    marker.handleMarkUnmarkAction(listOfTask, input);
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
