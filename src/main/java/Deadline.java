public class Deadline extends Task {
    
    public String dueDate;

    public Deadline (String description, String dueDate){
        super(description);
        this.dueDate = dueDate;
    }
    
    public String printTask (){
        if (this.isComplete){
            return "[D][X] " + this.description;
        }
        return "[D][ ] " + this.description; 
    }
}