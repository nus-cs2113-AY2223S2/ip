public class Greet {
    private String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";

    private String hello = " Hello! I'm Duke :D\n"
                    + " What can I do for you?\n";

    public void printHello(){
        SeparationLine line = new SeparationLine(45);
        Indentation indentation = new Indentation();

        line.drawSeparationLine();
        indentation.printIndentation(6);
        System.out.print("Hello from\n" + this.logo);

        line.drawSeparationLine();
        indentation.printIndentation(6);
        System.out.print(this.hello);

        line.drawSeparationLine();
    }
}
