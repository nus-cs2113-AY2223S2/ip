package orca;

public class Deadline extends Task {
    protected String by;

    Deadline(String userInput, int startIdx) throws OrcaException {
        try {
            String[] deadline = userInput.substring(startIdx).split(" /by ");
            this.description = deadline[0];
            this.by = deadline[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new OrcaException("The description/by of a deadline cannot be empty.");
        } catch (StringIndexOutOfBoundsException e) {
            throw new OrcaException("The description/by of a deadline cannot be empty.");
        }
        this.type = "D";
    }

    Deadline(String description, String by, boolean isDone) {
        this.description = description;
        this.by = by;
        this.isDone = isDone;
        this.type = "D";
    }


    /**
     * @return String
     */
    @Override
    public String toString() {
        return super.toString() + " (by: " + this.by + ")";
    }
}
