package chronos.userinterface;

import chronos.commandhandler.Command;
import chronos.commandhandler.InputParser;
import chronos.inoutput.Input;
import chronos.inoutput.Output;
import chronos.savehandler.Storage;
import chronos.tasktype.Event;
import chronos.tasktype.Task;
import chronos.tasktype.Stash;
import chronos.tasktype.Deadline;
import chronos.tasktype.Todo;
import chronos.exceptions.ChronosExceptions;

import java.util.ArrayList;
import java.util.Scanner;

import chronos.timer.Clock;
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

   public void deleteTask(String details) throws ChronosExceptions.InvalidInputException {
       try {
           int index = Integer.parseInt(details) - 1;
           Task task = stash.getTask(index);
           stash.deleteTask(index);
           //Output.printIsDone(stash, index);
           Output.printDeletedTask(task, stash.ObtainTaskCount());
       } catch (ArrayIndexOutOfBoundsException e) {
           System.out.println("The index you have entered is invalid");
       }

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
            try {
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
                case "delete":
                    deleteTask(userCommand.getDetails());
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
            } catch(ChronosExceptions.InvalidInputException exceptions){
                System.out.println("INVALID INPUT");
            }
        }
    }

}