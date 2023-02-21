# User Guide for Duke

## Features 

### Add and manage your own tasks

Manage your list of tasks easily by adding them to Duke.

### Keep track of task statuses

Easily mark and unmark your list of tasks in order to keep track of what tasks you have compeleted.

## Usage

### `todo` - Add a todo task.

Adds a todo task into the list of tasks.

Example of usage: 

`todo read book`

Expected outcome:

```
__________________________
To do added: read book
__________________________
```

### `deadline` - Add a deadline task.

Adds a deadline task into the list of tasks. Specify the deadline with `/by` of format ```yyyy-mm-dd```.

Example of usage: 

`deadline return book /by 2021-06-05`

Expected outcome:

```
__________________________
Deadline added: return book (by: Jun 05 2021)
__________________________
```

### `event` - Add an event task.

Adds an event task into the list of tasks. Specify the start and end of the event with `/from` and `/to` respectively.

Example of usage: 

`event project meeting /from Sun 4pm /to 6pm`

Expected outcome:

```
__________________________
Event added: project meeting (from: Sun 4pm to: 6pm)
__________________________
```

### `list` - List all current tasks.

Lists out all current tasks and their information.

Example of usage: 

`list`

Expected outcome:

```
__________________________
List of Tasks: 

1.[T][ ] read book
2.[D][ ] return book (by: Jun 05 2021)
3.[E][ ] project meeting (from: Sun 4pm to: 6pm)
__________________________
```

### `mark` and `unmark` - Mark or unmark a task.

Marks or unmarks a task based on a given task number.

Example of usage: 

`mark 1`
`list`

Expected outcome:

```
__________________________
Task set as done: read book
__________________________
```
```
__________________________
List of Tasks: 

1.[T][X] read book
2.[D][ ] return book (by: Jun 05 2021)
3.[E][ ] project meeting (from: Sun 4pm to: 6pm)
__________________________
```

### `find` - Find tasks.

Finds all tasks which contains the specified keyword in its task name and prints its respective tasknumber and information.

Example of usage: 

`find book`

Expected outcome:

```
__________________________
Tasks with matching keywords:

1.[T][X] read book
2.[D][ ] return book (by: Jun 05 2021)
__________________________
```

### `delete` - Delete a task.

Deletes the task of the specified task number. Deleting a task shifts the task numbers of subsequent tasks up.

Example of usage: 

`delete 1`
`list`

Expected outcome:

```
__________________________
Task 1 has been deleted
__________________________
```
```
__________________________
List of Tasks: 

1.[D][ ] return book (by: Jun 05 2021)
2.[E][ ] project meeting (from: Sun 4pm to: 6pm)
__________________________
```

