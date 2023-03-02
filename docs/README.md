# User Guide

## Features 

### Adding Tasks to List

Add as many tasks as you'd like to your Task List.

### Removing Tasks from List

If you no longer need to keep track of a task, you can remove it from the list.

### Display All Tasks

You can obtain a list of all current tasks. 

### Mark/Unmark Tasks as Complete

Mark tasks as complete when you finish them, or unmark them if you haven't finished yet. Completion is displayed as a checkbox next to the corresponding task.

### Choose from Three Types of Tasks to Match Your Needs

Tasks can be Todos (simple tasks with only a description), Deadlines (tasks with a specific due date), or Events (tasks that have a definite start and end date/time). Feel free to mix and match tasks of different types within your list!

### Saving Task List across Multiple Sessions

Your task list will automatically save in real time, so you don't need to worry about losing them when you close and re-open Duke.

### Search for Tasks

You can search for tasks by keywords to filter for tasks containing a specific keyword or date.

## Usage

### `list` - List all Tasks

Lists all tasks currently in the list, along with their completion status (and any applicable dates).

Example of usage: 

`list`

Expected outcome:

List of tasks in the list. Tasks are listed with a letter indicating their type (D for deadline, T for Todo, or E for Event), a checkbox indicating whether they are complete or not, their description, and any corresponding dates.

```
____________________________________________________________
Here are the tasks in your list:
1.[T][ ] CS3230 PA2
2.[D][X] IP (by: Friday)
3.[T][ ] read book
4.[D][ ] return book (by: Friday)
5.[E][ ] taiwan (from: Mar 13 to: Mar 22)
____________________________________________________________
```

### `mark` - Mark a Task as Complete

Marks the task corresponding to the input index as complete.

Example of usage: 

`mark 1`

Expected outcome:

An 'X' is placed next to the task, indicating its completion. A confirmation message is outputted for your convenience.

```
____________________________________________________________
Ok, I've marked this task as done:
[T][X] CS3230 PA2
____________________________________________________________
```

### `unmark` - Mark a Task as Not Complete

Marks the task corresponding to the input index as not complete. 

Example of usage: 

`unmark 1`

Expected outcome:

The 'X' is removed from next to the task, indicating the task as not complete. A confirmation message is outputted for your convenience.

```
____________________________________________________________
Ok, I've marked this task as not done yet:
[T][ ] CS3230 PA2
____________________________________________________________
```

### `delete` - Delete a Task

Removes the task corresponding to the input index from the task list. 

Example of usage: 

`delete 5`

Expected outcome:

Task is removed from the list. A confirmation message is outputted for your convenience.

```
____________________________________________________________
Noted. I've removed this task:
[E][ ] taiwan (from: Mar 13 to: Mar 22)
____________________________________________________________
```

### `todo` - Add a Todo Task

Adds a todo to the end of the task list. Requires a description of the todo.

Example of usage: 

`todo internship applications`

Expected outcome:

Adds a todo to the list. Prints out the new task and number of tasks in the list.

```
____________________________________________________________
Got it. I've added this task:
[T][ ] internship applications
Now you have 5 tasks in the list.
____________________________________________________________
```

### `deadline` - Add a Deadline Task

Adds a deadline to the end of the task list. Requires a description of the deadline, as well as a due date.

Example of usage: 

`deadline write essay /by Saturday 9pm`

Expected outcome:

Adds a deadline to the list with the corresponding description and due date. Prints out the new task and number of tasks in the list.

```
____________________________________________________________
Got it. I've added this task:
[D][ ] write essay (by: Saturday 9pm)
Now you have 6 tasks in the list.
____________________________________________________________
```

### `event` - Add an Event Task

Adds an event to the end of the task list. Requires a description of the event, start date/time, and end date/time.

Example of usage: 

`event work meeting /from Wednesday 2pm /to 4pm`

Expected outcome:

Adds an event to the list. Prints out the new task and number of tasks in the list.

```
____________________________________________________________
Got it. I've added this task:
[E][ ] work meeting (from: Wednesday 2pm to: 4pm)
Now you have 7 tasks in the list.
____________________________________________________________
```

### `find` - Search for Tasks

Searches for all tasks with descriptions or dates that contain the keyword in the argument.

Example of usage: 

`find book`

Expected outcome:

Lists all tasks that contain the keyword "book".

```
____________________________________________________________
Here are the matching tasks in your list:
3.[T][ ] read book
4.[D][ ] return book (by: Friday)
8.[E][ ] reading one chapter per day (from: today to: completion of book)
____________________________________________________________
```

### `bye` - Exit Duke 

Exits Duke. Task list is stored so that when you come back, you'll keep all of your progress.

Example of usage: 

`bye`

Expected outcome:

Quits the program.

```
____________________________________________________________
Bye. Hope to see you again soon!
____________________________________________________________
```