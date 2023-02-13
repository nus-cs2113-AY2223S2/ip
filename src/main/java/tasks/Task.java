package tasks;
import enums.DialogueTypes;
import managers.OutputDialogueManager;

public abstract class Task {
    private static int itemCount = 0;
    private String itemName;
    private int itemId;
    private boolean isDone;


    public Task(String itemName) {
        itemName = itemName.substring(0,1).toUpperCase() + itemName.substring(1);
        this.itemName = itemName;
        this.isDone = false;
    }

    public String getItemName() {
        return itemName;
    }

    public static int getItemCount() {
        return itemCount;
    }


    public static void incrementItemCount() {
        Task.itemCount++;
    }


    public boolean isDone() {
        return isDone;
    }

    public void markAsState(boolean state) {
        this.isDone = state;
    }
    public abstract void printTask();

    public abstract void printTaskWithoutId();
    public abstract String getClassType();
    public abstract String getToStore();
}
