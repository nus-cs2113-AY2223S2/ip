import errors.InvalidDeadlineException;

public class Deadlines extends Task{
    public static final int DEADLINE_DIVIDER_LENGTH = 4;
    private String itemName;
    private String deadline;

    public Deadlines(String itemName)
            throws InvalidDeadlineException {
        super(itemName);
        int indexOfDivider = itemName.indexOf("/by");
        if (indexOfDivider == -1 ||
                itemName.substring(indexOfDivider + DEADLINE_DIVIDER_LENGTH).isEmpty()) {
            throw new InvalidDeadlineException();
        }
        this.itemName = super.getItemName().substring(0,indexOfDivider);
        this.deadline = itemName.substring(indexOfDivider + DEADLINE_DIVIDER_LENGTH);
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
