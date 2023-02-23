package max.data;

public enum DataError {
    TASK_INVALID_LENGTH("Invalid task token length!"),
    TASK_INVALID_LABEL("Invalid task label!");


    private String errorMessage;

    DataError(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
