package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Parser {

    public static final DateTimeFormatter storedDateFormat = DateTimeFormatter.ofPattern("MMM d yyyy");
    public static final DateTimeFormatter inputDateFormat = DateTimeFormatter.ISO_LOCAL_DATE;
    public static Command parseCommand(String userInput) throws DukeException{
        String firstWordOfInput = userInput.split(" ")[0];
        CommandType commandType;
        String[] additionalParameters = null;
        switch (firstWordOfInput) {
        case CommandInputs.ADD_TODO_COMMAND_INPUT:
            commandType = CommandType.ADD_TODO_COMMAND;
            additionalParameters = getTaskNameParameter(userInput, CommandInputs.ADD_TODO_COMMAND_INPUT);
            break;
        case CommandInputs.ADD_DEADLINE_COMMAND_INPUT:
            commandType = CommandType.ADD_DEADLINE_COMMAND;
            additionalParameters = getDeadlineAdditionalParameters(userInput);
            break;
        case CommandInputs.ADD_EVENT_COMMAND_INPUT:
            commandType = CommandType.ADD_EVENT_COMMAND;
            additionalParameters = getEventAdditionalParameters(userInput);
            break;
        case CommandInputs.LIST_TASKS_COMMAND_INPUT:
            commandType = CommandType.LIST_TASKS_COMMAND;
            break;
        case CommandInputs.MARK_COMMAND_INPUT:
            commandType = CommandType.MARK_TASK_COMMAND;
            additionalParameters = getTaskNumbers(userInput);
            break;
        case CommandInputs.UNMARK_COMMAND_INPUT:
            commandType = CommandType.UNMARK_TASK_COMMAND;
            additionalParameters = getTaskNumbers(userInput);
            break;
        case CommandInputs.DELETE_COMMAND_INPUT:
            commandType = CommandType.DELETE_TASK_COMMAND;
            additionalParameters = getTaskNumbers(userInput);
            break;
        case CommandInputs.FIND_COMMAND_INPUT:
            commandType = CommandType.FIND_TASK_COMMAND;
            additionalParameters = getTaskNameParameter(userInput, CommandInputs.FIND_COMMAND_INPUT);
            break;
        case CommandInputs.END_PROGRAM_COMMAND_INPUT:
            commandType = CommandType.END_PROGRAM_COMMAND;
            break;
        default:
            throw new InvalidCommandException();
        }
        return new Command(commandType,additionalParameters);
    }

    private static String[] getTaskNumbers(String userInput) throws DukeException{
        userInput = userInput.trim();
        int indexOfFirstSpace = userInput.indexOf(" ");
        if(indexOfFirstSpace == -1 || indexOfFirstSpace == userInput.length() - 1){
            throw new TaskNumberException();
        }
        String[] taskNumbers = userInput.substring(indexOfFirstSpace).trim().split(" ");
        if(taskNumbers.length != 1){
            throw new TaskNumberException();
        }
        return taskNumbers;
    }


    protected static String[] getTaskNameParameter(String userInput, String commandName) throws DukeException{
        String[] additionalParameters = new String[1];
        int indexOfFirstSpace = userInput.indexOf(" ");
        if(indexOfFirstSpace == -1){
            throw new TaskNameException(commandName);
        }
        String name = userInput.substring(indexOfFirstSpace).trim();
        if(!isNameValid(name)){
            throw new TaskNameException(commandName);
        }
        additionalParameters[0] = name;
        return additionalParameters;
    }
    private static String[] getDeadlineAdditionalParameters(String userInput) throws DukeException{
        int indexOfFirstSpace = userInput.indexOf(" ");
        int indexOfBy =  userInput.indexOf(CommandInputs.ADD_DEADLINE_BY_COMMAND_INPUT);
        if(indexOfFirstSpace == -1){
            throw new TaskNameException(CommandInputs.ADD_DEADLINE_COMMAND_INPUT);
        }
        if(indexOfBy == -1 || indexOfBy < indexOfFirstSpace){
            throw new DeadlineParameterException();
        }
        String name = userInput.substring(indexOfFirstSpace,indexOfBy).trim();
        String by = userInput.substring(indexOfBy + CommandInputs.ADD_DEADLINE_BY_COMMAND_INPUT.length()).trim();
        String[] additionalParameters = new String[2];
        if(!isNameValid(name)){
            throw new TaskNameException(CommandInputs.ADD_DEADLINE_COMMAND_INPUT);
        }
        if(!isDateValid(by)){
            throw new TaskDateFormatException(CommandInputs.ADD_DEADLINE_COMMAND_INPUT);
        }
        // Parse with same formatter as when stored, since the same function is used to parse stored data as user input
        LocalDate byLocalDate = LocalDate.parse(by, inputDateFormat);
        additionalParameters[0] = name;
        additionalParameters[1] = byLocalDate.format(storedDateFormat);
        return additionalParameters;
    }

    private static String[] getEventAdditionalParameters(String userInput) throws DukeException{
        int indexOfFirstSpace = userInput.indexOf(" ");
        int indexOfFrom = userInput.indexOf(CommandInputs.ADD_EVENT_FROM_COMMAND_INPUT);
        int indexOfTo = userInput.indexOf(CommandInputs.ADD_EVENT_TO_COMMAND_INPUT);
        if(indexOfFirstSpace == -1){
            throw new TaskNameException(CommandInputs.ADD_EVENT_COMMAND_INPUT);
        }
        if(indexOfFrom == -1 || indexOfTo == -1 || indexOfFrom < indexOfFirstSpace || indexOfTo < indexOfFirstSpace){
            throw new EventParameterException();
        }
        String[] additionalParameters = new String[3];
        String name;
        String from;
        String to;
        if(indexOfFrom < indexOfTo){
            name = userInput.substring(indexOfFirstSpace,indexOfFrom).trim();
            from = userInput.substring(indexOfFrom + CommandInputs.ADD_EVENT_FROM_COMMAND_INPUT.length(), indexOfTo).trim();
            to = userInput.substring(indexOfTo + CommandInputs.ADD_EVENT_TO_COMMAND_INPUT.length()).trim();
        }else{
            name = userInput.substring(indexOfFirstSpace,indexOfTo).trim();
            from = userInput.substring(indexOfFrom + CommandInputs.ADD_EVENT_FROM_COMMAND_INPUT.length()).trim();
            to = userInput.substring(indexOfTo + CommandInputs.ADD_EVENT_TO_COMMAND_INPUT.length(), indexOfFrom).trim();
        }
        if(!isNameValid(name)){
            throw new TaskNameException(CommandInputs.ADD_EVENT_COMMAND_INPUT);
        }
        if(!isDateValid(from) || !isNameValid(to)){
            throw new TaskDateFormatException(CommandInputs.ADD_EVENT_COMMAND_INPUT);
        }

        // Parse with the same formatter as when stored, since same function is used to parse stored data as user input
        LocalDate fromLocalDate = LocalDate.parse(from, inputDateFormat);
        LocalDate toLocalDate = LocalDate.parse(to, inputDateFormat);
        if(toLocalDate.isBefore(fromLocalDate)){
            throw new TaskDateOrderException(CommandInputs.ADD_EVENT_COMMAND_INPUT);
        }
        additionalParameters[0] = name;
        additionalParameters[1] = fromLocalDate.format(storedDateFormat);
        additionalParameters[2] = toLocalDate.format(storedDateFormat);
        return additionalParameters;
    }

    private static boolean isNameValid(String name){
        return !name.isEmpty();
    }

    private static boolean isDateValid(String date){
        if(date.isEmpty()){
            return false;
        }
        try{
            String[] dateParameters = date.split("-");
            // Check if date can be parsed
            LocalDate.parse(date, inputDateFormat);
        }catch (Exception e){
            return false;
        }
        return true;
    }
}
