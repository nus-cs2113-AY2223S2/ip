import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public Duke() {

    }

    /*
    Main function that takes user input and interpets how to store and what to do with it
     */
    public static void main(String[] args) {

        ArrayList<String> userInputs = new ArrayList<>();
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner scan = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        greetUser();

        while (true) {
            Task tsk = null;
            String input = scan.nextLine();

            if (input.equals("bye")) {
                break;
            }
            if (input.contains("todo")) {
                tsk = new Todo(input, false);
            } else if (input.contains("event")) {
                tsk = parseEvent(input);
            } else if (input.contains("deadline")) {
                tsk = parseDeadline(input);
            }
            if (input.equals("list")) {
                listOut(userInputs, tasks);
            } else if (input.length() >= 4 && input.substring(0, 4).equals("mark")) {
                String[] tmpArr = input.split(" ");
                tasks.get(Integer.parseInt(tmpArr[1]) - 1).mark();
                System.out.println("\t____________________________________________________________");
                System.out.println("\tNice! I've marked this task as done:");
                System.out.println("\t  " + tasks.get(Integer.parseInt(tmpArr[1]) - 1));
                System.out.println("\t____________________________________________________________");

            } else if (input.length() >= 6 && input.substring(0, 6).equals("unmark")) {
                String[] tmpArr = input.split(" ");
                tasks.get(Integer.parseInt(tmpArr[1]) - 1).unMark();
                System.out.println("\t____________________________________________________________");
                System.out.println("\tNice! I've marked this task as done:");
                System.out.println("\t  " + tasks.get(Integer.parseInt(tmpArr[1]) - 1));
                System.out.println("\t____________________________________________________________");

            } else {
                addToList(input, userInputs);
                tasks.add(tsk);
                System.out.println("\t____________________________________________________________");
                System.out.println("\tGot it. I've added this task:");
                System.out.println("\t  " + tsk.toString());
                System.out.println("\tNow you have " + tasks.size() + " tasks in the list.");
                System.out.println("\t____________________________________________________________");

            }
        }
        exit();
    }
    /*
    This Returns the input as a Deadline object
     */
    public static Deadline parseDeadline(String input) {
        int idx = input.indexOf("/by");
        String desc = input.substring(8, idx);
        String by = input.substring(idx + 3);
        return new Deadline(desc, false, by);
    }
    /*
    This Returns the input as a Event object
     */
    public static Event parseEvent(String input) {
        int idx = input.indexOf("/from");
        int idx1 = input.indexOf("/to");
        String desc = input.substring(5, idx);
        String start = input.substring(idx + 5, idx1);
        String end = input.substring(idx1 + 3, input.length());

        return new Event(desc, false, start, end);
    }
    /*
    This Adds the input to an input array for the ability to keep track of
     */
    public static void addToList(String cmd, ArrayList<String> userInputs) {
        userInputs.add(cmd);
        userInputs.set(userInputs.size() - 1, userInputs.size() + ". [ ] " + userInputs.get(userInputs.size() - 1));
    }
    /*
    This method lists out the tasks in order
    */
    public static void listOut(ArrayList<String> userInputs, ArrayList<Task> tasks) {
        System.out.println("\t____________________________________________________________");
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("\t" + (i + 1) + "." + tasks.get(i));
        }
        System.out.println("\t____________________________________________________________");
    }
    /*
    Automated greet function
    */
    public static void greetUser() {
        System.out.println("\t____________________________________________________________");
        System.out.println("\tHello! I'm Duke");
        System.out.println("\tWhat can I do for you?");
        System.out.println("\t____________________________________________________________");
    }
    /*
    Exit message
    */
    public static void exit() {
        System.out.println("\t____________________________________________________________");
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println("\t____________________________________________________________");

    }

}
