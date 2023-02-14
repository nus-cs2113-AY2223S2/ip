package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.util.ArrayList;
public class Duke {

    static final int MAX_TASKS = 100;
    // private static Task[] taskList = new Task[MAX_TASKS];
    private static ArrayList<Task> taskList = new ArrayList<Task>();
    // private static int listCount = 0;

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
                    taskList.add(new ToDo(parsedInput[1]));
                    break;
                case ("deadline"):
                    try {
                        taskList.add(new Deadline(parsedInput[1], parsedInput[2]));
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Oops, deadline input has the wrong format");
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Oops, deadline task number does not exist");
                    }
                    break;
                case ("event"):
                    taskList.add(new Event(parsedInput[1], parsedInput[2], parsedInput[3]));
                    break;
                case("mark"):
                    try {
                        taskList.get(Integer.parseInt(parsedInput[1]) - 1).setDone();
                    } catch (NumberFormatException e) {
                        System.out.println("Oops, mark task description should be an integer");
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Oops, mark task number does not exist");
                    }
                    break;
                case("unmark"):
                    try {
                        taskList.get(Integer.parseInt(parsedInput[1]) - 1).setUndone();
                    } catch (NumberFormatException e) {
                        System.out.println("Oops, unmark task description should be an integer");
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Oops, unmark task number does not exist");
                    }
                    break;
                case("list"):
                    ui.printList(taskList);
                    break;
                case ("delete"):
                    try {
                        Task removeTask = taskList.get(Integer.parseInt(parsedInput[1]) - 1);
                        taskList.remove(Integer.parseInt(parsedInput[1]) - 1);
                        removeTask.delete(taskList);
                    } catch (NumberFormatException e) {
                        System.out.println("Oops, delete task description should be an integer");
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Oops, delete task number does not exist");
                    }
                    break;
            }
            ui.showLine();
            input = ui.getUserInput();
         }
        ui.farewell();
    }
}
