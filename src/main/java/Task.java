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

    /**
     * Prints the task in the following format:
     * {@code [index]: [status icon] [description]}
     * 
     * where the index has already been incremented by 1.
     * 
     * @param index The index to be printed
     */
    public void printTask(int index) {
        IO.printf("  %d: %c %s", index + 1, getStatusIcon(), getDescription());
    }

    public boolean setAsCompleted() {
        if (this.isCompleted) {
            return false;
        }
        this.isCompleted = true;
        return true;
    }

    public boolean setAsUncompleted() {
        if (!this.isCompleted) {
            return false;
        }
        this.isCompleted = false;
        return true;
    }
}
