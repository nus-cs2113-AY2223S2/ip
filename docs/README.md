# PAPA User Guide
<sub>By Choong Zhan Hong, Version 1.0</sub>

PAPA is your "Personal Assistant, Personal Angel". It will help you keep track of your tasks.

## Features 
Here is a breakdown of features that PAPA can afford you.
Feature usage in detail is below.

| Feature       | Description                         |
|---------------|-------------------------------------|
| Todo Task     | A simple task to track.             |
| Deadline Task | A task with a deadline.             |
| Event Task    | A task with a start and end time.   |
| List          | List all existing tasks.            |
| Find          | Finds task with specified keyword.  |
| Delete        | Deletes a task.                     |
| Help          | Gives you help on commands.         |
| Mark/Unmark   | To mark task completion.            |
| Bye           | To quit and save the tasks to disk. |


## Usage

### Todo Task
### `todo task_description` - Add Todo Task.

A todo task is added to the task list.
No limitation to task description. Spaces and other symbols are OK.

Example of usage: 

`todo eat breakfast`

Expected outcome:

```
Great! I've added the task for you: 
[T][ ] eat breakfast
```

### Deadline Task
### `deadline task_description /by deadline` - Add Deadline Task.

Adds a task with a deadline. 
The start is captured by what comes after `/from`, and likewise
the end is captured by whatever comes after `/to`.

Example of usage:

`deadline do homework /by 7pm`

Expected outcome:

```
Great! I've added the task for you: 
[D][ ] do homework (by: 7pm)
```

### Event Task
### `event task_description /from start /to end` - Add Event Task.

Adds a task with a start and end defined. The deadline is captured by whatever comes after `/by`.

Example of usage:

`event marathon /from 6am /to 10am`

Expected outcome:

```
Great! I've added the task for you: 
[E][ ] marathon from 6am to 10am
```

### List
### `list` - Lists out your tasks.

Lists your tasks (if any). It will also let you know if there are no tasks in your list.

Expected outcome:
```
Your Tasks: 
 1. [T][ ] eat breakfast
 2. [D][ ] do homework (by: 7pm)
 3. [E][ ] marathon from 6am to 10am
```
If no tasks:
```
Looks like you don't have anything to do. Nice!
```

### Delete Task
### `delete task_number` - Delete a specified task.

Deletes a task as specified by its number on the list (See above).

Example of usage:

`delete 1`

Expected outcome:
```
Your Tasks: 
 1. [T][ ] eat breakfast
 2. [D][ ] do homework (by: 7pm)
 3. [E][ ] marathon from 6am to 10am

> delete 1
Noted. I've removed this task:
[T][ ] eat breakfast
```

### Find Task
### `find keyword` - Finds task with specified keyword.

Finds task with description containing the keyword.

Example of usage:

`find homework`

Expected outcome:
```
Your Tasks: 
 1. [T][ ] eat breakfast
 2. [D][ ] do homework (by: 7pm)
 3. [E][ ] marathon from 6am to 10am

> find homework
Here are the matching tasks in your list: 
 1. [D][ ] do homework (by: 7pm)
```

### Mark Task as Done or Undone
### `mark task_number` - Mark a specific task as done.
### `unmark task_number` - Mark a specific task as not done.


Example of usage:
```
> mark 1
Marked as done: [D][X] do homework (by: 7pm)
```
```
> unmark 1
Marked as undone: [D][ ] do homework (by: 7pm)
```

### Help Message
### `help` - Get help message on existing commands.

Example of usage:
```
> help
PAPA is your personal task tracker. Options:
Add tasks: 
   todo <task_name>
   deadline <task_name> /by <due_date>
   event <task_name> /from <start> /to <end>
help        Show these tips.
list        List out existing tasks.
bye         Exit PAPA.
mark <n>    Mark the n-th task as done.
unmark <n>  Mark the n-th task as undone.
```

### Exit PAPA
### `bye` - Saves data to file and quits.
**NOTE:** Please use this command to exit PAPA as it will ensure the data is saved.
Otherwise, you may expect your data to not be saved properly.

Example of usage:
```
> bye
Bye. Hope to see you again soon!
```