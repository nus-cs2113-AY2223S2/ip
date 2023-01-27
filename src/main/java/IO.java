import java.util.Scanner;

//Purpose of this class is to handle user inputs to Duke
// ">>" is merely for UI purposes to prompt users to type in their next command
//Easier than typing scanner scanner
public class IO {
    Scanner toRead;
    IO(){
        this.toRead = new Scanner(System.in);
    }
    public String readInput(){
        System.out.print(">> ");
        return toRead.nextLine();
    }

}
