import java.util.Scanner;
public class Echo {
    private String command;

    public void echoInput(String inputCommand){
        this.command = inputCommand;
        Formatter formatter = new Formatter();
        formatter.drawSeparationLine(45);
        formatter.printIndentation(6);
        System.out.print("Added: "+this.command+'\n');
        formatter.drawSeparationLine(45);
    }
}
