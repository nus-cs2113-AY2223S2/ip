import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String greeting = "Hello! I'm Duke\n" + "What can I do for you?\n";
        String exit = "Bye. Hope to see you again soon! \n";
        String border = "____________________________________________________________ \n";


        System.out.println(logo + border + greeting + border);

        Scanner input = new Scanner(System.in);
        String entry = input.next();

        while (!entry.equals("bye")){
            System.out.println(border + entry + "\n" + border);
            entry = input.next();
        }

        System.out.println(border + exit + border);
    }
}
