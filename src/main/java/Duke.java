

public class Duke {


    public static void main(String[] args) {

        Greet hello = new Greet();
        CommandManager commandManager = new CommandManager();

        hello.printHello();
        commandManager.manageCommand();

    }
}
