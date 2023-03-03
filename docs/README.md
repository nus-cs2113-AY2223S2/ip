# User Guide

## Features 

### Add Tasks
You can add your todos, deadlines and events one by one into a list.

### Change Task status
After you complete a task, you can mark it as done. 
If you find out you did not finish the task, you can "unmark" the status to not done yet.

### List All Tasks
List all the tasks you have added.

### Delete a Task
You can delete a task by entering its index number.

### Find Tasks
You can search for tasks that contain a certain keyword.
n.
### Save Tasks
When you end the application, the tasks you added will be automatically stored in a local file.
The next time when you start the application, the task data stored in the local file will be loaded
into the task list in your current session.
## Usage

### `todo` - Add a todo task


Template of usage:

`todo {task description}`

Example of usage: 

`todo eat`

Expected outcome:

A todo type of task with the default status of "not done yet" is added
into the task list. `[T]` shows the task is a todo. `[ ]` shows the task has not been done yet. The total number of tasks in the list will also be shown. 

```
Got it. I've added this task:
  [T][ ] eat

Now you have 1 tasks in the list.
```



### `deadline` - Add a task with a deadline


Template of usage:

`deadline {task description} /by {task deadline}`

Example of usage:

`deadline finish ip /by today`

Expected outcome:

A deadline type of task with the default status of "not done yet" is added
into the task list. `[D]` shows the task has a deadline. `[ ]` shows the task has not been done yet.
The total number of tasks in the list will also be shown.

```
Got it. I've added this task:
  [D][ ] finish ip  (by: today)

Now you have 2 tasks in the list.
```


### `event` - Add a task with both start and end time


Template of usage:

`event {event description} /from {start time} /to {end time}`

Example of usage:

`event CS2113 lecture /from 4pm /to 6pm`

Expected outcome:

A deadline type of task with the default status of "not done yet" is added
into the task list. `[E]` shows the task is an event. `[ ]` shows the task has not been done yet.
The total number of tasks in the list will also be shown.

```
Got it. I've added this task:
  [E][ ] CS2113 lecture  (from: 4pm to: 6pm)

Now you have 3 tasks in the list.
```


### `delete` - Delete a task


Template of usage:

`delete {index number}`

Example of usage:

`delete 3`

Expected outcome:

The task with the index number will be deleted.

```
Got it. I've deleted this task:
  [E][ ] CS2113 lecture  (from: 4pm to: 6pm)
```


### `mark` - Mark the task as done


Template of usage:

`mark {index number}`

Example of usage:

`mark 1`

Expected outcome:

The task status with the index number will be marked as done. `[X]` means that
the task has been done.
```
Nice! I've marked this task as DONE:
  [T][X] eat
```

### `unmark` - Mark the task as not done yet


Template of usage:

`unmark {index number}`

Example of usage:

`unmark 1`

Expected outcome:

The task status with the index number will be marked as not done yet.

```
OK! I've marked this task as NOT DONE YET:
  [T][ ] eat
```
### `find` - Search tasks containing a keyword


Template of usage:

`find {keyword}`

Example of usage:

`find eat`

Expected outcome:

A list of matching tasks will be shown.
```
Here are the matching tasks in your list:
1. [T][ ] eat
```

### `list` - List all tasks


Template of usage:

`list`

Example of usage:

`list`

Expected outcome:

All tasks will be shown with their index number, in the order in which they were added.
```
Here is your list!
────────────────────────────────────────────────────────────────────────

1. [T][ ] eat
2. [D][ ] finish ip  (by: today)
```


### `guide` - Show user guide

Template of usage:

`guide`

Example of usage:

`guide`

Expected outcome:

A summary of the keywords in the user guide will be shown.
```
User Guide
list: get the list of all tasks
todo: add a task a task without duration or deadline to the list
deadline: add a task with deadline to the list
event: add a task with both starting and ending time
mark: mark a task as 'done'
unmark: mark a task as 'not done'
delete: delete a task from the list
find: find tasks from the list that match the keyword
```

### `bye` - End the application

Template of usage:

`bye`

Example of usage:

`bye`

Expected outcome:

A farewell message will be shown. Application will then be ended.
The task data will be stored in a local file.
```
 Bye. Hope to see you again soon meow!
```