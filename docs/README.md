# User Guide

Duke is a Personal Assistant Chat bot that helps users keep track of various tasks

## Features

### Supports addition of 3 types of tasks.

* `todo` - Adds a simple todo task
* `deadline` - Adds a deadline task with a due date
* `event` - Adds an event task with start details and end details

### Edit commands

* `mark` - Marks a task as done
* `unmark` - Unmark a task as undone 
* `delete` - Delete a task from stored list
* `list` - Lists out all the stored tasks 
* `find` - It returns all the tasks corresponding to key input by user
* `bye` - Exits the program

---

## Usage

### `todo`

Adds a todo task to list

**Command format**

`todo [task description]`

Examples

```
todo eat dinner
```

_____

### `deadline`

Adds a deadline task to list
Also adds due date for task

**Command format**

`deadline [task description] /by [due date YYYY-MM-DD]`

Examples

```
deadline attend her wedding /by 2019-12-10
```

---

### `event`

Adds an event task to list
Also adds start and end details of event

**Command format**

`event [task description] /by [start date YYYY-MM-DD]  [end date YYYY-MM-DD]`

Examples

```
attend the wedding /by 2019-10-12  2019-10-17
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
mark 3
```

_____

### `unmark`

Marks task as completed

**Command format**

`unmark [task index]` _task index can be gotten from list command_

Examples

```
unmark 5
```

_____

### `delete`

Deletes a task from stored list

**Command format**

`delete [task index]` _task index can be gotten from list command_

Examples

```
delete 4
```

_____

### `find`

Finds a task based on a keyword

**Command format**

`find [keyword]`
_returns tasks containing matching keyword or substring of keyword_

Examples

```
find dinner
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
