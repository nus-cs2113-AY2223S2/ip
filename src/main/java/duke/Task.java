package duke;

/**
 * Represents a task in a task list.
 * It consists of the description of the task and
 * a checkbox to note if the task is completed or not
 */

public class Task {

        /**
         * Creates a task.
         * @param description is the task name
         * @param isDone defines if a task is done
         */
        protected String description;
        protected boolean isDone;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        /**
         * To find out if a task is done
         * @return if the task is done or not
         */

        public String getStatusIcon() {
            return (isDone ? "X" : " "); // mark done task with X
        }

        public void markAsDone() {
            this.isDone=true;
        }
        public void markAsUnDone() {
            this.isDone=false;
        }

        public String taskDescription() {
            return description;
        }

        /**
         * To display the task in a specific way
         * @return the format of how the task is shown
         */
        public String toString() {
            return "["+ getStatusIcon() +"] " + description;
        }


}

