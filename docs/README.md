# MAX User Guide

MAX is the individual project component for CS2113, AY22/23 Semester 2.

```
 /$$      /$$  /$$$$$$  /$$   /$$
| $$$    /$$$ /$$__  $$| $$  / $$
| $$$$  /$$$$| $$  \ $$|  $$/ $$/       /^ ^\  -- BORK! I'M MAX!
| $$ $$/$$ $$| $$$$$$$$ \  $$$$/       / 0 0 \
| $$  $$$| $$| $$__  $$  >$$  $$       V\ Y /V
| $$\  $ | $$| $$  | $$ /$$/\  $$       / - \
| $$ \/  | $$| $$  | $$| $$  \ $$      /    |
|__/     |__/|__/  |__/|__/  |__/     V__)  ||
```

MAX is your *paw*-sonal productivity to help you *MAX*-imize your productivity!

## Features

Max comes with features for you to manage your daily tasks efficiently via CLI.

### Task Creation

Manage your work by creating tasks

MAX provides three types of tasks for creation:

1. `Todo`, for all your daily task needs
2. `Deadlines`, for tasks that need to be done fast
3. `Event`, to manage all sorts of appointments and meetings

### Task Management

MAX can help you `mark`, `list` and `fetch` your tasks with ease!

- MAX allows you to tick off your todos, deadline and events
  to give you a sense of accomplishment!
- MAX can also list all your tasks to get a overview of what
  you've done so far!
- Need to fetch or find some tasks easily?
  Tell MAX what you're looking for, and he'll dig it up for you.

### Persistent State

MAX automatically saves all your task data to your local hard drive.
When you start MAX up again,
MAX will load up your tasks in the previous session for you automatically!

## Usage

### `todo` - Add a TODO task

Create a `todo` type of task for MAX to remember.

Syntax:   
``todo (your_task_description)``

Example of usage:

`todo buy max 2 balls!`

Expected outcome:

MAX will read your `todo` description,
remember it and read it back to you via console after adding it to your task list.

```
Got it. Task added:
[T][ ] buy max 2 new balls!
You now have 1 tasks in your list.
```

### `deadline` - Add a Deadline Task

Create a `deadline` type of task for MAX to remember.  
Deadlines have a due date, a compulsory parameter that must be included.

Syntax:

`deadline (description) --by (YYYY-MM-dd HHmm)`

Example of usage:

`deadline feed max ^_^ --by 2023-02-17 1842`

Expected outcome:

MAX will read your `deadline` and due date,
remember it and read it back to you via console after adding it to your task list.

```
Got it. Task added:
[D][ ] feed max ^_^ (by: Feb 17 1842, 2023)
You now have 1 tasks in your list.
```

### `event` - Add an Event Task

Create a `event` type of task for MAX to remember.  
Events have a from and to date.  
These are compulsory parameters that must be included.

Syntax:

`event (description) --from (YYYY-MM-dd HHmm) --to (YYYY-MM-dd HHMM)`

Note that the order of `--to` and `--from` arguments do not matter!

Example of usage:

`event Max's vet appointment! --from 2023-12-13 0842 --to 2023-12-13 1949`

Expected outcome:

MAX will read your `event`, from and to dates.  
MAX will remember it and read it back to you via console after adding it to your task list.

```
Got it. Task added:
[E][ ] Max's vet appointment! (FROM: Dec 13 0842, 2023, TO: Dec 13 1949, 2023)
You now have 1 tasks in your list.
```

### `list` - Get your Task List

Get your list of tasks.  
The list contains an overview of each tasks' status.

Example of usage:

`list`

Expected outcome:

Returns your task list, with tasks sorted in chronological order when it was added.

```
Here's what's in your list:
1. [T][ ] buy max 2 new balls!
2. [D][ ] feed max ^_^ (by: Feb 17 1842, 2023)
3. [E][ ] Max's vet appointment! (FROM: Dec 13 0842, 2023, TO: Dec 13 1949, 2023)
```

### `mark` - Tag task as done

Marks a task in the tasklist as done.  
The task number is the number of the task from running `list`.
If the task is already done,
MAX will still carry on and mark it done again.

Syntax:  
``mark (task_number)``

Example of usage:

`mark 2`

Expected outcome:

MAX tags the task as done and reads the done task back to you.

If the task number is invalid, MAX will let you know.

```
Okay, marking this task as done: 
[D][X] feed max ^_^ (by: Feb 17 1842, 2023)
```

### `unmark` - Tag task as undone

Marks a task in the tasklist as NOT done.  
The task number is the number of the task from running `list`.
If the task is already not done,
MAX will still carry on and mark it as undone again.

Syntax:  
`unmark (task_number)`

Example of usage:

`unmark 2`

Expected outcome:

MAX tags the task as done and reads the done task back to you.

If the task number is invalid, MAX will let you know.

```
Okay, setting this task as undone: 
[D][ ] feed max ^_^ (by: Feb 17 1842, 2023)
```

### `delete` - Remove a Task

Removes a task from your task list.
The task number is the number of the task from running `list`.

Syntax:  
`delete (task_number)`

Example of usage:

`delete 2`

Expected outcome:

MAX removes the task matching the task number from your tasklist.
After that, MAX reads the removed task back to you.

If the task number is invalid, MAX will let you know.

```
Woof woof this task will be rem-woofed:
[D][ ] feed max ^_^ (by: Feb 17 1842, 2023)
You now have 2 tasks in your list.
```

### `fetch` or `find` - Describe action

Ask MAX to fetch tasks that match a search query.  
Fetch and Find do the same thing. Find is an alias for Fetch.

Syntax:

`find (your_query)`

The search query can be an arbitrarily long string with spaces.

Example of usage:

`find max`

Expected outcome:

MAX tags the task as done and reads the done task back to you.

If the task number is invalid, MAX will let you know.

```
Max sniffed out these matching tasks:
1. [T][ ] buy max 2 new balls!
2. [D][ ] feed max ^_^ (by: Feb 17 1842, 2023)
```

### `debug` - Activate debug mode

Activates a developer-friendly mode for testing.  
In debug mode, MAX will not save any data to disk.

Example of usage:

`debug`

Expected outcome:

MAX activates debug mode and warns you of the implications.

```
--- BORK BORK ---- THIS IS IMPORTANT! ---- BORK BORK ---
MAX is now in debug mode. No data will be saved or loaded from disk.
To exit debug mode, restart MAX.
```

### `exit` - Exit MAX

Exits MAX, returning you to your shell that executed MAX.

Example of usage:

`exit`

Expected outcome:

MAX says goodbye.

```
Goodbye! Thank you for using MAX.
```
