
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello I am Baymax you personal life assistant \n" + logo);
        System.out.println("What can I do for you today?");
        String line;
        Scanner in = new Scanner(System.in);

        while (true) {
            line = in.nextLine();
            if (line.equals("bye")) {
                System.out.println("Bye see you again!");
                break;
            }
            System.out.println(line);


        }


    }
}
