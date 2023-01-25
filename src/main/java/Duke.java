import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);
        String userInput;

        // Greeting the user
        Messages.line();
        Messages.logo();
        Messages.hello();
        Messages.line();
        
        do {
            userInput = inputScanner.nextLine();
            Messages.echo(userInput);
        } while (!(userInput.equals("bye")));

        Messages.bye();
        Messages.line();
    }
}
