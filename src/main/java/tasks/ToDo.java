package tasks;

import enums.DialogueTypes;
import managers.OutputDialogueManager;
public class ToDo extends Task {

    public static final String TODO_CLASS = "T";
    public static final String DONE_TODO_INDICATOR = "[T] [X]";
    public static final String NOT_DONE_TODO_INDICATOR = "[T] [ ]";

    public ToDo(String itemName) {
        super(itemName);
        incrementItemCount();
    }

    @Override
    public void printTask() {
        String status;
        if (super.isDone()) {
            status = DONE_TODO_INDICATOR;
        } else {
            status = NOT_DONE_TODO_INDICATOR;
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
        return TODO_CLASS;
    }

    @Override
    public String getToStore() {
        return " " + this.getItemName();
    }
}
