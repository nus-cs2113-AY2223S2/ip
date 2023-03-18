package mom.item;

import mom.utils.Message;
import mom.utils.MessageAction;

public class ItemAction {
    /**
     * Marks an item in the list as done/not done based on the item no.
     * If item no. is cannot be converted to int or invalid, returns an error.
     * 
     * @param parameters Find the item no. specified
     * @param isMark to mark/unmark the item in the list
     * @return Item that was marked/unmarked.
     */
    public static Item markItem(Item item, boolean isMark) {
        item.setStatus(isMark);

        System.out.println(isMark ? Message.INFO_MARK : Message.INFO_UNMARK);
        System.out.println(item);
        return item;
    }

    /**
     * Removes an item from the list.
     * 
     * @param parameters Gets the description and from/to of the item.
     */
    public static void deleteItem(Item removedItem, int itemsSize) {
        MessageAction.printDeleteItemMessage(removedItem, itemsSize);
    }
}
