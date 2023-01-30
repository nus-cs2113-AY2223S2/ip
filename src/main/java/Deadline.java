public class Deadline extends Task {
    protected String by;

    public Deadline(String description, int taskID) {
        super(description, taskID);
        if (super.description.contains("/by")) {
            int indexOfBy = super.description.indexOf("/by");
            if (indexOfBy + 3 < super.description.length()) {
                by = super.description.substring(indexOfBy + 3).trim();
                this.description = super.description.substring(0, indexOfBy);
            }
            else {
                System.out.println("You typed wronoglyyyy!");
            }
        }
        else {
            System.out.println("You didn't type correctly (ㆆ_ㆆ)");
        }
        taskCount += 1;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}