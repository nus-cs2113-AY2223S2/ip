# User Guide

_sagyo_ is a CLI(Command-Line Interface) task manager which allows you to keep up with
the things you need to do, quickly and efficiently.

## Features 
* Adding different types of tasks depending on what you need
    * `TodoTask`: a basic task with just a description
    * `DeadlineTask`: a task which has to be completed by a deadline
    * `EventTask` a task which spans a duration
* Clean and minimalistic UI
* Find tasks matching a keyword
* Persistent storage of tasks

## Quick Start

> **Note**
> `java 11` is required to run `sagyo`

### Through github releases
1. Download the latest `sagyo.jar` file from [github releases](https://github.com/WilsonOh/ip/releases)
2. Go the the directory where you downloaded the `sagyo.jar` file into
3. Run `java -jar sagyo.jar`

### Using Gradle

> **Note** Make sure you have `gradle` and `openjdk ^11` installed

1. Clone the repo with `git clone https://github.com/WilsonOh/ip.git`
2. In the cloned repo, run `./gradlew jar`
3. Run `java -jar app/build/libs/sagyo.jar`

### Command Menu
* Add new tasks
    * [`todo`](#todo)
    * [`deadline`](#deadline)
    * [`event`](#event)
* [Deleting tasks](#delete)
* [Mark task as done](#mark)
* [Mark task as not done](#unmark)
* [List all tasks](#list)
* [Find tasks](#find)
* [Exit the program](#bye)

## Usage

### `[t]odo`
Adds a `TodoTask` into the task list
#### Syntax
`(todo|t) [task description]`
#### Example
`todo return book to library`<br/>
`t return book to library`

---

### `[d]ead[l]ine`
Adds a `DeadlineTask` into the task list
#### Syntax
`(deadline|dl) [task description] /by [task deadline]`
#### Example
`deadline return book to library /by friday`<br/>
`dl return book to library /by friday`

---

### `[e]vent`
Adds an `EventTask` into the task list
#### Syntax
`(event|e) [task description] /from [task start time] /to [task end time]`
#### Example
`event mid terms for EE1111 /from 12pm /to 2pm`<br/>
`e mid terms for EE1111 /from 12pm /to 2pm`

---

### `[d]elete`
Deletes a task by index
#### Syntax
`(delete|d) [task index]`
#### Example
`delete 2`<br/>
`d 2`

---

### `mark`
Marks a task as done
#### Syntax
`mark [task index]`
#### Example
`mark 2`<br/>

---

### `unmark`
Marks a task as done
#### Syntax
`mark [task index]`
#### Example
`mark 2`<br/>

---

### `list`
Lists all the tasks
#### Syntax
`list`

---

### `[f]ind`
Find all tasks matching a keyword
#### Syntax
`(find|f) [keyword]`
#### Example
`find books` <br/>
`f return`

---

### `bye`
Exits the program
> You can also just press `CTRL-C` instead, but this gives you a nice goodbye message :)
