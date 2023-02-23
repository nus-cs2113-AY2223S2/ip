# Duke User Guide
Duke is a ChatBot designed to help you to track your daily tasks. Start trying it out now!
## Features

### 1. Add Task
**Add Todo Task**

`todo <task description>` 

Allows user to add a todo task with only a description to the list.

*Example of usage:*

```
todo Read book
```

*Expected outcome:*

```
	____________________________________________________________
	Got it. I've added this task:
		[T][ ] Read book
	Now you have 1 task in the list.
	____________________________________________________________
```

**Add Deadline Task**

`deadline <task description> /by <due date>` 

Allows user to add a deadline task with a description and a due date to the list.

*Example of usage:*

```
deadline Return book /by friday
```

*Expected outcome:*

```
	____________________________________________________________
	Got it. I've added this task:
		[D][ ] Return book (by: friday)
	Now you have 2 tasks in the list.
	____________________________________________________________
```

**Add Event Task**

`event <task description> /from <starting> /to <ending>` 

Allows user to add an event task with a description, starting time/date and ending 
time/date to the list.

*Example of usage:*

```
event Swimming /from 1400 /to 1500
```

*Expected outcome:*

```
	____________________________________________________________
	Got it. I've added this task:
		[E][ ] Swimming (from: 1400 to: 1500)
	Now you have 3 tasks in the list.
	____________________________________________________________
```

### 2. List all Tasks
`list`

Shows the user every task that is currently in the list.

*Example of usage:*

```
list
```

*Expected outcome:*

```
	____________________________________________________________
	Here are the tasks in your list:
	1.[T][ ] Read book
	2.[D][ ] Return book (by: friday)
	3.[E][ ] Swimming (from: 1400 to: 1500)
	____________________________________________________________
```
*When there are no tasks in the list:*

```
	____________________________________________________________
	There are no tasks in your list currently!
	____________________________________________________________
```

### 3. Mark / Unmark Task
**Mark Task**

`mark <task number in the list>` 

With reference to the task numbers in the list at
the point of calling the command, mark the task whose number is specified by the user.

*Example of usage:*

```
mark 1
```

*Expected outcome:*

```
	____________________________________________________________
	Nice! I've marked this task as done:
		[T][X] Read book
	____________________________________________________________
```

**Unmark Task**

`unmark <task number in the list>` 

With reference to the task numbers in the list at
the point of calling the command, unmark the task whose number is specified by the user.

*Example of usage:*

```
unmark 1
```

*Expected outcome:*

```
	____________________________________________________________
	OK, I've marked this task as not done yet:
		[T][ ] Read book
	____________________________________________________________
```

### 4. Delete Task

`delete <task number in the list>` 

With reference to the task numbers in the list at 
the point of calling the command, delete the task whose number is specified by the user.

*Example of usage:*

```
delete 1
```

*Expected outcome:*

```
	____________________________________________________________
	Noted. I've removed this task.
		[T][ ] Read book
	Now you have 2 tasks in the list.
	____________________________________________________________
```



### 5. Find All Tasks Containing A Keyword
`find <keyword>`

With reference to the keyword entered by the user, list all tasks whose description
contains this keyword.

*Example of usage:*

```
find book
```

*Expected outcome:*

```
	____________________________________________________________
	Here are the matching tasks in your list:
	1.[T][ ] Read book
	2.[D][ ] Return book (by: friday)
	____________________________________________________________
```

*When no task contains the keyword:*

```
	____________________________________________________________
	No task in your list matches the keyword.
	____________________________________________________________
```
### 6. Exit ChatBot

`bye`

Saves all the tasks which are currently in the list to the *tasks.txt* file and exits the ChatBot.

*Example of usage:*

```
bye
```

*Expected outcome:*

```
	____________________________________________________________
	Tasks were saved successfully.
	Bye. Hope to see you again soon!
	____________________________________________________________
```
