# User Guide

## Features 

### Storing the tasks in list
Any changes made to the list of tasks will be stored in a text file named 
`data.txt`, when booting up the application, the previously added tsks
will still be there.

### Adding a new Task

`type taskName [dates]`
- `type` refers to one of  `Todo`, `Deadline` or `Event`.
- `taskNaame` refers to the name of the task
- When adding tasks of type `Deadline` or `Event`, `[date]` needs to be 
specified with the following format:
```
deadline taskName /by date
event taskName /from startDate /to endDate
```
- If the date is constructed in the format of `yyyy-MM-ddd`, it will be 
automatically formatted to the form `MMM dd yyyy`, e.g., converting from
`2023-03-03` to `Mar 03 2023`

#### Example
```
> todo borrow book
_(ovo)-| Added:
  [T] Borrow book
_(#o#)_| Currently, the number of task I remembered is: 1
> deadline code iP project /by 2023-03-03
_(ovo)-| Added:
  [D] Code iP project (by: Mar 03 2023)
_(#o#)_| Currently, the number of task I remembered is: 2
> event project meeting /from Mon 2pm /to 4pm
_(ovo)-| Added:
  [E] Project meeting (from: Mon 2pm to: 4pm)
_(#o#)_| Currently, the number of task I remembered is: 3
```

### Delete a task

`delete INDEX`

Will delete the task with an index of `INDEX`, which is an integer representing 
the index of the task to be deleted, starting from 1.

#### Example
```
> delete 1
|(0o0)> Gocha! I will now delete this task:
  [T] Borrow book
_(#o#)_| Currently, the number of task I remembered is: 2
```

### Mark a task as done
`mark INDEX`
Will mark the task with an index of `INDEX` as done, where 
`INDEX` is an integer representing the index of the task to be marked,
starting from 1.

#### Example
```
> mark 1
*^(^v^)^* Great! I have marked this task as done:
1.[D] [X] Code iP project (by: Mar 03 2023)
```

### Mark a task as not done
`unmark INDEX`
Will mark the task with an index of `INDEX` as not done, where
`INDEX` is an integer representing the index of the task to be marked,
starting from 1.

#### Example
```
> unmark 1
_(@_@)_ Hmmm. I shall mark this task as not done:
1.[D] [ ] Code iP project (by: Mar 03 2023)
```
### List currently stored tasks
`list`

- Will display all the currently stored item in the list, with a format of:
  - Task of type `todo`: `INDEX. [T]state taskName`
  - Task of type `deadline`: `INDEX. [D]state taskName (by: date)`
  - Task of type `event`: `INDEX. [E]state taskName (from: startDate to: endDate)`
- `state` refer to if the task is done or not
  - `[X]` if the task have been marked as done
  - `[ ]` if the task have not been marked or have been marked as not done

#### Example
```
> list
|(*O*)| Here's the tasks I remembered:
1.[D] [ ] Code iP project (by: Mar 03 2023)
2.[E] [ ] Project meeting (from: Mon 2pm to: 4pm)
3.[T] [ ] Read book
```

### Find all tasks that contain a word
`find toFind`

Will find and display all tasks that contains the string `toFind` in the name of the task.
If there is no item in the list that have name containing `toFind`, an error message will be
displayed, followed by the list of all currently stored tasks.
#### Example
```
> find book
|(*v*)| Here are all the matching tasks I found:
1.[T] [ ] Read book
> find sleep
|(@A@)| I don't see any task that contain the word you are searching.
|(*O*)| Here's the tasks I remembered:
1.[D] [ ] Code iP project (by: Mar 03 2023)
2.[E] [ ] Project meeting (from: Mon 2pm to: 4pm)
3.[T] [ ] Read book

```
## Usage

### `bye` - End the program

Will exit the program.

#### Example
```
> bye
/(0A0)/ Bye. See you next time!
```
