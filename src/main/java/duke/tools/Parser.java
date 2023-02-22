package duke.tools;

import duke.exceptions.CommandDescriptionEmptyException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Todo;

/**
 * Process and make sense of user inputs.
 */
public class Parser {

    /**
     * Split a string with one or multiple words into an array of strings based on empty spaces.
     *
     * @param inputCommand
     * @return inputWords
     */
    public String[] splitCommand(String inputCommand){
        String inputWords[] = inputCommand.split(" ");
        return inputWords;
    }

    /**
     * Get the first word in the input command as the command type.
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
     * Get the second word onwards in the command as task description.
     *
     * @param inputWords
     * @return
     */
    public String getCommandDescription(String inputWords) throws CommandDescriptionEmptyException {
        String command = getCommandType(inputWords);
        if(command.equals("bye")||command.equals("list")){
            return inputWords;
        }else{
            if(command.length() == inputWords.length()){
                throw new CommandDescriptionEmptyException();
            }
            String commandDescriptionString = inputWords.substring((command.length())+1);
            return commandDescriptionString;
        }
    }
}
