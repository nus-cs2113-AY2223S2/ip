import java.util.Scanner;
public class CommandManager {
    private String command;

    public CommandManager() {
        this.command = " ";
    }

    public String readCommand(Scanner in){
        String inputCommand;
        inputCommand = in.nextLine();
        this.command = inputCommand;
        return inputCommand;
    }

    public void manageCommand(){
        Echo echo = new Echo();
        Bye bye = new Bye();
        Scanner in = new Scanner(System.in);

        while(!this.readCommand(in).equals("bye")){
            echo.echoInput(this.command);
        }
        bye.printBye();

    }
}
