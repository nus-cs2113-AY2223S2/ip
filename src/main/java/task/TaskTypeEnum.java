package task;

public enum TaskTypeEnum {
	TODO("TODO"),
	DEADLINE("DEADLINE"),
	EVENT("EVENT");

	private String value;
	TaskTypeEnum(String value) {
		this.value = value;
	}
}