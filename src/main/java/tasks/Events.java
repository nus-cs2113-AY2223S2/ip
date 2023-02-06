package tasks;
import enums.DialogueTypes;
import errors.InvalidEventException;
import managers.OutputDialogueManager;

public class Events extends Task{
    private String itemName;
    private String startTime;
    private String endTime;
    private int itemId;
    public Events(String itemName) throws InvalidEventException {
        super(itemName);
        int indexOfStartTime = itemName.indexOf("/from");
        int indexOfEndTime = itemName.indexOf("/to");
        if (indexOfEndTime == -1 || indexOfStartTime == -1) {
            throw new InvalidEventException();
        }
        this.itemName = super.getItemName().substring(0, indexOfStartTime);
        this.startTime = itemName.substring(indexOfStartTime + 6, indexOfEndTime);
        this.endTime = itemName.substring(indexOfEndTime + 4);
        incrementItemCount();
        this.itemId = getItemCount();
    }

    @Override
    public void printTask() {
        String status;
        if (super.isDone()) {
            status = "[E] [X]";
        } else {
            status = "[E] [ ]";
        }
        System.out.println(this.itemId + "." + status + " " + this.itemName +
                " (from: " + startTime + "To: " + endTime + ")");
    }

    @Override
    public void printTaskWithoutId() {
        System.out.println( "  [E] " + this.itemName +  " (from: " + startTime + "To: " + endTime + ")");
        OutputDialogueManager.printInteraction(DialogueTypes.COUNT_OF_TASKS);
        System.out.println(getItemCount());
    }
}
