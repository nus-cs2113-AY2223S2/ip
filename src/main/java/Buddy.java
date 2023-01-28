import java.util.Scanner;
import java.util.Arrays;

public class Buddy {
    public static void main(String[] args) {
        String greeting = "Hello there! I'm Buddy\n"
                + "How may I assist you?";
        String exitMessage = "Hope I was of help to you! Have a great day and see you again, Buddy :)";
        String divider = "________________________________________________________________________________";

        System.out.println(divider);
        System.out.println(greeting);
        System.out.println(divider);

        String[] listOfThings = new String[100];
        int currentPosition = 0;
        String command;
        Scanner in = new Scanner(System.in);
        command = in.nextLine();

        while (! command.equals("bye")){
            int index = 1;
            if (command.equals("list")){
                for (int i = 0; i < currentPosition; i++){ // while not null
                    System.out.println(index + ". " + listOfThings[index-1]);
                    index++;
                }
            }

            else {
                listOfThings[currentPosition] = command;
                System.out.println(divider);
                System.out.println("added: " + command);
                System.out.println(divider);
                currentPosition++;
            }
            command = in.nextLine();

        }

        System.out.println(divider);
        System.out.println(exitMessage);
        System.out.println(divider);

    }
}
