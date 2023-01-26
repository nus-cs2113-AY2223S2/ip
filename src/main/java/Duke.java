import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String line;
        Scanner in = new Scanner(System.in);
        System.out.println("____________________________________________________________\n" +
                " Hello! I'm Duke! \n" +
                " Enter \"list\" to see all tasks \n" +
                " Enter \"mark [idx]\" to mark task as done \n" +
                " Enter \"unmark [idx]\" to mark task as not done \n" +
                " Enter \"bye\" to exit the program \n" +
                "____________________________________________________________\n");
        Task[] store = new Task[100];
        int counter = 0;
        while (true) {
            line = in.nextLine();
            if (line.equals("bye")) {
                // exit program
                System.out.println("____________________________________________________________\n" +
                        " Bye. Hope to see you again soon!\n" +
                        "____________________________________________________________");
                break;

            } else if (line.equals("list")) {
                // show tasks stored by user
                System.out.println("____________________________________________________________\n" +
                        "Here are the tasks in your list: ");
                for (int i = 0; i < counter; i++) {
                    System.out.println(i+1 + ".[" + store[i].getStatusIcon() + "] " + store[i].getDescription());
                }
                System.out.println("____________________________________________________________\n");

            } else if (line.startsWith("mark ")) {
                // mark task as done
                try {
                    int idx = Integer.parseInt(line.substring(5)) - 1;
                    if (idx < 0 || idx >= counter) {
                        System.out.println("____________________________________________________________\n" +
                                "Please enter [idx] in the form of an integer from 1 to " + counter + ". \n" +
                                "____________________________________________________________\n");
                        continue;
                    }
                    store[idx].setDone();
                    System.out.println("____________________________________________________________\n" +
                            "Nice!, I've marked this task as done:\n" +
                            "[X] " + store[idx].getDescription() + "\n" +
                            "____________________________________________________________\n");
                } catch (NumberFormatException ex) {
                    System.out.println("____________________________________________________________\n" +
                            "Please enter [idx] in the form of an integer from 1 to " + counter + ". \n" +
                            "____________________________________________________________\n");
                }

            } else if (line.startsWith("unmark ")) {
                // mark task as NOT done
                try {
                    int idx = Integer.parseInt(line.substring(7)) - 1;
                    if (idx < 0 || idx >= counter) {
                        System.out.println("____________________________________________________________\n" +
                                "Please enter [idx] in the form of an integer from 1 to " + counter + ". \n" +
                                "____________________________________________________________\n");
                        continue;
                    }
                    store[idx].setNotDone();
                    System.out.println("____________________________________________________________\n" +
                            "OK, I've marked this task as not done yet:\n" +
                            "[ ] " + store[idx].getDescription() + "\n" +
                            "____________________________________________________________\n");
                } catch (NumberFormatException ex) {
                    System.out.println("____________________________________________________________\n" +
                            "Please enter [idx] in the form of an integer from 1 to " + counter + ". \n" +
                            "____________________________________________________________\n");
                }

            } else {
                // store new task
                if (counter == 100) {
                    System.out.println("____________________________________________________________\n" +
                            "Maximum number of tasks reached. \n" +
                            "____________________________________________________________\n");
                    continue;
                }
                store[counter] = new Task(line);
                counter++;
                System.out.println("____________________________________________________________\n" +
                        "added: " + line + "\n" +
                        "____________________________________________________________\n");
            }
        }
    }
}
