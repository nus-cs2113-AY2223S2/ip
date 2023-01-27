package tasks;

import io.Out;

public class Task {
    private String description;
    private boolean isCompleted;

    public Task(String description) {
        this.description = description;
        this.isCompleted = false;
    }

    public String getDescription() {
        return description;
    }

    /**
     * Icon taken from
     * {@link https://github.com/sindresorhus/figures/blob/main/index.js}
     */
    public char getStatusIcon() {
        return isCompleted ? '■' : '□';
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void print() {
        Out.printf("  %c %s", getStatusIcon(), getDescription());
    }

    public void toggleCompleted() {
        this.isCompleted = !this.isCompleted;
    }
}
