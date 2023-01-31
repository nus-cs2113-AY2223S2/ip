import java.util.Scanner;

public class Duke {
    public static final int LENGTH_DEADLINE = 8;
    public static final int LENGTH_TODO = 4;
    public static final int LENGTH_EVENT = 5;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("--------------------------------");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("--------------------------------");
        Task[] lists = new Task[100];
        int index = 0;
        while (true) {
            String line = in.nextLine();
            if (line.equals("bye")) {
                System.out.println("--------------------------------");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("--------------------------------");
                break;
            } else if (line.substring(0, 4).equals("mark")) {
                String number = line.substring(5, 6);
                int markIndex = Integer.parseInt(number);
                lists[markIndex - 1].isDone = true;
            } else if (line.contains("unmark")) {
                String number = line.substring(7, 8);
                int unMarkIndex = Integer.parseInt(number);
                lists[unMarkIndex - 1].isDone = false;
            } else if (line.equals("list")) {
                System.out.println("list:");
                for (int i = 0; i < index; i++) {
                    System.out.println("\t" + (i + 1) + "." + lists[i].toString());
                }
            } else {
                if (line.substring(0, LENGTH_TODO).equals("todo")) {
                    lists[index] = new Todo(line.substring(LENGTH_TODO + 1));
                    index++;
                } else if (line.substring(0, LENGTH_DEADLINE).equals("deadline")) {
                    int breakingPoint = line.indexOf("/by");
                    String description = line.substring(LENGTH_DEADLINE + 1, breakingPoint);
                    String by = line.substring(breakingPoint + 4);
                    lists[index] = new Deadline(description, by);
                    index++;
                } else if (line.substring(0, LENGTH_EVENT).equals("event")) {
                    int breakingPoint_1 = line.indexOf("/from");
                    int breakingPoint_2 = line.indexOf("/to");
                    String description = line.substring(LENGTH_EVENT + 1, breakingPoint_1);
                    String start = line.substring(breakingPoint_1 + 6, breakingPoint_2);
                    String end = line.substring(breakingPoint_2 + 4);
                    lists[index] = new Event(description, start, end);
                    index++;
                }

            }

        }
    }
}
