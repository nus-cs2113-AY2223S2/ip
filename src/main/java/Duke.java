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
        String line;
        ArrayList<Task> storage = new ArrayList<>();
        int words;
        Scanner input = new Scanner(System.in);
        String space = "____________________________";

        //greet
        System.out.println(space + "\nHello! I'm Duke");
        System.out.println("What can I do for you?\n" + space);

        //echo
        boolean hi = true;
        while (hi) {
            line = input.nextLine();
            words = line.split("//s").length;
            if (line.equals("bye")) {
                hi = false;
            } else if (line.equals("list")) {
                //list out storage
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < storage.size(); i++) {
                    int index = i + 1;
                    System.out.println(index + ". " + storage.get(i).isDone() + storage.get(i).description);
                }
                System.out.println(space);
            } else if (line.startsWith("mark")) {
                int x = Integer.parseInt(line.substring(5));
                storage.get(x-1).markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(storage.get(x-1).isDone() + storage.get(x-1).description + '\n' + space);
            } else {
                Task task = new Task(line);
                storage.add(task);
                System.out.println("added: " + line + '\n' + space);
            }
        }
        //exit
        System.out.println(space +"\nBye. Hope to see you again soon!\n"+space);
    }
}
