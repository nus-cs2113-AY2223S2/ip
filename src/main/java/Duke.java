

public class Duke {

    public static void startDuke(){
        CommandManager greetingCommand = new CommandManager();
        greetingCommand.sayHello();
    }

    public static void useDuke(){
        UI readInputCommand = new UI();
        String inputCommand = readInputCommand.readInput();
        CommandManager commandManager = new CommandManager();
        commandManager.setCommand(inputCommand);
        commandManager.executeCommand();
    }

    public static void main(String[] args) {
        startDuke();
        useDuke();
    }
}
