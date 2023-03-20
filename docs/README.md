# User Guide

## Features 

### Stores and saves your tasks.

If you need a quick checklist, you can use this CLI-based digital checklist that saves your previous tasks.

### Ability to mark tasks as complete and delete tasks.

After completion, users can mark their tasks and delete them as they wish.

## Usage

### `Keyword` - todo

Creates a task with no startdate or deadline. Ability to mark tasks as completed and delete them.

Example of usage: 

todo task0

After listing (see list), your list should have:

```
0. [T] [ ] task0
```

### `Keyword` - deadline

Creates a task with a deadline. Ability to mark tasks as completed and delete them. The deadline is defined by any characters after the first delimiter "/".

Example of usage: 

deadline task1 /tomorrow

After listing (see list), your list should have:

```
0. [T] [ ] task0
1. [D] [ ] task1 (by: tomorrow)
```

### `Keyword` - event

Creates a task with a start and deadline. Ability to mark tasks as completed and delete them. The start is defined by any characters after the first delimiter "/" and before the second delimiter "/". The end is defined as any characters after the second delimiter "/".

Example of usage: 

event task2 /start /end

After listing (see list), your list should have:

```
0. [T] [ ] task0
1. [D] [ ] task1 (by: tomorrow)
2. [E] [ ] task2 (from: start to: end)
```



### `Keyword` - list

Lists all tasks currently in the tasklist

Example of usage: 

list

```
Here are the tasks in your list:
0. [T] [ ] task0
1. [D] [ ] task1 (by: tomorrow)
2. [E] [ ] task2 (from: start to: end)
```


### `Keyword` - delete

Deletes task based on index on list. 

Example of usage: 

delete 2

After listing (see list), your list should have:

```
0. [T] [ ] task0
1. [D] [ ] task1 (by: tomorrow)
```

### `Keyword` - mark

Marks task as complete based on index on list. 

Example of usage: 

mark 0

After listing (see list), your list should have:

```
0. [T] [X] task0
1. [D] [ ] task1 (by: tomorrow)
```

### `Keyword` - unmark

Marks task as complete based on index on list. 

Example of usage: 

unmark 0

After listing (see list), your list should have:

```
0. [T] [ ] task0
1. [D] [ ] task1 (by: tomorrow)
```

### `Keyword` - find

Finds task based on keyword typed in.

Example of usage: 

find task

After listing (see list), your list should have:

```
Here are the following Tasks that have names containing your keyword:
0. [T] [X] task0
1. [D] [ ] task1 (by: tomorrow)
```

### `Keyword` - bye

Exits program, saves tasklist.

Example of usage: 

bye

Bye. Hope to see you again soon!
