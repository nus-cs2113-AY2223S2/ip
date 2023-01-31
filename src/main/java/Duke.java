import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("    Hello from\n" + logo);
        System.out.println("    _________________________________________");

        System.out.println("    Hello! I'm Duke");
        System.out.println("    What can I do for you?");
        System.out.println("    _________________________________________");
        System.out.println("     ");

        //Read in input from user
        String inputString;
        Scanner in;

        boolean exit = false;

        //Set up list to store user inputs
        Todo[] tasks = new Todo[100];
        int counter = 0;

        while (!exit) {
            in = new Scanner(System.in);
            inputString = in.nextLine();

            //switch cases for all specified types of input
            switch(inputString) {

            case "bye" :
                exit = true;
                break;

            case "list" :
                System.out.println("    _________________________________________");
                for (int i = 0; i < counter; ++i) {
                    if (tasks[i].isDone) {
                        System.out.print("    " + (i + 1) + ".");
                        tasks[i].printInList();
                    } else {
                        System.out.print("    " + (i + 1) + ".");
                        tasks[i].printInList();
                    }
                }
                System.out.println("    _________________________________________");
                System.out.println("     ");
                break;

            case "mark" :
                System.out.println("    Please specify task number: ");
                in = new Scanner(System.in);
                inputString = in.nextLine();
                int taskNumber = Integer.parseInt(inputString);
                tasks[taskNumber - 1].setDone(true);
                System.out.println("    _________________________________________");
                System.out.println("    " + (taskNumber) + "." + "[X] " + tasks[taskNumber - 1].getDescription());
                System.out.println("    _________________________________________");
                break;

            case "unmark" :
                System.out.println("    Please specify task number: ");
                in = new Scanner(System.in);
                inputString = in.nextLine();
                taskNumber = Integer.parseInt(inputString);
                tasks[taskNumber - 1].setDone(false);
                System.out.println("    _________________________________________");
                System.out.println("    " + (taskNumber) + "." + "[ ] " + tasks[taskNumber - 1].getDescription());
                System.out.println("    _________________________________________");
                break;

            case "todo" :
                System.out.println("    _________________________________________");
                System.out.println("    Please specify task: ");
                in = new Scanner(System.in);
                inputString = in.nextLine();
                tasks[counter] = new Todo(inputString);
                tasks[counter].print();
                counter++;
                System.out.println("    Now you have " + counter + " tasks in your list!");
                break;

            case "deadline" :
                System.out.println("    _________________________________________");
                System.out.println("    Please specify task: ");
                in = new Scanner(System.in);
                inputString = in.nextLine();
                inputString += '\0';
                int deadlinePosition = inputString.indexOf("/");
                int endPosition = inputString.indexOf("\0");
                String taskName = inputString.substring(0, deadlinePosition);
                String deadline = inputString.substring(deadlinePosition + 1, endPosition);
                tasks[counter] = new Deadline(taskName, deadline);
                tasks[counter].print();
                counter++;
                break;

            case "event" :
                System.out.println("    _________________________________________");
                System.out.println("    Please specify task: ");
                in = new Scanner(System.in);
                inputString = in.nextLine();
                inputString += '\0';
                int deadlineStartPosition = inputString.indexOf("/");
                int deadlineEndPosition = inputString.indexOf("|");
                endPosition = inputString.indexOf("\0");
                taskName = inputString.substring(0, deadlineStartPosition);
                String deadlineStart = inputString.substring(deadlineStartPosition + 1, deadlineEndPosition);
                String deadlineEnd = inputString.substring(deadlineEndPosition + 1, endPosition);
                tasks[counter] = new Event(taskName, deadlineStart, deadlineEnd);
                tasks[counter].print();
                counter++;
                break;


            default:
                in = new Scanner(System.in);
                inputString = in.nextLine();
                break;
            }
        }

        //When user types "bye"
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println("    _________________________________________");
        System.out.println("     ");
    }
}
