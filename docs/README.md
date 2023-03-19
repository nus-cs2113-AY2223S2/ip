# User Guide

Nova is a command-line (CLI) chatbot application that helps you keep track of your tasks.

## Features 

### Adding tasks to your list

You can add three different types of tasks: 
1. todo
2. deadline
3. event 

### Printing your list of tasks

You can view the tasks listed currently

### Mark your tasks

You can mark/unmark your tasks if you have completed/yet to complete them.

### Find tasks by keyword

You can find tasks that matches a specific keyword in your list 

### Delete your tasks

You can delete specific tasks from the list

### Save your tasks

Your tasks are saved after you exit the program and loaded once you reopen

## Usage

### `list` - Displaying the current list of tasks 

Prints out the tasks in the list

Example of usage: 

`list`

Expected outcome:

Nova will acknowledge and display the updated list

```
Here are all of your 3 tasks: 
1.[T][ ] run
2.[D][ ] eat (by: 14 July 2023 6pm)
3.[E][ ] sleep ( from 1 June to 2 June)
```

### `todo` - Adds a new task of type: Todo 

Adds a new task and updates the current number of tasks in the list 

Example of usage: 

`todo {description}`
 e.g.  {todo read}

Expected outcome:

Nova will acknowledge the added task and display the updated number of tasks

```
I have added this task: 
[T][ ] read
You now have 4 tasks in the list.
```

### `deadline` - Adds a new task of type: deadline

Adds a new task with a deadline and updates the current number of tasks in the list 

Example of usage: 

`deadline {description /by deadline}`
e.g. {deadline eat /by 14 July 2023 6pm}

Expected outcome:

Nova will acknowledge the added task and display the updated number of tasks

```
I have added this task: 
[D][ ] eat (by: 14 July 2023 6pm)
You now have 5 tasks in the list.
```

### `event` - Adds a new task of type: event

Adds a new task regarding an event and updates the current number of tasks in the list 

Example of usage: 

`event {description /from date /to date}`
e.g. {event sleep /from 1 June /to 2 June}

Expected outcome:

Nova will acknowledge the added task and display the updated number of tasks

```
I have added this task: 
[E][ ] sleep ( from 1 June to 2 June)
You now have 6 tasks in the list.
```

### `mark` - Mark a specific task by index 

Marks a task according to its index in the list

Example of usage: 

`mark {index}`

Expected outcome:

Nova will acknowledge and display the marked task

```
Nice! This task is completed
[D][X] eat (by: 14 July 2023 6pm)
```

### `unmark` - Unmarks a specific task by index 

Unmarks a task according to its index in the list

Example of usage: 

`unmark {index}`

Expected outcome:

Nova will acknowledge and display the unmarked task

```
Alright, this task has yet to be complete
[D][ ] eat (by: 14 July 2023 6pm)
```

### `delete` - Deletes a specific task by index 

Deletes a task according to its index in the list

Example of usage: 

`delete {index}`

Expected outcome:

Nova will acknowledge, delete the unmarked task and display the updated number of tasks

```
Noted. I have deleted this task
[D][ ] eat (by: 14 July 2023 6pm)
You now have 1 tasks in the list.
```


### `find` - Deletes a specific task by index 

Find tasks that matches a specific keyword in your list 

Example of usage: 

`find {keyword}`

Expected outcome:

Nova will acknowledge, search for the related tasks and display accordingly 

```
Here are the matching tasks in your list: 
1.[D][ ] eat (by: 14 July 2023 6pm)
2.[T][ ] eat
```

### `bye` - Exits the application 

Quits the program 

Example of usage: 

`bye`

Expected outcome:

Nova will greet you farewell 

```
Goodbye. Hope to see u again :) 
```




