import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Duke Duke = new Duke();
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
                } else if (command.equals("list")) {
                    Duke.tasks.list();
                } else {
                    System.out.println("Invalid command");
                }
            } else {
                phrase = commandPhrase[1];
                if (command.equals("mark")) {
                    Duke.tasks.changeTaskState(true, Integer.parseInt(phrase));
                } else if (command.equals("unmark")) {
                    Duke.tasks.changeTaskState(false, Integer.parseInt(phrase));
                } else if (command.equals("event")) {
                    Duke.tasks.addEvent(phrase);
                } else if (command.equals("todo")) {
                    Duke.tasks.addTodo(phrase);
                } else if (command.equals("deadline")) {
                    Duke.tasks.addDeadline(phrase);
                } else if (command.equals("delete")) {
                    Duke.tasks.delete(Integer.parseInt(phrase));
                } else {
                    System.out.println("Invalid command");
                }
            }
            Storage.saveToFile();
            UI.horizontalLine();
        }
        userInput.close();
        System.out.println("Bye. Hope to see you again soon!");
    }
}
