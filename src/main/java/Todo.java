public class Todo extends Task {

    public Todo (String description){
        super(description.substring(description.indexOf(" ") + 1));
    }

    public String printTask (){
        if (this.isComplete){
            return "[T][X] " + this.description;
        }
        return "[T][ ] " + this.description; 
    }
}