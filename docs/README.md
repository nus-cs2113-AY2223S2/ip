# User Guide

Duke is a Personal Assistant Chat bot that helps users keep track of various tasks

## Features

### Supports addition of 3 types of tasks.

* `todo` - Adds a simple todo task
* `deadline` - Adds a deadline task with a due date
* `event` - Adds an event task with start details and end details

### Edit commands

* `mark` - Marks a task as done
* `unmark` - Unmark a task
* `delete` - Delete a task from stored list
* `list` - Lists out stored tasks
* `find` - Finds a matching task stored from an input
* `bye` - Exits the programme

---

## Usage

### `todo`

Adds a todo task to list

**Command format**

`todo [task description]`

Examples

```
todo pay bills
```

_____

### `deadline`

Adds a deadline task to list
Also adds due date for task

**Command format**

`deadline [task description] /by [due date]`

Examples

```
deadline submit assignment /by 30th August 2030
```

---

### `event`

Adds an event task to list
Also adds start and end details of event

**Command format**

`event [task description] /from [start details] /to [end details]`

Examples

```
event meeting /from 5pm /to 7pm
```

_____

### `list`

Lists out all stored tasks

**Command format**

`list`

Examples

```
list
```

_____

### `mark`

Marks task as completed

**Command format**

`mark [task index]` _task index can be gotten from list command_

Examples

```
mark
```

_____

### `unmark`

Marks task as completed

**Command format**

`unmark [task index]` _task index can be gotten from list command_

Examples

```
unmark
```

_____

### `delete`

Deletes a task from stored list

**Command format**

`delete [task index]` _task index can be gotten from list command_

Examples

```
delete
```

_____

### `find`

Finds a task based on a keyword

**Command format**

`find [keyword]`
_returns tasks containing matching keyword or substring of keyword_

Examples

```
find meeting
```

_____

### `bye`

Exits the program and saves tasks into local storage

**Command format**

`bye`

Examples

```
bye
```

_____