import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Bunny {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        BunnySession bunny = new BunnySession();

        bunny.printMessage("Hello! I'm Bunny.\nWhat can I do for you?");

        while (true) {
            String input = in.nextLine();
            ParsedCommand inputCommand = new ParsedCommand(input);

            if (inputCommand.getCommand().equals("bye")) {
                break;
            } else if (inputCommand.getCommand().equals("list")) {
                if (bunny.numTasks() == 0) {
                    bunny.printMessage("Your TODO list is empty!");
                } else {
                    ArrayList<String> messageLines = new ArrayList<>();
                    for (int i = 0; i < bunny.numTasks(); i++) {
                        messageLines.add((i + 1) + ". " + bunny.getTask(i));
                    }
                    bunny.printMessage(messageLines);
                }
            } else if (inputCommand.getCommand().equals("mark")) {
                try {
                    int markIndex = Integer.parseInt(inputCommand.getPositionalArgument()) - 1;
                    bunny.getTask(markIndex).markAsDone();
                    bunny.printMessage("Nice! I've marked this task as done:\n\t" + bunny.getTask(markIndex));
                } catch (IndexOutOfBoundsException e) {
                    bunny.printMessage("To mark a TODO as done, provide a valid TODO number like so: \"mark 1\"");
                } catch (NumberFormatException e) {
                    bunny.printMessage("To mark a TODO as done, provide a valid TODO number like so: \"mark 1\"");
                }
            } else if (inputCommand.getCommand().equals("unmark")) {
                try {
                    int unmarkIndex = Integer.parseInt(inputCommand.getPositionalArgument()) - 1;
                    bunny.getTask(unmarkIndex).markAsNotDone();
                    bunny.printMessage("Nice! I've marked this task as not done yet:\n\t" + bunny.getTask(unmarkIndex));
                } catch (IndexOutOfBoundsException e) {
                    bunny.printMessage("To unmark a TODO as done, provide a valid TODO number like so: \"unmark 1\"");
                } catch (NumberFormatException e) {
                    bunny.printMessage("To unmark a TODO as done, provide a valid TODO number like so: \"unmark 1\"");
                }
            } else if (inputCommand.getCommand().equals("todo")){
                Task newTask = new Todo(inputCommand.getPositionalArgument());
                bunny.addTask(newTask);
                bunny.printMessage("I've added this task: " + newTask + "\nNow you have " + bunny.numTasks() + " tasks in the list.");
            } else if (inputCommand.getCommand().equals("deadline")){
                Task newTask = new Deadline(inputCommand.getPositionalArgument(), inputCommand.getFlagArgument("by"));
                bunny.addTask(newTask);
                bunny.printMessage("I've added this task: " + newTask + "\nNow you have " + bunny.numTasks() + " tasks in the list.");
            } else if (inputCommand.getCommand().equals("event")){
                Task newTask = new Event(inputCommand.getPositionalArgument(),
                        inputCommand.getFlagArgument("from"),
                        inputCommand.getFlagArgument("to"));
                bunny.addTask(newTask);
                bunny.printMessage("I've added this task: " + newTask + "\nNow you have " + bunny.numTasks() + " tasks in the list.");
            } else {
                bunny.printMessage("That is not a valid command!");
            }
        }

        bunny.printMessage("Bye. Hope to see you again soon!");
    }
}
