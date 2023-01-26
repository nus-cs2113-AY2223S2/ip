import java.util.Scanner;

public class Limey {
    public static void main(String[] args) {

        Speech.sayHi();
        //Scan new line
        
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        while(!line.equals("bye")) {
            Speech.printEcho(line);
            line = in.nextLine();
        }

        Speech.sayBye();
    }

}

