/**
 * To print logo and hello
 * Private attributes are logo and hello, public method to print greeting
 */
public class Greet {
    private String[] logo = {" ____        _        \n"
                    , "|  _ \\ _   _| | _____ \n"
                    , "| | | | | | | |/ / _ \\\n"
                    , "| |_| | |_| |   <  __/\n"
                    , "|____/ \\__,_|_|\\_\\___|\n"};

    private String[] hello = {"Hello! I'm Duke, your task assistant.\n"
                    , "Nice to meet you :D"
                    , "What can I do for you?\n"};

    public void printHello(){
        Formatter formatter = new Formatter();
        Tool tool = new Tool();
        formatter.addIndentation(logo);
        formatter.addIndentation(hello);

        formatter.drawSeparationLine(45);
        formatter.printIndentation(6);
        System.out.print("Hello from\n");
        tool.printStringArray(logo);
        formatter.drawSeparationLine(45);
        tool.printStringArray(hello);
        formatter.drawSeparationLine(45);
    }
}
