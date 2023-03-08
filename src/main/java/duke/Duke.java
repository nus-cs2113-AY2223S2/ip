package duke;

import duke.exceptions.*;
import duke.tools.Storage;
import duke.tools.UI;

import java.io.File;
import java.io.IOException;

/**
 * Keep track of users' tasks.
 * Allow users to add/delete/update/find tasks.
 */
public class Duke {

    private static UI ui = new UI();
    private static CommandManager commandManager = new CommandManager();
    private final static String UNKNOWN_COMMAND_MESSAGE = " OOPS!!! I'm sorry, but I don't know what that means :-(\n";
    private final static String EMPTY_CONTENT = "OOPS!!! The parameters cannot be empty.\n";
    private final static String MISSING_PARAMETER = "Date/Timing is missing!!\n";
    private final static String DATA_ERROR = "Loading/Writing to file has error!\n";
    private final static String MISSING_DATA_FILE = "You data file is currently empty, please add in more content first!\n";
    private final static String DELETE_EMPTY_TASKS = "The list is empty, there is nothing to be deleted\n";
    private final static String EDIT_EMPTY_TASKS = "The list is empty, there is nothing to be updated\n";
    private final static String INDEX_OUT_OF_BOUND = "The element you are looking for does not exist!!\n";
    private final static String WRONG_DATE_FORMAT = "Please follow date format YYYY-MM-DD!!\n";
    private final static String WRONG_INDEX_FORMAT = "Please enter an integer for index!!\n";

    public static void startDuke() throws IOException{
        commandManager.sayHello();
        Storage.loadData();
    }

    /**
     * Constantly listen to users input, execute the commands, and print relevant output, until exit command.
     * Catch possible errors that might arise in the process.
     */
    public static void useDuke(){
        while(true){
            try{
                String input = ui.readInput();
                commandManager.setCommand(input);
                commandManager.executeCommand();
            }catch(UnknownCommandException unknownCommand){
                ui.printError(UNKNOWN_COMMAND_MESSAGE);
            }catch(MissingParameterException missingParameter){
                ui.printError(MISSING_PARAMETER);
            }catch(CommandDescriptionEmptyException emptyContent){
                ui.printError(EMPTY_CONTENT);
            }catch(DeleteIndexOutOfBound indexOutOfBound){
                ui.printError(INDEX_OUT_OF_BOUND);
            }catch(UpdateOutOfBound outOfBound){
                ui.printError(INDEX_OUT_OF_BOUND);
            }catch(DeleteEmptyTasks deleteEmptyTasks){
                ui.printError(DELETE_EMPTY_TASKS);
            }catch(IOException dataError){
                ui.printError(DATA_ERROR);
            }catch(MissingFileException missingFile){
                ui.printError(MISSING_DATA_FILE);
            }catch(EditEmptyTasks editEmptyTask){
                ui.printError(EDIT_EMPTY_TASKS);
            }catch(WrongDateFormat wrongDateFormat){
                ui.printError(WRONG_DATE_FORMAT);
            }catch(NumberFormatException formatException){
                ui.printError(WRONG_INDEX_FORMAT);
            }finally{
                continue;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        startDuke();
        useDuke();
    }
}
