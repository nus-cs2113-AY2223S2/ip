package bro.tasks;

public class Task {
    private String name;
    private boolean completed;
    private String type;
    public Task(String name) {
        this.name = name;
        this.type = null;
        this.completed = false;
    }
    public Task(String name, boolean completed) {
        this.name = name;
        this.type = null;
        this.completed = completed;
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
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String mark() {      // Method to return completion mark "✔"
        if (this.completed) {
            return "✔";
        }
        else {
            return " ";
        }
    }
    @Override
    public String toString() {
        return name;
    }
}
