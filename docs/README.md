# User Guide For Duke Task Manager App

### App Summary

Duke Task Manger App is a command-line interface app that users can use to keep track
of various types of tasks.


## Feature 1: Adding Tasks
There are 3 types of tasks that are available to be added to the task list 

### 1. Todo Tasks 
Todo tasks are tasks that do not contain any deadlines
> Syntax: **todo** *[Name of Task]*

_Example of adding Todo Task_

**Input:** 

`todo CS2113 Quiz 7`

**Output:** 

`Got it. I've added this task:`

`[T][ ] CS2113 Quiz 7`

`Now you have 1 in the list.`

### 2. Deadline 
Deadline tasks are tasks contain a deadline
> Syntax: **deadline** *[Name of Task]* **/by** *[Deadline Of Task]*

_Example of adding Deadline Task_

**Input:**

`deadline EE2026 Graded Lab /by Fri 2359`

**Output:**

`Got it. I've added this task:`

`[D][ ] EE2026 Graded Lab (by: Fri 2359)`

`Now you have 1 in the list.`

### 3. Event 
Event tasks are tasks which contains a start time and an end time
> Syntax: **event** *[Name of Task]* **/from** *[start time]* **/to** *[end time]*

_Example of adding Event Task_

**Input:** 

`event EE2211 Mid Terms /from Sat 2pm /to Sat 4pm`

**Output:** 

`Got it. I've added this task:`

`[E][ ] EE2211 Mid Terms (from: Sat 2pm to: Sat 4pm)`

`Now you have 1 in the list.`

## Feature 2: Listing current tasks
List out all current tasks, displaying associated task information
> Syntax: **list**

_Example of listing out tasks:_

**Input:**

`list`

**Output:** 

`1. [E][ ] EE2211 Mid Terms (from: Sat 2pm to: Sat 4pm)`

`2. [T][ ] CS2113 Quiz 7`

`3. [D][ ] EE2026 Graded Lab (by: Fri 2359)`

## Feature 3: Deleting tasks
Deletes task according to task index
> Syntax: **delete** *[index]*

_Example of deleting task:_

Before Input: 

`1. [E][ ] EE2211 Mid Terms (from: Sat 2pm to: Sat 4pm)`

`2. [T][ ] CS2113 Quiz 7`

`3. [E][ ] CG2023 Lecture (from: Mon 2pm to: Mon 4pm)`

**Input:** 

`delete 2`

**Output:**

`Noted. I've removed this task:`

`[T][ ] CS2113 Quiz 7`

`Now you have 2 in the list.`

_list command to see task list_

`list `

`1. [E][ ] EE2211 Mid Terms (from: Sat 2pm to: Sat 4pm)`

`2. [E][ ] CG2023 Lecture (from: Mon 2pm to: Mon 4pm)`

## Feature 4: Marking and Unmarking tasks as completed
Users can mark task as done or undone according to task index
> Syntax: **mark** *[index]* OR **unmark** *[index]*

_Current Task List_

`1. [E][ ] EE2211 Mid Terms (from: Sat 2pm to: Sat 4pm)`

`2. [T][ ] CS2113 Quiz 7`

**Input:** 

`mark 2` 

**Output:**

`1. [E][ ] EE2211 Mid Terms (from: Sat 2pm to: Sat 4pm)`

`2. [T][X] CS2113 Quiz 7`

**Input:**

`unmark 2`

**Output:**

`1. [E][ ] EE2211 Mid Terms (from: Sat 2pm to: Sat 4pm)`

`2. [T][ ] CS2113 Quiz 7`



## Feature 5: Finding tasks
Users can find tasks according to a given keyword
> Syntax: **find** *[keyword]*

_Before Input:_ 

_Current Task List_ 

`1. [T][ ] CS2113 Quiz`

`2. [T][ ] CS2113 IP assignment`

`3. [T][ ] EE2026 Tutorial 2`

`4. [T][ ] EE2026 Lecture 2`

**Input:** 

`find CS2113`

**Output:** 

`1. [T][ ] CS2113 Quiz`

`2. [T][ ] CS2113 IP Assignment`

## Feature 6: Exiting Program
Exiting the program automatically saves given tasks.

The tasks saved will automatically be registered into user tasks upon reopening app.
> Syntax: **bye**

_Current Task List_

`1. [T][ ] CS2113 Quiz 7`

`2. [D][ ] EE2026 Graded Lab (by: Friday 6pm)`

`3. [E][ ] CG2023 Mid Terms (from: Thursday 2 pm to: Thursday 3 pm)`

**Input:**

`bye`

**Output:**

`Bye. Hope to see you again soon!`

_Reopen App_

**Output:**

`Hello! I'm Duke`

`What can I do for you?`

`File Found and successfully read`

`1. [T][ ] CS2113 Quiz 7`

`2. [D][ ] EE2026 Graded Lab (by: Friday 6pm)`

`3. [E][ ] CG2023 Mid Terms (from: Thursday 2 pm to: Thursday 3 pm)`

 
Note: Duke Task Bot saves the given tasks as a .txt file in a "data" file as "duke.txt" in the 
current directory the command line prompt is currently in.

If either the directory of the .txt file does not exist, one will be created. 






