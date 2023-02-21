package duke;

import duke.exceptions.*;
import duke.tools.Storage;
import duke.tools.UI;

import java.io.File;
import java.io.IOException;

public class Duke {

    private static UI ui = new UI();
    private static CommandManager commandManager = new CommandManager();
    private final static String UNKNOWN_COMMAND_MESSAGE = " OOPS!!! I'm sorry, but I don't know what that means :-(\n";
    private final static String EMPTY_CONTENT = "OOPS!!! The description of a task cannot be empty.\n";
    private final static String MISSING_PARAMETER = "Date/Timing is missing!!\n";
    private final static String DATA_ERROR = "Loading/Writing to file has error!\n";
    private final static String MISSING_DATA_FILE = "You data file is currently empty, please add in more content first!\n";
    private final static String DELETE_EMPTY_TASKS = "The list is empty, there is nothing to be deleted\n";
    private final static String EDIT_EMPTY_TASKS = "The list is empty, there is nothing to be updated\n";
    private final static String INDEX_OUT_OF_BOUND = "The element you are looking for does not exist!!\n";


    public static void startDuke() throws IOException{
        commandManager.sayHello();
        Storage.loadData();
    }

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
            }catch(DeleteEmptyTasks deleteEmptyTasks){
                ui.printError(DELETE_EMPTY_TASKS);
            }catch(IOException dataError){
                ui.printError(DATA_ERROR);
            }catch(MissingFileException missingFile){
                ui.printError(MISSING_DATA_FILE);
            }catch(EditEmptyTasks editEmptyTask){
                ui.printError(EDIT_EMPTY_TASKS);
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
