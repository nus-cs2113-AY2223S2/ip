public class ToDo extends Task{


    public ToDo(String taskName) {
        super(taskName);
        super.type = Types.TODO;
    }

    @Override
    public String listDescription(){
        return checkBoxOutput() + this.getTaskName();
    }
}
