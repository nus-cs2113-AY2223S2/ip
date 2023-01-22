import java.util.ArrayList;
import java.util.Scanner;

public class Bunny {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        BunnySession bunny = new BunnySession();

        bunny.printMessage("Hello! I'm Bunny.\nWhat can I do for you?");

        while(true) {
            String input = in.nextLine();
            if(input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                if (bunny.numTodos() == 0) {
                    bunny.printMessage("Your TODO list is empty!");
                } else {
                    ArrayList<String> messageLines = new ArrayList<>();
                    for(int i = 0; i < bunny.numTodos(); i++) {
                        messageLines.add((i + 1) + ". " + bunny.getTodo(i).getName());
                    }
                    bunny.printMessage(messageLines);
                }
            } else {
                bunny.addTodo(new Todo(input));
                bunny.printMessage("added: " + input);
            }
        }

        bunny.printMessage("Bye. Hope to see you again soon!");
    }
}
