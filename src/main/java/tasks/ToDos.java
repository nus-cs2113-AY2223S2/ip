package tasks;

public class ToDos extends Task{
    public ToDos(String itemName) {
        super(itemName);
    }

    @Override
    public void printTask() {
        String status = new String();
        if (super.isDone()) {
            status = "[T] [X]";
        } else {
            status = "[T] [ ]";
        }
        System.out.println(this.getItemId() + "." + status + " " + this.getItemName());
    }

    @Override
    public void printTaskWithoutId() {
        System.out.println( "  [T] " + this.getItemName());
        OutputDialogueManager.printInteraction(DialogueTypes.COUNT_OF_TASKS);
        System.out.println(getItemCount());
    }
}
