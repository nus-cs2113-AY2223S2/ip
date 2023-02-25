package parser;

import command.*;
import exception.InvalidIndexException;
import exception.InvalidInputException;
import task.Deadline;
import exception.IncompleteInputException;
import task.Event;
import task.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;


public class Parser {
    private boolean isExecuting;

    public Parser() {
        isExecuting = true;
    }

    public boolean isExecuting() {
        return this.isExecuting;
    }

    public static Task processSavedInput (String input) throws IncompleteInputException {
        String taskType = "", commandInfo = "";
        switch (input.charAt(1)) {
            case 'T' :
                taskType = Input.TODO.input;
                commandInfo = input.substring(7);
                break;
            case 'D' :
                taskType = Input.DEADLINE.input;
                commandInfo = input.substring(7).replace("(by:","/by").replace(")","");

                break;
            case 'E' :
                taskType = Input.EVENT.input;
                commandInfo = input.substring(7).replace("(from:","/from").replace("to:","/to").replace(")","");
                break;
        }
        Task taskToAdd = handleAddTask(taskType,commandInfo);
        if (input.charAt(4) == 'X') {
            taskToAdd.markAsDone();
        }
        return taskToAdd;
    }


    public static Task handleAddTask(String taskType, String commandInfo) throws IncompleteInputException, DateTimeParseException {
        Task newTask = null;
        if (taskType.equals(Input.TODO.input)) {
            newTask = new Task(commandInfo);
        }

        else if (taskType.equals(Input.DEADLINE.input)) {
            String[] infoArr = commandInfo.split("/by");
            if (infoArr.length != 2 ) {
                throw new IncompleteInputException ("Please specify your deadline");
            }

            //infoArr contains descStr and deadlineStr respectively
            newTask = new Deadline(infoArr[0].trim(), infoArr[1].trim());
        }

        else if (taskType.equals(Input.EVENT.input)) {
            String[] infoArr = commandInfo.split("/from|/to");
            if (infoArr.length !=3 ) {
                throw new IncompleteInputException ("Please specify the starting and ending time of your event");
            }
            //infoArr contains descStr, fromStr, and toStr respectively
            newTask = new Event(infoArr[0].trim(), infoArr[1].trim(), infoArr[2].trim());

        }

        return newTask;
    }

    public Command parse (String inputLine) throws IncompleteInputException, InvalidInputException ,NumberFormatException {
        //splits input based on one or more whitespaces into two words
        String[] inputWords = inputLine.split("\\s+", 2);
        String command = inputWords[0];

        if (command.equals(Input.BYE.input)) {
            isExecuting = false;
            return new ExitCommand();
        }
        else if (command.equals(Input.LIST.input)) {
           return new ListCommand();
        }
        else if (command.equals(Input.MARK.input)) {
            //inputWords[1] is string that no longer contains the command string
            if (inputWords.length < 2) {
                throw new IncompleteInputException ("Please specify which task you wish to mark");
            } else {
                    int indexToMark = Integer.parseInt(inputWords[1]) - 1; //turn it into 0-based
                    return new MarkCommand(indexToMark);
            }
        }
        else if (command.equals(Input.UNMARK.input)) {
            if (inputWords.length < 2) {
                throw new InvalidInputException("Please specify which task you wish to unmark");
            } else {
                int indexToUnmark = Integer.parseInt(inputWords[1]) - 1;
                return new UnmarkCommand(indexToUnmark);
            }
        }

        else if(command.equals(Input.DELETE.input)) {
            if(inputWords.length < 2) {
                throw new IncompleteInputException ("Please specify which task you wish to delete");
            } else {
                int indexToRemove = Integer.parseInt(inputWords[1])-1;
                return new RemoveCommand(indexToRemove);
            }
        }

        //check if command string matches either of todo,deadline,event
        else if (command.matches(Input.TODO.input + "|" + Input.DEADLINE.input + "|" + Input.EVENT.input)) {
            if (inputWords.length < 2) {
                throw new IncompleteInputException ("Please specify the description to the task that you wish to add");
            }
            Task taskToAdd = handleAddTask(command, inputWords[1]);
            return new AddTaskCommand(taskToAdd);
        } else {
            throw new InvalidInputException ("fsgfsuygu I don't know what that means :(");
        }
    }
}
