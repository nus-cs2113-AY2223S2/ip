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

    /**
     * executes a command(mark, unmark, delete, list etc) based on user instrcution
     *
     * @param UserInput - string that contains the user instructions
     * @param todoItems - an array that stores all the Todos Object
     */
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
                UserInput = parseEventString.parseString(UserInput);
                UI.addEvent(UserInput, todoItems);
                DukeFile.WriteToFile(todoItems);
                break;
            }
            case "deadline": {
                UI.Deadline(UserInput, todoItems);
                LocalDate dueBy = handleDate.getDate(UserInput);
                Deadline Item = new Deadline(UserInput.replace("/", ""), false, "D", dueBy);
                todoItems.add(Item);
                DukeFile.WriteToFile(todoItems);
                break;
            }
            case "todo": {
                Todos Item = new Todos(UserInput.substring(UserInput.indexOf((" "))), false, "T");
                todoItems.add(Item);
                UI.addTodo(todoItems, UserInput);
                DukeFile.WriteToFile(todoItems);
                break;
            }
            case "delete": {
                UI.DeleteItem(todoItems, UserInput);
                DukeFile.WriteToFile(todoItems);
                break;
            }
            case "find": {
                String[] parts = UserInput.split(" ");
                String toFind = String.join(" ", Arrays.copyOfRange(parts, 1, parts.length));
                ArrayList<Todos> foundItems = new ArrayList<>();
                for (Todos item : todoItems) {
                    if (item.item.contains(toFind)) {
                        foundItems.add(item);
                    }
                }
                UI.ListItems(foundItems);
                break;
            }
            default: {
                UI.Error();
                break;
            }
        }
    }
}
