# User Guide

## Features 

### Add Task to List

Add a ```todo```, ```deadline```, or ```event``` task to list.

### Delete Task 

Users can ```delete``` a todo, deadline, or event to list. 

### Manage Task

Able to ```mark```, ```unmark```, ```list```, and ```find``` tasks in list.

### Save Task

Can ```save``` tasks in a file called duke.txt.

## Usage

### `deadline` - add a deadline task

- Add a deadline to the list of tasks.
- Format: `deadline [task description] /by [date]`
- Example of usage: `deadline return book /by Sunday`
- Expected outcome:

```
Got it. I've added this task:
[D][ ] return book (by: Sunday)
Now you have X tasks in the list. 
```
### `todo` - add a todo task

- Add a todo to the list of tasks.
- Format: `todo [description]`
- Example of usage: `todo borrow book`
- Expected outcome:

```
Got it. I've added this task:
[T][ ] borrow book
Now you have X tasks in the list. 
```
### `event` - add an event task

- Add a todo to the list of tasks.
- Format: `event [description] /from [start date and time] /to [end date and time]`
- Example of usage: `event project meeting /from Mon 2pm /to 4pm`
- Expected outcome:

```
Got it. I've added this task:
[E][ ] project meeting (from: Mon 2pm to: 4pm)
Now you have X tasks in the list. 
```
### `list` - list all tasks 

- List all tasks in tasks list.
- Example of usage: `list`
- Expected outcome:

```
Here are the tasks in your list:
1.[T][] read book
2.[D][ ] return book (by: June 6th)
3.[E][ ] project meeting (from: Aug 6th 2pm to: 4pm)
```
### `mark` - mark task as complete

- Mark a task as done.
- Format: `mark [task number]`
- Example of usage: `mark 1`
- Expected outcome:

```
Nice! I've marked this task as done:
[T][X] return book
```
### `unmark` - unmark task as not complete

- Mark a task as not done by unmarking.
- Format: `unmark [task number]`
- Example of usage: `unmark 1`
- Expected outcome:

```
OK, I've marked this task as not done yet:
[T][ ] read book
```
### `delete` - delete a task in list

- Delete a task in the list.
- Format: `delete [task number]`
- Example of usage: `delete 3`
- Expected outcome:

```
Noted. I've removed this task:
[E][ ] project meeting (from: Aug 6th 2pm to: 4pm)
Now you have 4 tasks in the list.
```
### `find` - find a task in the list

- Delete a task in the list.
- Format: `find [keyword]`
- Example of usage: `find book`
- Expected outcome:

```
Here are the matching tasks in your list:
1.[T][X] read book
2.[D][X] return book (by: June 6th)
```
### `bye` - exit the app

- Terminate the application.
- Example of usage: `bye`
- Expected outcome:

```
Bye. Hope to see you again soon!
```
