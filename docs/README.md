# User Guide
This is a user guide for CS2113 iP by Kim Tae Won A0239794J (Github username: coregano)

## Setting up
Prerequisites: JDK 11, update intelliJ to latest release.
1. Open intelliJ
2. Open the project in intelliJ by
   1. Click "Open"
   2. Select the project directory
   3. Click "ok"
   4. Accept any defaults prompted
3. Configure the project to use **JDK 11** (no other versions allowed) as explained [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
4. Configure the **Project Language Level** to 'SDK default' 
5. Locate 'src/main/java/DukeManager/Duke.java' and right-click
6. Choose 'Run Duke.main()'
        If the setup is correct, you should see this:
```
    ____________________________________________________________
	  Welcome, new user. How may I help you?
    ____________________________________________________________
    ____________________________________________________________
	  Hello! I'm kimo 

	  What can I do for you?

    ____________________________________________________________
	  Enter command: 
```
## Features 

### Feature - Task

kimo will keep track of your tasks.

### Feature - Mark

User can mark tasks that they have completed.

### Feature - Save and load

The list of tasks will be automatically saved after every use of kimo and the list will be automatically loaded everytime kimo is run.

### Feature - Find tasks

User can use keywords to find tasks matching the description.


## Usage

### `todo` - Adds a new Todo task

Creates a new task of type Todo (contains task description) and stores it in the task list. 

Command: 
`todo [description]`

Example of usage: 
```
todo sweep floor
    ____________________________________________________________
	  Command entered:todo sweep floor
    ____________________________________________________________
    ____________________________________________________________
	  New task added: [T] [ ] sweep floor
    ____________________________________________________________
```

### `deadline` - Adds a new Deadline task

Creates a new task of type Deadline (contains task description and end date) and stores it in the task list.

Command:
`deadline [description] /by [enddate]`

Example of usage:
```
deadline clean room /by tomorrow
    ____________________________________________________________
	  Command entered:deadline clean room /by tomorrow
    ____________________________________________________________
    ____________________________________________________________
	  New task added: [D] [ ] clean room /by tomorrow
    ____________________________________________________________
```

### `event` - Adds a new Event task

Creates a new task of type Event (contains task description, start date end date) and stores it in the task list.

Command:
`event [description] /from [startdate] /to [enddate]`

Example of usage:
```
event gaming /from now /to end
    ____________________________________________________________
	  Command entered:event gaming /from now /to end
    ____________________________________________________________
    ____________________________________________________________
	  New task added: [E] [ ] gaming /from now /to end
    ____________________________________________________________
```

### `list` - shows the current list of tasks and their completeness 

Command: 
`list`

Example of usage:
```
list
    ____________________________________________________________
	  Command entered:list
    ____________________________________________________________
    ____________________________________________________________
	  	  These are the task(s) in your list: 
	  1. [T] [ ] sweep floor

	  2. [D] [ ] clean room /by tomorrow

	  3. [E] [ ] gaming /from now /to end

    ____________________________________________________________
```

### `mark` - Marks a task as done

Marks a task as completed in the list using a task index.

Command:
`mark [taskIndex]`

Example of usage:
```	
mark 1
    ____________________________________________________________
	  Command entered:mark 1
    ____________________________________________________________
    ____________________________________________________________
	  Marked Task: [T] [X] sweep floor
    ____________________________________________________________
```