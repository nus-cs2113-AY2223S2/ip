import java.util.Scanner;
import java.util.ArrayList;

//printing when there are errors
class DukeManager {
    private final TaskList taskList;

    DukeManager(TaskList taskList) {
        this.taskList = taskList;
    }

    public void run() {
        Scanner io = new Scanner(System.in);
        ArrayList<String> responseList = new ArrayList<String>();

        String input = io.nextLine();
        while (!input.equals("bye")) {
            try {
                if (Parser.parse(input,"list")) {
                    responseList = this.taskList.printTasks();
                } else if (Parser.parse(input,"mark")) {
                    //if mark / unmark is detected
                    responseList = this.taskList.markTask(input);
                } else if (Parser.parse(input,"delete")) {
                    responseList= this.taskList.deleteTask(input);
                } else if (Parser.parse(input, "todo") | Parser.parse(input, "deadline") |
                        Parser.parse(input, "event")) {
                    responseList= this.taskList.createTask(input);
                } else if (Parser.parse(input, "find")) {
                    responseList= this.taskList.findTasks(input);
                } else {
                    throw new IncorrectCommandException("I'm sorry, but I don't know what that means :-(");
                }
            } catch (Exception e) {
               Ui.printErrorMessage(e);
            }
            Ui.printResponse(responseList);
            Ui.printLineDivider();
            input = io.nextLine();
        }
        Storage.updateStorage(this.taskList);
    }

}
