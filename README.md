# Anna

Anna is a chatbot that helps you to organise your tasks. It can add, delete and mark done tasks you've listed. It can also help you keep track of deadlines and calender events.

Anna runs on a simple CLI interface that is lightweight and cross-platform friendly

## Setting up

Prerequisites: JDK 11
1. Download the ip.jar under 'Releases'
2. Extract the file and run

## Commands
To interact with Anna, please use the following commands

1. `bye` to exit the program
2. `list` to view tasks in the tasklist currently
3. `mark <task number>` sets a given task as done :D
4. `unmark <task number>` sets a given task as not done D:
5. `todo <task description>` adds a simple task into the tasklist
6. `deadline <task description> /by <duedate>` adds a task with a deadline into the tasklist
7. `event <task description> /from <startdate> /to <enddate>` adds a task with start and end times into the tasklist
8. `delete <task number>` removes a given task from the list

Anna also supports import/export of your tasklist in the form of a .txt file. This can be helpful if you want to refer to the tasklist in a future date!

1. `save` exports the current tasklist into a tasklist.txt file
2. `load` imports a previous tasklist into the program's tasklist

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