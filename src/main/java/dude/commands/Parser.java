package dude.commands;

import dude.task.Deadline;
import dude.task.Event;
import dude.task.ListManager;
import dude.task.Todo;
import dude.task.Task;
import exception.*;

import java.util.List;

public class Parser {
    public static void parseInput(String input) throws EmptyInputException {
        String[] commands = input.split(" ", 2);
        String nextCommand =formatNextInput(commands);
        int index;
        try {
            switch (commands[0]) {

            case "todo":
                ListManager.addNewTask(nextCommand,"todo");
                break;
            case "deadline":
                ListManager.addNewTask(nextCommand,"deadline");
                break;
            case "event":
                ListManager.addNewTask(nextCommand,"event");
                break;
            case "list":
                ListManager.printList();
                break;
            case "mark":
                ListManager.markDone(nextCommand);
                break;
            case "unmark":
                ListManager.markUndone(nextCommand);
                break;
            default:
                throw new EmptyInputException();
            }
        } catch (DudeException e){
            System.out.println(e.getMessage());
        }
    }

    public static Task createTodo(String input) throws InvalidTodoException {
        if (input.equals("")) {
            throw new InvalidTodoException();
        }
        return new Todo(input);
    }

    public static Task createDeadline1(String input) throws InvalidDeadlineException {
        if (input.equals("") | !input.contains("/by")) {
            throw new InvalidDeadlineException();
        }
        String[] description = input.split("/by", 2);
        String deadlineDesc = description[0].trim();
        String deadlineDate = description[1].trim();
        if (deadlineDesc.equals("") | deadlineDate.equals("")) {
            throw new InvalidDeadlineException();
        }
        return new Deadline(deadlineDesc, deadlineDate);
    }

    public static Task createEvent(String input) throws InvalidEventException {
        if (input.equals("") | !input.contains("/from") | !input.contains("/to")) {
            throw new InvalidEventException();
        }
        String[] description = input.split("/from", 2);
        String eventDesc = description[0].trim();
        String[] range = description[1].split("/to");
        String eventStart = range[0].trim();
        String eventEnd = range[1].trim();

        if (eventDesc.equals("") | eventStart.equals("") | eventEnd.equals("")){
            throw new InvalidEventException();
        }
        return new Event(eventDesc, eventStart,eventEnd);
    }

    public static String formatNextInput(String[] input){
        String output;
        if(input.length > 1){
            output = input[1].trim();
        } else {
            output = "";
        }
        return output;
    }
}


