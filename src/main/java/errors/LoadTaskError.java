package errors;

public class LoadTaskError extends Exception{
    public LoadTaskError(){
        super ("There seems to be some error loading this task, your file must be corrupted");
    }

    public LoadTaskError(String message){
        super(message);
    }
}
