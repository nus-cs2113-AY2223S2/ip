package parser;

import command.*;
import exceptions.DeadlineParamsFormatException;
import exceptions.EventParamsFormatException;
import ui.Ui;

import java.util.Arrays;

/**
 * Parse user's command, create corresponding command objects.
 */
public class Parser {
    /**
     * Parse user's command into type and params.
     * @param userCommand users' command (including all different types of commands)
     * @return type and params of the command
     */
    private static String[] getCommandTypeAndParams(String userCommand){
        String[] userCommandWords = userCommand.split(" ");
        String commandType = userCommandWords[0];
        String commandParams = null;
        if(userCommandWords.length > 1){
            commandParams = String.join(" ",
                    Arrays.copyOfRange(userCommandWords,1,userCommandWords.length));
        }
        String[] commandTypeAndParams = {commandType, commandParams};
        return commandTypeAndParams;
    }

    /**
     * Check the params String of a deadline command, and return a list of params.
     * @param commandParams A String containing all deadline params.
     * @return A list of separate params.
     * @throws DeadlineParamsFormatException
     */
    private static String[] getDeadlineCommandParmasList(String commandParams)
            throws DeadlineParamsFormatException {

        /* Exception 1: No '/' found */
        if(commandParams.indexOf('/') == -1){
            throw new NullPointerException();
        }

        /* Exception 2: Multiple '/' found */
        String[] commandParamsList = commandParams.split("/");
        if(commandParamsList.length != 2){
            throw new ArrayIndexOutOfBoundsException();
        }

        /* Exception 3: no 'by' keyword found */
        if(!commandParamsList[1].startsWith("by ")){
            throw new DeadlineParamsFormatException();
        }
        return commandParamsList;
    }


    /**
     * Parse deadline command's params and return an object of it.
     * @param commandParams A string of params.
     *                      Its correct Format is:'[description] /by [time]'
     * @return A deadline command object.
     */
    private static Command prepareDeadlineCommand(String commandParams){
        Ui ui = new Ui();
        String[] commandParamsList = new String[0];
        try {
            commandParamsList = getDeadlineCommandParmasList(commandParams);
            String todoString = commandParamsList[0];
            String deadlineString = commandParamsList[1].substring(3);

            return new DeadlineCommand(todoString,deadlineString);
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.showDeadlineParmsFormat();
        } catch (NullPointerException e) {
            ui.showNullPointerException();
        } catch (DeadlineParamsFormatException e) {
            ui.showDeadlineParmsFormat();
        }
        return new UnknownCommand(commandParams);
    }

    /**
     * Check the params String of an event command, and return a list of params.
     * @param commandParams A String containing all event params.
     * @returnA list of separate params.
     * @throws EventParamsFormatException
     */
    private static String[] getEventCommandParamsList(String commandParams)
            throws EventParamsFormatException {
        /* Exception 1: No '/' found */
        if (commandParams.indexOf('/') == -1) {
            throw new NullPointerException();
        }

        /* Exception 2: Less than 2 '/' or Multiple '/' found */
        String[] commandParamsList = commandParams.split("/");
        if (commandParamsList.length != 3) {
            throw new ArrayIndexOutOfBoundsException();
        }

        /* Exception 3: No 'from' or 'to' found */
        if (!(commandParamsList[1].startsWith("from ")
                && commandParamsList[2].startsWith("to "))) {
            throw new EventParamsFormatException();
        }

        return commandParamsList;
    }

    /**
     * Parse event command's params and return an object of it.
     * @param commandParams A string of params.
     *                      Its correct Format is:'[description] /from [time] /to [time]'
     * @return An event command object.
     */
    private static Command prepareEventCommand(String commandParams){
        Ui ui = new Ui();
        String[] commandParamsList = new String[0];
        try {
            commandParamsList = getEventCommandParamsList(commandParams);
            String eventString = commandParamsList[0].trim();
            String fromString = commandParamsList[1].substring(5).trim();   //magic number
            String toString = commandParamsList[2].substring(3).trim();

            return new EventCommand(eventString, fromString, toString);
        } catch (EventParamsFormatException e) {
            ui.showEventParamsFormat();
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.showEventParamsFormat();
        } catch (NullPointerException e) {
            ui.showNullPointerException();
        }
        return new UnknownCommand(commandParams);
    }


    /**
     * Parse find command's params and return an object of it.
     * @param commandParams A string of params. The method only checks if it is null.
     * @return An find command object.
     */
    private static Command prepareFindCommand(String commandParams) {
        if(commandParams == null) {
            return new UnknownCommand(commandParams);
        }
        return new FindCommand(commandParams);
    }

    /**
     * Parse user's command, create corresponding command objects.
     * @param userCommand User's command, including all different types of commands
     * @return Corresponding command objects.
     */
    public static Command parse(String userCommand){
        String[] commandTypeAndParams = getCommandTypeAndParams(userCommand);
        String commandType = commandTypeAndParams[0];
        String commandParams = commandTypeAndParams[1];

        switch (commandType){
            case("list"):{
                return new ListCommand();
            } case("add"):{
                return new AddCommand(commandParams);
            } case("mark"): {
                return new MarkCommand(commandParams, true);
            } case("unmark"):{
                return new MarkCommand(commandParams, false);
            } case("todo"):{
                return new TodoCommand(commandParams);
            } case("deadline"):{
                return prepareDeadlineCommand(commandParams);
            } case("event"):{
                return prepareEventCommand(commandParams);
            } case("delete"): {
                return new DeleteCommand(commandParams);
            } case("find"):{
                return prepareFindCommand(commandParams);
            } case("bye"):{
                return new ExitCommand();
            }default:{
                return new UnknownCommand(commandParams);
            }
        }
    }



}
