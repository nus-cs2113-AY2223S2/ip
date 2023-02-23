package Commands;

import FileIO.DukeFile;
import Parser.parseEventString;
import TaskItems.Deadline;
import TaskItems.Todos;
import UserInterface.UI;

import java.util.ArrayList;

public class Command {
    public String command;

    public Command(String command) {
        this.command = command;
    }

    public void executeCommand(String UserInput, ArrayList<Todos> todoItems) {

        if (this.command.equals("list")) {
            UI.ListItems(todoItems);
        } else if (this.command.equals("mark")) {
            UI.Mark(UserInput, todoItems);
            DukeFile.WriteToFile(todoItems);
        } else if (this.command.equals("unmark")) {
            UI.unMark(UserInput, todoItems);
            DukeFile.WriteToFile(todoItems);
        } else if (this.command.equals("event")) {
//            int count = new_tasks + 1;
            UserInput = parseEventString.parseString(UserInput);
            UI.addEvent(UserInput, todoItems);
            DukeFile.WriteToFile(todoItems);
//            new_tasks++;
        } else if (this.command.equals("deadline")) {
//            int count = new_tasks + 1;
            UI.Deadline(UserInput, todoItems);
            Deadline Item = new Deadline(UserInput, false, "D");
            todoItems.add(Item);
            DukeFile.WriteToFile(todoItems);
//            new_tasks++;
        } else if (this.command.equals("todo")) {
            Todos Item = new Todos(UserInput.substring(UserInput.indexOf((" "))), false, "T");
            todoItems.add(Item);
            UI.addTodo(todoItems, UserInput);
            DukeFile.WriteToFile(todoItems);
//            new_tasks++;
        } else if (this.command.equals("delete")) {
            UI.DeleteItem(todoItems, UserInput);
            DukeFile.WriteToFile(todoItems);
        } else {
            UI.Error();
        }
    }
}
