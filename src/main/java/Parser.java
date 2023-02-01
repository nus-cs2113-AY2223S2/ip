public class Parser {
    public static Command parseCommand(String userInput){
        String firstWordOfInput = userInput.split(" ")[0];
        CommandType commandType;
        String[] additionalParameters = null;
        switch(firstWordOfInput){
        case CommandInputs.ADD_TODO_COMMAND_INPUT:
            commandType = CommandType.ADD_TODO_COMMAND;
            additionalParameters = getToDoAdditionalParameters(userInput);
            if(additionalParameters == null){
                commandType = CommandType.UNKNOWN_COMMAND;
            }
            break;
        case CommandInputs.ADD_DEADLINE_COMMAND_INPUT:
            commandType = CommandType.ADD_DEADLINE_COMMAND;
            additionalParameters = getDeadlineAdditionalParameters(userInput);
            if(additionalParameters == null){
                commandType = CommandType.UNKNOWN_COMMAND;
            }
            break;
        case CommandInputs.ADD_EVENT_COMMAND_INPUT:
            commandType = CommandType.ADD_EVENT_COMMAND;
            additionalParameters = getEventAdditionalParameters(userInput);
            if(additionalParameters == null){
                commandType = CommandType.UNKNOWN_COMMAND;
            }
            break;
        case CommandInputs.LIST_TASKS_COMMAND_INPUT:
            commandType = CommandType.LIST_TASKS_COMMAND;
            break;
        case CommandInputs.MARK_COMMAND_INPUT:
            commandType = CommandType.MARK_TASK_COMMAND;
            additionalParameters = getTaskNumbers(userInput);
            if(additionalParameters == null){
                commandType = CommandType.UNKNOWN_COMMAND;
            }
            break;
        case CommandInputs.UNMARK_COMMAND_INPUT:
            commandType = CommandType.UNMARK_TASK_COMMAND;
            additionalParameters = getTaskNumbers(userInput);
            if(additionalParameters == null){
                commandType = CommandType.UNKNOWN_COMMAND;
            }
            break;
        case CommandInputs.END_PROGRAM_COMMAND_INPUT:
            commandType = CommandType.END_PROGRAM_COMMAND;
            break;
        default:
            commandType = CommandType.UNKNOWN_COMMAND;
            break;
        }
        return new Command(commandType,additionalParameters);
    }

    protected static String[] getTaskNumbers(String userInput){
        userInput = userInput.trim();
        int indexOfFirstSpace = userInput.indexOf(" ");
        if(indexOfFirstSpace == -1 || indexOfFirstSpace == userInput.length()){
            return null;
        }
        return userInput.substring(indexOfFirstSpace).trim().split(" ");
    }
    protected static String[] getToDoAdditionalParameters(String userInput){
        String[] additionalParameters = new String[1];
        int indexOfFirstSpace = userInput.indexOf(" ");
        if(indexOfFirstSpace == -1){
            return null;
        }
        String name = userInput.substring(indexOfFirstSpace).trim();
        if(!isNameValid(name)){
            return null;
        }
        additionalParameters[0] = name;
        return additionalParameters;
    }
    protected static String[] getDeadlineAdditionalParameters(String userInput){
        int indexOfFirstSpace = userInput.indexOf(" ");
        int indexOfBy =  userInput.indexOf(CommandInputs.ADD_DEADLINE_BY_COMMAND_INPUT);
        if(indexOfFirstSpace == -1 || indexOfBy == -1 || indexOfBy < indexOfFirstSpace){
            return null;
        }
        String name = userInput.substring(indexOfFirstSpace,indexOfBy).trim();
        String by = userInput.substring(indexOfBy + CommandInputs.ADD_DEADLINE_BY_COMMAND_INPUT.length()).trim();
        String[] additionalParameters = new String[2];
        if(!isNameValid(name) || !isDateValid(by)){
            return null;
        }
        additionalParameters[0] = name;
        additionalParameters[1] = by;
        return additionalParameters;
    }

    protected static String[] getEventAdditionalParameters(String userInput){
        int indexOfFirstSpace = userInput.indexOf(" ");
        int indexOfFrom = userInput.indexOf(CommandInputs.ADD_EVENT_FROM_COMMAND_INPUT);
        int indexOfTo = userInput.indexOf(CommandInputs.ADD_EVENT_TO_COMMAND_INPUT);
        if(indexOfFirstSpace == -1 || indexOfFrom == -1 || indexOfTo == -1){
            return null;
        }
        if(indexOfFrom < indexOfFirstSpace || indexOfTo < indexOfFirstSpace){
            return null;
        }
        String[] additionalParamaters = new String[3];
        String name;
        String from;
        String to;
        if(indexOfFrom < indexOfTo){
            name = userInput.substring(indexOfFirstSpace,indexOfFrom).trim();
            from = userInput.substring(indexOfFrom + CommandInputs.ADD_EVENT_FROM_COMMAND_INPUT.length(), indexOfTo).trim();
            to = userInput.substring(indexOfTo + CommandInputs.ADD_EVENT_TO_COMMAND_INPUT.length()).trim();
        }
        else{
            name = userInput.substring(indexOfFirstSpace,indexOfTo).trim();
            from = userInput.substring(indexOfFrom + CommandInputs.ADD_EVENT_FROM_COMMAND_INPUT.length()).trim();
            to = userInput.substring(indexOfTo + CommandInputs.ADD_EVENT_TO_COMMAND_INPUT.length(), indexOfFrom).trim();
        }
        if(!isNameValid(name) || !isDateValid(from) || !isNameValid(to)){
            return null;
        }
        additionalParamaters[0] = name;
        additionalParamaters[1] = from;
        additionalParamaters[2] = to;
        return additionalParamaters;
    }

    protected static boolean isNameValid(String name){
        return !name.isEmpty();
    }
    protected static boolean isDateValid(String date){
        return !date.isEmpty();
    }
}
