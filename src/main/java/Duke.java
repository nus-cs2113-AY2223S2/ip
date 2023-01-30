import java.util.Objects;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public static void main(String[] args) {
        //String logo = " ____        _        \n"
        //        + "|  _ \\ _   _| | _____ \n"
        //        + "| | | | | | | |/ / _ \\\n"
        //        + "| |_| | |_| |   <  __/\n"
        //        + "|____/ \\__,_|_|\\_\\___|\n";
        //System.out.println("Hello from\n" + logo);
        String line; ArrayList<String> storage = new ArrayList<>();
        Scanner input = new Scanner(System.in);
        String space = "____________________________";

        //greet
        System.out.println(space + "\nHello! I'm Duke");
        System.out.println("What can I do for you?\n" + space);

        //echo
        boolean hi = true;
        while (hi){
            line = input.nextLine();
            switch(line) {
                case "bye" :
                    hi = false;
                    break;
                case "list" :
                    //list out storage
                    for (int i = 0; i < storage.size(); i++) {
                        int index = i + 1;
                        System.out.println(index + ". " + storage.get(i));
                    }
                    System.out.println(space);
                    break;
                default :
                    storage.add(line);
                    System.out.println("added: " + line + '\n' + space);
                    break;
            }
        }

        //exit
        System.out.println(space + "\nBye. Hope to see you again soon!\n" + space);
    }
}
