import commandhandler.Command;
import commandhandler.InputParser;
import inoutput.Input;
import inoutput.Output;
import tasktype.Event;
import tasktype.Task;
import tasktype.Stash;
import tasktype.Deadline;
import tasktype.Todo;

public class Chronos {
    private static Input inOut;
    private static Stash stash;
    private static void addNew(Task task) {
        stash.addNewTask(task);
        Output.printNewTask(task, stash.ObtainTaskCount());
    }
    private static void toggleTaskStatus(String details){
        int index = Integer.parseInt(details) - 1;
        Task task = stash.getTask(index);
        task.toggleDone();
        Output.printIsDone(stash, index);
    }

    public static void main(String[] args) {
        inOut = new Input();
        stash = new Stash();
        Output.printWelcome();
        getUserName(inOut);
        inputCommands();
    }

    private static void inputCommands() {
        while(true){
            Command userCommand = InputParser.parseInput(inOut.readInput());
            String category = userCommand.getAction();

            switch (category){
            case "list":
                Output.printList(stash);
                continue;
            case "mark":
            case "unmark":
                toggleTaskStatus(userCommand.getDetails());
                continue;
            case "help":
                Output.printHelp();
                continue;
            case "bye":
                System.out.println("Bye bye, hope to see you some time soon!");
                return;
            case "todo":
                addNew(new Todo(userCommand.getDetails()));
                continue;
            case "event":
                addNew(new Event(userCommand.getDetails(), userCommand.getStart(), userCommand.getEnd()));
                continue;
            case "deadline":
                addNew(new Deadline((userCommand.getDetails()), userCommand.getDue()));
                continue;
            default:
                System.out.println("Sorry, I do not understand the input at this point in time.");
            }
        }
    }


    private static void getUserName(Input inOut) {
        System.out.println("What is your name? (Please enter name)\n");
        String userName = inOut.readInput();
        System.out.println("Hello, " + userName + ". You may enter 'list' to view your current To-Do list.");
    }
}

