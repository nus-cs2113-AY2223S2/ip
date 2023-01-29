package duke.task;

public class ToDo extends Task{


    public ToDo(String taskName) {
        super(taskName);
        super.type = "[T]";
    }

    @Override
    public String toString(){
        return checkBoxOutput() + this.taskName;
    }
}
