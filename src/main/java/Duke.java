

public class Duke {

    public static void startDuke(){
        CommandManager greetingCommand = new CommandManager();
        greetingCommand.sayHello();
    }

    public static void useDuke(){
        UI readInputCommand = new UI();
        String inputCommand = readInputCommand.readInput();
        CommandManager processCommand = new CommandManager();
        processCommand.setCommand(inputCommand);
        processCommand.executeCommand();
    }

    public static void main(String[] args) {
        startDuke();
        useDuke();

    }
}
