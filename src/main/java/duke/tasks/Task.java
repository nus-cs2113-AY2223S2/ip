package duke.Tasks;

public class Task {
    protected String status;
    protected String type;
    protected String description;

    public Task (String type, String status, String description) {
        this.type = type;
        this.status = status;
        this.description = description;
    }

    /**
     * Returns a string displaying the Task details
     * @return printFormat eg: [T] [ ] toDo
     */
    public String getPrintFormat() {
        return type + " " + status + " " + description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
