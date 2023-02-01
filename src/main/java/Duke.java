import java.util.Objects;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public static void getType(String s){

    }

    public static void main(String[] args) {
        //String logo = " ____        _        \n"
        //        + "|  _ \\ _   _| | _____ \n"
        //        + "| | | | | | | |/ / _ \\\n"
        //        + "| |_| | |_| |   <  __/\n"
        //        + "|____/ \\__,_|_|\\_\\___|\n";
        //System.out.println("Hello from\n" + logo);
        String line;
        ArrayList<Task> storage = new ArrayList<>();
        int words; int x; int taskcounter = 0;
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
            String arr[] = line.split(" ", 2);
            switch(arr[0]){
                case "bye" :
                    hi = false;
                    break;
                case "list" :
                    //list out storage
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < storage.size(); i++) {
                        int index = i + 1;
                        System.out.println(index + ". " + storage.get(i));
                    }
                    System.out.println(space);
                    break;
                case "mark" :
                    x = Integer.parseInt(line.substring(5));
                    storage.get(x-1).markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(storage.get(x-1).toString() + '\n' + space);
                    break;
                case "unmark" :
                    x = Integer.parseInt(line.substring(7));
                    storage.get(x-1).undo();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(storage.get(x-1).toString() + '\n' + space);
                    break;
                case "deadline" :
                    taskcounter++;
                    Deadline dl = new Deadline(line);
                    storage.add(dl);
                    System.out.println("Got it. I've added this task:\n" + dl);
                    System.out.println("Now you have " + taskcounter + " tasks in the list.\n" + space);
                    break;
                case "todo" :
                    taskcounter++;
                    Todo todo = new Todo(line);
                    storage.add(todo);
                    System.out.println("Got it. I've added this task:\n" + todo);
                    System.out.println("Now you have " + taskcounter + " tasks in the list.\n" + space);
                    break;
                default :
                    taskcounter++;
                    Event event = new Event(line);
                    storage.add(event);
                    System.out.println("Got it. I've added this task:\n" + event);
                    System.out.println("Now you have " + taskcounter + " tasks in the list.\n" + space);
                    break;
            }
        }
        //exit
        System.out.println(space +"\nBye. Hope to see you again soon!\n"+space);
    }
}
