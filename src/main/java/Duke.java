import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        Scanner userInput = new Scanner(System.in); //create Scanner object
        String inputCommand = userInput.nextLine(); //read user input
        while (!inputCommand.equals("bye")){
            System.out.println(inputCommand);
            inputCommand = userInput.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
