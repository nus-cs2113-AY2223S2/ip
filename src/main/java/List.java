import java.util.ArrayList;

	public class List {
		private ArrayList<Task> toDoList;

		public List() {
			this.toDoList = new ArrayList<Task>();
		}

		public void addTask(String userInput) {
			toDoList.add(new Task(userInput));
		}

		public void printList() {
			int counter = 1;
			for (Task task : toDoList) {
				System.out.println(counter + ". " + task.printName());
				counter++;
			}
		}
	}
