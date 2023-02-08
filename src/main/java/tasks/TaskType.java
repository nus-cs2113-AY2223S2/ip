package tasks;

public enum TaskType {
    TASK("task"),
    EVENT("event"),
    TODO("todo"),
    DEADLINE("deadline");

    private final String code;

    TaskType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString(){
        return code;
    }
}