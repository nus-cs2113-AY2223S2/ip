# User Guide For Duke Task Manager App

## Features 

### 1. Adding Tasks
#### There are 3 types of tasks that are available to be added to the task list 

#### 1. Todo 
##### Todo tasks are tasks that do not contain any deadlines
##### Syntax: **todo** *Name of Task*
#### Example of adding Todo Task
##### Input: todo CS2113 Quiz 7
##### Output: 
##### Got it. I've added this task:
##### [T][ ] CS2113 Quiz 7
##### Now you have 1 in the list.

#### 2. Deadline 
##### Deadline tasks are tasks contain a deadline
##### Syntax: **deadline** *Name of Task* **/by** *Deadline Of Task*
#### Example of adding Deadline Task
##### Input: deadline EE2026 Graded Lab /by Fri 2359
##### Output: 
##### Got it. I've added this task:
##### [D][ ] EE2026 Graded Lab (by: Fri 2359)
##### Now you have 1 in the list.

#### 3. Event 
##### Event tasks are tasks which contains a start time and an end time
##### Syntax: **event** *Name of Task* **/from** *start time* **/to** *end time*

##### Example of adding Event Task
###### Input: event EE2211 Mid Terms /from Sat 2pm /to Sat 4pm
###### Output: 
###### Got it. I've added this task:
###### [E][ ] EE2211 Mid Terms (from: Sat 2pm to: Sat 4pm)
###### Now you have 1 in the list.

### 2. Listing current tasks
#### List out all current tasks, displaying associated task information
##### Syntax: **list**

Example: list
Output: 
        1. [E][ ] EE2211 Mid Terms (from: Sat 2pm to: Sat 4pm)
        2. [T][ ] CS2113 Quiz 7
        3. [E][ ] EE2211 Mid Terms (from: Sat 2pm to: Sat 4pm)

### 3. Deleting tasks
#### Deletes task according to task index
##### Syntax: **delete** *index*

Example: 

Before Input: 
1. [E][ ] EE2211 Mid Terms (from: Sat 2pm to: Sat 4pm)
2. [T][ ] CS2113 Quiz 7
3. [E][ ] EE2211 Mid Terms (from: Sat 2pm to: Sat 4pm)

Input: delete 2

Output:
Noted. I've removed this task:
[T][ ] CS2113 Quiz 7
Now you have 2 in the list.

list 
1. [E][ ] EE2211 Mid Terms (from: Sat 2pm to: Sat 4pm)
2. [E][ ] EE2211 Mid Terms (from: Sat 2pm to: Sat 4pm)



### 4. Marking and Unmarking tasks as completed
#### Users can mark task as done or undone according to taskindex
##### Syntax: **mark** *index* OR **unmark** *index*

1. [E][ ] EE2211 Mid Terms (from: Sat 2pm to: Sat 4pm)
2. [E][ ] EE2211 Mid Terms (from: Sat 2pm to: Sat 4pm)

mark 2 

1. [E][ ] EE2211 Mid Terms (from: Sat 2pm to: Sat 4pm)
2. [E][X] EE2211 Mid Terms (from: Sat 2pm to: Sat 4pm)

### 5. Finding tasks
#### Users can find tasks according to a given keyword
##### Syntax: **find** *keyword*

Before Input: 
**list**
1. [T][ ] CS2113 Quiz
2. [T][ ] CS2113 IP assignment
3. [T][ ] EE2026 Tutorial 2
4. [T][ ] EE2026 Lecture 2

Input: find CS2113

Output: 
1. [T][ ] CS2113 Quiz
2. [T][ ] CS2113 IP Assignment

### 6. Exiting Program
#### Exiting the program automatically saves given tasks 
##### Syntax: **bye**





