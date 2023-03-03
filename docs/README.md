# The Pika Duke Project

## Features 

### Task List

Able to keep track of a list of tasks for the following types:

Todo: Includes a description of task

Deadline: Includes a description and deadline 

Event: Includes a description, start date and end date of an event

### Add Task

Allows the user to add the tasks to a list

### Delete Task

Allows the user to delete tasks from a list

### Mark/Unmark Task as done

Allows the user to mark or unmark a task as done or undone from the list

### Find tasks using keyword

Allows the user to find all tasks within the list whose description contains a keyword

### Saving Capabilities

Upon exit, the task list created by the user is saved.

Upon starting Duke up again, the old data is loaded in.

### Error Catching Capabilities

Able to catch many errors such as accessing an incorrect index, putting in wrong inputs for most use cases.

## Usage

### `todo/deadline/event` - Add a task

Adds a task of type todo/deadline/event to the list

The format is as follows for each task:

 `todo {description}`

 `deadline {description} /by {deadline}`

 `event{description} /from {Start Date} /to {end date}`

For example: 

`todo Do CS2113 Homework`

Expected outcome:

A task of todo, with a description "todo Do CS2113 Homework" will be stored in the list

```
  ____________________________________________________________
Pikapi add this task: 
  [T][ ] Do CS2113 Homework
Pikapi sees that now you have 1 tasks in the list
```



____________________________________________________________
### `list` - list all current tasks

Outputs all current tasks in the list

For example:
After adding the following 2 tasks:

`todo Do CS2113 Homework`
`deadline Do IP Project /by today`

Then call the list function

`list`

Expected outcome:

```
Here is your list:
1. [T][ ] Do CS2113 Homework
2. [D][ ] Do IP Project (by: today)
```
____________________________________________________________
### `delete` - delete a task

Delete a task of a specific index from the list

Format:

`delete {INTEGER}`

For example:

With this list: 
```
Here is your list:
1. [T][ ] Do CS2113 Homework
2. [D][ ] Do IP Project (by: today)
```

You can call 
`delete 2`

Expected outcome:
```
Pikapi has deleted the task: 
  [D][ ]Do IP Project 
Pikapi sees that now you have 1 tasks in the list
```
____________________________________________________________
### `mark/unmark` - Mark or unmark a task as don or not done

Puts a cross beside your task to take note of whether you have finished it or not

Format:

`mark {INTEGER}`
`unmark {INTEGER}`

### Do note that the integer must be between the values of 1 and the number of tasks in the list!
### Can only be called if the current list is not empty

An error will be caught and print out exactly where the User went wrong in case of incorrect use

For example:
I have the following list:

```
Here is your list:
1. [T][ ] Do CS2113 Homework
2. [T][ ] Do TP project
3. [T][ ] Other module work
```

Then call the list function

`mark 2`

Expected outcome:
```
Pikapi has marked the task as done

[X]  Do TP project
```

Now when you type in the word list again, you get:

```
Here is your list:
1. [T][ ] Do CS2113 Homework
2. [T][X] Do TP project
3. [T][ ] Other module work
```

We can also unmark a task:

Let us type in `unmark 2` as an input 
Expected outcome:
```
Pikapi has unmarked the task

[ ]  Do TP project
```

Now when you type in the word list again, you get:

```
Here is your list:
1. [T][ ] Do CS2113 Homework
2. [T][ ] Do TP project
3. [T][ ] Other module work
```


____________________________________________________________
### `find` - Find all tasks with a certain keyword

Find all tasks with a keyword in the list

For example:

With this list:
```
Here is your list:
1. [T][ ] Do CS2113 Homework
2. [T][ ] Do TP project
3. [T][ ] Other module work
```

You can call
`find Do`

Expected outcome:
```
Pikapi has found  list of things pertaining to your keyword
Here is your list:
1. [T][ ] Do CS2113 Homework
2. [T][ ] Do TP project
```

If no task is found with the keyword, this will appear:

```
No tasks with descriptions containing the keyword is found, Pikapi tried his best
```

No keyword will show a corresponding error (i.e. inputting just the word `find`)
```
No Task keyword provided to find, Pikapi tried :3
```

-------------------------------------
### Error Catching
Able to catch the following errors with the corresponding error messages:

1) Negative / 0 index access of array
   E.g. `mark 0`

Expected output:

```
PIKAPII we dont have negative or tasks indexed as 0 >:<
```

2) Out of bounds

Expected output:
```
PIKAPII you dont have that many tasks! Please write a number between 1 and {max length of tasklist}
```

3) input String as index

E.g. `mark fhbdusishbijf`

Expected output:
```
Pikapiii you did not give me an index(number)! Please give me a number after the mark/unmark/delete keyword please :3 UwU
```

4) Empty description/deadline/start date/ end date for a task

e.g. `deadline /by today`
 
Expected output:
```
Description cannot be empty :<, please write a description after the word deadline
```

There are many variants and customized outputs for each error case, to see each error message for this please go into the ErrorMessages.java class under the errors package

5) Handling Corrupt Data

If someone has corrupted a specific line in the data file upon loading, that specific line of data will not be considered.

The rest of the file will continue loading

Expected output:

```
PIKAPII There seems to be something wrong with this task in your save file, not loading a specific task into the task list
```

6) Accessing empty list

Depending on the command used, the error message will be different

E.g. `list`

Expected output:
```
Pikapi's list is completely empty, please add some tasks!
```

E.g. `mark 1`

Expected output:

```
PIKAPII the list is currently empty! You can't mark/unmark an empty list! Please input some tasks for Pikapi to add :3
```

7) Invalid Command

If random things are typed into the command line
E.g. `hgfsudohfuds`

Expected output:
```
Pikapi is unable to find that command, please type in a correct command
```
____________________________________________________________
Last but not least...

### It sounds like a cute Pikachu!

Or at least I tried to. I hope you enjoy!
