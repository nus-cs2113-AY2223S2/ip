import java.util.Locale;
import java.util.Scanner;
public class Arsdorint {
    static Scanner input = new Scanner(System.in);
    static String logo = "    ___                         _                                 _\n"
            + "   / _ \\     _____   _____  ___| |   ___    _____   _   _____   _| |_\n"
            + "  / /_\\ \\   /  ___| /  __/ /  _  |  / _ \\  /  ___| | | |  _  \\ |_   _|\n"
            + " / _____ \\  | /    __\\ \\   | |_| | | |_| | | /     | | | | | |   | |\n"
            + "/_/     \\_\\ |_|   /____/   \\_____|  \\___/  |_|     |_| |_| |_|   |_|\n";

    public static void echo(String command) {
        System.out.println("Arsdorint says: " + command);
    }

    public static String ask() {
        String ask;
        return ask = input.nextLine();
    }

    public static void main(String[] args) {
        //System.out.println("____________________________________________________________\n" +
                //" Hello! I'm Arsdorint\n" +
                //" What can I do for you?");
        String command = " ";
        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________\n" +
                " Hello! I'm Arsdorint, a member of Arsdorint Team\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n");
        while (true) {
            command = ask();
            if (command.toLowerCase().contains("bye")) break;
            echo(command);
        }

        System.out.println("Arsdorint says:" + " Bye. Hope to see you again soon!\n" +
                "____________________________________________________________\n");
    }
}
