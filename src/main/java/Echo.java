import java.util.Scanner;
public class Echo {
    private String command;

    public Echo() {

    }

    public void echoInput(String inputCommand){
        this.command = inputCommand;
        SeparationLine line = new SeparationLine(45);
        Indentation indentation = new Indentation();

        line.drawSeparationLine();
        indentation.printIndentation(6);
        System.out.print(this.command+'\n');
        line.drawSeparationLine();
    }
}
