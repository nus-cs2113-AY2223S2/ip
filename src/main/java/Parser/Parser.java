package Parser;

import Exceptions.GootExceptionHandler;
import Tasklist.*;
import Exceptions.GootExceptions;
import UI.UserInterface;
import Storage.Storage;

import java.util.ArrayList;

public class Parser {
    /**
     * this function is in charge of reading user inputs, storing and reading from a text file and generating responses
     *
     * @param input is a string containing a keyword,taskName and task details.Eg: event orientation /from now /to later
     */

    public static void interpretInput(String input) throws GootExceptions {
        GootExceptionHandler errorHandler = new GootExceptionHandler();
        String[] inputSplitBySpace = input.split(" ");

        if (inputSplitBySpace[0].equals("todo")) {
            boolean emptyToDoCommand = errorHandler.incompleteInformation(input);
            if (!emptyToDoCommand) {
                ToDo todo = new ToDo(input.substring(5), Tasklist.lastIndex + 1, "T");
                Tasklist.addToTaskArrayList(todo);
                UserInterface.acknowledgeTaskAdded();
                Storage.save();
                UserInterface.savedMessage();
            }
        } else if (inputSplitBySpace[0].equals("deadline")) {
            boolean emptyDeadlineCommand = errorHandler.incompleteInformation(input);
            if (!emptyDeadlineCommand) {
                try {
                    errorHandler.validateDeadline(input.split("/"));
                    int taskNumber = Tasklist.lastIndex + 1;
                    Deadline deadline = new Deadline(Deadline.readName(input), taskNumber, Deadline.readBy(input),
                            "D");
                    Tasklist.addToTaskArrayList(deadline);
                    UserInterface.acknowledgeTaskAdded();
                    Storage.save();
                    UserInterface.savedMessage();
                } catch (GootExceptions e) {
                    errorHandler.wrongNumberOfSlashesDeadline();
                }
            }


        } else if (inputSplitBySpace[0].equals("event")) {
            boolean emptyEventCommand = errorHandler.incompleteInformation(input);

            if (!emptyEventCommand) {
                try {
                    errorHandler.validateEvent(input.split("/"));
                    String fromString = Event.readFromTo(input)[0];
                    String toString = Event.readFromTo(input)[1];
                    int taskNumber = Tasklist.lastIndex + 1;
                    Event event = new Event(Event.readName(input), taskNumber, fromString, toString, "E");
                    Tasklist.addToTaskArrayList(event);
                    UserInterface.acknowledgeTaskAdded();
                    Storage.save();
                    UserInterface.savedMessage();
                } catch (GootExceptions e) {
                    errorHandler.wrongNumberOfSlashesEvent();
                }
            }
        } else if (inputSplitBySpace[0].equals("mark") | inputSplitBySpace[0].equals("unmark")) {
            boolean emptyMarkCommand = errorHandler.incompleteInformation(input);
            if (!emptyMarkCommand) {
                int indexToMark = Integer.parseInt(inputSplitBySpace[1]);
                try {
                    errorHandler.validateIndex(indexToMark, Tasklist.lastIndex);
                    Tasklist.markOrUnmark(indexToMark);
                    Storage.save();
                    UserInterface.savedMessage();
                } catch (GootExceptions exception) {
                    errorHandler.indexOutOfBound();
                }
            }
        } else if (input.equals("list")) {
            UserInterface.printList();

        } else if (inputSplitBySpace[0].equals("delete")) {
            boolean emptyDeleteCommand = errorHandler.incompleteInformation(input);
            if (!emptyDeleteCommand) {
                int indexToDelete = Integer.parseInt(inputSplitBySpace[1]);
                try {
                    errorHandler.validateIndex(indexToDelete, Tasklist.lastIndex);
                    Tasklist.deleteFromTaskArray(indexToDelete);
                    Storage.save();
                    UserInterface.savedMessage();
                } catch (GootExceptions exception) {
                    errorHandler.indexOutOfBound();
                }
            }
        } else if (inputSplitBySpace[0].equals("find")) {
            boolean emptyFindCommand = errorHandler.incompleteInformation(input);
            if (!emptyFindCommand) {
                ArrayList<String> listOfTasksFound = Tasklist.find(input.substring(5));
                UserInterface.printFoundList(listOfTasksFound);
            }
        } else {
            errorHandler.unidentifiedKeyword();
        }
    }
}
