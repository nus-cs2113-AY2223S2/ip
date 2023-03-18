# User Guide

## Features

### Add tasks

You can add 3 types of tasks, todo, deadline and event, into your task list.
1. Todo - Basic task with
2. Deadline - Tasks that need to be done by certain date
3. Event - Tasks that have certain period of time

### Mark as done

This feature is to allow you to mark the task as done.

If you have finished the task, you can mark the task as done.

### Mark as undone

This feature is to let to mark the task as undone.

If you have not finished the task, you can mark the task as undone.

### List the tasks

This feature is to list all the tasks in the task list.

### Delete the tasks

This feature is to delete the specific task according to its index number.
Once you have entered the command, the task will be removed from the list.

### Find the tasks

Find all the tasks according to the keywords in the command.

## Usage

---
### `todo`
Add to do to the task list.

Example of usage:

`todo {task description}`

Example

```
todo read books
```

Expected outcome:

```
Got it. I've added this task: 
[T][ ] read books
```
---
### `deadline`
Add deadline to the task list.

Example of usage:

`deadline {task description} /by {the date of deadline}`

Example:

```
deadline read books /by Feb 28th
```

Expected outcome:

```
Got it. I've added this task: 
[D][ ] read books Feb 28th
```
---
### `event`

Add event to the task list

Example of usage:

`event {task description} /from {start} /to {end}`

Example:

```
event read books /from Feb 2nd /to Feb 28th
```

Expected outcome:

```
Got it. I've added this task: 
[E][ ] read books from: Feb 2nd to: Feb 28th
```
---
### `mark`

Mark the task as done

Example of usage:

`mark {index number}`

Example:

```
mark 1
```

Excepted outcome:

```
Nice! I've marked this task as done:)
[T][√] read books
```
---
### `unmark`

Mark the task as undone

Example of usage:

`unmark {index number}`

Example:

```
unmark 1
```

Excepted outcome:

```
Nice! I've unmarked this task as done:P
[T][] read books
```
---
### `delete`

Delete the task and show the total number of tasks

Example of usage:

`delete {task index number}`

Example:

```
delete 1
```

Excepted outcome:
```
Got it. I've removed this task: 
[T][ ] read books
 There are 4 tasks in your list.
```
---
### `find`

Find the tasks according to keywords

 `find {keywords}`

Example:

```
find books
```

Excepted outcome

```
Here are the matching tasks in your list: 

1. [T][ ] read books
2. [D][ ] read books Feb 28th
3. [E][ ] read books from: Feb 2nd to: Feb 28th
```
---
### `list`

List all the tasks

Example:

`list`

Expected outcome:
```
1. [T][ ] read books
2. [T][ ] wash clothes
3. [T][ ] buy flowers
4. [D][ ] read books Feb 28th
5. [E][ ] read books from: Feb 2nd to: Feb 28th
```
---

### `help`

List all the command format

Example:

```
    todo {description} --add todo
    deadline {description} /by {deadline} --add deadline
    event {description} /from {startTime} /to {endTime}  --add event
    mark {task index number} --mark the task as done
    unmark {task index number} --mark the task as undone
    delete {task index number} --delete the task
    find {keywords}  --find the task according to keywords
    list --show the current task list
    exit --exit the application
```

---

### `exit`

Exit the application

Example:
```
Bye. Hope to see you again soon! I will be missing you:("
```
----