# User Guide

## Features 

### Listing of Tasks
User is able to add tasks for Duke to keep track of

### Editing of Tasks
User is able to mark Tasks as done/undone, delete and find Tasks

### List Storage
User is able to obtain a text file of the Task list that can be transferred to another device


## Usage

### `Todo` - Adds a Task without a timing associated with it

Example of usage: 

**Input:** `Todo eat dinner`

**Output:** `[T] [ ] eat dinner - Added, I have`

`[T]` represents the Task as a Todo task

---

### `Deadline` - Adds a Task that has a corresponding deadline

Example of usage:

**Input:** `Deadline study for test /by monday`

**Output:** `[D] [ ] study for test (by monday) - Added, I have`

`[D]` represents the Task as a Deadline task

---

### `Event` - Adds a Task that has a start and end time

Example of usage:

**Input:** `Event wedding /monday 2pm to 6pm`

**Output:** `[E] [ ] wedding (monday 2pm to 6pm) - Added, I have`

`[E]` represents the Task as a Event task

---

### `List` - Lists out all the Tasks previously added

Example of usage:

**Input:** `Deadline study for test /by monday`

**Input:** `Event wedding /monday 2pm to 6pm`

**Input:** `List`

**Output:** `As shown, list is:`

`1. [D] [ ] study for test (by monday)`

`2. [E] [ ] wedding (monday 2pm to 6pm)`

---

### `Mark` `Unmark` - Marks or unmarks Tasks in the list as done

Example of usage:

**Input:** `Mark 1`

**Output:** `[E] [X] wedding (monday 2pm to 6pm) - Marked it, I have`

`[X]` indicates that the Task is done

`1` is the index of the task to be marked

---

### `Delete` - Delete the Task from the list

Example of usage:

**Input:** `Delete 1`

**Output:** `Deleted, I have`

`1` is the index of the task to be deleted

---

### `Find` - Lists out all Tasks in the list with the keyword

Example of usage:

**Input:** `Find study`

**Output:** `2. [D] [ ] study for test (by monday)`

`study` is the keyword