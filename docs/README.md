# User Guide
Duke is an application which can take your commands and help you manage your tasks.

## Features 

### Feature-Add tasks to your task list

You can add three types of tasks to your task list: todo, deadline, and event. 
Todo is a task with no time constraints. 
Deadline is a task with an ending time. 
Event is a task with a time range.

### Feature-Modify tasks in your task list

You can have three types of modifications in your task list: delete, mark, unmark. 
Delete can remove the whole task. 
Mark can set the status of your task as done.
Unmark can set the status of your task as undone.

### Feature-List and store the tasks
You can check the contents in your task list. The task list record is automatically read when the application is running, and is automatically saved when the application is terminated.

## Usage

### Keyword-todo

Add a task which has no deadline or time frame.

Example of usage:
`todo borrow book`

Expected outcome:
Echo the newly created task. Display the number of tasks in the task list.
```
Got it. I've added this task:
       [T][ ] borrow book
     Now you have 5 tasks in the list.
```

### Keyword-deadline

Add a task which has the deadline.

Example of usage: 
`deadline return book /by Sunday`

Expected outcome:
Echo the newly created task. Display the number of tasks in the task list.
```
Got it. I've added this task:
       [D][ ] return book (by: Sunday)
     Now you have 6 tasks in the list.
```

### Keyword-event

Add a task which has has a time frame.

Example of usage: 
`event project meeting /from Mon 2pm /to 4pm`

Expected outcome:
Echo the newly created task. Display the number of tasks in the task list.
```
Got it. I've added this task:
       [E][ ] project meeting (from: Mon 2pm to: 4pm)
     Now you have 7 tasks in the list.
```

### Keyword-list

Display all the tasks in the task list.

Example of usage: 
`list`

Expected outcome:
Print out the information of all the tasks in a sequential order.
```
Here are the tasks in your list:
     1.[T][X] read book
     2.[D][ ] return book (by: June 6th)
     3.[E][ ] project meeting (from: Aug 6th 2pm to: 4pm)
     4.[T][X] join sports club
     5.[T][ ] borrow book
```

### Keyword-delete

Delete a task at a specific index.

Example of usage: 
`delete 3`

Expected outcome:
Echo the task deleted. Display the number of tasks in the task list.
```
Noted. I've removed this task:
       [E][ ] project meeting (from: Aug 6th 2pm to: 4pm)
     Now you have 4 tasks in the list.
```

### Keyword-mark

Mark a task at a specific index as done.

Example of usage: 
`mark 3`

Expected outcome:
Echo the task marked. Display the information of the task.
```
Nice! I've marked this task as done:
       [E][X] project meeting (from: Aug 6th 2pm to: 4pm)
```

### Keyword-mark

Mark a task at a specific index as undone.

Example of usage: 
`unmark 3`

Expected outcome:
Echo the task unmarked. Display the information of the task.
```
OK, I've marked this task as not done yet:
       [E][ ] project meeting (from: Aug 6th 2pm to: 4pm)
```

### Keyword-find

Display the tasks that contain the keyword.

Example of usage: 
`find book`

Expected outcome:
Display the information of the tasks that contain the keyword.
```
Here are the matching tasks in your list:
[T][X] read book
[D][X] return book (by: June 6th)
```
