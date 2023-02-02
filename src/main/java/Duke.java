import java.util.ArrayList;
import java.util.List;
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
        List<String> allInputs = new ArrayList();
        while (!inputCommand.equals("bye")){
            if (inputCommand.equals("list")){
                for (int i = 0; i < allInputs.size(); i ++){
                    System.out.println(i + ". " + allInputs.get(i));
                }
            }
            else {
                System.out.println("added: " + inputCommand);
                allInputs.add(inputCommand);
            }
            inputCommand = userInput.nextLine();

        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
