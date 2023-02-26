package tusky.tasks;

public enum TaskType {
    TASK("TASK"),
    EVENT("EVENT"),
    TODO("TODO"),
    DEADLINE("DEADLINE");

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