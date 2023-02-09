import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Duke Duke = new Duke();
        Duke.greet();
        Scanner userInput = new Scanner(System.in); // create Scanner object
        String inputCommand; // read user input
        String[] commandPhrase;
        String command, phrase;
        while (true) {
            inputCommand = userInput.nextLine();
            commandPhrase = inputCommand.split(" ", 2);
            command = commandPhrase[0];
            if (commandPhrase.length < 2) {

                if (command.equals("bye")) {
                    break;
                }
                if (command.equals("list")) {
                    Duke.list();
                }
            } else {
                phrase = commandPhrase[1];
                if (command.equals("mark")) {
                    Duke.changeTaskState(true, Integer.parseInt(phrase));
                }
                if (command.equals("unmark")) {
                    Duke.changeTaskState(false, Integer.parseInt(phrase));
                }
                if (command.equals("event")) {
                    Duke.addEvent(phrase);
                }
                if (command.equals("todo")) {
                    Duke.addTodo(phrase);
                }
                if (command.equals("deadline")) {
                    Duke.addDeadline(phrase);
                }
            }
        }
        userInput.close();
        System.out.println("Bye. Hope to see you again soon!");
    }
}
