package Commands;

import FileIO.DukeFile;
import Parser.parseEventString;
import TaskItems.Deadline;
import TaskItems.Todos;
import UserInterface.UI;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class Command {
    public String command;

    public Command(String command) {
        this.command = command;
    }

    public void executeCommand(String UserInput, ArrayList<Todos> todoItems) {

        switch (this.command) {
            case "list": {
                UI.ListItems(todoItems);
                break;
            }
            case "mark": {
                UI.Mark(UserInput, todoItems);
                DukeFile.WriteToFile(todoItems);
                break;
            }
            case "unmark": {
                UI.unMark(UserInput, todoItems);
                DukeFile.WriteToFile(todoItems);
                break;
            }
            case "event": {
//            int count = new_tasks + 1;
                UserInput = parseEventString.parseString(UserInput);
                UI.addEvent(UserInput, todoItems);
                DukeFile.WriteToFile(todoItems);
//            new_tasks++;
                break;
            }
            case "deadline": {
//            int count = new_tasks + 1;
                UI.Deadline(UserInput, todoItems);
                LocalDate dueBy = handleDate.getDate(UserInput);
                Deadline Item = new Deadline(UserInput, false, "D", dueBy);
                todoItems.add(Item);
                DukeFile.WriteToFile(todoItems);
//            new_tasks++;
                break;
            }
            case "todo": {
                Todos Item = new Todos(UserInput.substring(UserInput.indexOf((" "))), false, "T");
                todoItems.add(Item);
                UI.addTodo(todoItems, UserInput);
                DukeFile.WriteToFile(todoItems);
//            new_tasks++;
                break;
            }
            case "delete": {
                UI.DeleteItem(todoItems, UserInput);
                DukeFile.WriteToFile(todoItems);
                break;
            }
            default: {
                UI.Error();
                break;
            }
        }
    }
}
