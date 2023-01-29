package task;

public enum TaskType {
	TODO("TODO"),
	DEADLINE("DEADLINE"),
	EVENT("EVENT");

	private String value;
	TaskType(String value) {
		this.value = value;
	}
}
