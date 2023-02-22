package dude.task;

import dude.commands.Io;
import dude.commands.Parser;
import dude.exception.DudeException;
import dude.exception.InvalidDeleteException;
import dude.exception.InvalidFindException;
import dude.exception.InvalidMarkException;
import dude.exception.InvalidUnmarkException;

import java.util.ArrayList;

public class ListManager {
    private static final ArrayList<Task> list = new ArrayList<>();
    private static int index = 0;

    public static Task getTask(int index) {
        return list.get(index);
    }

    public static ArrayList<Task> getList() {
        return list;
    }

    public static void printList() {
        System.out.println(Io.LINE);
        if (list.size() == 0) {
            System.out.println("List currently empty! Please input tasks" + "\n" + Io.LINE);
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + "." + list.get(i));
        }
        System.out.println(Io.LINE);
    }

    public static Task getString(int index) {
        return list.get(index);
    }

    public static void markDone(String index) throws InvalidMarkException {
        try {
            int intIndex = Integer.parseInt(index) - 1;
            if (intIndex >= list.size() || intIndex < 0) {
                throw new InvalidMarkException();
            }
            list.get(intIndex).setDone();
            Io.markDoneMessage();
            System.out.println(list.get(intIndex) + "\n" + Io.LINE);
        } catch (NumberFormatException e) {
            throw new InvalidMarkException();
        }
    }

    public static void markUndone(String index) throws InvalidUnmarkException {
        try {
            int intIndex = Integer.parseInt(index) - 1;
            if (intIndex >= list.size() || intIndex < 0) {
                throw new InvalidUnmarkException();
            }
            list.get(intIndex).setUndone();
            Io.markUndoneMessage();
            System.out.println(list.get(intIndex) + "\n" + Io.LINE);
        } catch (NumberFormatException e) {
            throw new InvalidUnmarkException();
        }
    }


    public static void addNewTask(String input, String taskType, Boolean isSilent) throws DudeException {
        Task newTask;
        switch (taskType) {
        case "todo":
            newTask = Parser.createTodo(input);
            break;
        case "deadline":
            newTask = Parser.createDeadline(input);
            break;
        case "event":
            newTask = Parser.createEvent(input);
            break;
        default:
            newTask = null;
            break;
        }
        list.add(newTask);
        if (!isSilent) {
            Io.addedMessage(index);
        }
        index++;
    }

    public static void deleteTask(String index) throws InvalidDeleteException {
        try {
            int intIndex = Integer.parseInt(index) - 1;
            if (intIndex >= list.size() | intIndex < 0) {
                throw new InvalidDeleteException();
            }
            Io.deletedMessage(intIndex);
            list.remove(intIndex);
            System.out.println("You now have " + list.size() + " tasks in your list");
            ListManager.index--;
        } catch (NumberFormatException e) {
            throw new InvalidDeleteException();
        }
    }

    public static void findTask(String input) throws InvalidFindException{
        if(input.equals("")) {
            throw new InvalidFindException();
        }
        Boolean isFound = false;
        //System.out.println(Io.LINE + "\n" + "Here are the matching tasks in your list:" + "\n");
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getDescription().contains(input.toLowerCase())) {
                if(isFound == false) {
                    System.out.println(Io.LINE + "\n" + "Here are the matching tasks in your list:" + "\n");
                }
                System.out.println((i + 1) + "." + list.get(i));
                isFound = true;
            }
        }
        if(isFound == false) {
            System.out.println("No task found with keyword: " + input);
        }
        System.out.println(Io.LINE);
    }
}
