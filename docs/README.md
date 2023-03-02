User Guide
Features
Adding a Todo Task
Add a new Todo task to the list of tasks.

Adding a Deadline Task
Add a new Deadline task to the list of tasks.

Adding an Event Task
Add a new Event task to the list of tasks.

Listing all Tasks
List all the tasks in the TaskManager.

Marking a Task as Done
Mark a task in the TaskManager as done.

Unmarking a Task
Unmark a task in the TaskManager.

Deleting a Task
Delete a task from the TaskManager.

Clearing all Tasks
Clear all the tasks from the TaskManager.

Finding Tasks by Keyword
Find tasks in the TaskManager by a keyword.

Usage
todo - Add a Todo task
Add a new Todo task to the list of tasks.

Example of usage:

todo read book

Expected outcome:

Roger. The following todo has been added:
[T][ ] read book
You now have 1 item in the list

deadline - Add a Deadline task
Add a new Deadline task to the list of tasks.

Example of usage:

deadline return book /by Sunday

Expected outcome:

Roger. The following deadline has been added:
[D][ ] return book (by: Sunday)
You now have 1 item in the list

event - Add an Event task
Add a new Event task to the list of tasks.

Example of usage:

event project meeting /at Monday 2pm

Expected outcome:

Roger. The following event has been added:
[E][ ] project meeting (at: Monday 2pm)
You now have 1 item in the list

list - List all Tasks
List all the tasks in the TaskManager.

Example of usage:

list

Expected outcome:

Here are the tasks in your list:
1.[T][ ] read book
2.[D][ ] return book (by: Sunday)
3.[E][ ] project meeting (at: Monday 2pm)

mark - Mark a Task as Done
Mark a task in the TaskManager as done.

Example of usage:

mark 2

Expected outcome:

Nice! I've marked this task as done:
[D][X] return book (by: Sunday)


unmark - Unmark a Task
Unmark a task in the TaskManager.

Example of usage:

unmark 2

Expected outcome:

Nice! I've unmarked this task:
[D][ ] return book (by: Sunday)
delete - Delete a Task
Delete a task from the TaskManager.

Example of usage:

delete 2

Expected outcome:

Noted. I've removed this task:
[D][ ] return book (by: Sunday)

Example of usage:

clear

Expected outcome:

All data deleted. You have no items in your list.

find - Find Tasks by Keyword
Find tasks in the TaskManager by a keyword.

Example of usage:

find book

Expected outcome:

Here are the matching tasks in your list:
1.[T][ ] read book