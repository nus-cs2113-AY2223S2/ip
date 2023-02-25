package parser;

import command.*;
import task.Deadline;
import exception.DukeException;
import task.Event;
import task.Task;


public class Parser {
    private boolean isExecuting;
    private static final String TODO_STRING = "todo";
    private static final String DEADLINE_STRING = "deadline";
    private static final String EVENT_STRING = "event";

    private static final String LINE = "___________________________________________";
    private static final String EXIT_STRING = "bye";
    private static final String LIST_STRING = "list";
    private static final String MARK_STRING = "mark";
    private static final String UNMARK_STRING = "unmark";
    private static final String DELETE_STRING = "delete";


    public Parser() {
        isExecuting = true;
    }

    public boolean isExecuting() {
        return this.isExecuting;
    }

    public static Task processSavedInput (String input) throws DukeException {
        String taskType = "", commandInfo = "";
        switch (input.charAt(1)) {
            case 'T' :
                taskType = TODO_STRING;
                commandInfo = input.substring(7);
                break;
            case 'D' :
                taskType = DEADLINE_STRING;
                commandInfo = input.substring(7).replace("(by:","/by").replace(")","");

                break;
            case 'E' :
                taskType = EVENT_STRING;
                commandInfo = input.substring(7).replace("(from:","/from").replace("to:","/to").replace(")","");
                break;
        }
        Task taskToAdd = handleAddTask(taskType,commandInfo);
        if (input.charAt(4) == 'X') {
            taskToAdd.markAsDone();
        }
        return taskToAdd;
    }



    public static Task handleAddTask(String taskType, String commandInfo) throws DukeException {
        Task newTask = null;
        if (taskType.equals(TODO_STRING)) {
            newTask = new Task(commandInfo);
        } else if (taskType.equals(DEADLINE_STRING)) {
            String[] infoArr = commandInfo.split("/by");
            if (infoArr.length != 2 ) {
                throw new DukeException("Please specify your deadline");
            }
            //infoArr contains descStr and deadlineStr respectively
            newTask = new Deadline(infoArr[0].trim(), infoArr[1].trim());
        } else if (taskType.equals(EVENT_STRING)) {
            String[] infoArr = commandInfo.split("/from|/to");
            if (infoArr.length !=3 ) {
                throw new DukeException("Please specify the starting and ending time of your event");
            }
            //infoArr contains descStr, fromStr, and toStr respectively
            newTask = new Event(infoArr[0].trim(), infoArr[1].trim(), infoArr[2].trim());
        }
        return newTask;
    }

    public Command parse (String inputLine) throws DukeException, NumberFormatException {
        //splits input based on one or more whitespaces into two words
        String[] inputWords = inputLine.split("\\s+", 2);
        String command = inputWords[0];

        if (command.equals(EXIT_STRING)) {
            isExecuting = false;
            return new ExitCommand();
        }
        else if (command.equals(LIST_STRING)) {
           return new ListCommand();
        }
        else if (command.equals(MARK_STRING)) {
            //inputWords[1] is string that no longer contains the command string
            if (inputWords.length < 2) {
                throw new DukeException("Please specify which task you wish to mark");
            } else {
                    int indexToMark = Integer.parseInt(inputWords[1]) - 1; //turn it into 0-based
                    return new MarkCommand(indexToMark);
            }
        }
        else if (command.equals(UNMARK_STRING)) {
            if (inputWords.length < 2) {
                throw new DukeException("Please specify which task you wish to unmark");
            } else {
                int indexToUnmark = Integer.parseInt(inputWords[1]) - 1;
                return new UnmarkCommand(indexToUnmark);
            }
        }

        else if(command.equals(DELETE_STRING)) {
            if(inputWords.length < 2) {
                throw new DukeException("Please specify which task you wish to delete");
            } else {
                int indexToRemove = Integer.parseInt(inputWords[1])-1;
                return new RemoveCommand(indexToRemove);
            }
        }
        //check if command string matches either of the string
        else if (command.matches("todo|deadline|event")) {
            if (inputWords.length < 2) {
                throw new DukeException("Please specify the description to the task that you wish to add");
            }
            Task taskToAdd = handleAddTask(command, inputWords[1]);
            return new AddTaskCommand(taskToAdd);
        } else {
            throw new DukeException("fsgfsuygu I don't know what that means :(");
        }
    }
}
