# DukeBot User Guide

DukeBot is a personal Chatbot that helps you keep track of your tasks!
## Features 

DukeBot can help you add, list, delete, find and mark or unmark tasks!

### Add

There are 3 tasks that DukeBot can add for you in your list of tasks:
1. Todo
2. Event
3. Deadline

### `todo` - Add a todo task

Adds a todo task to the list.

<h5>Syntax</h5>
`todo <task_description>`

<h5> Example of usage:  </h5>

`todo eat breakfast`

<h5> Expected outcome: </h5>

DukeBot will add the todo task 'eat breakfast' as a todo task in the list!

```
        Got it! Added this task: 
	    [T] [ ] eat breakfast
	Now you have 1 task in the list
```
### `event` - Add an event task

Adds an event task to the list.

Events have a 'from' and 'to' datetime to include.

<h5>Syntax</h5>
`event <task_description> /from <date_time> /to <date_time>`

<h5> Example of usage:  </h5>

`event birthday party /from Friday 6pm /to Friday 10pm`

<h5> Expected outcome: </h5>

DukeBot will add the event task 'birthday party' as an event task in the list with 'from' and 'to' datetime parameters in brackets!

```
        Got it! Added this task: 
	    [E] [ ] birthday party (from:  Friday 6pm  to:  Friday 10pm)
	Now you have 2 tasks in the list
```
### `deadline` - Add a deadline task

Adds a deadline task to the list.

Deadlines have a 'by' datetime to include.

<h5>Syntax</h5>
`deadline <task_description> /by <date_time>`

<h5> Example of usage:  </h5>

`deadline CS2113 assignment /by March 3 11:59pm`

<h5> Expected outcome: </h5>

DukeBot will add the deadline task 'CS2113 assignment' as a deadline task in the list with 'by' datetime parameter in brackets!

```
        Got it! Added this task: 
	    [D] [ ] CS2113 assignment (by:  March 3 11:59pm)
	Now you have 3 tasks in the list
```
### Display All Tasks: `List`

DukeBot can display all the tasks currently in your list of tasks.

<h5>Syntax</h5>
`list`

<h5> Example of usage:  </h5>

`list`

<h5> Expected outcome: </h5>

DukeBot will display all the tasks currently in your list of tasks.

```
        Here are the tasks in your list:

	1. [T] [ ] eat breakfast


	2. [E] [ ] birthday party (from:  Friday 6pm  to:  Friday 10pm)


	3. [D] [ ] CS2113 assignment (by:  March 3 11:59pm)
```


### Delete a Task: `Delete`

DukeBot can delete a task from your list of tasks.

<h5>Syntax</h5>
`delete <task_number>`

<h5> Example of usage:  </h5>

`delete 1`

<h5> Expected outcome: </h5>

DukeBot will delete task 1 from the list

```
        Noted! I have deleted this task: 
		[T] [ ] eat breakfast
	Now you have 2 tasks in the list
```
### Find Relevant Tasks: `Find`

DukeBot can display tasks from the list that contains the same word as the word from user input.

<h5>Syntax</h5>
`find <word>`

<h5> Example of usage:  </h5>

`find party`

<h5> Expected outcome: </h5>

DukeBot will find and display event task 'birthday party' which contains the word 'party'.

```
        Here are the matching tasks in your list: 


	1. [E] [ ] birthday party (from:  Friday 6pm  to:  Friday 10pm)
```
### Mark a Task: `Mark`

DukeBot can mark a task as done. 
If the task is already done, DukeBot will not need to change the status of the task.

<h5>Syntax</h5>
`mark <task_number>`

<h5> Example of usage:  </h5>

`mark 1`

<h5> Expected outcome: </h5>

DukeBot will mark task 1 as done with an 'X'.

```
        Nice! I've marked this task as done:

		[E] [X] birthday party (from:  Friday 6pm  to:  Friday 10pm)
```
### Unmark a Task: `Unmark`

DukeBot can mark a task as not done.
If the task is already not done, DukeBot will not need to change the status of the task.

<h5>Syntax</h5>
`unmark <task_number>`

<h5> Example of usage:  </h5>

`unmark 1`

<h5> Expected outcome: </h5>

DukeBot will unmark the done status of task 1 by removing the 'X'.

```
        Oki! I've marked this task as not done yet:

		[E] [ ] birthday party (from:  Friday 6pm  to:  Friday 10pm)
```
### Exit the Program: `Bye`

Exits the DukeBot program.

<h5>Syntax</h5>
`bye`

<h5> Example of usage:  </h5>

`bye`

<h5> Expected outcome: </h5>

DukeBot program will stop execution until it is run again.

```
        Goodbye! Hope to see you again soon ^^!
```


