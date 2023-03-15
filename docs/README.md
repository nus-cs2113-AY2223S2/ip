# User Guide

## Features 

### Feature-Adding tasks

Duke can store your tasks to complete.

### Feature-Different types of tasks

Duke allows three different types of tasks: todo, deadline and event.

### Feature-Mark a task as done/ undone

Duke can change the completion status of a task.

### Feature-Viewing tasks

All the tasks and the corresponding type and completion status can be seen.

### Feature-Viewing tasks

Tasks can be deleted.

### Feature-Storing information

Duke can store your tasks locally when the programme is exited and loads the stored data whenever the programmed is run.

## Usage

### command and details

To use Duke, you need to tell it the command keyword and the details of the command.

The format can be seen in the example below with the corresponding output.

#### **Adding a task**

Input the type of the task (todo, deadline, event) and follow the format given below:

```
  todo study

  deadline submit assignments /by fri

  event lecture /from 4pm fri /to 6pm fri
```  

**_Note: the "/" for deadline and event is very important for Duke to recognise the time/ duration!_**

#### **Listing all the tasks**

Simply enter list to see your tasks:

```
list
______________________________
1. [T][ ]study
2. [D][ ]submit assignments (by: fri)
3. [E][ ]lecture (from: 4pm fri to: 6pm fri)
______________________________
```

#### **Marking a task as done/ undone**

```
mark 1
```
```
______________________________
Nice! I've marked this task as done:
[T][X]study
______________________________
```
```
mark 2
```
```
______________________________
Nice! I've marked this task as done:
[D][X]submit assignments (by: fri)
______________________________
```

```
list
______________________________
1. [T][X]study
2. [D][X]submit assignments (by: fri)
3. [E][ ]lecture (from: 4pm fri to: 6pm fri)
______________________________
```

#### **Finding a task**

```
find study
______________________________
Here are the matching tasks in your list:
[T][X]study
______________________________
```
```
find submit
______________________________
Here are the matching tasks in your list:
[D][X]submit assignments (by: fri)
______________________________
```

#### **Deleting a task**

```
list
______________________________
1. [T][X]study
2. [D][X]submit assignments (by: fri)
3. [E][ ]lecture (from: 4pm fri to: 6pm fri)
______________________________
```
```
delete 2
______________________________
deleted:
[D][X]submit assignments (by: fri)
______________________________

Now you have 2 tasks in the list
```
```
list
______________________________
1. [T][X]study
2. [E][ ]lecture (from: 4pm fri to: 6pm fri)
______________________________
```


