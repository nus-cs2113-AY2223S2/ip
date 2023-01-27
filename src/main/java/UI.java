import java.util.Scanner;
public class UI {
    public void printOutput(String[] captions, String[] tasks){
        Formatter formatter = new Formatter();
        Tool tool = new Tool();
        formatter.drawSeparationLine();
        tool.printStringArray(captions);
        tool.printStringArray(tasks);
        formatter.drawSeparationLine();
    }

    public String readInput(){
        Scanner in = new Scanner(System.in);
        String inputCommand = in.nextLine();
        return inputCommand;
    }


}
