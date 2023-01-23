/**
 * Print logo and hello.
 * Private attributes are logo and hello,
 * Public method to print greeting.
 */
public class Greet {
    private String[] logo = {" ____        _        "
                    , "|  _ \\ _   _| | _____ "
                    , "| | | | | | | |/ / _ \\"
                    , "| |_| | |_| |   <  __/"
                    , "|____/ \\__,_|_|\\_\\___|"};

    private String[] hello = {"Hello! I'm Duke, your task assistant."
                    , "Nice to meet you :D"
                    , "What can I do for you?"};

    public void printHello(){
        Formatter formatter = new Formatter();
        Tool tool = new Tool();
        formatter.addStringIndentation(logo);
        formatter.addStringIndentation(hello);

        formatter.drawSeparationLine();
        formatter.printIndentation(6);
        System.out.print("Hello from\n");
        tool.printStringArray(logo);
        formatter.drawSeparationLine();
        tool.printStringArray(hello);
        formatter.drawSeparationLine();
    }
}
