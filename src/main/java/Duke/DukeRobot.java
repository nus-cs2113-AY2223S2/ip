package Duke;

import Duke.DukeCommandLine.DukeCommandLineInput;
import Duke.DukeCommandLine.DukeTaskInputException;
import Duke.DukeFunction.DukeList;
import Duke.DukeFunction.DukePrinter;
import Duke.DukeTask.DukeDeadline;
import Duke.DukeTask.DukeEvent;
import Duke.DukeTask.DukeTask;

import java.util.Scanner;

public class DukeRobot {
    private static DukeCommandLineInput processCommandLine(String commandLine) {
        commandLine = commandLine.trim();
        if(!commandLine.contains(" ")) {
            return new DukeCommandLineInput(commandLine, "");
        }
        String[] command = commandLine.split(" ");
        String lineRemaining = commandLine.substring(commandLine.indexOf(" ")+1);
        lineRemaining = lineRemaining.trim();
        return new DukeCommandLineInput(command[0], lineRemaining);
    }
    private static DukeTask processTask(String inputTask) throws DukeTaskInputException {
        if(inputTask.equals("")) {
            throw new DukeTaskInputException("☹ OOPS!!! The description of a task cannot be empty.");
        }
        return new DukeTask(inputTask);
    }
    private static DukeDeadline processDeadline(String inputTask) throws DukeTaskInputException {
        try {
            String deadlineName = inputTask.substring(0, inputTask.indexOf("/by"));
            deadlineName = deadlineName.trim();
            String deadlineTime = inputTask.substring(inputTask.indexOf("/by")+4);
            deadlineTime = deadlineTime.trim();
            return new DukeDeadline(deadlineName, deadlineTime);
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeTaskInputException("\"Please use the format: " +
                    "deadline <task name> /by <deadline time>");
        }
    }
    private static DukeEvent processEvent(String inputTask) throws DukeTaskInputException {
        try {
            String eventName = inputTask.substring(0, inputTask.indexOf("/from")-1);
            eventName = eventName.trim();
            String eventTimeFrom = inputTask.substring(inputTask.indexOf("/from")+6,
                    inputTask.indexOf("/to")-1);
            eventTimeFrom = eventTimeFrom.trim();
            String eventTimeTo = inputTask.substring(inputTask.indexOf("/to")+4);
            return new DukeEvent(eventName, eventTimeFrom, eventTimeTo);
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeTaskInputException("Please use the format: " +
                    "event <task name> /from <event time from> /to <event time to>");
        }
    }
    public static void main(String[] args) {
        DukePrinter.printGreeting();
        DukeList.loadTask();
        Scanner in = new Scanner(System.in);
        while (true) {
            String commandLine = in.nextLine();
            DukeCommandLineInput command = processCommandLine(commandLine);
            int id;
            switch (command.getCommandType()){
            case "bye":
                DukePrinter.printStringln("Bye. Hope to see you again soon!");
                DukePrinter.printGoodbyeLogo();
                return;
            case "list":
                DukeList.listTask();
                break;
            case "mark":
                try {
                    id = Integer.parseInt(command.getCommandMessage());
                } catch (NumberFormatException integerException) {
                      DukePrinter.printErrorln("Sorry, the id is invalid!");
                    break;
                }
                DukeList.markDone(id-1);
                break;
            case "unmark":
                try {
                    id = Integer.parseInt(command.getCommandMessage());
                } catch (NumberFormatException integerException) {
                    DukePrinter.printErrorln("Sorry, the id is invalid!");
                    break;
                }
                DukeList.unmarkDone(id-1);
                break;
            case "delete":
                try {
                    id = Integer.parseInt(command.getCommandMessage());
                } catch (NumberFormatException integerException) {
                    DukePrinter.printErrorln("Sorry, the id is invalid!");
                    break;
                }
                DukeList.deleteTask(id-1);
                break;
            case "todo":
                try {
                    DukeTask newTask = processTask(command.getCommandMessage());
                    DukeList.addTask(newTask);
                } catch (DukeTaskInputException e) {
                    DukePrinter.printErrorln(e.getMessage());
                }
                break;
            case "deadline":
                try {
                    DukeDeadline deadline = processDeadline(command.getCommandMessage());
                    DukeList.addTask(deadline);
                } catch (DukeTaskInputException e) {
                    DukePrinter.printErrorln(e.getMessage());
                }
                break;
            case "event":
                try {
                    DukeEvent event = processEvent(command.getCommandMessage());
                    DukeList.addTask(event);
                } catch (DukeTaskInputException e) {
                    DukePrinter.printErrorln(e.getMessage());
                }
                break;
            case "save":
                DukeList.saveTask();
                break;
            default:
                DukePrinter.printErrorln("Sorry, I don't know what that means :-( ("
                        + command.getCommandType() + ")");
                break;
            }
        }
    }
}
