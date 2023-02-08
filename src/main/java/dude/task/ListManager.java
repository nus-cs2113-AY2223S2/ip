package dude.task;

import dude.Dude;
import dude.commands.Interface;
import dude.commands.Parser;
import exception.*;

import java.util.ArrayList;

public class ListManager {
    private static final ArrayList<Task> list = new ArrayList<>();
    private static int index = 0;

    public static int getSize() {
        return index;
    }

//    public static void addToList(String input,) {
//        if (index == 100) {
//            Interface.listFullMessage();
//            return;
//        }
//        Task inputTask = ;
//        list.add(inputTask);
//        index += 1;
//        Interface.addedMessage(input);
//    }

    public static void printList() {
        System.out.println(Interface.LINE);
        for (int i = 0; i < index; i++) {
            System.out.println((i + 1) + "." + list.get(i));
        }
        System.out.println(Interface.LINE);
    }

    public static Task getString(int index){
        return list.get(index);
    }

    public static void markDone(String index) throws InvalidMarkException {
        int intIndex = Integer.parseInt(index) - 1;
        if(index.equals("") | intIndex >= list.size() | intIndex < 0){
            throw new InvalidMarkException();
        }
        list.get(intIndex).setDone();
        Interface.markDoneMessage();
        System.out.println(list.get(intIndex));
        System.out.println(Interface.LINE);
    }

    public static void markUndone(String index) throws InvalidMarkException {
        int intIndex = Integer.parseInt(index) - 1;
        if(index.equals("") | intIndex >= list.size() | intIndex < 0){
            throw new InvalidMarkException();
        }
        list.get(intIndex).setUndone();
        Interface.markDoneMessage();
        System.out.println(list.get(intIndex));
        System.out.println(Interface.LINE);
    }


    public static void addNewTask(String input,String taskType) throws DudeException {
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
        Interface.addedMessage(index);
        index++;
    }

}
