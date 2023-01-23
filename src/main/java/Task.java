public class Task {
        protected String description;
        protected boolean isDone;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public String getStatusIcon() {
            return (isDone ? "X" : " "); // mark done task with X
        }

        public String markAsDone(boolean isDone)
        {
            this.isDone=true;
            return getStatusIcon();
        }
        public String markAsUnDone(boolean isDone)
        {
            this.isDone=false;
            return getStatusIcon();
        }
}

