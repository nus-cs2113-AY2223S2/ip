package duke.tools;

import duke.exceptions.CommandDescriptionEmptyException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Todo;

public class Parser {

    /**
     * Split a string with one or multiple words into an array of strings
     *
     * @param inputCommand
     * @return inputWords
     */
    public String[] splitCommand(String inputCommand){
        String inputWords[] = inputCommand.split(" ");
        return inputWords;
    }

    /**
     * Get the first word in the input command.
     * Convert the command into small letter before returning.
     *
     * @param inputWords
     * @return command
     */
    public String getCommandType(String inputWords){
        String[] commandWords = splitCommand(inputWords);
        String command = changeCommandLowerCase(commandWords[0]);
        return command;
    }

    /**
     * Convert command into small letters and return the resulted word.
     *
     * @param command
     * @return
     */
    public String changeCommandLowerCase(String command){

        return command.toLowerCase();
    }

    /**
     * Get the description of the task in the command.
     * The second word onwards in the command.
     *
     * @param inputWords
     * @return
     */
    public String getCommandDescription(String inputWords) throws CommandDescriptionEmptyException {
        String command = getCommandType(inputWords);
        String description;
        switch(command){
        case "mark":
        case "unmark":
        case "delete":
        case "event":
        case "todo":
        case "deadline":
        case "find":
        case "date":
            if(command.length() == inputWords.length()){
                throw new CommandDescriptionEmptyException();
            }
            String commandDescriptionString = inputWords.substring((command.length())+1);
            description = commandDescriptionString;
            break;
        default:
            description =  inputWords;
            break;
        }
        return description;
    }

}
