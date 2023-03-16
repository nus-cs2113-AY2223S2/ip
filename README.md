# Anna

Anna is a chatbot that helps you to organise your tasks. It can add,
delete and mark done tasks you've listed. It can also help you keep track of deadlines
and calender events.

Anna runs on a simple CLI interface that is lightweight and cross-platform friendly.
Anna is also designed to be friendly to first time users by being capable of handling missing inputs

## Setting up

Prerequisites: JDK 11
1. Download the ip.jar under 'Releases'
2. Extract the file into a folder you wish to keep it (e.g. `C:\Users\<username>\Desktop\AnnaBot`)
3. Open a terminal (e.g Command Prompt for Windows) and navigate to the previous directory<br>
4. Run the jar file using command `java -jar ip.jar`<br>
#### Example (Windows Command Prompt):
```
C:\Users\user> cd Desktop\AnnaBot
C:\Users\user\Desktop\AnnaBot> java -jar ip.jar
Hi it's Anna!
What do you need to do?
You may load existing data using the load command
```

## Commands
To interact with Anna, please use the following commands

### exit the program: `bye`
This command exits the running instance.
```
bye
Go away Anna
O-kay bye......
```
### view the current tasklist: `list`
`list` will show the current tasklist in order of addition (oldest task first)
```
todo task1
Okay! I've added: [T] task1
event task2 /from today /to tomorrow
Okay! I've added: [E] task2
list
Here's what we've gotta do:
1. [T][ ] task1
2. [E][ ] task2
    Start: from today
    End: to tomorrow
We currently have 2 tasks
```

### Mark task as done: `mark <task number>`
Sets a given task as completed. Yay!
```
mark 1
Okay I've marked item 1 as done:
1. [T][X] task1
```

### Mark task as not done: `unmark <task number>`
Sets a given task as not done
```
unmark 1
Oh we aren't done with item 1?
1. [T][ ] task1
```

### Create a simple todo task: `todo <task description>`
Creates a standard todo task and adds it into the tasklist
```
todo task1
Okay! I've added: [T] task1
```
Anna will prompt you if you forget to input the name of the task
```
todo
What are you referring to?
task1
Okay! I've added: [T] task1
```
### Create a task with a deadline: `deadline <task description> /by <duedate>`
Creates a task with a deadline and adds it into the tasklist
```
deadline task2 /by 5 jan
Okay! I've added: [D] task2
```
Anna will prompt you for the deadline if it is missing
```
deadline task2
When is this due by?
5 jan
Okay! I've added: [D] task2
```
### Create a task with start and end dates:<br/>`event <task description> /from <startdate> /to <enddate>`
Creates a task with start and end times and adds it into the tasklist
```
event task3 /from today /to tomorrow
Okay! I've added: [E] task3
```
Similarly to `deadline`, Anna will prompt you if you are missing date details
```
event task3 /to tomorrow
When does this event start?
today
Okay! I've added: [E] task3
```
### Remove a task: `delete <task number>`
The given task will be deleted from the tasklist
```
delete 1
Okay! I've deleted task: 1
```
### Loading and Saving of tasklist
Anna also supports import/export of your tasklist in the form of a .txt file. This can be helpful if you want to refer to the tasklist in a future date!

`save` exports the current tasklist into a tasklist.txt file
```
save
Successfully exported TaskList!
Written to: C:\Users\lhyao\Desktop\TaskList.txt
```
`load` imports a previous tasklist into the program's tasklist <br>
Anna looks for a `TaskList.txt` file in the same directory to import from
```
load
Okay! I've added: [D] task2
Okay! I've added: [D] task2
Okay! I've added: [E] task3
Okay! I've added: [E] task3
Okay! I've added: [T] task1
```
## Usage Example

#### Input Example 1
`deadline ip /by tonight`

#### Expected Output 1
`Okay! I've added: [D] ip`

#### Input Example 2
`list`

#### Expected Output 2
```
Here's what we've gotta do:
1. [D][ ] ip 
    Deadline: tonight
We currently have 1 tasks
```