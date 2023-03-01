# User Guide

Welcome to the Haru task bot! This bot can be used as your personal todo list.
```
	____________________________________________________________
	 __   __
	|  | |  |   ____     _  _   _    _
	|  |_|  |  / _  \   | |/_\ | |  | |
	|   _   | | |_|  \  |  /   | \_/  |
	|__| |__|  \___/\_\ |_|     \__/|_|

	Hello! I'm Haru
	What can I do for you?
	____________________________________________________________
```
Ensure that there is a folder named "data" with a .txt file named "tasklist" inside to read and write the tasks successfully. 

## Features

### Add Task

Add Todo, Deadline, or Event.

### List Tasks

List current tasks.

### Find Tasks

Search for tasks containing a keyword.

### Mark Task as Done

Mark a task as done.

### Unmark Task as Undone

Unmark a task as not done.

### Delete Tasks

Delete a task.

### Exit Program

Exit program and save all changes made to the list of tasks.

## Usage 

### Add Task

Format of usage:

1) `todo (task description)`
2) `deadline (task description) /by dd-M-yyyy hh:mm a`
3) `event (task description) /from dd-M-yyyy hh:mm a /to dd-M-yyyy hh:mm a`


Example of usage:

1) `todo homework`
2) `deadline return book /by 14-2-2023 01:00 PM`
3) `event project meeting /from 02-3-2023 12:00 PM /to 02-3-2023 02:00 PM`

### List Tasks

Format of usage: `list`

Example output:

```
list
	____________________________________________________________
	Here are the tasks in your lists:
	1.[T][X] borrow book
	2.[T][ ] homework
	3.[D][X] return book (by: 14 February 2023 01:00 PM)
	4.[E][X] project meeting (from: 02 March 2023 12:00 PM to: 02 March 2023 02:00 PM)
	5.[D][ ] iP (by: 03 March 2023 10:00 AM)
	6.[D][ ] wrap book present (by: 21 May 2023 09:30 AM)
	____________________________________________________________
```

### Find Tasks

Format of usage: `find (keyword)`

Example usage: `find book`

Example output:

```
find book
	____________________________________________________________
	Here are the matching tasks in your list:
	1.[T][X] borrow book
	3.[D][X] return book (by: 14 February 2023 01:00 PM)
	6.[D][ ] wrap book present (by: 21 May 2023 09:30 AM)
	____________________________________________________________
```

### Mark Task as Done

Format of usage: `mark (task number)`

Example usage: `mark 2`

Example output:

```
list
	____________________________________________________________
	Here are the tasks in your lists:
	1.[T][X] borrow book
	2.[T][ ] homework
	3.[D][X] return book (by: 14 February 2023 01:00 PM)
	4.[E][X] project meeting (from: 02 March 2023 12:00 PM to: 02 March 2023 02:00 PM)
	5.[D][ ] iP (by: 03 March 2023 10:00 AM)
	6.[D][ ] wrap book present (by: 21 May 2023 09:30 AM)
	____________________________________________________________
mark 2
	____________________________________________________________
	Nice! I've marked this task as done:
	[T][X] homework
	____________________________________________________________

```

### Unmark Task as Undone

Format of usage: `unmark (task number)`

Example usage: `unmark 2`

Example output:

```
list
	____________________________________________________________
	Here are the tasks in your lists:
	1.[T][X] borrow book
	2.[T][X] homework
	3.[D][X] return book (by: 14 February 2023 01:00 PM)
	4.[E][X] project meeting (from: 02 March 2023 12:00 PM to: 02 March 2023 02:00 PM)
	5.[D][ ] iP (by: 03 March 2023 10:00 AM)
	6.[D][ ] wrap book present (by: 21 May 2023 09:30 AM)
	____________________________________________________________
unmark 2
	____________________________________________________________
	OK, I've marked this task as not done yet:
	[T][ ] homework
	____________________________________________________________

```

### Delete Tasks

Format of usage: `delete (task number)`

Example usage: `delete 2`

Example output:

```
list
	____________________________________________________________
	Here are the tasks in your lists:
	1.[T][X] borrow book
	2.[T][ ] homework
	3.[D][X] return book (by: 14 February 2023 01:00 PM)
	4.[E][X] project meeting (from: 02 March 2023 12:00 PM to: 02 March 2023 02:00 PM)
	5.[D][ ] iP (by: 03 March 2023 10:00 AM)
	6.[D][ ] wrap book present (by: 21 May 2023 09:30 AM)
	____________________________________________________________
delete 2
	____________________________________________________________
	Noted. I've removed this task:
		[T][ ] homework
	Now you have 5 tasks in the list.
	____________________________________________________________

```

### Exit Program

Format of usage: `bye`

Example output:

```
bye
	____________________________________________________________
	Bye. Hope to see you again soon!
	____________________________________________________________

```
