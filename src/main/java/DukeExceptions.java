public abstract class DukeExceptions extends Exception {
    
    private String message;

    private static final String BARRIER = "____________________________________________________________";

    public DukeExceptions(String errorMsg) {
        this.message = errorMsg;
    }

    public String getMessage() {
        return this.message;
    }

    public static class noTasksException extends DukeExceptions {
        public noTasksException(String errorMsg) {
            super(errorMsg);
            System.out.println(BARRIER + "\n\n" + errorMsg);
        }
    }

    public static class invalidNumberException extends DukeExceptions {
        public invalidNumberException(String errorMsg) {
            super(errorMsg);
            System.out.println(errorMsg);
        }
    }

    public static class taskStatusException extends DukeExceptions {
        public taskStatusException(String errorMsg) {
            super(errorMsg);
            System.out.println(errorMsg);
        }
    }
}