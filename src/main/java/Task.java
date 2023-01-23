public class Task {
    private static int itemCount;
    private String itemName;
    private int itemId;

    public Task(String itemName) {
        this.itemName = itemName;
        itemCount++;
        this.itemId = itemCount;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public static int getItemCount() {
        return itemCount;
    }

    public int getItemId() {
        return itemId;
    }

    public void printTask() {
        System.out.println(this.itemId + ". " + this.itemName);
    }
}
