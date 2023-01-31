public class Task {
    protected String description;


    public Task(String description) {
        this.description = description;

    }
    public String getStatusIcon() {
        return "";
    }
    public String getTypeIcon() {
        return "";
    }
    public void setDone(boolean b) {
    }
    public String getDescription () {
        return this.description;
    }
}
