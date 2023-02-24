public class Todo extends Task {

    /**
    * Initializer for Todo class
    *
    * @param description the name of the todo task
    */
    public Todo(String description) {
        super(description);

    }
    
    /**
    * method to get the task icon 
    *
    * @return T because it stands for ToDo
    */
    @Override
    public String getTaskIcon() {
        return "T";
    }


}
