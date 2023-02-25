# User Guide

## Features 

### Feature-Add Tasks

Allows you to add tasks of different types
1. Todo
2. Deadline
3. Event

### Feature-mark/ unmark

Allows you to mark a task as done /not done.

### Feature-list

Allows you to list all tasks in your task list

### Feature-delete

Allows you to delete a task from your task list

### Feature-bye

Ends the bot and allows you to save your current task list

## Usage

### `todo` - add tasks of type [todo]

Example of usage: 

`todo description`

Expected outcome:

adds task of type [todo] to your task list

```
Okay auntie add for you this one: 
    [T] [ ] description
Now you got 5 tasks
```

### `deadline` - add tasks of type [deadline]

Example of usage: 

`deadline description /by day`

Expected outcome:

adds task of type [deadline] to your task list

```
Okay auntie add for you this one: 
    [D] [ ] description (by: day)
Now you got 6 tasks
```

### `event` - add tasks of type [event]

Example of usage: 

`event description /from day /to day`

Expected outcome:

adds task of type [event] to your task list

```
Okay auntie add for you this one: 
    [E] [ ] description (from: day to: day)
Now you got 7 tasks
```

### `list` - list all tasks in your task list

Example of usage: 

`list`

Expected outcome:

list all tasks in your task list in order

```
Auntie show you ah... all this are your tasks:
    1.[T] [ ] description
    2.[D] [ ] description (by: day)
    3.[E] [ ] description (from: day to: day)
```

### `mark`/ `unmark` - mark a task as done /not done.

Example of usage: 

`mark 1`

Expected outcome:

mark a task as done /not done as seen by the [X] symbol

```
Very good... finish more task next time more successfull than me okay
    [T] [X] description
```

### `delete` - delete a task from the task list

Example of usage: 

`delete 1`

Expected outcome:

task is removed

```
Always so lazy... Okay Auntie remove this one for you:
    [T] [X] description
```

### `bye` - Saves current task list and ends the bot

Example of usage: 

`bye`

Expected outcome:

saves and end bot

```
================================================================================

Saving files...
...done
================================================================================

        Auntie very tired talking to you lah. Better not wake me up again ah I tell u first!

================================================================================
```
