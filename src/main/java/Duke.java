import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void printList(List<String> l1) {
        int index;
        for (int i = 0; i < l1.size(); i += 1) {
            index = i + 1;
            System.out.println(index + ". " + l1.get(i));
        }
        System.out.println();
    }
    public static void main(String[] args) {
        /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);*/
        System.out.println("Hello! I'm a Robot");
        System.out.println("What can I do for you?\n");
        List<String> list = new ArrayList<String>();
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        while(!line.equals("bye")){
            if (line.equals("list")) {
                if (!list.isEmpty()){
                    System.out.println();
                    printList(list);
                } else {
                    System.out.println('\n' + "List is empty!" + '\n');
                }
            } else {
                System.out.println('\n' + "added: " + line + '\n');
                list.add(line);
            }
            line = in.nextLine();
        }
        System.out.println('\n' + "Bye. Hope to see you again soon!");
    }
}
