# User Guide

## Features 

1. Supports three different types of tasks: `Todo`, `Deadline` and `Event`
2. Supports marking tasks as completed or not completed
3. Displays stored tasks
4. Reminds user about upcoming deadlines
5. Supports searching for previously added tasks

## Symbols used
- T: `Todo`
- D: `Deadline`
- E: `Event`
- \[ \]: Task not yet completed
- \[X\]: Task is completed

## Todo

Adds a task without deadlines

### Usage

### `todo`

Example of usage: 

`todo [task-description]`

Expected outcome:

```
------------------------------------------------------------
Got it. I have added this task to the list!
    [T][ ] [task-description]
Now you have [n] tasks in the list.

------------------------------------------------------------
```

## Deadline

Adds a task with a deadline

### Usage

### `deadline`

Example of usage: 

`deadline [task-description] /by [date]`

Expected outcome:

```
------------------------------------------------------------
Got it. I have added this task to the list!
    [D][ ] [task-description] (by: [date])        
Now you have [n] tasks in the list.

------------------------------------------------------------
```

### Date Format
- Date Format: "yyyy-M-d" or "yyyy-M-d HH:mm"
   - If no time is specified, Duke sets time to 23:59
- Deadline cannot be set in the past, else task will not be added

## Event

Adds a task with a start and end date

### Usage

### `event`

Example of usage: 

`event [task-description] /from [start-date] /to [end-date]`

Expected outcome:

```
------------------------------------------------------------
Got it. I have added this task to the list!
    [E][ ] [task-description] ([start-date] - [end-date])        
Now you have [n] tasks in the list.

------------------------------------------------------------
```

### Date Format
- Date Format: "yyyy-M-d" or "yyyy-M-d HH:mm"
   - If no time is specified, Duke sets time to 23:59
- End Date cannot be set in the past, else task will not be added
- Start Date cannot be after End Date, else task will not be added

## List tasks

Displays all added tasks

### Usage

### `list`

Example of usage: 

`list`

Expected outcome:

```
------------------------------------------------------------
    Here are the tasks in your list:
1.[T][ ] [todo-description]
2.[D][ ] [deadline-description] (by: [date])
3.[E][X] [event-description] ([start-date] - [end-date])
------------------------------------------------------------
```

## Search for tasks

Displays tasks that contains query in task description

### Usage

### `find`

Example of usage: 

`find [query]`

Expected outcome:

```
------------------------------------------------------------
Here are the tasks I found containing : [query]
1.[T][ ] [todo-description]
2.[D][ ] [deadline-description] (by: [date])
3.[E][X] [event-description] ([start-date] - [end-date])
------------------------------------------------------------
```

## Upcoming

Displays tasks with deadlines within 3 days
- Will only return `deadline` and `event` tasks as `todo` tasks do not have a deadline

### Usage

### `upcoming`

Example of usage: 

`upcoming`

Expected outcome:

```
------------------------------------------------------------
Please be reminded that these tasks are due soon:
1.[D][ ] [deadline-description] (by: [date])
2.[E][X] [event-description] ([start-date] - [end-date])
------------------------------------------------------------
```

## Mark

Marks task as completed using its index
- Recommended to use [`list`](#list-tasks) before `mark` to get the task index

### Usage

### `mark`

Example of usage: 

`mark [index]`

Expected outcome:

```
------------------------------------------------------------
Nice! I've marked this task as done:
    [E][X] [event-description] ([start-date] - [end-date])

------------------------------------------------------------
```

## Unmark

Marks task as not completed using its index
- Recommended to use [`list`](#list-tasks) before `unmark` to get the task index

### Usage

### `unmark`

Example of usage: 

`unmark [index]`

Expected outcome:

```
------------------------------------------------------------
OK, I've marked this task as not done yet:
    [E][X] [event-description] ([start-date] - [end-date])

------------------------------------------------------------
```

## Delete

Deletes task using its index
- Recommended to use [`list`](#list-tasks) before `delete` to get the task index

### Usage

### `delete`

Example of usage: 

`delete [index]`

Expected outcome:

```
------------------------------------------------------------
Noted. I've removed this task:
    [E][X] [event-description] ([start-date] - [end-date])
Now you have [n] tasks in the list.
------------------------------------------------------------
```

## Exits

Exits program and saves all added tasks to database
- Database is found at `data/duke.txt`

### Usage

### `bye`

Example of usage: 

`bye`

Expected outcome:

```
------------------------------------------------------------
Bye. Hope to see you again soon!
------------------------------------------------------------
```