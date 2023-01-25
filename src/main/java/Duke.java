import java.util.Objects;
import java.util.Scanner;
public class Duke {
    static Task[] list = new Task[100];
    static int listId = 0;
    public static void greet() {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Duke  U ´ᴥ` U\n" + "What can I do for you?");
        System.out.println("____________________________________________________________");
    }
    public static void goodBye() {
        System.out.println("Bye. Hope to see you again soon!ﾉ~");
        System.out.println("____________________________________________________________");
    }
    public static void echo() {
        Scanner scan = new Scanner(System.in);
        String s;
        s = scan.nextLine();
        if (s.equals("bye")) {
            goodBye();
            return;
        }
        if (s.equals("list")) {
            for (int i = 0; i < listId; i += 1) {
                System.out.print(i+1);
                System.out.println(". [" + list[i].getStatusIcon() + "] " + list[i].description);
            }
            System.out.println("____________________________________________________________");
        } else if (s.equals("mark")) {
            Scanner id = new Scanner(System.in);
            System.out.println("Please enter the index of the task you want to mark: ");
            int markId = id.nextInt()-1;
            if (list[markId].isDone) {
                System.out.println("This task has already been marked as done ੯•໒꒱❤︎");
                System.out.println("____________________________________________________________");
            } else {
                list[markId].markAsDone();
                System.out.println("I've marked this task as done ੯•໒꒱❤︎:");
                System.out.println("[X] " + list[markId].description);
                System.out.println("____________________________________________________________");
            }
        } else if (s.equals("unmark")) {
            Scanner id = new Scanner(System.in);
            System.out.println("Please enter the index of the task you want to unmark: ");
            int markId = id.nextInt()-1;
            if (list[markId].isDone == false) {
                System.out.println("This task hasn't been marked as done yet ∪･ω･∪");
                System.out.println("____________________________________________________________");
            } else {
                list[markId].markAsNotDone();
                System.out.println("I've unmarked this task ∪･ω･∪:");
                System.out.println("[ ] " + list[markId].description);
                System.out.println("____________________________________________________________");
            }
        }
        else {
            Task t = new Task(s);
            list[listId] = t;
            System.out.println("____________________________________________________________");
            System.out.println("added: " + s);
            System.out.println("____________________________________________________________");
            listId += 1;
        }
        echo();
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        greet();
        echo();
    }
}
