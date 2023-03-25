import java.util.Scanner;

/*
 * Initialises Duke class
 * If earlier written file exists, displays files
 * Continuously promtps user for input until specified terminating input is received
 * List of inputs include bye, list, mark, unmark, event, todo, deadline, delete, find
 */
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
                } else if (command.equals("clear")) {
                    Duke.tasks.clearTaskList();
                } else if (command.equals("list")) {
                    Duke.tasks.list();
                } else if (command.equals("overdue")) {
                    Duke.tasks.listOverdue();
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
                } else if (command.equals("find")) {
                    Duke.tasks.find(phrase);
                } else {
                    System.out.println("Invalid command");
                }
            }
            Duke.storage.saveToFile(Duke.tasks);
            UI.horizontalLine();
        }
        userInput.close();
        System.out.println("Bye. Hope to see you again soon!");
    }
}
