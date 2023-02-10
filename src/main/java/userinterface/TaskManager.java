package userinterface;

import commandhandler.Command;
import commandhandler.InputParser;
import inoutput.Input;
import inoutput.Output;
import tasktype.Event;
import tasktype.Task;
import tasktype.Stash;
import tasktype.Deadline;
import tasktype.Todo;

import java.util.Scanner;

import timer.Clock;
import timer.Break;
import timer.WorkTask;
public class TaskManager {
    private static Input inOut;
    private static Stash stash;

    public TaskManager(Input inOut, Stash stash) {
        this.inOut = inOut;
        this.stash = stash;
    }

    public void addNew(Task task) {
        stash.addNewTask(task);
        Output.printNewTask(task, stash.ObtainTaskCount());
    }

    public void toggleTaskStatus(String details) {
        try {
            int index = Integer.parseInt(details) - 1;
            Task task = stash.getTask(index);
            task.toggleDone();
            Output.printIsDone(stash, index);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("The index you have entered is invalid");
        }
    }

    public void  timerModule() {
        Scanner timerCommand = new Scanner(System.in);
        Clock clock = new Clock();
        clock.startWork();
        System.out.println("Press Enter to start a break, or type 'cancel' to stop the timer: ");
        while (timerCommand.hasNextLine()) {
            String line = timerCommand.nextLine();
            if (line.equals("cancel")) {
                clock.cancelClock();
                break;
            } else {
                clock.startBreak();
            }
            timerCommand.close();
        }
    }
    public void inputCommands() {
        while (true) {
            Command userCommand = InputParser.parseInput(inOut.readInput());
            String category = userCommand.getAction();

            switch (category) {
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
            case "todo":
                addNew(new Todo(userCommand.getDetails()));
                continue;
            case "event":
                addNew(new Event(userCommand.getDetails(), userCommand.getStart(), userCommand.getEnd()));
                continue;
            case "deadline":
                addNew(new Deadline((userCommand.getDetails()), userCommand.getDue()));
                continue;
            case "done":
                System.out.println("Bye bye, hope to see you some time soon!");
                return;

            case "timer":
                timerModule();
                continue;

            default:
                System.out.println("Sorry, I do not understand the input at this point in time.");
            }

        }
    }

}