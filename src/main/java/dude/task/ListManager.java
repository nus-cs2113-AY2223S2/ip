package dude.task;

import dude.commands.Interface;

import java.util.ArrayList;

public class ListManager {
    private static final ArrayList<Task> list = new ArrayList<>();
    private static int index = 0;

    public static int getSize() {
        return index;
    }

    public static void addToList(String input) {
        if (index == 100) {
            Interface.listFullMessage();
            return;
        }
        Task inputTask = new Task(input);
        list.add(inputTask);
        index += 1;
        Interface.addedMessage(input);
    }

    public static void printList() {
        System.out.println(Interface.LINE);
        for (int i = 0; i < index; i++) {
            System.out.println((i + 1) + "." + list.get(i));
        }
        System.out.println(Interface.LINE);
    }

    public static void markDone(int index) {
        list.get(index).setDone();
        Interface.markDoneMessage();
        System.out.println(list.get(index));
        System.out.println(Interface.LINE);
    }

    public static void markUndone(int index) {
        list.get(index).setUndone();
        Interface.markUndoneMessage();
        System.out.println(list.get(index));
        System.out.println(Interface.LINE);
    }
}
