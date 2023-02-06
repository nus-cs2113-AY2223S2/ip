package tasks;

public class Events extends Task{
    private String itemName;
    private String startTime;
    private String endTime;
    public Events(String itemName) {
        super(itemName);
        int indexOfStartTime = itemName.indexOf("/from");
        int indexOfEndTime = itemName.indexOf("/to");
        this.itemName = super.getItemName().substring(0, indexOfStartTime);
        this.startTime = itemName.substring(indexOfStartTime + 6, indexOfEndTime);
        this.endTime = itemName.substring(indexOfEndTime + 4);
    }

    @Override
    public void printTask() {
        String status = new String();
        if (super.isDone()) {
            status = "[E] [X]";
        } else {
            status = "[E] [ ]";
        }
        System.out.println(this.getItemId() + "." + status + " " + this.itemName +
                " (from: " + startTime + "To: " + endTime + ")");
    }

    @Override
    public void printTaskWithoutId() {
        System.out.println( "  [E] " + this.itemName +  " (from: " + startTime + "To: " + endTime + ")");
        OutputDialogueManager.printInteraction(DialogueTypes.COUNT_OF_TASKS);
        System.out.println(getItemCount());
    }
}
