package DukeMain;

import ErrorHandling.ErrorHandler;

import FileIO.DukeFile;

import Parser.parseEventString;
import TaskItems.Deadline;
import TaskItems.Event;
import TaskItems.Todos;

import UserInterface.UI;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public static void main(String[] args) throws IOException {
        Scanner ReadingUserInput = new Scanner(System.in);
        boolean justStarted = true;
        UI.greet();
        String UserInput = ReadingUserInput.nextLine();

        //File management segment
        File file = new File("Data.txt");
        ArrayList<Todos> todoItems;
        if (file.exists()) {
            todoItems = DukeFile.loadListFromFile("Data.txt");
        } else {
            todoItems = new ArrayList<>();
        }
        //end of file management segment

        int new_tasks = 0;
        while (!UserInput.equalsIgnoreCase("bye")) {
            if (!ErrorHandler.isInputValid(UserInput)) {
                UserInput = ReadingUserInput.nextLine();
                continue;
            }
            if (UserInput.equalsIgnoreCase("list")) {
                UI.ListItems(todoItems);
            } else if (UserInput.startsWith("mark")) {
                UI.Mark(UserInput,todoItems);
                DukeFile.WriteToFile(todoItems);
            } else if (UserInput.startsWith("unmark")) {
                UI.unMark(UserInput, todoItems);
                DukeFile.WriteToFile(todoItems);
            } else if (UserInput.startsWith("event")) {
                int count = new_tasks + 1;
                UserInput = parseEventString.parseString(UserInput);
                UI.addEvent(UserInput, count, todoItems);
                DukeFile.WriteToFile(todoItems);
                new_tasks++;
            } else if (UserInput.startsWith("deadline")) {
                int count = new_tasks + 1;
                UI.Deadline(UserInput, count);
                Deadline x = new Deadline(UserInput, false, "D");
                todoItems.add(x);
                DukeFile.WriteToFile(todoItems);
                new_tasks++;
            } else if (UserInput.startsWith("todo")) {
                Todos x = new Todos(UserInput.substring(UserInput.indexOf((" "))), false, "T");
                todoItems.add(x);
                UI.addTodo(todoItems, new_tasks, UserInput);
                DukeFile.WriteToFile(todoItems);
                new_tasks++;
            } else if(UserInput.startsWith("delete")) {
                UI.DeleteItem(todoItems, UserInput);
                DukeFile.WriteToFile(todoItems);
            }else {
                UI.Error();
            }
            UserInput = ReadingUserInput.nextLine();

        }
        UI.sayBye();

    }
}
