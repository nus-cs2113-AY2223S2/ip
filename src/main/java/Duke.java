import java.util.Scanner;

public class Duke {

    private final static UI READ_COMMAND = new UI();
    private final static CommandManager COMMAND_MANAGER = new CommandManager();
    private final static String UNKNOWN_COMMAND_MESSAGE = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n";
    private final static String EMPTY_CONTENT = "☹ OOPS!!! The description of a todo cannot be empty.\n";

    public static void startDuke(){
        COMMAND_MANAGER.sayHello();
    }

    public static void useDuke(){
        while(true){
            try{
                String input = READ_COMMAND.readInput();
                COMMAND_MANAGER.setCommand(input);
                COMMAND_MANAGER.executeCommand();
            }
            catch(UnknownCommandException unknownCommand){
                READ_COMMAND.printError(UNKNOWN_COMMAND_MESSAGE);
            }
            catch(ContentEmptyException emptyContent){
                System.out.println(EMPTY_CONTENT);
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
