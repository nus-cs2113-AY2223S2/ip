# User Guide
## What is Limey
Limey is a CLI application used to manage any tasks that you may have. It is capable of remembering which task you have done and which you have not yet so you never need to worry about forgetting about important events and deadlines

## Features 
### Adding task
-Todo, deadline or event

### Find a task
  
### List all tasks
  
### Mark a task as done
  
### Un-mark a task
  
### Delete a task
  
### Get help


## Usage

### `todo TASKNAME` - adds a task of todo type to your task list

Example of usage: 

`todo clean dishes` - adds clean dishes to the task list as a todo type task

```
____________________________________________________________
	added: clean dishes
	Total number of current tasks: 4
____________________________________________________________
```
### `deadline TASKNAME /by YYYY-MM-DDTHH:MM` - adds a task of deadline type to your task list

Example of usage: 

`deadline lab assignment /by 2023-02-08T23:59` - adds lab assignment which is due on 8th Feb 2023 at 11:59PM to the task list as a deadline type task

```
____________________________________________________________
	added: lab assignment (by: 08 Feb 2023 11:59 PM)
	Total number of current tasks: 2
____________________________________________________________
```
### `event TASKNAME /by YYYY-MM-DDTHH:MM` - adds a task of deadline type to your task list

Example of usage: 

`event exam /from 2023-02-08T14:00 /to 2023-02-08T15:30` - adds exam which is from 8th Feb 2023 at 2PM to 3:30PM to the task list as a event type task

```
____________________________________________________________
	added: exam (from 08 Feb 2023 2:00 PM to 08 Feb 2023 3:30 PM)
	Total number of current tasks: 3
____________________________________________________________

```
### `find SEARCHTERM` - searches through all tasks for searchterm
Example of usage: 

`find lab assignment` - prints all tasks with "lab assignment" in the name

```
____________________________________________________________
	1.[D][ ] lab assignment (by: 08 Feb 2023 11:59 PM)
____________________________________________________________

```
### `list` - list all tasks
Example of usage: 

`list` - prints all tasks 

```
____________________________________________________________
	1.[T][ ] clean dishes
	2.[D][ ] lab assignment (by: 08 Feb 2023 11:59 PM)
	3.[E][ ] exam (from 08 Feb 2023 2:00 PM to 08 Feb 2023 3:30 PM)
____________________________________________________________

```
### `mark LISTNUM` - marks the LISTNUM item in the task list as done
Example of usage: 

`mark 2` - to mark the second item on the list as done

```
____________________________________________________________
	I have just marked this task as done:
	[D][X] lab assignment (by: 08 Feb 2023 11:59 PM)
____________________________________________________________

```
### `unmark LISTNUM` - marks the LISTNUM item in the task list as not done
Example of usage: 

`unmark 2` - to mark the second item on the list as not done

```
____________________________________________________________
	This task has been marked as not done yet:
	[D][ ] lab assignment (by: 08 Feb 2023 11:59 PM)
____________________________________________________________

```
### `delete LISTNUM` - deletes the LISTNUM item in the task list
Example of usage: 

`delete 2` - to delete the second item on the list as not done

```
____________________________________________________________
	This task has been deleted:
	[D][ ] lab assignment (by: 08 Feb 2023 11:59 PM)
____________________________________________________________

```
### `help` - prints all valid commands with useful examples
Example of usage: 

```
____________________________________________________________
	The following are some helpful commands on how to use Limey.
	To add a task(todo/deadline/event):
		"todo math" OR "deadline lab assignment /by 2023-02-08T23:59" OR "event exam /from 2023-02-08T14:00 /to 2023-02-08T15:30"
	To find a task:
		"find lab assignment" returns all tasks with "lab assignment" in the name
	To list all tasks:
		"list" lists all tasks
	To mark a task:
		"mark 2" to mark the second item on the list
	To un-mark a task:
		"unmark 2" to un-mark the second item on the list
	To delete a task:
		"delete 2" to delete the second item on the list
	To get help from this page again:
		"help" will show all the helpful commands listed above
____________________________________________________________
```

