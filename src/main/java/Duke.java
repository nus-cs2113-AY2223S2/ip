import java.util.Scanner;

// TODO: Add docstrings for the functions
public class Duke {
    private static boolean keepAlive;
    private static Task[] tasklist = new Task[100];
    private static int numTasks;


    public static void setKeepAlive(boolean keepAlive) {
        Duke.keepAlive = keepAlive;
    }

    public static void PrintBorder(){
        System.out.println("____________________________________________________________");
    }

    public static void Exit(){
        System.out.print("Goodbye! Thank you for using Duke.\n");
    }

    // TODO: Add more robust command string handling
    // TODO: Add a '$' Symbol to hint user to type in a command
    public static void handleCommand(String command){
        // Update the keepAlive flag
        // TODO: Refactor into a switch case when commands start to get bloated
        String[] commandList = command.split(" ");
        String cmd = commandList[0];
        switch (cmd){
        case "Bye":
        case "bye":
        case "exit":
            setKeepAlive(false);
            Exit();
            break;
        case "list":
            System.out.println("Here's what's in your list:");
            for(int i=0; i<numTasks; ++i){
                // Print number, box, description in that order
                Task curr = tasklist[i];
                System.out.print(i+1 + ". " + curr.getDescription() +'\n');
            }
            break;
        case "mark":
            if(commandList.length < 2){
                System.out.println("Missing argument: Task number");
                return;
            }
            // TODO: parsing int from command is inherently unsafe. Should try-catch this
            int taskNum = Integer.parseInt(commandList[1]) - 1; // Convert to 0-idx
            // Update its value
            tasklist[taskNum].markAsDone();
            // TODO: add an additional check to see if it is already marked as done and print different text
            System.out.println("Okay, marking this task as done: ");
            System.out.println(tasklist[taskNum].getDescription());
            break;
        case "unmark":
            if(commandList.length < 2){
                System.out.println("Missing argument: Task number");
                return;
            }
            // TODO: parsing int from command is inherently unsafe. Should try-catch this
            taskNum = Integer.parseInt(commandList[1]) - 1; // Convert to 0-idx
            // Update its value
            tasklist[taskNum].markAsUndone();
            // TODO: add an additional check to see if it is already marked as done and print different text
            System.out.println("Okay, setting this task as undone: ");
            System.out.println(tasklist[taskNum].getDescription());
            break;
        default:
            // Default behaviour is to add the command
            // Insert command into tasklist
            String desc = "";
            // TODO: Use a more efficient way of string building
            for(String s: commandList){
                if(desc.length() > 0){
                    desc = desc + " " + s;
                }else{
                    desc = s;
                }

            }
            Task t = new Task(desc);
            tasklist[numTasks] = t;
            ++numTasks;
            System.out.println("Okay, adding: \"" + desc +"\"!");
        }
    }

    public static void Greet(){
        PrintBorder();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        PrintBorder();
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Greet();
        setKeepAlive(true);

        // Init tasks subsystem
        numTasks = 0;

        // Init IO
        Scanner input = new Scanner(System.in);

        // Event driver loop
        while(keepAlive){
            String command = input.nextLine();
            PrintBorder();
            handleCommand(command);
            PrintBorder();
        }
    }
}
