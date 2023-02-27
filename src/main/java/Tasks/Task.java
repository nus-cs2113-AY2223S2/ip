package Tasks;

import javax.swing.plaf.PanelUI;

public class Task {

    protected String type;
    protected String description;
    protected boolean isDone;

    public String getType() { return type; }
    public String getDescription() {
        return description;
    }

    public boolean getIsDone() {
        return isDone;
    }


    // class initialization
    public Task(String description, String type) {
        this.description = description;
        this.isDone = false;
        this.type = type;
    }

    // class modifiers
    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    // returning status icon
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String getStatusFileIcon() { return (isDone ? "1" : "0"); }

    public String toString() {
        return '[' + getStatusIcon() + "] "+ description + " ";
    }
}
