import java.util.Scanner;

public class Duke {

    private final static UI READ_COMMAND = new UI();
    private final static CommandManager COMMAND_MANAGER = new CommandManager();
    private final static String UNKNOWN_COMMAND_MESSAGE = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n";
    private final static String EMPTY_CONTENT = "☹ OOPS!!! The description of a task cannot be empty.\n";
    private final static String MISSING_PARAMETER = "☹ Date/Timing is missing!!\n";

    public static void startDuke(){
        COMMAND_MANAGER.sayHello();
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
            }finally{
                continue;
            }

        }
    }

    public static void main(String[] args) {
        startDuke();
        useDuke();
    }
}
