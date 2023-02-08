package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

public class Duke {

    static final int MAX_TASKS = 100;
    private static Task[] taskList = new Task[MAX_TASKS];
    private static int listCount = 0;


    public static void main(String[] args) {
        Ui ui = new Ui();
        Parser parser = new Parser();
        ui.greet();
        String input = ui.getUserInput();
        while(input.compareTo("bye") != 0) {
            String[] parsedInput;
            try{
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
            switch(parsedInput[0]) {
                case ("todo"):
                    taskList[listCount] = new ToDo(parsedInput[1]);
                    break;
                case ("deadline"):
                    try {
                        taskList[listCount] = new Deadline(parsedInput[1], parsedInput[2]);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Oops, deadline input has the wrong format");
                        ui.showLine();
                        input = ui.getUserInput();
                        continue;
                    }
                    break;
                case ("event"):
                    taskList[listCount] = new Event(parsedInput[1], parsedInput[2], parsedInput[3]);
                    break;
                case("mark"):
                    try {
                        taskList[Integer.parseInt(parsedInput[1]) - 1].setDone();
                    } catch (NumberFormatException e) {
                        System.out.println("Oops, mark task description should be an integer");
                        ui.showLine();
                        input = ui.getUserInput();
                        continue;
                    }
                    listCount -= 1;
                    break;
                case("unmark"):
                    try {
                        taskList[Integer.parseInt(parsedInput[1]) - 1].setUndone();
                    } catch (NumberFormatException e) {
                        System.out.println("Oops, unmark task description should be an integer");
                        ui.showLine();
                        input = ui.getUserInput();
                        continue;
                    }
                    listCount -= 1;
                    break;
                case("list"):
                    ui.printList(taskList, listCount);
            }
            ui.showLine();
            listCount += 1;
            input = ui.getUserInput();
         }
        ui.farewell();
    }
}
