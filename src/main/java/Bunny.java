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
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                if (bunny.numTodos() == 0) {
                    bunny.printMessage("Your TODO list is empty!");
                } else {
                    ArrayList<String> messageLines = new ArrayList<>();
                    for (int i = 0; i < bunny.numTodos(); i++) {
                        messageLines.add((i + 1) + ". " + bunny.getTodo(i));
                    }
                    bunny.printMessage(messageLines);
                }
            } else if (input.startsWith("mark")) {
                try {
                    int markIndex = Integer.parseInt(input.split(" ")[1]) - 1;
                    bunny.getTodo(markIndex).markAsDone();
                    bunny.printMessage("Nice! I've marked this task as done:\n\t" + bunny.getTodo(markIndex));
                } catch (IndexOutOfBoundsException e) {
                    bunny.printMessage("To mark a TODO as done, provide a valid TODO number like so: \"mark 1\"");
                } catch (NumberFormatException e) {
                    bunny.printMessage("To mark a TODO as done, provide a valid TODO number like so: \"mark 1\"");
                }
            } else if (input.startsWith("unmark")) {
                try {
                    int unmarkIndex = Integer.parseInt(input.split(" ")[1]) - 1;
                    bunny.getTodo(unmarkIndex).markAsNotDone();
                    bunny.printMessage("Nice! I've marked this task as not done yet:\n\t" + bunny.getTodo(unmarkIndex));
                } catch (IndexOutOfBoundsException e) {
                    bunny.printMessage("To unmark a TODO as done, provide a valid TODO number like so: \"unmark 1\"");
                } catch (NumberFormatException e) {
                    bunny.printMessage("To unmark a TODO as done, provide a valid TODO number like so: \"unmark 1\"");
                }
            } else {
                bunny.addTodo(new Todo(input));
                bunny.printMessage("added: " + input);
            }
        }

        bunny.printMessage("Bye. Hope to see you again soon!");
    }
}
