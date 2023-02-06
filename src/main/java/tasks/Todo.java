package tasks;

import tasks.Task;

public class Todo extends Task {

    public Todo(String description, int num){
        super(description,num);
    }
    public String toString() {
        return num + ".[T]" + super.toString();
    }
}
