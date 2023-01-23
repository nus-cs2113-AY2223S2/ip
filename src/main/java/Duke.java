import java.util.Scanner;
public class Duke {

    public static void line () {
        System.out.println("    =====================================================================================");
    }
    public static void greet () {
        line();
        System.out.println("    Hello! I'm Duke\n" + "    What can I do for you?\n");
        line();
    }
    public static void sayGoodbye() {
        line();
        System.out.println("    Bye. Hope to see you again soon!\n");
        line();
    }

    public static void echo(String echoWords){
        line();
        System.out.println("    " + echoWords + "\n");
        line();
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        greet();

        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();

        while(line.equals("bye") == false){
            echo(line);
            line = in.nextLine();
        }

        sayGoodbye();
    }

}