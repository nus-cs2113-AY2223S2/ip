package command;

import tasks.Todo;
import tasks.Task;
import utility.Parser;
import utility.Ui;
import validation.TaskValidation;

import java.util.ArrayList;

public class todo {
    public static void executeTodo(String userInput, ArrayList<Task> tasks) {
        // Removes command word to get the description
        String description = Parser.removeCommandWord(userInput);

        if (!TaskValidation.isNull(description)) {
            return;
        }

        // Creates a new task
        Todo newTodo = new Todo(description);
        tasks.add(newTodo);

        // Prints acknowledgement
        Ui.printAcknowledgement("Todo", description, String.valueOf(tasks.size()));

    }
}
