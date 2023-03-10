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
     * The LENGTH and INDEX below depends on the Format of the command:
     * Todo:     todo [description]
     * Deadline: deadline [description] /by [time]
     * Event:    event [description] /from [time] /to [time]
     */
    static final int DEADLINE_PARAMS_LENGTH = 2;
    static final int DEADLINE_BY_INDEX = 1;
    static final int DEADLINE_DESCRIPTION_INDEX = 0;
    static final int DEADLINE_TIME_INDEX = 1;
    static final int DEADLINE_TIME_START_AT_NUM = 3;
    static final int EVENT_PARAMS_LENGTH = 3;
    static final int EVENT_FROM_INDEX = 1;
    static final int EVENT_TO_INDEX = 2;
    static final int EVENT_DESCRIPTION_INDEX = 0;
    static final int EVENT_FROM_START_AT_NUM = 5;
    static final int EVENT_TO_START_AT_NUM = 3;
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
        if(commandParamsList.length != DEADLINE_PARAMS_LENGTH){
            throw new ArrayIndexOutOfBoundsException();
        }

        /* Exception 3: no 'by' keyword found */
        if(!commandParamsList[DEADLINE_BY_INDEX].startsWith("by ")){
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
            String todoString = commandParamsList[DEADLINE_DESCRIPTION_INDEX];
            String deadlineString = commandParamsList[DEADLINE_TIME_INDEX]
                    .substring(DEADLINE_TIME_START_AT_NUM);

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
        if (commandParamsList.length != EVENT_PARAMS_LENGTH) {
            throw new ArrayIndexOutOfBoundsException();
        }

        /* Exception 3: No 'from' or 'to' found */
        if (!(commandParamsList[EVENT_FROM_INDEX].startsWith("from ")
                && commandParamsList[EVENT_TO_INDEX].startsWith("to "))) {
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
            String eventString = commandParamsList[EVENT_DESCRIPTION_INDEX].trim();
            String fromString = commandParamsList[EVENT_FROM_INDEX]
                    .substring(EVENT_FROM_START_AT_NUM).trim();   //magic number
            String toString = commandParamsList[EVENT_TO_INDEX]
                    .substring(EVENT_TO_START_AT_NUM).trim();

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
            } case("help"):{
                return new HelpCommand();
            } default: {
                return new UnknownCommand(userCommand);
            }
        }
    }



}
