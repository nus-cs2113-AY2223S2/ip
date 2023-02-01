package dude.commands;

import dude.task.ListManager;
import dude.task.Todo;

import java.util.List;

public class Parser {
    public static void parseInput(String input) {
        String[] commands = input.split(" ",2);
        int index;
        switch (commands[0]) {

        case "bye":
            Interface.printBye();
            System.exit(0);
        case "todo":
            ListManager.addTodo(commands[1]);
            break;
        case "deadline":
            String[] deadline = commands[1].split("/by",2);
            ListManager.addDeadline(deadline[0],deadline[1]);
            break;
        case "event":
            String[] description = commands[1].split("/from",2);
            String[] duration = description[1].split("/to",2);
            ListManager.addEvent(description[0],duration[0],duration[1]);
            break;
        case "list":
            ListManager.printList();
            break;
        case "mark":
            index = Integer.parseInt(commands[1]) - 1;
            if (index <= ListManager.getSize()) {
                ListManager.markDone(index);
            } else {
                System.out.println("That task does not exist!");
            }
            break;
        case "unmark":
            index = Integer.parseInt(commands[1]) - 1;
            if (index <= ListManager.getSize()) {
                ListManager.markUndone(index);
            } else {
                System.out.println("That task does not exist!");
            }
            break;
        case "":
            System.out.println("Input a task");
            break;
        }
    }

}
