import allexceptions.*;
import alltasks.*;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String arguments[]) {
        ArrayList<Task> list_Items = new ArrayList<>(); // store the tasks (C++ vector)

        int counter = 0; // counts the number of tasks in the list
        int index = 0; // this is used for index when mark / unmark
        System.out.println("Hi there! My name is Coffee");
        System.out.println("How can I help you today?");

        Task task_Array[];
        task_Array = new Task[110];
        boolean isRunning = true;

        while (isRunning) {
            Scanner command = new Scanner(System.in);
            String input_Command = command.nextLine();
            // e.g. todo borrow book
            // e.g. delete 3
            // e.g. mark 1
            String[] first_Word_Array;
            first_Word_Array = input_Command.split(" ", 2);
            // first_Word_Array[0, 1] is now ["todo", "borrow book"]
            // first_Word_Array[0, 1] is now ["delete", "3"]
            // first_Word_Array[0, 1] is now ["mark", "1"]
            String first_Word = first_Word_Array[0]; // e.g. todo or delete
            String[] tokens;

            switch (first_Word) {

            case "todo": // e.g. todo borrow book
                try {
                    if (first_Word_Array.length == 1) { 
                        throw new todoMissingException();
                    }
                    // input_Command is "todo borrow book"
                    Todo todo_Word = new Todo(first_Word_Array[1]);
                    // todo_Word is "borrow book"
                    //list_Items.add(counter, todo_Word);
                    //list_Items.add(counter, new Task(input_Command)); // [X] todo borrow book
                    //list_Items.add(counter, new Task(first_Word_Array[1])); // [X] borrow book
                    list_Items.add(counter, todo_Word);
                    //task_Array[counter] = todo_Word;
                    counter = counter + 1;
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + todo_Word); // e.g. borrow book
                    System.out.println("Now you have " + counter + " tasks in the list.");
                    //task_Array[counter] = new Task(input_Command);
                    //list_Items.add(counter, new Task(input_Command));
                    //System.out.println(list_Items.get(counter - 1)); // counter = 0
                    // System.out.println(list_Items.get(counter)); // counter == 1
                    } catch (todoMissingException exception){
                     System.out.println("OOPS!!! The description of a todo cannot be empty.");
                }
                break;

            case "deadline":
                String [] get_Weekday;
                get_Weekday = first_Word_Array[1].split("/by", 2);
                Deadline deadline_Word = new Deadline(get_Weekday[0]);
                list_Items.add(counter, deadline_Word);
                //task_Array[counter] = deadline_Word;
                counter = counter + 1;
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + deadline_Word + " (by:" + get_Weekday[1] + ")");
                System.out.println("Now you have " + counter + " tasks in the list.");
                //task_Array[counter] = new Task(input_Command);
                list_Items.add(counter, new Task(input_Command));
                //System.out.println("added: " + input_Command);
                break;

            case "event":
                String[] get_Meeting;
                get_Meeting = first_Word_Array[1].split("/", 3);
                String[] meeting_From;
                String[] meeting_To;
                meeting_From = get_Meeting[1].split(" ", 2);
                meeting_To = get_Meeting[2].split(" ", 2);
                Event meeting_Type = new Event(get_Meeting[0]);
                list_Items.add(counter, meeting_Type);
                //task_Array[counter] = meeting_Type;
                counter = counter + 1;
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + meeting_Type + "(from: " + meeting_From[1] + "to: " + meeting_To[1] + ")");
                System.out.println("Now you have " + counter + " tasks in the list.");
                //task_Array[counter] = new Task(input_Command);
                list_Items.add(counter, new Task(input_Command));
                //System.out.println("added: " + input_Command);
                //counter = counter + 1;
                break;

            case "mark": // e.g. mark 2
                tokens = input_Command.split(" ");
                // tokens [0,1] is now ["mark", "1"]
                index = Integer.parseInt(tokens[1]); // from string to int (1)
                //task_Array[index].markAsDone();
                list_Items.get(index - 1).markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(" " + list_Items.get(index - 1));
                break;

            case "unmark":
                tokens = input_Command.split(" ");
                index = Integer.parseInt(tokens[1]);
                list_Items.get(index - 1).markAsNotDone();
                //task_Array[index].markAsNotDone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(" " + list_Items.get(index - 1));
                break;

            case "list":
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < counter; i = i + 1) {
                    Task item = list_Items.get(i);
                    System.out.println((i + 1) + ". " + item);
                }
                break;

            case "bye":
                System.out.println("I look forward to seeing you again! Goodbye!");
                isRunning = false;
                return;

            default:
                try {
                    throw new wrongCommandException();
                } catch (wrongCommandException exception) {
                    System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            }
        }
    }
}
