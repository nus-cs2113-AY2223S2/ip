import java.util.ArrayList;
import java.util.Scanner;

public class Rica {
    private static final String LINE = "____________________________________________________________";
    private static final String LIST_TRIGGER = "list";
    private static final String MARK_TRIGGER = "mark";
    private static final String UNMARK_TRIGGER = "unmark";
    private static final String TODO_TRIGGER = "todo";
    private static final String BYE_TRIGGER = "bye";
    private static final String ADD_PHRASE = " New todo I'll remember: ";
    private static final String BYE_PHRASE = " Leaving so soon? Come back anytime, I'll be happy to help!";
    private static ArrayList<Todo> pastTodos = new ArrayList<>();

    private static ArrayList<Todo> getPastTodos() {
        return Rica.pastTodos;
    }

    /**
     * Adds a new todo to the todo list with the given description
     *
     * @param command Command describing the new todo being added
     */
    private static void addTodo(String command) {
        String[] parameters = command.split(" ");
        StringBuilder descriptionBuilder = new StringBuilder();
        for (int i = 1; i < parameters.length; i += 1) {
            descriptionBuilder.append(parameters[i]);
            if (i != (parameters.length - 1)) {
                descriptionBuilder.append(" ");
            }
        }
        String description = descriptionBuilder.toString();
        if (description.isBlank()) {
            printlnWithIndent(" Did you forget something? Don't think there's any todo with no name");
            return;
        }
        Todo newTodo = new Todo(description);
        Rica.getPastTodos().add(newTodo);
        printlnWithIndent(Rica.ADD_PHRASE + description);
    }

    /**
     * Mark a given todo in the todo list as done
     *
     * @param indexOfTodo Index of given todo in the todo list
     * @return Todo object representing the desired todo being marked as done
     */
    private static Todo markDone(int indexOfTodo) {
        if (indexOfTodo < 0 || indexOfTodo >= Rica.getPastTodos().size()) {
            printlnWithIndent(" You alright? I can't mark a todo that doesn't exist as done xD");
            return null;
        }
        Todo selectedTodo = Rica.getPastTodos().get(indexOfTodo);
        if (selectedTodo.getIsDone()) {
            printlnWithIndent(" Take a break maybe? Alright marked as done my friend:");
            printlnWithIndent("    " + selectedTodo);
            return selectedTodo;
        }
        Rica.getPastTodos().remove(indexOfTodo);
        selectedTodo = selectedTodo.setDone(true);
        Rica.getPastTodos().add(indexOfTodo, selectedTodo);
        printlnWithIndent(" Shall remember that this todo as done:");
        printlnWithIndent("    " + selectedTodo);
        return selectedTodo;
    }

    /**
     * Mark a given todo in the todo list as not done
     *
     * @param indexOfTodo Index of desired todo in the todo list
     * @return Todo object representing the desired todo being marked as not done
     */
    private static Todo unmarkDone(int indexOfTodo) {
        if (indexOfTodo < 0 || indexOfTodo >= Rica.getPastTodos().size()) {
            printlnWithIndent(" You alright? I don't think that todo exists xD");
            return null;
        }
        Todo selectedTodo = Rica.getPastTodos().get(indexOfTodo);
        if (!selectedTodo.getIsDone()) {
            printlnWithIndent(" Getting a little ahead of yourself are you xD It's not even done:");
            printlnWithIndent("    " + selectedTodo);
            return selectedTodo;
        }
        Rica.getPastTodos().remove(indexOfTodo);
        selectedTodo = selectedTodo.setDone(false);
        Rica.getPastTodos().add(indexOfTodo, selectedTodo);
        printlnWithIndent(" (Why??) Anyway, I've marked this todo as not done yet:");
        printlnWithIndent("    " + selectedTodo);
        return selectedTodo;
    }

    private static boolean hasAnyTodos() {
        return !Rica.getPastTodos().isEmpty();
    }

    public static void main(String[] args) {
        printlnWithIndent(Rica.LINE);
        printlnWithIndent(" Hello! I'm R.I.C.A.");
        printlnWithIndent((" That's Really-Intelligent-Chat-Assistant for you!"));
        printlnWithIndent(" How may I be of assistance?");
        printlnWithIndent(Rica.LINE);
        Rica.runCommands();
    }

    private static void printlnWithIndent(String line) {
        System.out.print("    ");
        System.out.println(line);
    }

    /**
     * Prints out the list of todos added so far, or inform the user if no todos have been added
     * yet
     */
    private static void printTodos() {
        if (!Rica.hasAnyTodos()) {
            printlnWithIndent(" Hope I'm not amnesiac, but I don't remember any todos?");
        } else {
            ArrayList<Todo> todos = Rica.getPastTodos();
            printlnWithIndent(" I think you have these todos:");
            for (int i = 1; i <= todos.size(); i += 1) {
                printlnWithIndent(" " + i + ". " + todos.get(i - 1));
            }
        }
    }

    /**
     * Parse the command entered into CLI and execute the corresponding actions
     */
    private static void runCommands() {
        Scanner in = new Scanner(System.in);
        String command = "";
        do {
            command = in.nextLine();
            printlnWithIndent(Rica.LINE);
            String[] params = command.split(" ");
            switch (params[0]) {
            case Rica.MARK_TRIGGER:
                int indexOfTodo = Integer.valueOf(params[1]) - 1;
                Rica.markDone(indexOfTodo);
                break;
            case Rica.UNMARK_TRIGGER:
                indexOfTodo = Integer.valueOf(params[1]) - 1;
                Rica.unmarkDone(indexOfTodo);
                break;
            case Rica.LIST_TRIGGER:
                printTodos();
                break;
            case Rica.TODO_TRIGGER:
                Rica.addTodo(command);
                break;
            case Rica.BYE_TRIGGER:
                printlnWithIndent(Rica.BYE_PHRASE);
                break;
            }
            printlnWithIndent(Rica.LINE);
        } while (!command.equals(Rica.BYE_TRIGGER));
    }
}
