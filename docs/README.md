# User Guide

## Features 

### Feature-Duke

Duke is a app helps manage tasks and deadlines, optimized for use via a
Command Line Interface (CLI)

## Usage

### `list` - Describe action

Describe the action and its outcome.

Example of usage: 

`keyword (optional arguments)`

Expected outcome:

Description of the outcome.

```
expected output
```

### `bye` - Describe action

Describe the action and its outcome.

Example of usage:

`keyword (optional arguments)`

Expected outcome:

Description of the outcome.

```
expected output
```
### `find` - Describe action

Describe the action and its outcome.

Example of usage:

`keyword (optional arguments)`

Expected outcome:

Description of the outcome.

```
expected output
```

### `todo` - tasks without any date/time attached to it

Example of usage:

`todo borrow book`

Expected outcome:

```
    Got it. I've added this task:
       [T][ ] borrow book
     Now you have 5 tasks in the list.

```

### `event` - tasks that start at a specific date/time and ends at a specific date/time

Example of usage:

`event project meeting /from Mon 2pm /to 4pm`

Expected outcome:

```
    Got it. I've added this task:
       [E][ ] project meeting (from: Mon 2pm to: 4pm)
     Now you have 7 tasks in the list.
```

### `deadline` - tasks that need to be done before a specific date/time

Example of usage:

`deadline return book /by Sunday`

Expected outcome:


```
    Got it. I've added this task:
       [D][ ] return book (by: Sunday)
    Now you have 6 tasks in the list.
```

### `delete` - delete task

Example of usage:

`delete 3`

Expected outcome:

```
    Noted. I've removed this task:
       [E][ ] project meeting (from: Aug 6th 2pm to: 4pm)
    Now you have 4 tasks in the list.
```