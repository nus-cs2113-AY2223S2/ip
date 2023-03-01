package task;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        setDescription(description);
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getIsDone() {
        return (isDone ? "1" : "0");
    }

    public String nthNumber(int number) {
        if (number > 0) {
            int lastDigit = number % 10;
            int secondLastDigit = (number / 10) % 10;

            if (secondLastDigit == 1) {
                return "th";
            } else {
                switch (lastDigit) {
                    case 1:
                        return "st";
                    case 2:
                        return "nd";
                    case 3:
                        return "rd";
                    default:
                        return "th";
                }
            }
        } else {
            return "";
        }
    }

    public String stringRepresentation() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }
}
