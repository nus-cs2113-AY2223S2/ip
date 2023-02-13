package tasks;

import enums.DialogueTypes;
import managers.OutputDialogueManager;
public class ToDos extends Task{
    public ToDos(String itemName) {
        super(itemName);
        incrementItemCount();
    }

    @Override
    public void printTask() {
        String status;
        if (super.isDone()) {
            status = "[T] [X]";
        } else {
            status = "[T] [ ]";
        }
        System.out.println("." + status + " " + this.getItemName());
    }

    @Override
    public void printTaskWithoutId() {
        System.out.println( "  [T] " + this.getItemName());
        OutputDialogueManager.printInteraction(DialogueTypes.COUNT_OF_TASKS);
        System.out.println(getItemCount());
    }

    @Override
    public String getClassType () {
        return "T";
    }

    @Override
    public String getToStore() {
        return " " + this.getItemName();
    }
}
