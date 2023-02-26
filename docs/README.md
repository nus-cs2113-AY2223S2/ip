# Duke User Guide

## Setting up Duke

1. Ensure you have Java 11 or above
2. Download the latest copy of Duke.jar [here]()
3. Move the file to a folder where you want to run Duke
4. Open the command terminal in that same folder
5. Use the following command to run the programme:
`java -jar duke.jar`
6. If duke runs successfully, you will see the following welcome message:
` Hello! I'm Duke
   What can I do for you?`
## Features 

### Add Task

Add a task to the task list.

### Delete Task

Delete a task from the task list.

### List Tasks

Shows all the current tasks in the task list.

### Mark Task

Mark a specific task as done in the task list.

### Unmark Task

Unmark a specific task as done in the task list.

### Find Task

Finds a task with a specific keyword in the task list.

## Usage

### `todo` - Adds a todo task to the task list

Adds a todo task to the task list and shows the number of tasks in the list.

Example of usage: 

`todo read book`

Expected outcome:

A todo type task has been added to the task list, with the number of tasks in the list.

```
 Got it. I've added this task:
 [T][ ] read book
 Now you have 1 task in the list.
```

### `deadline` - Adds a deadline task

Adds a deadline task to the task list and shows the number of tasks in the list.

Example of usage:

`deadline study for exams /by noon`

Expected outcome:

A deadline type task has been added to the task list, with the number of tasks in the list.

```
 Got it. I've added this task:
 [D][ ] study for exams (by: noon)
 Now you have 2 tasks in the list.
```

### `event` - Adds a event task

Adds an event task to the task list and shows the number of tasks in the list.

Example of usage:

`event career fair /from noon /to evening`

Expected outcome:

A deadline type task has been added to the task list, with the number of tasks in the list.

```
 Got it. I've added this task:
 [E][ ] career fair (from: noon to: evening)
 Now you have 3 tasks in the list.
```

### `list` - Shows all tasks

Shows all task in the task list.

Example of usage:

`list`

Expected outcome:

All tasks descriptions.

```
Here are the tasks in the list:
1.[T][ ] read book
2.[D][ ] study for exams (by: noon)
3.[E][ ] career fair (from: noon to: evening)
```

### `delete` - Delete a specified task

Deletes a specified task from the task list and shows the remaining tasks left in the list.

Example of usage:

`delete 2`

Expected outcome:

Deletes the second task in the task list, and shows the remaining number of tasks left.

```
 Noted. I've removed this task:
 [D][ ] study for exams (by: noon)
 Now you have 2 tasks in the list.
```

### `mark` - Mark a specified task as done

Mark the status of a specified task as done.

Example of usage:

`mark 1`

Expected outcome:

Mark an "X" for the task specified.

```
 Nice! I've marked this task as done:
[T][X] read book
```

### `unmark` - Unmark a specified task as done

Unmark the status of a specified task as done.

Example of usage:

`unmark 1`

Expected outcome:

Unmark an "X" for the task specified.

```
 OK, I've marked this task as not done yet:
[T][ ] read book
```

### `find` - Find a task in the task list

Find the tasks containing the specified keywords in the task list.

Example of usage:

`find book`

Expected outcome:

Shows all tasks with the matching keywords in the task list.

```
Here are the matching tasks in your list:
1.[T][ ] read book
```