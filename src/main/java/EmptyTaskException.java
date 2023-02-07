public class EmptyTaskException extends Exception {
    public void printErrorMessage(){
        System.out.println("OOPS! The description of task cannot be empty");
    }
}
