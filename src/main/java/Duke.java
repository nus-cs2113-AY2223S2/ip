import java.util.Scanner;

public class Duke {

    public static void startDuke(){
        CommandManager greetingCommand = new CommandManager();
        greetingCommand.sayHello();
    }

    public static void useDuke(){
        UI readInputCommand = new UI();
        CommandManager commandManager = new CommandManager();
        //Only use one scanner object, otherwise there will be errors
        Scanner in = new Scanner(System.in);

        while(true){
            String input = readInputCommand.readInput(in);
            commandManager.setCommand(input);
            commandManager.executeCommand();
        }
    }

    public static void main(String[] args) {
        startDuke();
        useDuke();
    }
}
