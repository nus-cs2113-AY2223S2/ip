package hina.task;

import hina.task.Task;

public class Deadline extends Task {
    private String by;
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String toString() {
        String mark;
        if (super.isDone()) {
            mark = "X";
        } else {
            mark = " ";
        }
        return String.format("[D][%s] %s (by: %s)", mark, super.getDescription(), by);
    }
}
