# Duke - User Guide
Similar to post-it notes, Duke is a friendly remainder app that helps people to keep track of their todos, deadlines and events that they have! It uses a command-line interface, which make inputs swift and convenient! 

## Quick Start
1. Ensure that you have Java 11 and above installed in your computer.
2. Download the ***duke.jar*** [here](https://github.com/pinyoko573/ip/releases)
3. Make sure that you do not have ***items.txt*** in the folder where *duke.jar* is located, as this file will be used to store your items!
4. Launch your command prompt/terminal and navigate to the folder where *duke.jar* is located.
5. Run the following command: `java -jar duke.jar`

## Features
### Keeps a list of items

Duke enables you to keep track a list of items and display it whenever you need it.

### Flexible task inputs

With different types of items, you can specify an item with dateline, or an item with start & end date.

### Marks item that are completed/uncompleted

You can mark items once you have completed them.

### Find specific item

With many items on hand, Duke allows to search and retrieve the item that you are finding.

### Persistent data

Items are stored into a text file (items.txt) after each action is performed, so you don't need to key them again.

## Commands

### `list` - List down all the items

Shows the list of items recorded.

Format: `list`

Expected output:
```
list
_________________
Here are the items in your list:
1. [T][ ] to eat
2. [D][ ] to swim (by: today)
3. [E][ ] to sleep (from: today to: tomorrow)
_________________
```

### `mark` - Mark an item as done

Marks a specific item as done using the **number** shown from the list.

Format: `mark (no)`

Expected output:

Shows the item that has been marked as done.
```
mark 2
_________________
Good job! I've marked this item as done:
[D][X] to swim (by: today)
_________________
```

### `unmark` - Unmark an item as not done

Marks a specific item as not done using the **number** shown from the list.

Format: `unmark (no)`

Expected output:

Shows the item that has been marked as not done.
```
unmark 2
_________________
OK, I've marked this item as not done yet:
[D][ ] to swim (by: today)
_________________
```

### `todo` - Create a Todo item

Adds an item of type Todo into the list.

Format: `todo (description)`

Expected output:

Displays the Todo item added and the number of items after adding.
```
todo to cook
_________________
Got it. I've added this item:
[T][ ] to cook
Now you have 4 items in the list.
_________________
```

### `deadline` - Create a Deadline item

Adds an item of type Deadline with datemark specified into the list.

Format: `deadline (description) /by (datemark)`

Expected output:

Displays the Deadline item added and the number of items after adding.
```
deadline to play /by everyday
_________________
Got it. I've added this item:
[D][ ] to play (by: everyday)
Now you have 5 items in the list.
_________________
```

### `event` - Create an Event task

Adds an item of type Event with from & to date specified into the list.

Format: `event (description) /from (start date) /to (end date)`

Expected output:

Displays the Event item added and the number of items after adding.
```
event to exercise /from 1pm /to 3pm
_________________
Got it. I've added this item:
[E][ ] to exercise (from: 1pm to: 3pm)
Now you have 6 items in the list.
_________________
```

### `delete` - Delete a task

Delete an item using the **number** shown from the items list.

Format: `delete (no)`

Expected output:

Shows the item that has been deleted and the number of items remaining.
```
delete 6
_________________
Got it. I've deleted this item:
[E][ ] to exercise (from: 1pm to: 3pm)
Now you have 5 items in the list.
_________________
```

### `find` - Find specific items

Find all items that contain the specified keyword.

Format: `find (keyword)`

Expected output:

Returns the list of items that contain the keyword.
```
find to s
_________________
Here are the matching items in your list:
1. [D][ ] to swim (by: today)
2. [E][ ] to sleep (from: today to: tomorrow)
_________________
```

### `exit` - Exit Duke

Exits the Duke application.

Format: `exit`

Expected output:
```
exit
_________________
Bye. Thanks for using me!
```