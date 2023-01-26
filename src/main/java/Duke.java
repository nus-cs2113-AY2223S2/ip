import java.util.Locale;
import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("what can i do for you");
        String input = myObj.nextLine();
        while(!input.equalsIgnoreCase("bye"))
        {
            System.out.println(input);
            input = myObj.nextLine();
        }
        System.out.println("Bye! see you soon!");
    }
}
