# User Guide
Duke is a CLI based task-mananger. Duke can keep track of a variety of tasks, while also ensuring that this process is secure and seamless.

The user guide serves as a handbook for users who wish to do two main things:
- Understand more about what Duke can/cannot do
- Maximise their use cases for Duke as a personal Task Manager

The handbook is catered to users who are new to CLI based platforms therefore most parts are kept concicse and are filled with examples, however some parts might appear verbose to advanced users. Regardless, users are encouraged to read the whole guide, in the order specified in the [Tabel of Contents](#table-of-contents). Advanced users can check out the apprendix which delves deeper into the kind of inputs the commands can handle.

# Table of Contents
- [User Guide](#user-guide)
- [Start](#starting-procedures)
- [Features](#features)
  - [Add A Task](#add-a-task)
    - [Todo](#todo)
    - [Deadline](#deadline)
    - [Event](#event)
  - [List](#list)
  - [Mark](#mark)
  - [Unmark](#unmark)
  - [Delete](#delete)
  - [Find](#find)
  - [Help](#help)
  - [Bye](#bye)
- [Appendix](#appendix)

# Starting Procedures
If you're new to CLIs as a whole, fret not. Here is a list of things you would need to do to get started:
- Firstly, you would need to download the `ip.jar` file.
- Move that file to a different folder, preferably if you create a new folder called `dukeTesting` specifically for this. Even if you do not do so, it is perfectly fine, this is because the `dukeData.txt` will spawn in the same directory you run the `ip.jar` file from. 
- Next, you would need to check if your system has atleast java 11 running, any higher version is fine but `Duke` was built on java 11 so its good to know that.
- Now you're ready to start running Duke! Head over to the directory where the `ip.jar` file is and run the following command `java -jar ip.jar`
- You would see the following loading screen:
```
Initializing Data collection sites......................
Initializing Duke packages..............................
Initializing corefunctioanlities........................
Initializing Duke.......................................
Initializing ExceptionHandler...........................
Initializing TaskList...................................
Initializing Ui.........................................
corefunctionalities complete............................
Initializing datatypes..................................
Initializing Tasks......................................
Initializing Events.....................................
Initializing Deadlines..................................
Initializing Todos......................................
Initializing TaskFileHandler............................
datatypes complete......................................

```
- This means that Duke is up and booting, soon you would arrive at a screen that greets you like this:
```
Hello from
 ____        _        
|  _ \ _   _| | _____ 
| | | | | | | |/ / _ \
| |_| | |_| |   <  __/
|____/ \__,_|_|\_\___|

---------------------------------------------------------------------------------
Hello! I'm Duke
What can I do for you?
If you are unsure of the commands, type 'help'
---------------------------------------------------------------------------------

```
- The next part on the guide will inform you on what are some features and how should a user use them

# Features
The features and potential use cases are listed below

## Add a `Task`
Duke can support three main kinds of `Tasks`. Duke is able to differentiate between these three kinds of `Tasks` based on the keywords alloted to each task.

### `todo`
A simple task which a user needs to complete. A `todo` **must be accompanied with a description of the task**. Additionally, `todo` is *case sensitive*, hence typing `Todo` or `tODO` or anything else would be rejected by Duke (as you will see below). 

Hence this is the format of any todo:
`todo <DESCRIPTION_OF_TASK>`

Here is an example of how todo is used:

#### Normal todo example
```
todo get milk
	---------------------------------------------------------------------------------
	Got it. I've added this task:
		[T][ ] get milk
	Now you have 1 task in the list
	---------------------------------------------------------------------------------

```
In the case above, the description of the todo is *get milk*.

More detailed examples of how Duke handles other `todo` outputs can be found in the [More examples on todo](Appendix#More-examples-on-todo) section.

### `deadline`
A simple `Task` which a user needs to complete **by a certain deadline**. A deadline must be accompanied by a **description, a '/by' term and a deadline in a certain format**. Like `todo`, `deadline` is also case senstive.

The format of the deadline is as such:
`deadline <DESCRIPTION_OF_TASK> /by yyyy-mm-dd`

Here is an example of how `deadline` is used. 

#### Normal Deadline Example
```
deadline get milk /by 2023-02-27
	---------------------------------------------------------------------------------
	Got it. I've added this task:
		[D][ ] get milk (by: Feb 27 2023)
	Now you have 2 tasks in the list
	---------------------------------------------------------------------------------

```
Note that `2023-02-27` follows the `yyyy-mm-dd` format

More detailed examples of how Duke handles other `todo` outputs can be found in the [More examples on deadline](Appendix#More-examples-on-deadline) section.

### `event`
A simple `Task` which a user needs to complete **between a range of dates**. An event must be accompanied by a **description, a '/from' term, a '/to' term and dates for the 2 terms in a specific format**. Like `todo` and `deadline`, `event` is also case sensitive.

The format of the event task is as such:
`event <DESCRIPTION_OF_TASK> /from yyyy-mm-dd /to yyyy-mm-dd`

An example is listed below:

#### Normal Event Example
```
event CS2113 midterms /from 2023-03-03 /to 2023-03-04
	---------------------------------------------------------------------------------
	Got it. I've added this task:
		[E][ ] CS2113 midterms (from: Mar 3 2023 to: Mar 4 2023)
	Now you have 1 task in the list
	---------------------------------------------------------------------------------
```
Note the formats of the `/from` and `/to` dates.

More detailed examples of how Duke handles other `event` inputs can be found in the [More examples on event](Appendix#More-examples-on-event) section.


# `list`
This is a command which the use could use to see all the `Tasks` they have created so far. Do note that the list is **case sensitive**. 

The format is as such: `list`

An example of its usage would be the following:
```
list
	---------------------------------------------------------------------------------
	Here are the tasks in your list:
	1.[E][ ] CS2113 midterms (from: Mar 3 2023 to: Mar 4 2023)
	2.[T][ ] get milk
	3.[T][ ] get some sleep
	---------------------------------------------------------------------------------

```

Additionally, there are 2 pairs of square braces on each level, the first pair of square braces indicates the kind of task that is listed.
- `[T]` indicates that the task is a `todo`.
- `[D]` indicates that the task is a `deadline`.
- `[E]` indicates that the task is a `event`.

The pair of braces beside the task indicators are the **completion indicators.**
- `[ ]` refers to a task that is not complete.
- `[X]` refers to a task that has been completed.


# `mark`
This command allows the user mark a task if they have already completed it. This ensures that users are able to view tasks which they have completed, and tasks which they have not. As mentioned in the pervious section, the completion status of a `Task` is represented through the **completion indicators**. For a quick recap, do read through the section about the [list](#list) command again. Duke is able to understand which task a user wishes to mark based on the serial number of the task they wish to mark.

In gist, this is the format of marking: `mark <SERIAL_NUMBER_OF_TASK>`.

Here is an example that elucidates the same:
```
mark 2
	---------------------------------------------------------------------------------
	Nice! I've marked this task as done:
		[T][X] get milk
	---------------------------------------------------------------------------------

```

# `unmark`
Users are advised to go through [mark](#mark) before reading through this section, as the `unmark` command is just the opposite of marking. Unmark would turn a marked task into an umarked task. Hence the syntax of the marking and unmarking is the same. In order to unmark, the user would need to inform Duke of the serial number of the task which they wish to unmark.

The format is as such: `unmark <SERIAL_NUMBER_OF_TASK>`

Here is an example which demonstrates this:
```
unmark 2
	---------------------------------------------------------------------------------
	Nice! I've marked this task as not done:
		[T][ ] get milk
	---------------------------------------------------------------------------------

```
# `delete`

The delete command allows the user to delete a certain task of their choice. **It is very imnportant to note that `mark` and `delete` are very different, even though they both can be used after the completion of  Task**. The `mark` command only marks an item as completed, **but still leaves it in the list of tasks**. The `delete` command **removes the task from the list as a whole**, *there is no way to recover the task after this*. Similar to [mark](#mark) and [unmark](#unmark), in order to delete a `Task`, the user needs to supply the serial number of the task to Duke.

This is the format of the `delete` command: `delete <SERIAL_NUMBER_OF_TASK>`.

Here is an example:
```
delete 3
	---------------------------------------------------------------------------------
	Noted! I've removed this task!
		[T][ ] get some sleep
	Now you have 2 tasks in the list
	---------------------------------------------------------------------------------

```

# `find`
The `find` command can be used to look for a specific phrase in the descriptions for all the tasks which are listed. This does not include tasks which have been deleted. Duke would then return all the tasks which have this phrase in their descriptins

The format of the `find` command is as such: `find <PHRASE_TO_LOOK_FOR>`.

An example of how to use this command can be seen below:
```
find CS2113
	---------------------------------------------------------------------------------
	1.[E][ ] CS2113 midterms (from: Mar 3 2023 to: Mar 4 2023)
	---------------------------------------------------------------------------------

```
# `help`

The help command can be used to get a list of all the commands and how to use them. In a certain sense, its a more portable version of this user guide included in Duke, and can be invoked by using: `help`.

Here is an example:

```
help
	---------------------------------------------------------------------------------
	Hi! These are the commands which duke understands!
	---------------------------------------------------------------------------------
	list - This would display all the existing Tasks in the Task List
	---------------------------------------------------------------------------------
	todo - Creates a todo, use it by adding 'todo' and some description. An example is listed below:
		'todo get milk'
	---------------------------------------------------------------------------------
	deadline - Creates a deadline, use it by adding 'deadline' followed by some description and a deadline which follows '/by'
	Note that the dates must follow the following format: yyyy-mm-dd
		'deadline get milk /by 2023-12-01'
	---------------------------------------------------------------------------------
	event - Creates an event, use it by adding 'event' ,some description, a start date followed by '/from' and an end date followed by '/to'
	Note that the dates must follow the following format: yyyy-mm-dd
		'event get some milk /from 2023-03-01 /to 2023-03-02
	---------------------------------------------------------------------------------
	mark - mark would inform Duke to mark a task as complete. To invoke type 'mark' followed by the serial number of the specific task
		 'mark 1'
	---------------------------------------------------------------------------------
	unmark - unmark would inform Duke to unmark a task as incomplete. To invoke type 'unmark' followed by the serial number of the specific task
		 'unmark 1'
	---------------------------------------------------------------------------------
	delete - delete would inform Duke to delete a task. To invoke type 'delete' followed by the serial number of the specific task
		 'delete 1'
	---------------------------------------------------------------------------------
	find - find would inform Duke to look for a certain phrase across all the Tasks. To invoke type 'find' followed by the phrase you wish to look for
		 'find book'
	---------------------------------------------------------------------------------
	bye - to exit the program!
	---------------------------------------------------------------------------------

```
# `bye`
This is the final command listed here. Using this command would quit the application and return back to your terminal.
This is the format of the command: `bye `.

An example is as such:
```
bye
	---------------------------------------------------------------------------------
	Bye. Hope to see you again soon!
	---------------------------------------------------------------------------------

```

# Final Notes
The commands listed above are the basic features of Duke. If you wish to know more about what kind of inputs these commands can handle in detail, do check out the appendix!

At any rate, Happy Coding!


# Appendix
## More examples on todo
Here are some examples of todo being used and the respective outputs:
#### todo example with no description

In the odd chance a user forgets to supply Duke with the description, the following happens:
```
todo
	---------------------------------------------------------------------------------
	Please ensure that the todo has a description!
	---------------------------------------------------------------------------------
```
#### `Todo` instead of `todo`
As mentioned previously, if a user were to enter `Todo` instead of `todo`, Duke would reject it and ask the user to enter again.
```
Todo get more milk
	---------------------------------------------------------------------------------
	Please enter a valid input
	---------------------------------------------------------------------------------

```

## More examples on deadline
Here are some examples of deadline being used and the respective outputs:
#### deadline example no description
If the user forgets to enter the description the following happens:
```deadline /by 2023-02-27
	---------------------------------------------------------------------------------
	Please ensure that the deadline isn't empty!
	---------------------------------------------------------------------------------

```

#### deadline example no deadline set
If the user forgets to enter the deadline all together, the following happens:
```
deadline get milk /by 
	---------------------------------------------------------------------------------
	Please ensure that the deadline isn't empty!
	---------------------------------------------------------------------------------

```

In the event the user decides to leave the deadline with all white spaces, the following happens:
```
deadline get milk /by      
	---------------------------------------------------------------------------------
	Please ensure that the deadline is not composed of solely white spaces!
	---------------------------------------------------------------------------------

```
#### deadline example missing `deadline` phrase
If the deadline phrase was missing, this happens:
```
get food /by 2023-07-22
	---------------------------------------------------------------------------------
	Please enter a valid input
	---------------------------------------------------------------------------------

```

#### deadline example with missing `/by` phrase
In the event the user forgets to enter the `/by` phrase
```
deadine get food 2023-07-22
	---------------------------------------------------------------------------------
	Please enter a valid input
	---------------------------------------------------------------------------------

```

#### deadline example with the date in the wrong format
In the event a user enters the date in the wrong format, the following happens:

```
deadline get food /by tomorrow
	---------------------------------------------------------------------------------
	Please ensure that the deadline follows the following format: yyyy-mm-dd.
	---------------------------------------------------------------------------------

```
#### deadline example with date in the past
Duke is aware of the current date, it can access your systems date. Hence entering a deadline that is in the past would warrant a prompt from Duke:
```
deadline travel to the past /by 2019-02-02
	---------------------------------------------------------------------------------
	Please ensure that the deadline isn't before the current date
	---------------------------------------------------------------------------------

```


## More examples on event
Here are some examples of event being used and the respective outputs:

#### event example with missing descriptions and range
If the event description and range were missing, this is how Duke would react:
```
event
	---------------------------------------------------------------------------------
	Please ensure that the event isn't empty!
	---------------------------------------------------------------------------------


```

#### event example with missing range of dates

This is what would happen if the user missed on adding the dates to his task:
```
event CS2113 Homework
	---------------------------------------------------------------------------------
	Please ensure that you include the '/from' phrase to indicate the start of the event!
	---------------------------------------------------------------------------------

```


#### event examples with missing description
This is what would happen if the description were to be missing:
```
event    /from 2023-04-04 /to 2023-05-05
	---------------------------------------------------------------------------------
	Please ensure that the event isn't empty!
	---------------------------------------------------------------------------------

```

#### event example with /from date in the wrong format
This is what would happen if the /from date is in the wrong format:
```
event CS2113 HW /from today /to 2023-12-02
	---------------------------------------------------------------------------------
	Please ensure that the from and to dates follow the following format: yyyy-mm-dd.
	---------------------------------------------------------------------------------
```


#### event example with /to date in the wrong format
This is what would happen if the to date is in the wrong format:
```
event CS2113 HW /from 2023-12-02 /to tomorrow
	---------------------------------------------------------------------------------
	Please ensure that the from and to dates follow the following format: yyyy-mm-dd.
	---------------------------------------------------------------------------------
```

#### event examples with /from date after the /to date
This is what would happen if the /from date is after the /to date:
```
event CS2113 HW /from 2026-01-10 /to 2023-01-01
	---------------------------------------------------------------------------------
	Please ensure that your dates are chronologically appropriate...
	The from date cannot be after the to date
	---------------------------------------------------------------------------------

```

#### event examples with range of dates in the past
This is what would happen if the dates are in the past:
```
event CS2113 HW /from 2019-01-01 /to 2019-02-01
	---------------------------------------------------------------------------------
	Please ensure that the from and to dates aren't in the past...
	---------------------------------------------------------------------------------

```
