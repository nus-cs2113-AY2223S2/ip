import java.util.Scanner;

// TODO: Add docstrings for the functions
public class Duke {
    private static boolean keepAlive;

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
        if(cmd.equals("Bye")){
            // Termination
            setKeepAlive(false);
        }else{
            // Echo out the command
            PrintBorder();
            System.out.println(cmd);
            PrintBorder();
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
        Scanner input = new Scanner(System.in);
        while(keepAlive){
            String command = input.nextLine();
            handleCommand(command);
        }
        Exit();
    }
}
