import java.util.Scanner;
public class CommandManager {
    private String command;

    public CommandManager() {
        this.command = " ";
    }

    public void readCommand(){
        Scanner in = new Scanner(System.in);
        String inputCommand;
        inputCommand = in.nextLine();
        this.command = inputCommand;
    }

    public void manageCommand(){
        Echo echo = new Echo();
        Bye bye = new Bye();

        while(this.command!="bye"){
            this.readCommand();
            echo.echoInput(this.command);
        }

        bye.printBye();

    }
}
