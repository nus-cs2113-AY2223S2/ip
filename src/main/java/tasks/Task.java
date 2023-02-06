package tasks;

public class Task {
    private static int itemCount = 0;
    private String itemName;
    private int itemId;
    private boolean isDone;


    public Task(String itemName) {
        itemName = itemName.substring(0,1).toUpperCase() + itemName.substring(1);
        this.itemName = itemName;
        itemCount++;
        this.itemId = itemCount;
        this.isDone = false;
    }

    public String getItemName() {
        return itemName;
    }

    public static int getItemCount() {
        return itemCount;
    }

    public int getItemId() {
        return itemId;
    }

    public boolean isDone() {
        return isDone;
    }

<<<<<<< HEAD:src/main/java/tasks/Task.java
    public void setAsDone() {
        this.isDone = true;
    }
    public void setAsUndone() {
        this.isDone = false;
=======
    public void markAsState(boolean state) {
        this.isDone = state;
>>>>>>> branch-Level-5:src/main/java/Task.java
    }
    public void printTask() {
        String status;
        if (isDone) {
            status = "[X]";
        } else {
            status = "[ ]";
        }
        System.out.println(this.itemId + "." + status + " " + this.itemName);
    }

    public void printTaskWithoutId() {
        System.out.println(this.itemName);
        OutputDialogueManager.printInteraction(DialogueTypes.COUNT_OF_TASKS);
        System.out.println(itemCount);
    }
}
