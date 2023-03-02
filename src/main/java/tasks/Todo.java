package tasks;

public class Todo extends Task{

    public Todo(String description){
        super(description);
    }
    
   @Override
   public String printTask(){
       return "[T] " + super.printTask();
   }

   public String encode(){
       String encodedString = "todo" + "/" + super.encode();
       return encodedString;
   }
}
