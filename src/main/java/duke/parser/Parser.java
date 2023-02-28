package duke.parser;

import duke.task.TaskList;
import duke.exception.EmptyTaskException;
import duke.ui.Ui;

/**
 * Processes the user's input into valid commands.
 */
public class Parser {
    private static Ui ui;
    private static final int OFFSET = -1;
    public Parser(){
        ui = new Ui();
    }

    private static boolean isMarkCommand(String userInput, String Command){
        return userInput.length()>5 && Command.equalsIgnoreCase("mark");
    }

    private static boolean isUnmarkCommand(String userInput, String Command){
        return userInput.length()>7 && Command.equalsIgnoreCase("unmark");
    }

    private static boolean isDeleteCommand(String userInput, String Command){
        return userInput.length()>7 && Command.equalsIgnoreCase("delete");
    }
    private static boolean isToDoCommand(String userInput, String Command){
        return userInput.length()>5 && Command.equalsIgnoreCase("todo");
    }
    private static boolean isDeadlineCommand(String userInput, String Command){
        return userInput.length()>9 && Command.equalsIgnoreCase("deadline");
    }
    private static boolean isEventCommand(String userInput, String Command){
        return userInput.length()>6 && Command.equalsIgnoreCase("event");
    }
    private static boolean isFindCommand(String userInput, String Command){
        return userInput.length()>5 && Command.equalsIgnoreCase("find");
    }
    

    /**
     * Checks the user's input and applies the appropriate command, given that a valid command was given.
     * Also prints feedback to the user if valid or invalid command is given.
     * @param userInput The input string as written by the user through the Command Line Interface.
     * @param taskList The list of Task(s) and its child classes (ToDo, Deadline, Event).
     */
    public static void parseInput(String userInput, TaskList taskList){
        userInput = userInput.trim(); //removes excess whitespace in front and back of command
        String[] commandLine = userInput.split(" "); //split substrings by whitespaces
        String remainder = userInput.substring(userInput.indexOf(" ")+1);
        if (userInput.equalsIgnoreCase("list")){
            taskList.listTasks();
        } else if (userInput.equalsIgnoreCase("helo")){
            ui.printHelp();
        }else if (isMarkCommand(userInput, commandLine[0])){
            parseMarkCommand(remainder, taskList);
        } else if (isUnmarkCommand(userInput, commandLine[0])){
            parseUnmarkCommand(remainder, taskList);
        } else if (isDeleteCommand(userInput, commandLine[0])){
            parseDeleteCommand(remainder, taskList);
        } else if (isToDoCommand(userInput,commandLine[0])){
            parseToDoCommand(remainder, taskList);
        } else if (isDeadlineCommand(userInput, commandLine[0])){
            parseDeadlineCommand(remainder, taskList);
        } else if (isEventCommand(userInput, commandLine[0])) {
            parseEventCommand(remainder, taskList);
        } else if (userInput.equalsIgnoreCase("bye")){
            ui.handleExit();
        } else if (isFindCommand(userInput,commandLine[0])){
            parseFindCommand(remainder, taskList);
        } else{
            ui.showException("IllegalCommandException");
        }
    }

    private static void parseFindCommand(String remainder, TaskList taskList) {
        String description = remainder.trim();
        taskList.find(description);
    }

    private static void parseEventCommand(String remainder, TaskList taskList) {
        try{
            taskList.generateEvent(remainder);
        } catch(EmptyTaskException e){
            ui.showException("EmptyTaskException");
        }
    }

    private static void parseDeadlineCommand(String remainder, TaskList taskList) {
        try{
            taskList.generateDeadline(remainder);
        }catch(EmptyTaskException e){
            ui.showException("EmptyTaskException");
        }
    }

    private static void parseToDoCommand(String remainder, TaskList taskList) {
        try{
            taskList.generateToDo(remainder);
        } catch(EmptyTaskException e){
            ui.showException("EmptyTaskException");
        }
    }


    private static void parseDeleteCommand(String taskIndex, TaskList taskList) {
        try{
            int targetIndex = Integer.parseInt(taskIndex) + OFFSET;
            taskList.deleteTask(targetIndex);
        }catch(NumberFormatException e){
            ui.showException("NumberFormatException");
        }
    }

    private static void parseUnmarkCommand(String taskIndex, TaskList taskList) {
        try{
            int targetIndex = Integer.parseInt(taskIndex) + OFFSET;
            taskList.markTaskAsUndone(targetIndex);
        } catch(NumberFormatException e){
            ui.showException("NumberFormatException");
        }
    }

    private static void parseMarkCommand(String taskIndex, TaskList taskList) {
        try{
            int targetIndex = Integer.parseInt(taskIndex) + OFFSET;
            taskList.markTaskAsDone(targetIndex); //fix in task manager
        } catch (NumberFormatException e){
            ui.showException("NumberFormatException");
        }
    }

}
