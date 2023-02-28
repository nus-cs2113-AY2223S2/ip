# User Guide
Duke is a helpful tool for managing a variety of chores, assisting you in organizing your day and increasing productivity.
All the best features of any task manager are available to you in a compact yet potent package thanks to its optimized CLI performance!

## Features 

### Store a list of tasks

- Only three different type of tasks are allowed to store ( todo, deadline, event).

### Mark/ Unmark task

- Keep track of the progress of the task.

### View all tasks
- View all saved tasks and  its progress.

### Find occurrence of specified tasks
- find tasks in the list which contains the input keyword.

### Delete task
- Remove specified task form the main list

## Usage

### `todo` - Adds a todo task to the list

A todo task is task with no associated dateTime parameters.
Adds a named todo task to the task list, which will be stored on local device.

Example of usage: 

`todo study CS2113 final`

Expected outcome:

A todo task of name "study CS2113 final" is added to the task list

```
Got it. I've added this task:
[T][ ] study CS2113 final
Now you have X in the list
```
### `deadline` - Adds a deadline task to the list

- A deadline task is task with **one** associated dateTime parameters.
- Adds a named deadline task to the task list, which will be stored on local device.
- Must follow specified dateTime format `MMM d yyyy ha` , For example `May 3 2023 9PM` ( capital `PM`)

Example of usage:

`deadline study CS2113 final /by May 3 2023 9PM`

Expected outcome:

A deadline task of name "study CS2113 final" is added to the task list

```
Got it. I've added this task:
[D][ ] study CS2113 final (by: May 3 2023 9PM )
Now you have X in the list
```

### `event` - Adds a event task to the list

- A event task is task with **two** associated dateTime parameters, namely startTime and endTime for the event
- Adds a named event task to the task list, which will be stored on local device.
- Must follow specified dateTime format `MMM d yyyy ha` , For example `May 3 2023 9PM` ( capital `PM`)


Example of usage:

`event study CS2113 final /from May 1 2023 4PM /to May 3 2023 6PM`

Expected outcome:

A event task of name "study CS2113 final" is added to the task list

```
Got it. I've added this task:
[E][ ] study CS2113 final (from: May 1 2023 4PM to: May 3 2023 6PM)
Now you have X in the list
```

### `list` - prints all tasks that are saved in your local device

- Shows all tasks with taskType, task name, progress and associated time.

Example of usage:

`list`

Expected outcome:

```
Here are the tasks in your list:
1.[D][X] study CS2113 final (by: May 3 2023 9PM )
2.[E][ ] study CS2113 final (from: May 1 2023 4PM to: May 3 2023 6PM)
```

### `mark` - Mark a specific task as done

- The task specified will be marked as done and changes will be stored on your local device

Example of usage:

`mark 1`

Expected outcome:

```
OK, I've marked this task as done:
[D][X] study CS2113 final (by: May 3 2023 9PM )
```

### `unmark` - Mark a specific task as  **NOT** done

- The task specified will be marked as **NOT** done and changes will be stored on your local device

Example of usage:

`unmark 1`

Expected outcome:

```
OK, I've marked this task as not done yet:
[E][ ] study CS2113 final (from: May 1 2023 4PM to: May 3 2023 6PM)
```

### `delete` - Delete specific tasks

- The task specified will be removed and changes will be stored on your local device

Example of usage:

`delete 1`

Expected outcome:

```
Noted. I've removed this task:
[T][ ] study
Now you have 2 tasks in the list.
```


### `find` - find and shows all tasks with keywords

- The list of tasks which contain the keyword will be print to CLI.

Example of usage:

`find study`

Expected outcome:

```
Here are the matching tasks in your list:
1. [E][ ] study CS2113 final (from: May 1 2023 4PM to: May 3 2023 6PM)
2. [T][ ] study CS2040
3. [D][ ] research study (by: May 3 2023 7PM )
```


### `bye` - Ends the program

- Closes the program, tasks created will be stored in local device

Example of usage:

`bye`

Expected outcome:

```
 Bye. Hope to see you again soon!
```


