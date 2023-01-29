package task;


public class Deadline extends ToDo {
    protected String endDate;

	public Deadline(String description, String endDate) throws EmptyDescriptionException {
        // TODO consider when endDate is null
        super(description);
        setEndDate(endDate);
    }
    public void setEndDate(String newEndDate) {
        endDate = newEndDate;
    }
    public String getEndDate() {
        return endDate;
    }
    @Override
    public String toString() {
        return String.format("[D][%c] %s (by: %s)", getStatusIcon(), description, endDate);
    }
}