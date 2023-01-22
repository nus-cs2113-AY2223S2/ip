import java.util.*;

public class Duke {
    public static void main(String[] args) {
        System.out.println("\tHello! I'm Duke");
        System.out.println("\tWhat can I do for you?\n");

        Scanner in = new Scanner(System.in);
        String line = in.nextLine();

        ArrayList<String> tasks = new ArrayList<String>();

        while (!line.equals("bye")) {
            switch (line) {
            case "list":
                if (tasks.size() > 0 ) {
                    for (int i = 0; i < tasks.size(); i += 1) {
                        System.out.printf("\t%d. %s\n", i + 1, tasks.get(i));
                    }
                }
                else {
                    System.out.println("\tThere is no tasks\n");
                }
                break;
            default:
                if (line.length() == 0) {
                    break;
                }
                else {
                    tasks.add(line);
                    System.out.printf("\tadded: %s\n", line);
                }
            }
            line = in.nextLine();
        }

        System.out.println("\tBye. Hope to see you again soon!");
    }
}
