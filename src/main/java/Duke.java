import java.util.Scanner;
import java.util.Arrays;

public class Duke {
    public static void main(String[] args) {
        String logo = "Hello! I'm Duke\n" + "What can I do for you?\n";
        System.out.println(logo);
        Scanner in = new Scanner(System.in);
        String line;

        Task[] todo = new Task[100];
        int i = 1;
        String[] Split = new String[2];
        while (true) {
            line = in.nextLine();
            Split = line.split(" ");
            Task task = new Task(line);

            if (line.equals("list")) {

                for (int j = 1; j < i; j++) {
                    System.out.println((j) + "." + "[" + todo[j].getStatusIcon() + "] " + todo[j].description);
                    //System.out.println(todo[j].isDone);
                }
            } else if (line.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                return;
            } else if (Split[0].equals("mark")) {
//                System.out.println(" 1 ");
//                System.out.println("Integer.parseInt(Split[1]) = "+ Integer.parseInt(Split[1]));
                todo[Integer.parseInt(Split[1])].setIsDone();
                System.out.println("Nice! I've marked this task as done:\n");
                System.out.println("[" + todo[Integer.parseInt(Split[1])].getStatusIcon() + "]" + todo[Integer.parseInt(Split[1])].getDescription());
            } else if (Split[0].equals("unmark")) {
//                System.out.println(" 1 ");
//                System.out.println("Integer.parseInt(Split[1]) = "+ Integer.parseInt(Split[1]));
                todo[Integer.parseInt(Split[1])].setasnotDone();
                System.out.println("Never-mind!! Unmarking\n");
                System.out.println("[" + todo[Integer.parseInt(Split[1])].getStatusIcon() + "]" + todo[Integer.parseInt(Split[1])].getDescription());
            } else {
                todo[i] = task;
                i++;
                System.out.println("added:" + line);
            }


        }
    }
}
