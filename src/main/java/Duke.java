import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String line;
        Scanner in = new Scanner(System.in);

        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Duke");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");

        Task[] list = new Task[100];
        int nextEmptyIndex = 0;

        boolean done = false;

        while (!done) {
            line = in.nextLine();
            if (line.equals("bye")) {
                done = true;
            } else if (line.equals("list")) {
                System.out.println("____________________________________________________________");
                for (int i = 0; i < nextEmptyIndex; i++) {
                    String prefix = (i+1) + ".[" + (list[i].getIsDone() ? "X" : " ") + "] ";
                    System.out.println(prefix + list[i].getDesc());
                }
                System.out.println("____________________________________________________________");
            } else if (line.split(" ")[0].equals("mark")) {
                int index = Integer.parseInt(line.split(" ")[1])-1;
                list[index].setIsDone(true);
                System.out.println("____________________________________________________________");
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("[X] " + list[index].getDesc());
                System.out.println("____________________________________________________________");
            } else if (line.split(" ")[0].equals("unmark")) {
                int index = Integer.parseInt(line.split(" ")[1])-1;
                list[index].setIsDone(false);
                System.out.println("____________________________________________________________");
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("[ ] " + list[index].getDesc());
                System.out.println("____________________________________________________________");
            } else {
                list[nextEmptyIndex] = new Task(line);
                nextEmptyIndex++;
                System.out.println("____________________________________________________________");
                System.out.println("added: " + line);
                System.out.println("____________________________________________________________");
            }
        }

        System.out.println("____________________________________________________________");
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}
