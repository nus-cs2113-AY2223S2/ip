import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;


public class Duke {

    private static final String BYE_MESSAGE = "Bye. Hope to see you again soon!";

    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_EVENT = "event";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_MARK = "mark";
    private static final String COMMAND_UNMARK = "unmark";
    private static final String COMMAND_DELETE = "delete";
    private static final String COMMAND_FIND = "find";

    private static final int REMOVE_FROM = 5;
    private static final int REMOVE_TO = 3;
    private static final int REMOVE_BY = 3;

    private Storage storage;
    private Ui ui;
    private TaskList tasks;
    private Parser parser;


    public Duke (String filepath){
        ui = new Ui();
        storage = new Storage(filepath);
        tasks = new TaskList();
        Scanner in = new Scanner(System.in);
        ui.printWelcomeMessage();
        try{
            storeTasksInList(storage.loadFileContents());
        }catch (IOException e){
            ui.showLoadingError();
        }
        while(true){
            String userInput = in.nextLine();
            processUserInput(userInput,true);
        }
    }
    public void storeTasksInList(ArrayList<String> commandsFromFile){
        for(String s: commandsFromFile){
            processUserInput(s,false);
        }
    }

    public static void main(String[] args) throws IOException {
        new Duke("DUKE/Duke.txt");
    }

    private void processUserInput(String userInput, boolean canPrintFeedback) {
        parser = new Parser();
        String[] commandAndDescription = parser.splitIntoCommand(userInput);
        String command = commandAndDescription[0];
        String description = commandAndDescription[1];
        try {
            switch (command) {
                case COMMAND_BYE:
                    ui.printByeMessage();
                    break;
                case COMMAND_EVENT: //append
                    executeEventCommand(description,canPrintFeedback);
                    if(canPrintFeedback){
                        storage.appendToFile(userInput);
                    }
                    break;
                case COMMAND_DEADLINE: //append
                    executeDeadlineCommand(description,canPrintFeedback);
                    if(canPrintFeedback){
                        storage.appendToFile(userInput);
                    }
                    break;
                case COMMAND_LIST:
                    executeListCommand(canPrintFeedback);
                    break;
                case COMMAND_TODO: //append
                    executeTodoCommand(description,canPrintFeedback);
                    if(canPrintFeedback){
                        storage.appendToFile(userInput);
                    }
                    break;
                case COMMAND_MARK: //write
                    executeMarkCommand(description,canPrintFeedback);
                    if(canPrintFeedback){
                        storage.writeToFile();
                    }
                    break;
                case COMMAND_UNMARK://write
                    executeUnmarkCommand(description,canPrintFeedback);
                    if(canPrintFeedback){
                        storage.writeToFile();
                    }
                    break;
                case COMMAND_DELETE:
                    executeDeleteCommand(description);
                    break;
                case COMMAND_FIND:
                    executeFindCommand(description);
                    break;
                default:
                    ui.showInvalidCommand();
            }
        }catch(IOException e){
            ui.showCannotEditFile();
        }
    }

    private void executeUnmarkCommand(String description,boolean canPrintFeedback) {
        //description is the list number
        //convert description(string) to description(int)
        Integer index = Integer.parseInt(description) - 1;
        tasks.unmarkTaskAtIndex(index);
        if(canPrintFeedback) {
            ui.printUnmarkFeedback(tasks.getTasks(), index);
        }
    }
    private void executeFindCommand(String description){
        ArrayList<Task> foundTasks = new ArrayList<>();
        foundTasks = tasks.findTasks(description);
        System.out.println("Tasks found:");
        ui.printList(foundTasks);
    }

    private  void executeDeleteCommand(String description){
        Integer index = Integer.parseInt(description)-1;
        String deletedTask = tasks.deleteTaskAtIndex(index);
        ui.printDeleteFeedback(tasks.getTasks(),deletedTask);
    }

    private void executeMarkCommand(String description,boolean canPrintFeedback) {
        Integer index = Integer.parseInt(description) - 1;
        tasks.markTaskAtIndex(index);
        if (canPrintFeedback) {
            ui.printMarkFeedback(tasks.getTasks(), index);
        }
    }

    private void executeTodoCommand(String description,boolean canPrintFeedback) {
        try {
            tasks.addTodoToList(description);
            if(canPrintFeedback) {
                ui.printAcknowledgement(tasks.getTasks());
            }
        }catch (Exception e){
            ui.showInsufficientTodoArgs();
        }
    }

    private void executeDeadlineCommand(String description,boolean canPrintFeedback) {
        try {
            String[] indexArr = splitDescriptionDeadline(description);
            String name = indexArr[0].trim();
            String byDate = indexArr[1].substring(REMOVE_BY).trim();
            tasks.addDeadlineToList(name,byDate);
            if(canPrintFeedback) {
                ui.printAcknowledgement(tasks.getTasks());
            }
        }catch(DukeException e){
            ui.showInsufficientDeadlineArgs();
        }
    }

    private static String[] splitDescriptionDeadline(String description) throws DukeException {
        String[] indexArr = description.split("/", 2);
        if(indexArr.length < 2){
            throw new DukeException(new IllegalArgumentException());
        }
        return indexArr;
    }

    private void executeListCommand(boolean canPrintFeedback) {
        if(canPrintFeedback) {
            ui.printList(tasks.getTasks());
        }
    }

    private void executeEventCommand(String description,boolean canPrintFeedback) {
        try {
            String[] indexArr = splitDescriptionEvent(description);
            String name = indexArr[0].trim();
            String fromDate = indexArr[1].substring(REMOVE_FROM).trim();
            String toDate = indexArr[2].substring(REMOVE_TO).trim();
            tasks.addEventToList(name, fromDate, toDate);
            if(canPrintFeedback) {
                ui.printAcknowledgement(tasks.getTasks());
            }
        }catch (DukeException e){
            ui.showInsufficientEventArgs();
        }

    }

    private static String[] splitDescriptionEvent(String description) throws DukeException {
        String[] indexArr = description.split("/", 3);
        if(indexArr.length < 3){
            throw new DukeException(new IllegalArgumentException());
        }
        return indexArr;
    }



}
