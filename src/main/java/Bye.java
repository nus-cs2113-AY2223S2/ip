/**
 * Print bye and exit code.CS
 */
public class Bye {
    private String bye = " Bye. Hope to see you again soon!\n";

    public void printBye(){
        Formatter formatter = new Formatter();
        formatter.drawSeparationLine();
        formatter.printIndentation(6);
        System.out.print(this.bye);
        formatter.drawSeparationLine();
    }
}
