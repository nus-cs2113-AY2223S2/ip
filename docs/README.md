# User Guide

## Features 

### Feature-todo

You can use this to create a new todo task with a description.

### Feature-deadline

You can use this to create a new deadline task with a description and a due by date.

### Feature-event

You can use this to create a new event task with a description and a /from time and a /to time.

### Feature-list

You can use this to list out all of the tasks, which will display if in order of and if it is marked as done.

### Feature-mark

You can use this feature to mark a task as done for good up-keeping.

### Feature-unmark

You can use this feature to un-mark a task as done for good up-keeping.

### Feature-delete

You can use this feature to remove a task from the list.

### Feature-find

You can use this feature to list out all of the tasks that match with a specified term.

### Feature-Automatic Saving

All modifications using the aforementioned features will be saved to your device in a text file automatically.

## Usage

### `Keyword` - todo

Add a general task without a time frame.

Example of usage: 

`todo take out the trash`

Expected outcome:
```
	____________________________________________________________
todo take out the trash
	____________________________________________________________
	Got it. I've added this task:
	  [T][ ] take out the trash
	Now you have 1 tasks in the list.
	____________________________________________________________
Successfully wrote to the file.
	____________________________________________________________
```

Description of the outcome.
```
    Reads in the todo and stores it in a list and saves to the file.
```

### `Keyword` - deadline

Add a task with a deadlione.

Example of usage:

`deadline take out the trash /by 7pm`

Expected outcome:
```
	____________________________________________________________
deadline take out the trash /by 7pm
	____________________________________________________________
take out the trash /by 7pm
	Got it. I've added this task:
	  [D][ ] take out the trash (by: 7pm)
	Now you have 2 tasks in the list.
	____________________________________________________________
Successfully wrote to the file.
	____________________________________________________________

```

Description of the outcome.
```
    Reads in the deadline and stores it in a list and saves to the file.
```

### `Keyword` - event

Add a task with a deadlione.

Example of usage:

`event take out the trash /from 8am /to 10am`

Expected outcome:
```
	____________________________________________________________
	Got it. I've added this task:
	  [E][ ] take out the trash (from: 8am to: 10am)
	Now you have 3 tasks in the list.
	____________________________________________________________
Successfully wrote to the file.
	____________________________________________________________

```

Description of the outcome.
```
    Reads in the event and stores it in a list and saves to the file.
```

### `Keyword` - list

Lists out all the entered in tasks and tasks loaded in at start.

Example of usage:

`list`

Expected outcome:
```
list
	____________________________________________________________
Here are the tasks in your list:
	1.[T][ ] take out the trash
	2.[D][ ] take out the trash (by: 7pm)
	3.[E][ ] take out the trash (from: 8am to: 10am)
	____________________________________________________________

```

Description of the outcome.
```
    Lists out all the taskst with their descriptions, times, and if they are marked done or not.
```

### `Keyword` - mark

Mark a task as done.

Example of usage:

`mark 3`

Expected outcome:
```
mark 3
	____________________________________________________________
	Nice! I've marked this task as done:
	  [E][X] take out the trash (from: 8am to: 10am)
	____________________________________________________________
Successfully wrote to the file.
	____________________________________________________________

```

Description of the outcome.
```
    Marked the task as done and displayed the marked task now denoted with '[X]'
```
### `Keyword` - unmark

unmark a task as not done.

Example of usage:

`unmark 3`

Expected outcome:
```
unmark 3
	____________________________________________________________
	OK, I've marked this task as not done yet:
	  [E][ ] take out the trash (from: 8am to: 10am)
	____________________________________________________________
Successfully wrote to the file.
	____________________________________________________________

```

Description of the outcome.
```
    Marked the task as NOT done and displayed the marked task now denoted with '[ ]'
```

### `Keyword` - delete

removes a task from the list from a specified index

Example of usage:

`delete 3`

Expected outcome:
```
delete 3
	____________________________________________________________
	Noted. I've removed this task:
	  take out the trash 
	Now you have 2 tasks in the list.
	____________________________________________________________
Successfully wrote to the file.
	____________________________________________________________

```

Description of the outcome.
```
    Deletes the specified task from the list. Echos what task it was. Resaves to the file because it was now changed.
```

### `Keyword` - find

lists out all the tasks that match a specified term.

Example of usage:

`find trash`

Expected outcome:
```
list
	____________________________________________________________
Here are the tasks in your list:
	1.[T][ ] take out the trash
	2.[D][ ] take out the trash (by: 7pm)
	3.[T][ ] find a book
	____________________________________________________________
find trash
	____________________________________________________________
	____________________________________________________________
	 Here are the matching tasks in your list:

[T][ ] take out the trash
[D][ ] take out the trash (by: 7pm)
	____________________________________________________________


```

Description of the outcome.
```
   Finds all the tasks that match the specified query and lists them out. 
```