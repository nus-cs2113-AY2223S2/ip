public class ToDos extends Task {
    private String taskLabel = "[T]";
    public ToDos (String input){
        super(input.substring(5)); // for ToDos tasks, description = input
        super.setTaskLabel(taskLabel);
    }
    @Override
    public String toString(){
        return this.taskLabel + this.mark + " " + this.description;
    }
}
