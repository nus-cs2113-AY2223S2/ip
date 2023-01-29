
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Iterator;

public class Duke {
//    public static void addToList(String input) {
//        System.out.print("\t____________________________________________________________\n\t "
//                + "added: " + input + "\n\t____________________________________________________________\n");
//    }

    public static void main(String[] args) {
        String divider = "\t____________________________________________________________";
        Scanner in = new Scanner(System.in);
        ArrayList<String> toDoList = new ArrayList<>();
        String line;
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.print(divider + '\n'
                + " \t Hello! I'm Duke\n\t What can I do for you?\n" + divider + "\n\n");
        line = in.nextLine(); //obtain next input
        while (!line.equals("bye")) {
            if (line.equals("list")) { //list out to-do list
                Iterator<String> it = toDoList.iterator();
                int num = 1;
                System.out.println(divider);
                while (it.hasNext()) {
                    System.out.println("\t "+ num + "." + it.next());
                    ++num;
                }
                System.out.println(divider);
            } else {
                toDoList.add(line);
                System.out.println(divider + "\n\t " + line +'\n' + divider);
                //addToList(line);
            }
            line = in.nextLine();
        }

        System.out.println(divider + "\n\t Bye. Hope to see you again soon!\n" + divider);


    }
}
