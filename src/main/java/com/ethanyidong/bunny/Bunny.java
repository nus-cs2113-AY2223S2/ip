package com.ethanyidong.bunny;

import com.ethanyidong.bunny.arg.InvalidCommandException;

import java.util.ArrayList;
import java.util.Scanner;

public class Bunny {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        BunnySession bunny = new BunnySession();

        bunny.printMessage("Hello! I'm Bunny.\nWhat can I do for you?");

        replloop:
        while (true) {
            String input = in.nextLine();
            ParsedCommand inputCommand = new ParsedCommand(input);

            try {
                inputCommand.validateCommand(bunny);
            } catch (InvalidCommandException ice) {
                bunny.printMessage(ice.toString());
                continue;
            }

            switch (inputCommand.getCommand()) {
            case BYE:
                break replloop;
            case LIST: {
                if (bunny.numTasks() == 0) {
                    bunny.printMessage("Your TODO list is empty!");
                } else {
                    ArrayList<String> messageLines = new ArrayList<>();
                    for (int i = 0; i < bunny.numTasks(); i++) {
                        messageLines.add((i + 1) + ". " + bunny.getTask(i));
                    }
                    bunny.printMessage(messageLines);
                }
                break;
            }
            case MARK: {
                int markIndex = Integer.parseInt(inputCommand.getPositionalArgument()) - 1;
                bunny.getTask(markIndex).markAsDone();
                bunny.printMessage("Nice! I've marked this task as done:\n\t" + bunny.getTask(markIndex));

                break;
            }
            case UNMARK: {
                int unmarkIndex = Integer.parseInt(inputCommand.getPositionalArgument()) - 1;
                bunny.getTask(unmarkIndex).markAsNotDone();
                bunny.printMessage("Nice! I've marked this task as not done yet:\n\t" + bunny.getTask(unmarkIndex));
                break;
            }
            case ADD_TODO: {
                Task newTask = new Todo(inputCommand.getPositionalArgument());
                bunny.addTask(newTask);
                bunny.printMessage("I've added this task: " + newTask + "\nNow you have " + bunny.numTasks() + " tasks in the list.");
                break;
            }
            case ADD_DEADLINE: {
                Task newTask = new Deadline(inputCommand.getPositionalArgument(), inputCommand.getFlagArgument("by"));
                bunny.addTask(newTask);
                bunny.printMessage("I've added this task: " + newTask + "\nNow you have " + bunny.numTasks() + " tasks in the list.");
                break;
            }
            case ADD_EVENT: {
                Task newTask = new Event(inputCommand.getPositionalArgument(),
                        inputCommand.getFlagArgument("from"),
                        inputCommand.getFlagArgument("to"));
                bunny.addTask(newTask);
                bunny.printMessage("I've added this task: " + newTask + "\nNow you have " + bunny.numTasks() + " tasks in the list.");
                break;
            }
            default: {
                bunny.printMessage("That is not a valid command!");
            }
            }
        }
        bunny.printMessage("Bye. Hope to see you again soon!");
    }
}
