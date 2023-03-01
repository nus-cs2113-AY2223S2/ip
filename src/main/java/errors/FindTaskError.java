package errors;

public class FindTaskError  extends Exception{
    public FindTaskError(){
        super("There seems like some error in finding this task");
    }

    public FindTaskError(String message){
        super(message);
    }
}
