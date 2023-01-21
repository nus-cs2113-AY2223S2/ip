public class Bye {
    private String bye = " Bye. Hope to see you again soon!\n";

    public void printBye(){
        SeparationLine line = new SeparationLine(45);
        Indentation indentation = new Indentation();

        line.drawSeparationLine();
        indentation.printIndentation(6);
        System.out.print(this.bye+'\n');

        line.drawSeparationLine();
    }
}
