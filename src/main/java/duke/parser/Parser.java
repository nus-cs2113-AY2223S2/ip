package duke.parser;

import duke.outputs.Messages;
import duke.exception.UnknownCommandException;
import duke.commands.ByeCommand;
import duke.commands.Command;
import duke.commands.DeadlineCommand;
import duke.commands.DeleteCommand;
import duke.commands.EventCommand;
import duke.commands.UnknownCommand;
import duke.commands.ListCommand;
import duke.commands.MarkCommand;
import duke.commands.TodoCommand;
import duke.commands.TypesOfCommands;
import duke.commands.UnmarkCommand;

public class Parser {
    // How the program runs based on user's input commands
    public Command parseInput(String userInput) throws UnknownCommandException {
        String[] inputArray = userInput.split(" ");
        TypesOfCommands commandType;

        try {
            commandType = TypesOfCommands.valueOf(inputArray[0].toUpperCase());
        } catch (IllegalArgumentException exception) {
            throw new UnknownCommandException();
        }

        switch (commandType) {

        case TODO:
            return parserToDo(inputArray);

        case DEADLINE:
            return parserDeadline(inputArray);

        case EVENT:
            return parserEvent(inputArray);

        case LIST:
            return new ListCommand();

        case DELETE:
            return parserDelete(inputArray);

        case MARK:
            return parserMark(inputArray);

        case UNMARK:
            return parserUnmark(inputArray);

        case BYE:
            return new ByeCommand();

        default:
            throw new UnknownCommandException();
        }
    }

        private Command parserToDo (String[] inputArray){
            if (inputArray.length == 1) {
                return new UnknownCommand(Messages.ERROR_MESSAGE_MISSING_TASK_DESCRIPTION);
            }
            String userInput = "";

            for (int i = 0 ; i < inputArray.length ; i++){
                userInput = userInput + System.lineSeparator() + inputArray[i];
            }

            userInput = userInput.replaceFirst("todo", "").trim();
            String taskDescription = userInput;
            return new TodoCommand(taskDescription);

        }
        private Command parserDeadline (String[] inputArray){
            if (inputArray.length == 1) {
                return new UnknownCommand(Messages.ERROR_MESSAGE_MISSING_TASK_DESCRIPTION);
            }
            String userInput = "";

            for (int i = 0 ; i < inputArray.length ; i++){
                userInput = userInput + System.lineSeparator() + inputArray[i];
            }
            userInput = userInput.replaceFirst("deadline", "").trim();
            int separatorIdx = userInput.indexOf("/");
            String taskName = userInput.substring(0, separatorIdx - 1);
            String by = userInput.substring(separatorIdx + 4);
            return new DeadlineCommand(taskName, by);

        }
        private Command parserEvent (String[] inputArray){
            if (inputArray.length == 1) {
                return new UnknownCommand(Messages.ERROR_MESSAGE_MISSING_TASK_DESCRIPTION);
            }
            String userInput = "";

            for (int i = 0 ; i < inputArray.length ; i++){
                userInput = userInput + System.lineSeparator() + inputArray[i];
            }
            userInput = userInput.replaceFirst("event", "").trim();
            int separatorIdx = userInput.indexOf("/");
            int lastSeparatorIdx = userInput.lastIndexOf("/");
            String taskName = userInput.substring(0, separatorIdx - 1);
            String startTime = userInput.substring(separatorIdx + 6, lastSeparatorIdx - 1);
            String endTime = userInput.substring(lastSeparatorIdx + 4);

            return new EventCommand(taskName, startTime, endTime);

        }

        private Command parserMark (String[] inputArray){
            if (inputArray.length == 1) {
                return new UnknownCommand(Messages.ERROR_MESSAGE_MISSING_TASK_INDEX);
            }
            try {
                return new MarkCommand(Integer.parseInt(inputArray[1]) - 1);
            } catch (NumberFormatException exception) {
                return new UnknownCommand(Messages.ERROR_MESSAGE_TASK_NOT_FOUND);
            }

        }

        private Command parserUnmark (String[] inputArray){
            if (inputArray.length == 1) {
                return new UnknownCommand(Messages.ERROR_MESSAGE_MISSING_TASK_INDEX);
            }
            try {
                return new UnmarkCommand(Integer.parseInt(inputArray[1]) - 1);
            } catch (NumberFormatException exception) {
                return new UnknownCommand(Messages.ERROR_MESSAGE_TASK_NOT_FOUND);
            }

        }

        private Command parserDelete (String[] inputArray){
            if (inputArray.length == 1) {
                return new UnknownCommand(Messages.ERROR_MESSAGE_MISSING_TASK_INDEX);
            }
            try {
                return new DeleteCommand(Integer.parseInt(inputArray[1]) - 1);
            } catch (NumberFormatException exception) {
                return new UnknownCommand(Messages.ERROR_MESSAGE_TASK_NOT_FOUND);
            }
        }

}



//        switch (keyword) {
//        case "todo":
//            try {
//                if (inputArray.length == 1) {
//                    throw new DukeException();
//                }
//                userInput = userInput.replaceFirst("todo", "").trim();
//                UI.addNewTodo(userInput);
//                Todo todoTask = new Todo(userInput, "T");
//                tasksArray.add(todoTask);
//                UI.printListLength(tasksArray.size());
//                tasksCount++;
//                updateFile();
//            } catch (DukeException e) {
//                e.todoError();
//            }
//            break;
//
//        case "deadline":
//            try {
//                if (inputArray.length == 1) {
//                    throw new DukeException();
//                }
//                userInput = userInput.replaceFirst("deadline", "").trim();
//                int separatorIdx = userInput.indexOf("/");
//                String taskName = userInput.substring(0, separatorIdx - 1);
//                String by = userInput.substring(separatorIdx + 4);
//                UI.addNewDeadline(taskName, by);
//                Deadline deadlineTask = new Deadline(taskName, "D", by);
//                tasksArray.add(deadlineTask);
//                tasksCount++;
//                UI.printListLength(tasksArray.size());
//                updateFile();
//            } catch (DukeException e) {
//                e.deadlineError();
//            }
//            break;
//
//        case "event":
//            try {
//                if (inputArray.length == 1) {
//                    throw new DukeException();
//                }
//                userInput = userInput.replaceFirst("event", "").trim();
//                int separatorIdx = userInput.indexOf("/");
//                int lastSeparatorIdx = userInput.lastIndexOf("/");
//                String taskName = userInput.substring(0, separatorIdx - 1);
//                String startTime = userInput.substring(separatorIdx + 6, lastSeparatorIdx - 1);
//                String endTime = userInput.substring(lastSeparatorIdx + 4);
//                UI.addNewEvent(taskName, startTime, endTime);
//                Event eventTask = new Event(taskName, "E", startTime, endTime);
//                tasksArray.add(eventTask);
//                tasksCount++;
//                UI.printListLength(tasksArray.size());
//                updateFile();
//            } catch (DukeException e) {
//                e.eventError();
//            }
//            break;
//
//        case "mark":
//            try {
//                if (inputArray.length == 1) {
//                    throw new DukeException();
//                }
//                int taskNum = Integer.parseInt(inputArray[1]);
//                markTask(taskNum);
//
//            } catch (IndexOutOfBoundsException e) {
//                System.out.println(MARGIN);
//                System.out.println("Please key in a valid task number to mark!");
//                System.out.println(MARGIN);
//
//            } catch (DukeException e) {
//                e.emptyListError();
//            }
//            break;
//
//        case "unmark":
//            try {
//                if (inputArray.length == 1) {
//                    throw new DukeException();
//                }
//                int taskNum = Integer.parseInt(inputArray[1]);
//                unmarkTask(taskNum);
//            } catch (IndexOutOfBoundsException e) {
//                System.out.println(MARGIN);
//                System.out.println("Please key in a valid task number to unmark!");
//                System.out.println(MARGIN);
//
//            } catch (DukeException e) {
//                e.emptyListError();
//            }
//            break;
//
//        case "delete":
//            try {
//                if (inputArray.length == 1) {
//                    throw new DukeException();
//                }
//                int taskNum = Integer.parseInt(inputArray[1]);
//                deleteTask(taskNum);
//
//            } catch (DukeException e){
//                System.out.println(MARGIN);
//                System.out.println("There is nothing to delete as list is empty! O_O");
//                System.out.println(MARGIN);
//
//            } catch (IndexOutOfBoundsException e) {
//                System.out.println(MARGIN);
//                System.out.println("Please key in a valid task number to delete!");
//                System.out.println(MARGIN);
//
//            }
//            break;
//
//        case "bye":
//            UI.endProgram();
//            updateFile();
//            break;
//
//        case "list":
//            accessList();
//            break;
//

//        default:
//            System.out.println(MARGIN);
//            System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
//            System.out.println(MARGIN);
//            break;
//        }
//    }
//}
