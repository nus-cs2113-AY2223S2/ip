package duke;

import duke.commands.Command;
import duke.commands.CommandResult;
import duke.commands.TaskCommand;
import duke.exception.DukeException;
import duke.exception.TaskLoadErrorException;
import duke.outputs.Messages;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasks.Tasklist;
import duke.ui.UI;


public class Duke {
    private final Storage storage;
    private final Tasklist taskList;
    private final UI ui;
    private final Parser parser;


//    public Duke(String filePath) {
//        TaskList taskList1;
//        this.ui = new UI();
//        this.parser = new Parser();
//        this.storage = new Storage(filePath);
//        try {
//            taskList1 = storage.loadTaskList();
//        } catch (ErrorLoadingTaskException exception) {
//            ui.speakToUser(exception.getBlobMessages());
//            taskList1 = new TaskList();
//        }
//        this.taskList = taskList1;
//    }

//    public void run() {
//        ui.showWelcomeMessage();
//        boolean isExit = false;
//        while (!isExit) {
//            try {
//                String fullCommand = ui.readCommand();
//                ui.showLine(); // show the divider line ("_______")
//                Command c = Parser.parse(fullCommand);
//                c.execute(tasks, ui, storage);
//                isExit = c.isExit();
//            } catch (DukeException e) {
//                ui.showError(e.getMessage());
//            } finally {
//                ui.showLine();
//            }
//        }
//    }

    Duke(String filePath) {
        Tasklist taskList1;
        this.ui = new UI();
        this.parser = new Parser();
        this.storage = new Storage(filePath);
        try {
            taskList1 = storage.textFileToProgram();
        } catch (TaskLoadErrorException exception) {
            System.out.println(Messages.ERROR_MESSAGE_LOADING_TASK_ERROR);
            taskList1 = new Tasklist();
        }
        this.taskList = taskList1;
    }



    // Run program here:
    public static void main(String[] args) {
        new Duke("data/duke.txt");
    }
    public void end() {
        storage.updateFile(taskList);
        ui.endProgram();
        System.exit(0);
    }

    public String[] getResponse(String input) {
        try {
            Command command = parser.parseInput(input);
            CommandResult result;

            if (command.isByeCommand()) {
                end();
            }

            if (command.isTaskCommand()) {
                TaskCommand taskCommand = (TaskCommand) command;
                taskCommand.createList(taskList);
                result = taskCommand.execute();
                storage.updateFile(taskList);
            } else {
                result = command.execute();
            }
            return result.getResultMessages();
        } catch (DukeException exception) {
            return exception.getDukeMessages();
        }
    }
}




//    public void run(){
//        try {
//            Storage.checkFileAccess();
//            List<Task> tasksList = Storage.textFileToProgram();
//            tasksArray.addAll(tasksList);
//
//        } catch (FileNotFoundException e) {
//            System.out.println("File not found");
//        } catch (IOException e) {
//            System.out.println("Something went wrong: " + e.getMessage());
//        }
//        String userInput;
//        Scanner in = new Scanner(System.in);
//        UI.welcomeMessage();
//        do {
//            userInput = in.nextLine();
//            enterCommand(userInput);
//        } while (!userInput.equals("bye"));
//    }












