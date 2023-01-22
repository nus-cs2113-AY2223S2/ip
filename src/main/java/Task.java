public class Task {
    private String name;
    private boolean completed;

    public Task(String name) {
        this.name = name;
        this.completed = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted() {
        this.completed = true;
    }
    public void setUncompleted() {
        this.completed = false;
    }

    public String mark() {      // Method to return completion mark "✔"
        if (this.completed) {
            return "✔";
        }
        else {
            return " ";
        }
    }
}
