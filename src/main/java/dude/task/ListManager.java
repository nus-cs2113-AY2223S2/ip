package dude.task;

import dude.commands.Interface;
import dude.commands.Parser;
import exception.*;

import java.util.ArrayList;

public class ListManager {
    private static final ArrayList<Task> list = new ArrayList<>();
    private static int index = 0;

    public static Task getTask(int index){
        return list.get(index) ;
    }
    public static ArrayList<Task> getList(){
        return list;
    }
    public static void printList() {
        System.out.println(Interface.LINE);
        if(list.size() == 0){
            System.out.println("List currently empty! Please input tasks");
            System.out.println(Interface.LINE);
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + "." + list.get(i));
        }
        System.out.println(Interface.LINE);
    }

    public static Task getString(int index) {
        return list.get(index);
    }

    public static void markDone(String index) throws InvalidMarkException {
        try {
            int intIndex = Integer.parseInt(index) - 1;
            if (intIndex >= list.size() | intIndex < 0) {
                throw new InvalidMarkException();
            }
            list.get(intIndex).setDone();
            Interface.markDoneMessage();
            System.out.println(list.get(intIndex));
            System.out.println(Interface.LINE);
        } catch (NumberFormatException e){
            throw new InvalidMarkException();
        }
    }

    public static void markUndone(String index) throws InvalidUnmarkException {
        try {
            int intIndex = Integer.parseInt(index) - 1;
            if (intIndex >= list.size() | intIndex < 0) {
                throw new InvalidUnmarkException();
            }
            list.get(intIndex).setUndone();
            Interface.markUndoneMessage();
            System.out.println(list.get(intIndex));
            System.out.println(Interface.LINE);
        } catch (NumberFormatException e){
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
        if(!isSilent){
            Interface.addedMessage(index);
        }
        index++;
    }

    public static void deleteTask(String index) throws InvalidDeleteException{
        try {
            int intIndex = Integer.parseInt(index) - 1;
            if (intIndex >= list.size() | intIndex < 0) {
                throw new InvalidDeleteException();
            }
            Interface.deletedMessage(intIndex);
            list.remove(intIndex);
            System.out.println("You now have " + list.size() + " tasks in your list");
            ListManager.index--;
        } catch (NumberFormatException e) {
            throw new InvalidDeleteException();
        }
    }
}
