
import java.util.Scanner;
public class Duke {
    public static void echoCommands(String input){
        System.out.println("\t____________________________________________________________\n\t "
                 + input + "\n\t____________________________________________________________\n");

    }
    public static void main(String[] args) {
        String divider = "\t____________________________________________________________\n";
        Scanner in = new Scanner(System.in);
        String line;
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.print(divider
                + " \t Hello! I'm Duke\n\t What can I do for you?\n"+ divider);
        line = in.nextLine();
        while (!line.equals("bye")) {
            echoCommands(line);
            line = in.nextLine();
            }

        System.out.print(divider +"\t Bye. Hope to see you again soon!\n" +divider);



    }
}
