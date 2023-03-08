import allexceptions.*;
import alltasks.*;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * This class represents Coffee Bot that helps users to keep track of their personal schedules.
 * Coffee Bot takes in commands by users to keep track of Todos, Deadlines and events tasks.
 */
public class Duke {
    public static void main(String arguments[]) {
        ArrayList<Task> list_Items = new ArrayList<>();
        int counter = 0;
        int index = 0;
        System.out.println("Hi there! My name is Coffee");
        System.out.println("How can I help you today? :)");
        Task task_Array[];
        task_Array = new Task[110];
        boolean isRun = true;
        //Solution below adapted and reused from Student Oh Yi Xiu Wilson
        // with modifications made by Wilson Lee Jun Wei
        Storage storage = new Storage(list_Items);
        list_Items.addAll(storage.get_Tasks_From_File());
        //@@ Student Oh Yi Xiu Wilson
        while (isRun) {
            Scanner command = new Scanner(System.in);
            String input_Command = command.nextLine();
            String[] first_Word_Array;
            first_Word_Array = input_Command.split(" ", 2);
            String first_Word = first_Word_Array[0];
            String[] tokens;

            switch (first_Word) {
            case "todo": // e.g. todo borrow book
                try {
                    if (first_Word_Array.length == 1) { 
                        throw new todoMissingException();
                    }
                    Todo todo_Word = new Todo(first_Word_Array[1]);
                    list_Items.add(counter, todo_Word);
                    counter = counter + 1;
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + todo_Word);
                    System.out.println("Now you have " + list_Items.size() + " tasks in the list.");
                    } catch (todoMissingException exception){
                     System.out.println("OH NO!!!! The description of a todo cannot be empty.");
                }
                break;

            case "delete":
                System.out.println("Noted. I've removed this task:");
                Task task = list_Items.remove(Integer.parseInt(first_Word_Array[1]) - 1);
                System.out.println(task);
                System.out.println("Now you have " + list_Items.size() + " tasks in the list.");
                break;

            case "find":
                System.out.println("Here are the matching keywords in your list:");
                for (int i = 0; i < list_Items.size(); i++) {
                    if (list_Items.get(i).getDescription().contains(first_Word_Array[1])) {
                        System.out.print(i + 1);
                        System.out.print(". ");
                        System.out.println(list_Items.get(i));
                    }
                }
                break;

            case "deadline":
                String [] get_Weekday;
                get_Weekday = first_Word_Array[1].split("/by", 2);
                Deadline deadline_Word = new Deadline(get_Weekday[0], get_Weekday[1]);
                list_Items.add(counter, deadline_Word);
                counter = counter + 1;
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + deadline_Word + " (by:" + get_Weekday[1] + ")");
                System.out.println("Now you have " + counter + " tasks in the list.");
                list_Items.add(counter, new Task(input_Command));
                break;

            case "event":
                String[] get_Meeting;
                get_Meeting = first_Word_Array[1].split("/", 3);
                String[] meeting_From;
                String[] meeting_To;
                meeting_From = get_Meeting[1].split(" ", 2);
                meeting_To = get_Meeting[2].split(" ", 2);
                Event meeting_Type = new Event(get_Meeting[0], meeting_From[1], meeting_To[1]);
                list_Items.add(counter, meeting_Type);
                counter = counter + 1;
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + meeting_Type);
                System.out.println("Now you have " + counter + " tasks in the list.");
                list_Items.add(counter, new Task(input_Command));
                break;

            case "mark":
                tokens = input_Command.split(" ");
                index = Integer.parseInt(tokens[1]);
                list_Items.get(index - 1).markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(" " + list_Items.get(index - 1));
                break;

            case "unmark":
                tokens = input_Command.split(" ");
                index = Integer.parseInt(tokens[1]);
                list_Items.get(index - 1).markAsNotDone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(" " + list_Items.get(index - 1));
                break;

            case "list":
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < list_Items.size(); i = i + 1) {
                    Task item = list_Items.get(i);
                    System.out.println((i + 1) + ". " + item);
                }
                break;

            case "bye":
                System.out.println("I look forward to seeing you again! Goodbye!");
                isRun = false;
                return;

            default:
                try {
                    throw new wrongCommandException();
                } catch (wrongCommandException exception) {
                    System.out.println("OH NO!!! My deepest apologies, but I don't understand what that means :(");
                }
            }
            //Solution below adapted and reused from Student Oh Yi Xiu Wilson
            // with modifications made by Wilson Lee Jun Wei
            storage.write_Tasks_To_File();
            //@@ Student Oh Yi Xiu Wilson
        }
    }
}
