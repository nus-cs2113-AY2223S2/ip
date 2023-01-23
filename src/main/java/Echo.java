import java.util.Scanner;
public class Echo {
    private String command;

    /**
     * Echo back the newly added task/input command to users.
     *
     * @param inputCommand new task.
     */
    public void echoInput(String inputCommand){
        this.command = inputCommand;
        Formatter formatter = new Formatter();
        formatter.drawSeparationLine();
        formatter.printIndentation(6);
        System.out.print("Added: "+this.command+'\n');
        formatter.drawSeparationLine();
    }
}
