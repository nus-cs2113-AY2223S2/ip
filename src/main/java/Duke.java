import java.util.Scanner;
import java.util.Arrays;
public class Duke {

    public static void echo(String line) {
        String exitCommand = "bye";
        while (!line.equals(exitCommand)) {
            System.out.println(line);
            Scanner in = new Scanner(System.in);
            line = in.nextLine();
        }
        System.out.println("Bye! Hope to see you again");
    }
    
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hi! My name is Duke. \n Nice to meet you!");

        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        echo(line);
    }
}
