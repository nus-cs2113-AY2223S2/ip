# User Guide

## Features

### Add todo

Adds a todo to the task list

### Add event

Adds an event with from and to details

### Add deadline

Adds a deadline with details on when it is due(by)

### Mark as done

Marks a task as done

### Mark as not done

Marks a task as not done

### Find a task

Finds a task

### Quit

Quits Duke

## Usage

### `todo` - Describe action

Adds a todo

Example of usage:
`todo run`

Expected outcome:
` adds task to list`

Adds to-do to the tasklist

```
Tasks saved successfully to file.
Now you have 1 tasks in your list
```

### `event /from /to` - Describe action

Adds a event

Example of usage:
`event run /2pm to /4pm`

Expected outcome:
` adds event with from being 2pm and to being 4pm to the list `

Adds event to the tasklist

```
Tasks saved successfully to file.
Now you have 2 tasks in your list
```

### `deadline /by ` - Adds a deadline

Example of usage:
`deadline return book /monday`

Expected outcome:
` adds deadline to retun book by monday`

Adds deadline to the tasklist

```
Tasks saved successfully to file.
Now you have 3 tasks in your list
```

### `mark [index]` - marks the task with index in tasklist as done

Example of usage:
`mark 1`

Expected outcome:
` marks task as done`

Marks a task as done

```
You are crushing it, 1 task down!
 [T] [X] run
Tasks saved successfully to file.
```

### `unmark [index]` - unmarks the task with index in tasklist as not done

Example of usage:
`unmark 1`

Expected outcome:
` marks task as not done`

Marks a task as not done

```
I have unchecked it for you
 [T] [ ] run
Tasks saved successfully to file.
```

### `find` - finds the tasks with that word in the task list

Example of usage:
`find run`

Expected outcome:
` returns tasks that have the word run in them`

```
Here are the matching tasks in your list:
1.[T] [ ] run 
```
### `bye` - quits duke 

Example of usage:
`bye`

Expected outcome:
` quits duke`

```
Bye see you again!
```