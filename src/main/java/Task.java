public interface Task {
    String task = "";
    boolean isComplete = false;

    String getTask();
    char getComplete();
    void setComplete();
    void setIncomplete();
}
