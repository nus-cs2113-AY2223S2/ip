public class EmptyDescriptionException extends Exception {
    protected String typeOfTask;

    public EmptyDescriptionException (String typeOfTask) {
        this.typeOfTask = typeOfTask;
    }
    public void printErrorMessage() {
        if (typeOfTask.equals("marked") || typeOfTask.equals("unmarked")) {
            System.out.println("OOPS!! Task to be " + typeOfTask + " was not specified!");
        } else {
            System.out.println("OOPS!! The description of a " + typeOfTask + " cannot be empty!");
        }
    }
}
