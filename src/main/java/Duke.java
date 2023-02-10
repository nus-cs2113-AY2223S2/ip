import java.util.Scanner;
// split commands with body of commands
public class Duke {
    public static void main(String arguments[]) {
        int counter = 0;
        int index = 0;
        System.out.println("Hi there! My name is Coffee");
        System.out.println("How can I help you today?");

        Task task_Array[];
        task_Array = new Task[110]; // task_Array is an instance of array type
        boolean isRunning = true;


        while (isRunning) {
            Scanner command = new Scanner(System.in); // reading in inputs
            String input_Command = command.nextLine();
            // e.g. "todo borrow book"
            // e.g. event project meeting /from Mon 2pm /to 4pm
            String[] first_Word_Array;
            first_Word_Array = input_Command.split(" ", 2);
            //System.out.println(first_Word_Array[1]);
            // first_Word_Array[0, 1] is now ["todo", "borrow book"]
            // first_Word_array[0, 1] is now ["event", "project meeting /from Mon 2pm /to 4pm]

            String first_Word = first_Word_Array[0];
            // first_Word is "todo" or "deadline" or "event"
            // first_Word_Array[1] is now "project meeting /from Mon 2pm /to 4pm"
            // first_Word_Array[1] is now "return book /by Sunday"
            //System.out.println(first_Word);
            String[] tokens;
            // mark put in every case
            switch (first_Word) {
            case "todo":
                Todo todo_Word = new Todo(first_Word_Array[1]); // todo return book
                counter = counter + 1;
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + todo_Word);
                System.out.println("Now you have " + counter + " tasks in the list.");
                task_Array[counter] = todo_Word;
                break;

            case "deadline":
                String [] get_Weekday;
                get_Weekday = first_Word_Array[1].split("/by", 2);
                Deadline deadline_Word = new Deadline(get_Weekday[0]); // deadline return book /by Sunday
                counter = counter + 1;
                System.out.println("Got it. I've added this task:");
                System.out.println(" " + deadline_Word + " (by:" + get_Weekday[1] + ")");
                System.out.println("Now you have " + counter + " tasks in the list.");
                task_Array[counter] = deadline_Word;
                break;

            case "event": // event project meeting /from Mon 2pm /to 4pm
                //first_Word_Array[1] is project meeting /from Mon 2pm /to 4pm
                String[] get_Meeting;
                get_Meeting = first_Word_Array[1].split("/", 3);
                //System.out.println(get_Meeting[0]); // project meeting
                //System.out.println(get_Meeting[1]); // from Mon 2pm
                String[] meeting_From;
                String[] meeting_To;
                meeting_From = get_Meeting[1].split(" ", 2);
                meeting_To = get_Meeting[2].split(" ", 2);

                //System.out.println(get_Meeting[2]); // to 4pm
                Event meeting_Type = new Event(get_Meeting[0]);
                counter = counter + 1;
                // Event meeting_Details_From = new Event(get_Meeting[1]);
                // Event meeting_Details_To = new Event(get_Meeting[2]);
                System.out.println("Got it. I've added this task:");
                System.out.println(" " + meeting_Type + "(from: " + meeting_From[1] + "to: " + meeting_To[1] + ")");
                System.out.println("Now you have " + counter + " tasks in the list.");
                break;

            case "mark":
                tokens = input_Command.split(" ");
                index = Integer.parseInt(tokens[1]) - 1;
                task_Array[index].markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(task_Array[index]);
                break;

            case "unmark":
                tokens = input_Command.split(" ");
                index = Integer.parseInt(tokens[1]) - 1;
                task_Array[index].markAsNotDone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(task_Array[index]);
                break;

            case "list":
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < counter; i = i + 1) {
                    System.out.println((i + 1) + ". " + task_Array[i+1]);
                }
                break;


            case "bye":
                System.out.println("I look forward to seeing you again! Goodbye!");
                isProgramRunning = false;
                return;

            default:
                task_Array[counter] = new Task(input_Command); // constructor read book
                System.out.println("added: " + input_Command); // added: read book
                counter = counter + 1;
            }
        }
    }
}
