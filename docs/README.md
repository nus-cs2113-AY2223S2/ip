# User Guide
This project, 'Duke', is
a Personal Assistant Chatbot that helps a person 
to keep track of various things.
## Features 

### Feature- 3 types of tasks
1. Todo. A todo is a task with description.
2. Deadline. A deadline is a task with description and a deadline.
3. Event. An event is a task with description, a start time and an end time.


## Usage
### `Function` - List:
show the list of all the tasks added.
### `Function` - Add: 
add a task of todo, deadline or event type.

`Format:`
'todo' + task description

'deadline' + task description + /by + end time

'event' + task description + /from +start time + by/ + end time

Example of usage (Sample input): 
````
`todo read book`
`deadline return book /by 3pm`
`event meeting /from 4pm /to 5pm`
````
Expected outcome:
````
list:
1.[T][ ]read book
2.[D][ ]return book /by 3pm
3.[E][ ]meeting /from 4pm /to 5pm`
````
### `Function` - mark/unmark
mark a task as done, or unmark a task as not done yet.

Example of usage (Sample input):
````
`mark 1`
`mark 2`
`unmark 1`
````
Expected outcome:
````
list:
	1.[T][ ]read book
	2.[D][X]return book /by 3pm
	3.[E][ ]meeting /from 4pm /to 5pm
````
### `Function` - delete
delete a task by its index

Example of usage (Sample input):
````
`delete 3`
````
Expected outcome:
````
list:
	1.[T][ ]read book
	2.[D][X]return book /by 3pm
````

### `Function` - find
find all the task that contains the given content.

Example of usage (Sample input):
````
`find book`
````
Expected outcome:
````
Here are the matching task in your list:
	[T][ ]read book
Here are the matching task in your list:
	[D][X]return book /by 3pm
````
### `Function` - help
print the help list

Expected outcome:
````
Enter "todo task_name" to add a task.
Enter "event task_name /from start_time /to end_time" to add an event.
Enter "deadline task_name /by end_time" to add a deadline
Enter "mark index"to mark a task as done.
Enter "unmark index"to mark a task as not done.
Enter "delete index"to delete a task.
Enter "find content"to find task(s) that contain the content.
Enter "list" to print all the tasks.
Enter "bye" to terminate this program.
````
