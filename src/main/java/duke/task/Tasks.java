package duke.task;

import java.util.ArrayList;

public abstract class Tasks {
    private String item;
    private boolean isMarked;
    public Tasks(String item, boolean isMarked) {
        this.item = item;
        this.isMarked = isMarked;
    }
    public void setMarked(boolean isMarked) {
        this.isMarked = isMarked;
    }
    public String toString() {
        String status;
        if (isMarked) {
            status = "[X] ";
        } else {
            status = "[ ] ";
        }
        return status + item;
    }
}
