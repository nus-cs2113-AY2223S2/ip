# User Guide
Duke is a CLI based task-mananger. Duke can keep track of a variety of tasks, while also ensuring that this process is secure and seamless.

The user guide serves as a handbook for users who wish to do two main things:
- Understand more about what Duke can/cannot do
- Maximise their use cases for Duke as a personal Task Manager

The handbook is catered to users who are new to CLI based platforms therefore most parts are kept concicse and are filled with examples, however some parts might appear verbose to advanced users.

# Table of Contents
- [User Guide](User-Guide)
- [Start](Starting-Procedures)
- [Features](Features)
  - [Add A Task](Features#Add-a-Task)
    - [todo](Features#Add-a-Task#todo)

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
event CS2113 midterms /from 2023-03-03 /to 2023-03-04
	---------------------------------------------------------------------------------
	Got it. I've added this task:
		[E][ ] CS2113 midterms (from: Mar 3 2023 to: Mar 4 2023)
	Now you have 1 task in the list
	---------------------------------------------------------------------------------
Note the formats of the `/from` and `/to` dates.

More detailed examples of how Duke handles other `event` inputs can be found in the [More examples on event](Appendix#More-examples-on-event) section.








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


## More examples on event
Here are some examples of event being used and the respective outputs:
