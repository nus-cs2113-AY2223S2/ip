package bro.tasks;

public class ToDo extends Task{
    public ToDo(String name) {
        super(name);
        this.setType("T");
    }
    public ToDo(String name, boolean completed) {
        super(name, completed);
        this.setType("T");
    }
}