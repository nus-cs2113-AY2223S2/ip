// Java program to read data of various types using Scanner class.
import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String welcomeMessage = "____________________________________________________________\n" +
                " Hello! I'm Duke, ready to serve you\n" +
                " What can I do for my master?\n" +
                "____________________________________________________________\n";
        System.out.println(welcomeMessage);

        // Declare the object and initialize with
        // predefined standard input object
        Scanner sc = new Scanner(System.in);

        // String input
        String input = "";

        // String data
        Task[] tasks = new Task[100];

        int counter = 1;

        while(true) {
            // Scan the input
            input = sc.nextLine();

            // Create Text Line
            String textLine = "\n___________________________******___________________________\n";

            // Print begin text line
            System.out.println(textLine);

            // Prints output
            if (input.equals("bye")) {
                String byeText = "Bye. Hope to see you again soon!";
                System.out.println(byeText);
                break;
            }
            else if (input.equals("list")){

                for (int index = 1; index < counter; index += 1){
                    System.out.println((index) + "." + "[" + tasks[index].getStatusIcon() + "]" + " " + tasks[index].getDescription());
                }
            }
            else if (input.substring(0, 4).equals("mark")) {
                if (input.length() <= 5) {
                    System.out.println("Please enter a valid number after space, e.g. mark 5");
                    continue;
                }
                String indexString = input.substring(5);
                indexString = indexString.replaceAll("\\s", "");
                int index = Integer.parseInt(indexString);
                if (index < counter && index != 0) {
                    if (tasks[index].isDone())  {
                        System.out.println("  It is already done silly! Do something else...");
                        continue;
                    }
                    else {
                        tasks[index].markAsDone();
                        System.out.println("  Nice! I've marked this task as done:\n"+"    [" +
                                tasks[index].getStatusIcon() + "]" + " " + tasks[index].getDescription());
                    }

                }
                else {
                    System.out.println("Please enter a valid number");
                    continue;
                }

            }
            else if (input.substring(0, 6).equals("unmark")) {
                if (input.length() <= 5) {
                    System.out.println("Please enter a valid number after space, e.g. mark 5");
                    continue;
                }
                String indexString = input.substring(7);
                indexString = indexString.replaceAll("\\s", "");
                int index = Integer.parseInt(indexString);
                if (index < counter && index != 0) {
                    if (!tasks[index].isDone())  {
                        System.out.println("  It is not done silly! Do it quickly!");
                        continue;
                    }
                    else {
                        tasks[index].markAsNotDone();
                        System.out.println("  Okay... whatever you say sir.... unmarked!\n"+"    [" +
                                tasks[index].getStatusIcon() + "]" + " " + tasks[index].getDescription());
                    }

                }
                else {
                    System.out.println("Please enter a valid number");
                    continue;
                }
            }
            else {
                Task t = new Task(input);
                tasks[counter] = t;
                counter += 1;
                System.out.println(input);
            }

            // Print end text line
            System.out.println(textLine);

        }




    }
}
