import java.util.Scanner;

public class Duke {
    public static String HORIZONTAL_LINE = "____________________________________________________________";

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        greetUser();
        String command = getUserCommand();
        while(!command.equals("bye")){
            executeCommand(command);
            command = getUserCommand();
        }
        exitProgram();
    }

    public static void greetUser(){
        System.out.println(HORIZONTAL_LINE);
        System.out.println("[Duke]: Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println(HORIZONTAL_LINE);
    }

    public static void exitProgram(){
        System.out.println(HORIZONTAL_LINE);
        System.out.println("[Duke]: Bye. Hope to see you again soon!");
        System.out.print(HORIZONTAL_LINE);
    }

    public static String getUserCommand(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("[User]: ");
        return scanner.nextLine();
    }

    public static void executeCommand(String command){
        echo(command);
    }

    public static void echo(String command){
        System.out.println(HORIZONTAL_LINE);
        System.out.println("[DUKE]: "+ command);
        System.out.println(HORIZONTAL_LINE);
    }


}
