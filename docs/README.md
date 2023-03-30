# User Guide

## Quick Start

- Ensure you have `Java 11` installed on your computer. You can check your Java version using the following command in the terminal.

`java --version`

- Download the `.jar` file from here: https://github.com/nikkiDEEE/ip/releases
- To run `ThomasShelby`, open the terminal and change the current directory to same one as the jar file.
- Run the following command in the terminal:

`java -jar ip.jar`

## Features 

### Task Manager

Core features of `ThomasShelby` include adding tasks of the following 3 types to a list:

- `todo`: `[T]`
  - Simple tasks with a `description`
- `deadline`: `[D]`
  - Tasks with a `description` and (do) `by`
- `event`: `[E]` 
  - Tasks with a `description` that have a `start` and `end` date

You can also `add`, `delete`, `mark`, `unmark`, and `find` by keyword.

### Save data

- Any changes to tasks are saved locally in the relative file path `/data/data.txt` upon exiting the program. 
- These saved tasks will be loaded automatically upon starting the program again.

## Usage

### `todo` - Adds a ToDo task

This command adds a ToDo task to the list.

Format:
```
todo TASK_DESCRIPTION
```

**Compulsory:** `TASK_DESCRIPTION` corresponds to the description of the task.

Example of usage: 
```
todo grocery shopping
```

Expected outcome:
- a symbol denoting the task type `T`
- the status of completion `[ ]`
- the `TASK_DESCRIPTION`

Description of the outcome.

```
Don't sleep on it.
added: [T][ ] grocery shopping
```

### `deadline` - Adds a Deadline task

This command adds a Deadline task to the list.

Format:
```
deadline TASK_DESCRIPTION /DEADLINE
```

**Compulsory:** 
- `TASK_DESCRIPTION` corresponds to the description of the task
- `/`before the `DEADLINE`

Example of usage:
```
deadline lab submission /sunday 2359
```

Expected outcome:
- a symbol denoting the task type `D`
- the status of completion `[ ]`
- the `TASK_DESCRIPTION`
- the `DEADLINE` details

Description of the outcome:

```
Time is money, chop chop!
added: [D][ ] lab submission (by: sunday 2359)
```

### `event` - Adds an Event task

This command adds an Event task to the list

Format:
```
event TASK_DESCRIPTION \START \END
```

**Compulsory:**
- `TASK_DESCRIPTION` corresponds to the description of the task
- `/`before the `START` and `END`

Example of usage:
```
event jetbrains hackathon /3 march /7 march
```

Expected outcome:
- a symbol denoting the task type `E`
- the status of completion `[ ]`
- the `TASK_DESCRIPTION`
- the `START` and `END` details

Description of the outcome:

```
I'll see you there.
added: [E][ ] jetbrains hackathon (3 march - 7 march)
```

### `list` - Prints tasks

Print a list of all the tasks.

Format:
```
list
```

Example of usage:
```
list
```

Expected outcome:
- `TASK_SERIAL_NUMBER` (e.g., `1.`, `2.` etc)
- followed by the respective task details (description is unique to each task)

Description of the outcome:

```
Here are the tasks in your list:
1. [T][ ] grocery shopping
2. [D][ ] lab submission (by: sunday 2359)
3. [E][ ] jetbrains hackathon (3 march - 7 march)
```

### `mark` - Marks tasks

Marks the task by user-specific `TASK_SERIAL_NUMBER` with an `X`, denoting task completion.

Format:
```
mark TASK_SERIAL_NUMBER
```

**Compulsory:** `TASK_SERIAL_NUMBER` corresponds to the task to be marked (follow the list S/N)

Example of usage:
```
mark 2
```

Expected outcome:
- Marks the task corresponding to `TASK_SERIAL_NUMBER` as complete.
- Quip for completing the task

Description of the outcome:
```
That was long due, well done.
[D][X] lab submission (by: sunday 2359)
```

### `unmark` - Unmarks tasks

Unmarks the task by user-specific `TASK_SERIAL_NUMBER` with an `X`, denoting task completion.

Format:
```
unmark TASK_SERIAL_NUMBER
```

**Compulsory:** `TASK_SERIAL_NUMBER` corresponds to the task to be unmarked (follow the list S/N)

Example of usage:
```
unmark 2
```

Expected outcome:
- Unmarks the task corresponding to `TASK_SERIAL_NUMBER` as incomplete.
- Quip upon unmarking the task

Description of the outcome:
```
You've gotten lazy.
[D][ ] lab submission (by: sunday 2359)
```

### `delete` - Deletes tasks

Deletes a task from the list.

Format:
```
delete TASK_SERIAL_NUMBER
```
**Compulsory:** `TASK_SERIAL_NUMBER` corresponds to the task to be deleted (follow the list S/N)

Example of usage:
```
delete 2
```

Expected outcome:
- Deletes the task corresponding to 'TASK_SERIAL_NUMBER' from the list
- Quip upon deleting the task

Description of the outcome:
```
That's off the list: 
[D][ ] lab submission (by: sunday 2359)
You're left with 2 task(s).
```

### `find` - Finds task(s)

Finds and lists all tasks that contain the user-specific keyword(s).

Format:
```
find TASK_QUERY
```
**Compulsory:** `TASK_QUERY` corresponds to the related tasks to be found

Example of usage:
```
find shopping
```

Expected outcome:
- Prints a list of tasks if `TASK_DESCRIPTION` contains the `TASK_QUERY` keyword.

Description of the outcome:
```
Here are the tasks in your list:
1. [T][ ] grocery shopping
```

### `bye` - Exits the program

Exits ThomasShelby.

Format:
```
bye
```

Example of usage:
```
bye
```

Expected outcome:
- Exclusive ThomasShelby quip.
- Prompt to ensure data has been saved.
- Exits the program.

Description of the outcome:
```
Successful. Tasks saved to 'data.txt' file
Cheers.
```


