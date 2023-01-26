import java.util.Scanner;

// TODO: Add docstrings for the functions
public class Duke {
    private static boolean keepAlive;
    private static String[] tasklist = new String[100];
    private static int numTasks;


    public static void setKeepAlive(boolean keepAlive) {
        Duke.keepAlive = keepAlive;
    }

    public static void PrintBorder(){
        System.out.println("____________________________________________________________");
    }

    public static void Exit(){
        PrintBorder();
        System.out.print("Goodbye! Thank you for using Duke.\n");
        PrintBorder();
    }

    // TODO: Add more robust command string handling
    // TODO: Add a '$' Symbol to hint user to type in a command
    public static void handleCommand(String cmd){
        // Update the keepAlive flag
        // TODO: Refactor into a switch case when commands start to get bloated
        if(cmd.equals("Bye") || cmd.equals("bye") || cmd.equals("exit")){
            // Termination
            setKeepAlive(false);
        }else if (cmd.equals("list")){
            PrintBorder();
            for(int i=0; i<numTasks; ++i){
                System.out.println(i+1 + ". " + tasklist[i]);
            }
            PrintBorder();
        }else{
            // Echo out the command
            PrintBorder();
            System.out.println("Okay, adding: \"" + cmd +"\"!");
            PrintBorder();
            // Insert command into tasklist
            tasklist[numTasks] = cmd;
            ++numTasks;
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
            handleCommand(command);
        }
        Exit();
    }
}
