---
title: Modifying Tasks
---

## Usage

### `delete [index]` - deletes a task at index

`[index]` - Must be an integer within the range of the list (i.e. as per the example
below, index can only be numbers 1 - 4)

Removes a task from the list

For example these are the current tasks in the list now:

```
Here are the tasks in your list:
1. [T][ ] buy milk
2. [D][ ] buy milk (by: dinner time)
3. [E][ ] lecture time (from: 24-02-2023 1600 to: 24-02-2023 1800)
4. [E][ ] lecture time (from: afternoon to: evening)
Now you have 4 tasks in the list.
```

Example of usage:

`delete 3`

Expected outcome:

You should see a successful output showing the
deleted task with the total number of tasks
currently in the list as shown:

```
Noted sir, I have removed 
[E][ ]lecture time
from the list
Now you have 3 task(s) in the list.
```

### `mark [index]` - marks a task at index

`[index]` - Must be an integer within the range of the list (i.e. as per the example
below, index can only be numbers 1 - 4)

Marks a task in the list

For example these are the current tasks in the list now:

```
Here are the tasks in your list:
1. [T][ ] buy milk
2. [D][ ] buy milk (by: dinner time)
3. [E][ ] lecture time (from: 24-02-2023 1600 to: 24-02-2023 1800)
4. [E][ ] lecture time (from: afternoon to: evening)
Now you have 4 tasks in the list.
```

Example of usage:

`mark 4`

Expected outcome:

You should see a successful output showing the
marked task:

```
Noted sir, I have marked 
[E][X] lecture time
as done.
```

### `unmark [index]` - deletes a task at index

`[index]` - Must be an integer within the range of the list (i.e. as per the example
below, index can only be numbers 1 - 4)

Unmark a task from the list

For example these are the current tasks in the list now:

```
Here are the tasks in your list:
1. [T][ ] buy milk
2. [D][ ] buy milk (by: dinner time)
3. [E][ ] lecture time (from: 24-02-2023 1600 to: 24-02-2023 1800)
4. [E][ ] lecture time (from: afternoon to: evening)
Now you have 4 tasks in the list.
```

Example of usage:

`unmark 4`

Expected outcome:

You should see a successful output showing the
unmarked task:

```
Noted sir, I have marked 
[E][ ]lecture time
as not done.
```

Go [back](README.md)
