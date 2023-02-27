# User Guide

The following is a User Guide for a personal Assistant Chatbot named Duke (and his partner Uncle Simon)

## Main Features 

- Add and delete 3 types of tasks for different use cases
- List all tasks and search for them via a keyword
- Mark and Unmark tasks
- Singlish mode (feat.: Uncle Sam)

### Feature - Three Types of Tasks

The chatbot allows the user to enter tasks in 3 formats:

#### todo: 
A task that only has a description. Suitable for simple things like Shopping list
	For example: 
	- ```1.[T][_] buy milk from the store```
	- The ```[T]``` icon represents a todo task

#### deadline: 
A task that has a description and a date to complete the task by. Suitable for tasks that needs to be completed by a deadline.
	For example: 
	- ```1.[D][_] buy milk from the store (by: next thursday 5 pm)```
	- The ```[D]``` icon represents a deadline task

#### event 
A task that has a description with a start and end date. Suitable for tasks that needs to be completed within a certain timeframe.
	For example: 
	- ```1.[E][_] buy milk from the store (from: next tuesday 10 am to: next thursday 5pm)```
	- The ```[E]``` icon represents an event task

### Feature - Add, List, Delete, Find

The chatbot allows the user to add and delete tasks:

#### Add
 Adding a task requires the user to key in the command name for the type of tasks, followed by the description and certain arguments depending the type of tasks
	- For example:
		- ``` todo buy milk from the store ```
		- ``` deadline buy milk from the store /by next thursday 5 pm ```
		- ``` event buy milk from the store /from next tuesday 10 am /to next thursday 5pm ```
	- Result: 
 ```
	1.[T][_] buy milk from the store
	2.[D][_] buy milk from the store (by: next thursday 5 pm)
	3.[E][_] buy milk from the store (from: next tuesday 10 am to: next thursday 5pm)
``` 

#### List
To see all the tasks added, simple type:  ```list```
	- For example, if you have typed the above commands, typing list will show the list of tasks you have added: 
```
	1.[T][_] buy milk from the store
	2.[D][_] buy milk from the store (by: next thursday 5 pm)
	3.[E][_] buy milk from the store (from: next tuesday 10 am to: next thursday 5pm)
```

#### Delete
Consider the following tasks:
```
	1.[T][_] buy milk from the store
	2.[D][_] buy milk from the store (by: next thursday 5 pm)
	3.[E][_] buy milk from the store (from: next tuesday 10 am to: next thursday 5pm)
````
To delete, simply type: ``` delete <task index>```
	- For example:
		- ``` delete 2 ```
			 ```2.[D][_] buy milk from the store (by: next thursday 5 pm)``` Will be deleted
Typing ```list``` will now show:
```	
	1.[T][_] buy milk from the store
	2.[E][_] buy milk from the store (from: next tuesday 10 am to: next thursday 5pm)
```

#### Find
Consider the following tasks:
```
	1.[T][_] buy milk from the store
	2.[D][_] buy eggs from the store (by: next thursday 5 pm)
	3.[E][_] buy baby milk powder from the store (from: next tuesday 10 am to: next thursday 5pm)
````
To find tasks that contains a certain keyword, simply type: ``` find <keyword>```
	- For example:
		- ``` find milk  ```
	- The chatbot will now show the tasks that contains the  ```milk```  keyword: 
```
	 1.[T][_] buy milk from the store
	 3.[E][_] buy baby milk powder from the store (from: next tuesday 10 am to: next thursday 5pm) 
```
Do note that the find command only search tasks descriptions only returns a result if it contains the keyword. In addition, it does not search for substrings in words. 
	- For example, consider the following tasks:
```	
	1.[T][_] ask the bookkeeper if they have new books in stock
	2.[D][_] buy a new book from the store (by: next thursday 5 pm)
```
Typing ```find book``` will now show:
```
	2.[D][_] buy a new book from the store (by: next thursday 5 pm)
```
Although task 1 contains the string ```book``` in ```bookkeeper``` , the find command does not consider it to the same as ```book``` .

### Feature - Mark/Unmark

The chatbot allows tasks to be marked or unmarked.

The ```[X]``` icon represents a mark task
The ```[_]``` icon represents an unmark task

#### Mark
To mark a task, simply type:  ```mark <task index>```
	- For example, suppose you have the following tasks:
```
	1.[T][_] buy milk from the store
	2.[D][_] buy eggs from the store (by: next thursday 5 pm)
	3.[E][_] buy baby milk powder from the store (from: next tuesday 10 am to: next thursday 5pm)
```
Typing ```mark 2``` will now show:
```
	1.[T][_] buy milk from the store
	2.[D][X] buy eggs from the store (by: next thursday 5 pm)
	3.[E][_] buy baby milk powder from the store (from: next tuesday 10 am to: next 
```

#### Unmark
To mark a task, simply type:  ```unmark <task index>```
	- For example, suppose you have the following tasks:
```
	1.[T][_] buy milk from the store
	2.[D][X] buy eggs from the store (by: next thursday 5 pm)
	3.[E][_] buy baby milk powder from the store (from: next tuesday 10 am to: next thursday 5pm)
```
Typing ```unmark 2``` will now show:
```
	1.[T][_] buy milk from the store
	2.[D][_] buy eggs from the store (by: next thursday 5 pm)
	3.[E][_] buy baby milk powder from the store (from: next tuesday 10 am to: next 
```

### Feature - Singlish and Exiting program

The chatbot has a "Singlish" mode, which alters the messages Duke says. All functionalities and command remain the same.

To toggle between regular Duke and "Uncle Simon" (the bot's name in Singlish mode), simply type: ```change lang```

To exit the program, simply type:  ```bye```


## Summary Table

| Command | Description |
|---------|-------------|
| todo \<description\> | Adds a todo task with the given description |
| deadline \<description\> /by \<deadline\> | Adds a deadline task with the given description and deadline |
| event \<description\> /from \<start\> /to \<end\> | Adds an event task with the given description and start and end dates |
| list | Displays all the tasks that have been added |
| delete \<task index\> | Deletes the task with the given index |
| find \<keyword\> | Displays all the tasks whose description contains the given keyword |
| mark \<task index\> | Marks the task with the given index as done |
| unmark \<task index\> | Marks the task with the given index as undone |
| change lang | Toggles Singlish mode on or off |
| bye | Exits the program |







