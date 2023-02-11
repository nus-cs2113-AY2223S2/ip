package duke;

import duke.exceptions.ContentEmptyException;
import duke.exceptions.MissingFileException;
import duke.exceptions.MissingParameterException;
import duke.exceptions.UnknownCommandException;
import duke.tools.UI;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Duke {

    private final static UI READ_COMMAND = new UI();
    private final static CommandManager COMMAND_MANAGER = new CommandManager();
    private final static String UNKNOWN_COMMAND_MESSAGE = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n";
    private final static String EMPTY_CONTENT = "☹ OOPS!!! The description of a task cannot be empty.\n";
    private final static String MISSING_PARAMETER = "☹ Date/Timing is missing!!\n";
    private final static String DATA_ERROR = "☹ Loading/Writing to file has error!\n";
    private final static String FILE_NOT_FOUND_ERROR = "☹ Your list is empty, please add tasks!!\n";

    public static void startDuke() throws IOException{
        COMMAND_MANAGER.sayHello();
        READ_COMMAND.loadData();
    }

    public static void useDuke(){
        while(true){
            try{
                String input = READ_COMMAND.readInput();
                COMMAND_MANAGER.setCommand(input);
                COMMAND_MANAGER.executeCommand();
            }catch(UnknownCommandException unknownCommand){
                READ_COMMAND.printError(UNKNOWN_COMMAND_MESSAGE);
            }catch(MissingParameterException missingParameter){
                READ_COMMAND.printError(MISSING_PARAMETER);
            }catch(ContentEmptyException emptyContent){
                READ_COMMAND.printError(EMPTY_CONTENT);
            }catch(IOException dataError){
                READ_COMMAND.printError(DATA_ERROR);
            }catch(MissingFileException fileNotFound){
                READ_COMMAND.printError(FILE_NOT_FOUND_ERROR);
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
