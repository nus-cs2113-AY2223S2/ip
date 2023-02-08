import java.util.Scanner;

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
            parsedInput = parser.parseInput(input);
            switch(parsedInput[0]) {
                case ("todo"):
                    taskList[listCount] = new Todo(parsedInput[1]);
                    break;
                case ("deadline"):
                    taskList[listCount] = new Deadline(parsedInput[1], parsedInput[2]);
                    break;
                case ("event"):
                    taskList[listCount] = new Event(parsedInput[1], parsedInput[2], parsedInput[3]);
                    break;
                case("mark"):
                    taskList[Integer.parseInt(parsedInput[1]) - 1].setDone();
                    listCount -= 1;
                    break;
                case("unmark"):
                    taskList[Integer.parseInt(parsedInput[1]) - 1].setUndone();
                    listCount -= 1;
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
