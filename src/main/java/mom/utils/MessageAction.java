package mom.utils;

import java.util.ArrayList;

import mom.item.Item;

public class MessageAction {
    /**
     * Prints the list of items.
     * 
     * @params items List of items that will be printed.
     */
    public static void printList(ArrayList<Item> items) {
        System.out.println(Message.INFO_LIST);
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            System.out.println((i+1) + ". " + item);
        }
    }

    /**
     * Prints the list of filtered items.
     * 
     * @params filteredItems List of filtered items that will be printed.
     */
    public static void printFilteredList(ArrayList<Item> filteredItems) {
        if (filteredItems.isEmpty()) {
            System.out.println(Message.INFO_FIND_NONE);
            return;
        }

        System.out.println(Message.INFO_FIND);
        for (int i = 0; i < filteredItems.size(); i++) {
            Item item = filteredItems.get(i);
            System.out.println((i+1) + ". " + item);
        }
    }

    /**
     * Prints the welcome message when application is launched.
     */
    public static void printWelcomeMessage() {
        System.out.println(Message.LINE);
        System.out.println(Message.INFO_WELCOME);
        System.out.println(Message.LINE);
    }

    /**
     * Prints the message after an item is added.
     * 
     * @param item Prints the description and status of the item.
     * @param totalItems Prints the total number of items from the list.
     */
    public static void printAddItemMessage(Item item, int totalItems) {
        System.out.println(Message.INFO_ITEM_ADD);
        System.out.println(item);
        System.out.printf(Message.INFO_ITEM_COUNT.toString(), totalItems);
    }

    /**
     * Prints the message after an item is deleted.
     * 
     * @param item Prints the description and status of the item.
     * @param totalItems Prints the total number of items from the list.
     */
    public static void printDeleteItemMessage(Item item, int totalItems) {
        System.out.println(Message.INFO_ITEM_DELETE);
        System.out.println(item);
        System.out.printf(Message.INFO_ITEM_COUNT.toString(), totalItems);
    }
}
