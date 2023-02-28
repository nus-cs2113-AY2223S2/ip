package parser;

import command.*;
import exceptions.DeadlineParamsFormatException;
import exceptions.EventParamsFormatException;
import ui.Ui;

import java.io.IOException;
import java.util.Arrays;

public class Parser {
    public static String[] getCommandTypeAndParams(String userCommand){
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

    public static String[] getDeadlineCommandParmasList(String commandParams) throws DeadlineParamsFormatException {

        //input: String /by [ddl]        [have not done]: String /by ddl or /by ddl String
        //Exception 1: No '/'
        if(commandParams.indexOf('/')==-1){
            throw new NullPointerException();
        }

        //Exception 2: Multiple '/'
        String[] commandParamsList = commandParams.split("/");
        if(commandParamsList.length!=2){
            throw new ArrayIndexOutOfBoundsException();
        }

        //Exception 3: no 'by'
        if(!commandParamsList[1].startsWith("by ")){
            throw new DeadlineParamsFormatException();
        }
        return commandParamsList;
    }

    public static Command prepareDeadlineCommand(String commandParams){
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

    public static String[] getEventCommandParamsList(String commandParams) throws EventParamsFormatException {
        //input: String /from [startTime] /to [endTime]        [haven't done]: process case like /to /from
        //Exception 1: No '/'
        if (commandParams.indexOf('/') == -1) {
            throw new NullPointerException();
        }

        //Exception 2: Less than 2 '/' or Multiple '/'
        String[] commandParamsList = commandParams.split("/");
        if (commandParamsList.length != 3) {
            throw new ArrayIndexOutOfBoundsException();
        }

        //Exception 3: No 'from' or 'to'
        if (!(commandParamsList[1].startsWith("from ") && commandParamsList[2].startsWith("to "))) {
            throw new EventParamsFormatException();
        }

        return commandParamsList;
    }

    public static Command prepareEventCommand(String commandParams){
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
            } case("delete"):{
                return new DeleteCommand(commandParams);
            } case("bye"):{
                return new ExitCommand();
            }default:{
                return new UnknownCommand(commandParams);
            }
        }
    }



}
