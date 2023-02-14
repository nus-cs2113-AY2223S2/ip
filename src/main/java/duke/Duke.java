package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Duke {
    private static ArrayList<Task> taskList = new ArrayList<Task>();

    public static void main(String[] args) {
        FileProcessor fileProcessor = null;
        try {
            fileProcessor = new FileProcessor(taskList);
        } catch (IOException e) {
            System.out.println("Error");
        }
        Ui ui = new Ui();
        Parser parser = new Parser();
        ui.greet();
        String input = ui.getUserInput();
        while (input.compareTo("bye") != 0) {
            String[] parsedInput;
            try {
                parsedInput = parser.parseInput(input);
            } catch (DukeException e) {
                System.out.println("Oops, I'm sorry, I don't know what that means :(");
                ui.showLine();
                input = ui.getUserInput();
                continue;
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Oops, " + input + " cannot have an empty description");
                ui.showLine();
                input = ui.getUserInput();
                continue;
            } catch (StringIndexOutOfBoundsException e) {
                String[] getCommand = input.split(" ", 2);
                System.out.println("Oops, " + getCommand[0] + " is in the wrong format");
                ui.showLine();
                input = ui.getUserInput();
                continue;
            }
            switch (parsedInput[0]) {
                case ("todo"):
                    taskList.add(new ToDo(parsedInput[1]));
                    break;
                case ("deadline"):
                    try {
                        taskList.add(new Deadline(parsedInput[1], parsedInput[2]));
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Oops, deadline input has the wrong format");

                    }
                    break;
                case ("event"):
                    taskList.add(new Event(parsedInput[1], parsedInput[2], parsedInput[3]));
                    break;
                case ("mark"):
                    try {
                        taskList.get(Integer.parseInt(parsedInput[1]) - 1).setDone();
                    } catch (NumberFormatException e) {
                        System.out.println("Oops, mark task description should be an integer");
                    }
                    break;
                case ("unmark"):
                    try {
                        taskList.get(Integer.parseInt(parsedInput[1]) - 1).setUndone();
                    } catch (NumberFormatException e) {
                        System.out.println("Oops, unmark task description should be an integer");
                    }
                    break;
                case ("list"):
                    ui.printList(taskList);
                    break;
            }
            ui.showLine();
            input = ui.getUserInput();
        }
        fileProcessor.writeFile(taskList);
        ui.farewell();
    }
}
