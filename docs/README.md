# User Guide

Duke is a personal assistant that helps manage a list of tasks.

## Key Features

### Manage your tasks

Duke can save your tasks, and keep track of whether you've done a task.

### Find your tasks

Forgot what you've typed as the task name? Duke can find it for you if you have a word you remember you typed!

## Usage

### `bye` - exit

Exits the program.

### `list` - lists all tasks

Lists all the tasks in your list. Duke will also include the type of task, whether it's been completed, and other
relevant information.

Example of usage:

`list`

Expected outcome:

```
...
Here are the tasks in your list:
1.[T][ ] math homework
```

### `todo` - add a todo task

Adds a task of type Todo.

Example of usage:

`todo math homework`

Expected outcome:

```
...
Got it. I've added this task:
[T][ ] math homework
```

### `event`  - add an event

Adds a task of type Event.

Example of usage:

`event math lecture /from 1pm /to 3pm`

Expected outcome:

```
...
Got it. I've added this event:
 [E][ ] math lecture (from: 1pm  to: 3pm)
```

### `deadline` - add a deadine

Adds a task of type Deadline.

Example of usage:

`deadline english essay /by 2pm`

Expected outcome:

```
...
Got it. I've added this deadline:
 [D][ ] english essay (by: 2pm)
```

### `mark` - mark task as done

Mark a task as done.

Example of usage:

`mark 1`

Expected outcome:

```
...
Nice! I've marked this task as done:
[T][x] math homework
```

### `unmark` - unmark task

Unmarks a task as done - marks a task as undone.

Example of usage:

`unmark 1`

Expected outcome:

```
...
OK, I've marked this task as not done yet:
[T][ ] math homework
```

### `remove` - remove a task

Removes a task from the list of tasks.

Example of usage:

`remove 1`

Expected outcome:

```
...
Okay, I've removed task 1
```

### `find` ` finds a task

Finds a task based on the keywords provided.

Example of usage:

`find lecture`

Expected outcome:

```
...
Here are the matching tasks in your list:
2.[E][ ] math lecture  (from: 1pm  to: 3pm)
```
