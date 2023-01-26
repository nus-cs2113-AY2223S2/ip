import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        System.out.println("\tHello! I'm Duke");
        System.out.println("\tWhat can I do for you?\n");

        Scanner in = new Scanner(System.in);
        String line = in.nextLine();

        ArrayList<Task> tasks = new ArrayList<Task>();

        while (!line.equals("bye")) {
            if (line.length() == 0) {
                line = in.nextLine();
                continue;
            }
            String[] wordList = line.split(" ");
            String command = wordList[0];
            switch (command) {
            case "list":
                if (wordList.length != 1) {
                    System.out.println("\t wrong number of arguments for list");
                    break;
                }
                if (tasks.size() > 0) {
                    for (int i = 0; i < tasks.size(); i += 1) {
                        System.out.printf("\t%d.[%s] %s\n", i + 1, tasks.get(i).getStatusIcon(), tasks.get(i).getDescription());
                    }
                } else {
                    System.out.println("\tThere is no tasks");
                }
                break;
            case "unmark":
                try {
                    if (wordList.length != 2) {
                        System.out.println("\tWrong number of arguments for unmark");
                        line = in.nextLine();
                        continue;
                    }
                    int index = Integer.parseInt(wordList[1]) - 1;
                    tasks.get(index).setDone(false);
                    System.out.println("\tOK, I've marked this task as not done yet:");
                    System.out.printf("\t[ ] %s\n", tasks.get(index).getDescription());
                } catch (Exception exception) {
                    System.out.println(exception);
                }
                break;
            case "mark":
                try {
                    if (wordList.length != 2) {
                        System.out.println("\tWrong number of arguments for mark");
                        line = in.nextLine();
                        continue;
                    }
                    int index = Integer.parseInt(wordList[1]) - 1;
                    tasks.get(index).setDone(true);
                    System.out.println("\tNice! I've marked this task as done:");
                    System.out.printf("\t[X] %s\n", tasks.get(index).getDescription());

                } catch (Exception exception) {
                    System.out.println(exception);
                }
                break;
            default:
                tasks.add(new Task(line));
                System.out.printf("\tadded: %s\n", line);
            }
            line = in.nextLine();
        }
        System.out.println("\tBye. Hope to see you again soon!");
    }
}
