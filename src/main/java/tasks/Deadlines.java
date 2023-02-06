package tasks;

public class Deadlines extends Task{
    private String itemName;
    private String deadline;

    public Deadlines(String itemName) {
        super(itemName);
        int indexOfDivider = itemName.indexOf("/by");
        this.itemName = super.getItemName().substring(0,indexOfDivider);
        this.deadline = itemName.substring(indexOfDivider + 4);
    }

    @Override
    public void printTask() {
        String status = new String();
        if (super.isDone()) {
            status = "[D] [X]";
        } else {
            status = "[D] [ ]";
        }
        System.out.println(this.getItemId() + "." + status + " " + this.itemName + " (by: " + deadline + ")");
    }

    @Override
    public void printTaskWithoutId() {
        System.out.println( "  [D] " + this.itemName + " (by: " + deadline + ")");
        OutputDialogueManager.printInteraction(DialogueTypes.COUNT_OF_TASKS);
        System.out.println(getItemCount());
    }
}
