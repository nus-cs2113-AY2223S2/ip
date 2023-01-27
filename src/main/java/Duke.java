import java.util.Objects;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        //String logo = " ____        _        \n"
        //        + "|  _ \\ _   _| | _____ \n"
        //        + "| | | | | | | |/ / _ \\\n"
        //        + "| |_| | |_| |   <  __/\n"
        //        + "|____/ \\__,_|_|\\_\\___|\n";
        //System.out.println("Hello from\n" + logo);
        String line;
        Scanner input = new Scanner(System.in);
        String space = "____________________________";

        //greet
        System.out.println(space + "\nHello! I'm Duke");
        System.out.println("What can I do for you?\n" + space);

        //echo
        while (true){
            line = input.nextLine();
            if (Objects.equals(line, "bye")){
                break;
            }
            System.out.println(line + '\n' + space);
        }

        //exit
        System.out.println(space + "\nBye. Hope to see you again soon!\n" + space);
    }
}
