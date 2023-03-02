public class Parser {

    public static Command getCommand(){
        String userInput = UI.getUserCommand().toLowerCase();
        return getCommand(userInput);
    }

    public static Command getCommand(String userInput){

        if(userInput.startsWith("todo")){
            return new AddTodoCommand(userInput);
        }else if(userInput.startsWith("deadline") && isValidDeadline(userInput)){
            return new AddDeadlineCommand(userInput);
        }else if(userInput.startsWith("event") && isValidEvent(userInput)){
            return new AddEventCommand(userInput);
        }else if(userInput.startsWith("list")){
            return new ListCommand();
        }else if(userInput.startsWith("mark")){
            return new MarkCommand(userInput);
        }else if(userInput.startsWith("unmark")){
            return new UnmarkCommand(userInput);
        }else if(userInput.startsWith("delete")){
            return new DeleteCommand(userInput);
        }else if(userInput.startsWith("find")){
            return new FindCommand(userInput);
        } else if(userInput.startsWith("bye")){
            return new ByeCommand();
        }

        UI.printInputErrorComment();
        return null;
    }

    public static boolean isValidDeadline(String userInput){
        if(userInput.contains("by")){
            return true;
        }
        return false;
    }

    public static boolean isValidEvent(String userInput){
        if(userInput.contains("from") && userInput.contains("to")){
            return true;
        }
        return false;
    }

}
