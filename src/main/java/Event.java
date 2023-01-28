public class Event extends Task {
    
    public String startTime;
    public String endTime;

    public Event(String description, String start, String end){
        super(description);
        this.startTime = start;
        this.endTime = end;
    }
    
    public String printTask (){
        if (this.isComplete){
            return "[E][X] " + this.description;
        }
        return "[E][ ] " + this.description; 
    }
}