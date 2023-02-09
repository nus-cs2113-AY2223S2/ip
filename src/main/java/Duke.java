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
            String[] splitInput = input.split(" ");

            switch (splitInput[0]) {
            case "bye":
                exit();
                return;
            case "todo":
                try {                
                    tsk = new Todo(splitInput[1], false);
                    addToList(input, userInputs);
                    tasks.add(tsk);
                    addTaskPrint(tasks, tsk);
                } catch (IndexOutOfBoundsException de) {
                    printExceptionMsg("todo", "description of a todo cannot be empty.");
                } catch (DukeException de) {

                }
                

                break;               
            case "event":
                tsk = parseEvent(input);
                addToList(input, userInputs);
                tasks.add(tsk);
                addTaskPrint(tasks, tsk);
                break;
            case "deadline": 
                tsk = parseDeadline(input);
                addToList(input, userInputs);
                tasks.add(tsk);
                addTaskPrint(tasks, tsk);
                break;
            case "list": 
                listOut(userInputs, tasks);
                break;
            case "mark":
                tasks.get(Integer.parseInt(splitInput[1]) - 1).mark();
                System.out.println("\t____________________________________________________________");
                System.out.println("\tNice! I've marked this task as done:");
                System.out.println("\t  " + tasks.get(Integer.parseInt(splitInput[1]) - 1));
                System.out.println("\t____________________________________________________________"); 
                break;
            case "unmark":                
                tasks.get(Integer.parseInt(splitInput[1]) - 1).unMark();
                System.out.println("\t____________________________________________________________");
                System.out.println("\tOK, I've marked this task as not done yet:");
                System.out.println("\t  " + tasks.get(Integer.parseInt(splitInput[1]) - 1));
                System.out.println("\t____________________________________________________________");
                break;
            default:
                System.out.println("\t____________________________________________________________");
                System.out.println(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");;
                System.out.println("\t____________________________________________________________");

                break;
            }                            
        }
    }
    public static void printExceptionMsg(String task, String err) {
        System.out.println("\t____________________________________________________________");
        System.out.println(" ☹ OOPS!!! The description of a todo cannot be empty.");
        System.out.println("\t____________________________________________________________");
    }
    public static void addTaskPrint(ArrayList<Task> tasks, Task tsk) {
        System.out.println("\t____________________________________________________________");
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t  " + tsk.toString());
        System.out.println("\tNow you have " + tasks.size() + " tasks in the list.");
        System.out.println("\t____________________________________________________________");
    }
    /*
    This Returns the input as a Deadline object
     */
    public static Deadline parseDeadline(String input) {
        int idx = input.indexOf("/by");
        String desc = input.substring(8, idx);
        String by = input.substring(idx + 3);
        Deadline tsk = null;
        try {
            tsk = new Deadline(desc, false, by);
        } catch (DukeException de) {

        }
        return tsk;
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
        Event tsk = null;
        try {
            tsk = new Event(desc, false, start, end);
        } catch (DukeException de) {

        }
        return tsk;
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
