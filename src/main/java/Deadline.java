public class Deadline extends Task {
    protected String by;

    Deadline(String userInput, int startIdx) {
        String[] deadline = userInput.substring(startIdx).split(" /by ");
        this.description = deadline[0];
        this.by = deadline[1];
        this.type = "D";
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + this.by + ")";
    }
}
