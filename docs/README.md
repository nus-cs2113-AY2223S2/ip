# Sherlock User Guide

## Features

### Adding a task
Add different types of tasks to your tasks list

You can put todos, deadlines, events, as well as any other
generic task you have in mind.

### Listing tasks
List all the tasks you have now

### Finding a task
Find specific tasks by some keyword

### Tracking tasks completion
Mark your tasks as DONE or NOT DONE

### Storing tasks
The program stores and retrieves all of your added task that are
automatically added to a file named tasks.txt.

## Usage
### `add` - add a generic task

Use this command when your task doesn't fit any of the standard 
task types.

Example of usage:

`add go to library`

Expected outcome:

The program notifies you of the new task being
created and the total number of the tasks in your list.

The task status is set by default as NOT DONE.
```
Got it. I've added this task:
[ ] go to library
Now you have 1 task in the list.
```
### `todo` - add a todo

Use this command when you want to save some todo that has no 
time constraints.

Example of usage:

`todo gym`

Expected outcome:

The program notifies you of the new todo being
created and the total number of the tasks in your list.

The todo status is set by default as NOT DONE.
```
Got it. I've added this task:
[T][ ] gym
Now you have 2 tasks in the list.
```

### `deadline` - add a task with deadline

Use this command when your task is due to some date.
Specify the deadline with a ***/by*** flag using the following format:

`dd-MM-yyyy(required) HH:mm(optional)`

The default time if not specified is 23:59.

Example of usage:

`deadline thesis /by 23-05-2023`

Expected outcome:

The program notifies you of the new deadline being
created and the total number of the tasks in your list.

The deadline status is set by default as NOT DONE.
```
Got it. I've added this task:
[D][ ] thesis (by: 23 May 2023 23:59)
Now you have 3 tasks in the list.
```

### `event` - add an event

Use this command when your type of task is some event
(e.g., conference).

Specify the event time frames with /from and /to flags 
that should have a following format:

`dd-MM-yyyy(required) HH:mm(optional)`

The default time if not specified is 23:59.

Example of usage:

`event Web Dev Conf /from 05-05-2023 08:00 /to 10-05-2023`

Expected outcome:

The program notifies you of the new event being
created and the total number of the tasks in your list.

The event status is set by default as NOT DONE.
```
Got it. I've added this task:
[E][ ] Web Dev Conf (from: 05 May 2023 08:00; to: 10 May 2023 23:59)
Now you have 4 tasks in the list.
```

### `list` - list all of your tasks

Use this command to list all your tasks.

The program lists each task in a following format:

`#. {Type} {Task Name} {(by, from, to if such exist)}`

Example of usage:

`list`

Expected outcome:

```
1. [ ] go to library
2. [T][ ] gym
3. [D][ ] thesis (by: 23 May 2023 23:59)
4. [E][ ] Web Dev Conf (from: 05 May 2023 08:00; to: 10 May 2023 23:59)
```
### `mark` - mark the task as DONE 

Use this command to mark your task as DONE.

You should use the index provided in the `list` command.

Example of usage:

`mark 3`

Expected outcome:

```
Nice! I've marked this task as done:
[D][X] thesis (by: 23 May 2023 23:59)
```
### `unmark` - unmark the task back to NOT DONE

Use this command to unmark your task back to NOT DONE.

You should use the index provided in the `list` command.

Example of usage:

`unmark 3`

Expected outcome:

```
OK, I've marked this task as not done yet:
[D][ ] thesis (by: 23 May 2023 23:59)
```

### `find` - find the task by some term

Use this command to find all tasks that have a given term.

Example of usage:

- `find blah`

    Expected outcome (Nothing is found):
    
    ```
    No task is found for this term
    ```
- `find web`

  Expected outcome:

  ```
    Here are the matching tasks in your list:
    1. [E][ ] Web Dev Conf (from: 05 May 2023 08:00; to: 10 May 2023 23:59)
    2. [D][ ] create website (by: 06 Aug 2023 23:59)
  ```

### `delete` - delete the task by its index

Use this command to delete a task.

You should use the index provided in the `list` command.

Example of usage:

`delete 3`

Expected outcome:

The program notifies you of the task being
deleted and the new number of the tasks in your list.

```
Noted. I've removed this task:
[D][ ] thesis (by: 23 May 2023 23:59)
Now you have 4 tasks in the list.
```

### `bye` - exit the Sherlock program

Use this command to exit the program.

Example of usage:

`bye`

Expected outcome:

```
Bye. Hope to see you again soon!
```
