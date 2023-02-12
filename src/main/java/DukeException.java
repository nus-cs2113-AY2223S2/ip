public class DukeException extends Exception{
    public String description;
    public DukeException(){
        this.description = "No Description";
    }
    public DukeException(String description) {
        this.description = description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getDescription() {
        return this.description;
    }
}
