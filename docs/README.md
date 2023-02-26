# User Guide

## Features 

### Feature-ABC

Description of the feature.

### Feature-XYZ

Description of the feature.

## Usage

### `Keyword` - Describe action

Describe the action and its outcome.

Example of usage: 

`keyword (optional arguments)`

Expected outcome:

Description of the outcome.

```
expected output
```
# Goot User Guide
Goot is a virtual list that allows users to record,delete and mark tasks such as 
deadlines, events and to-dos.

## 1. Adding tasks to Goot
### 1.1 Add To-do
### `todo <taskName>` 
- adds a to-do task to Goot
#### Example input
`todo have fun`

#### Example output
```
__________________________________
I've added this task:
  [T][ ] have fun
Now you have 1 tasks in the list.
__________________________________
__________________________________
Saved!
__________________________________
```
### 1.2 Add Deadline
### `deadline <taskName> /by <dueDate>`
- adds a deadline task to Goot
#### Example input
`deadline ps2 /by wednesday`

#### Example output
```
__________________________________
I've added this task:
  [D][ ] ps2 (by: wednesday)
Now you have 2 tasks in the list.
__________________________________
__________________________________
Saved!
__________________________________

```
### 1.3 Add Event
### `event <taskName> /from <startTime> /to <endTime>`
- adds a event task to Goot
#### Example input
`event halloween /from friday /to monday`

#### Example output
```
__________________________________
I've added this task:
  [E][ ] halloween (from: friday to: monday)
Now you have 3 tasks in the list.
__________________________________
__________________________________
Saved!
__________________________________

```
# 2. Display full list of tasks
### `list`
#### Example input
`list`
#### Example output
```
__________________________________
These are the tasks in your list:
1.[T][ ] have fun
2.[D][ ] ps2 (by: wednesday)
3.[E][ ] halloween (from: friday to: monday)
__________________________________
```

# 3. Deleting tasks from Goot
### `delete <taskNumber>`
#### Example input
`delete 2`
#### Example output
```
__________________________________
 Bye Bye task! It was nice meeting you :)
  [E][ ] halloween (from: friday to: monday)
 Now you have 2 tasks in the list.
__________________________________
__________________________________
Saved!
__________________________________
```
# 4. Marking tasks as done
### `mark <taskNumber>`
#### Example input
`mark 1`
#### Example output
```
__________________________________
Okie I've marked this task as done:
  [T][X] have fun
__________________________________
__________________________________
Saved!
__________________________________
```

# 5. Finding tasks by name
### `find <taskName>`
- returns the task(s) that contain the 'taskName'
#### Example input
`find ha`
#### Example output
```
__________________________________
I found these!
1.[T][X] have fun
2.[E][ ] halloween (from: friday to: monday)
__________________________________
```
*since both 'have fun' and 'halloween' contain 'ha', they both appear
  in the results*

# 6. Terminate Goot
### `bye`
#### Example input
`bye`
#### Example output
```
__________________________________
Bye bye! see you soon!
__________________________________
```