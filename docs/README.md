# Mom - User Guide
Similar to your real-life mom, Mom is a naggy remainder app that helps people to keep track of their todos, deadlines and events that they have! It uses a command-line interface, which make inputs swift and convenient! (Honestly, can't you remember it yourself?)

## Quick Start
1. Ensure that you have Java 11 and above installed in your computer.
2. Download the ***ip.jar*** [here](https://github.com/pinyoko573/ip/releases)
3. Make sure that you do not have ***reminders.txt*** in the folder where *ip.jar* is located, as this file will be used to store the reminders!
4. Launch your command prompt/terminal and navigate to the folder where *ip.jar* is located.
5. Run the following command: `java -jar ip.jar`

Note: **DO NOT** modify ***reminders.txt*** unless you know what you are doing!

## Features
### Keeps a list of reminders

Mom enables you to keep track a list of reminders and display it whenever you need it. (Why can't you do it yourself??)

### Flexible reminder inputs

With different types of reminders, you can specify a reminder with dateline, or a reminder with start & end date. (Better than calendars, right??)

### Marks reminder that are completed/uncompleted

You can mark reminders once you have completed them. (Good job?)

### Find specific reminder

With many reminders, Mom allows to search and retrieve the reminder that you are finding. (Similar to asking your mom to find your lost stuff at home, no?)

### Persistent data

Reminders are stored into a text file (reminders.txt) after each action is performed, so you don't need to key them again. (Aren't moms amazing?)

## Commands

### `list` - List down all the reminders

Shows the list of reminders recorded.

Format: `list`

Expected output:
```
list
_________________
Here are the things I need to remind you again and again...
1. [T][ ] to eat
2. [D][ ] to swim (by: 20-10-2020 06:00 PM)
3. [E][ ] to sleep (from: 20-10-2020 12:00 PM to: 20-10-2020 03:00 PM)
_________________
```

### `mark` - Mark a reminder as done

Marks a specific reminder as done using the **number** shown from the list.

Format: `mark (no)`

Expected output:

Shows the reminder that has been marked as done.
```
mark 2
_________________
Finally done this task? You could have done it earlier.
[D][X] to swim (by: 20-10-2020 06:00 PM)
_________________
```

### `unmark` - Unmark a reminder as not done

Marks a specific reminder as not done using the **number** shown from the list.

Format: `unmark (no)`

Expected output:

Shows the reminder that has been marked as not done.
```
unmark 2
_________________
I thought you told me this task was done???
[D][ ] to swim (by: 20-10-2020 06:00 PM)
_________________
```

### `todo` - Create a Todo reminder

Adds a reminder of type Todo into the list.

Format: `todo (description)`

Expected output:

Displays the Todo added and the number of reminders after adding.
```
todo to cook
_________________
Ok!! But make sure you finish this task soon:
[T][ ] to cook
Now you have 4 items that I need to remember...
_________________
```

### `deadline` - Create a Deadline item

Adds a reminder of type Deadline with datemark(or dateline) specified into the list.
Datemark format must be strictly followed: "dd-MM-yyyy HH:mm". Example: "20-10-2020 18:00".

Format: `deadline (description) /by (datemark)`

Expected output:

Displays the Deadline added and the number of reminders after adding.
```
deadline to play /by 20-10-2020 18:00
_________________
Ok!! But make sure you finish this task soon:
[D][ ] to play (by: 20 Oct 2020 06:00 PM)
Now you have 5 items that I need to remember...
_________________
```

### `event` - Create an Event task

Adds a reminder of type Event with from & to date specified into the list.
From (start date) and To (end date) format must be strictly followed: "dd-MM-yyyy HH:mm". Example: "20-10-2020 18:00".
The start date must not be after the end date.

Format: `event (description) /from (start date) /to (end date)`

Expected output:

Displays the Event added and the number of reminders after adding.
```
event to exercise /from 20-10-2020 12:00 /to 20-10-2020 15:00
_________________
Ok!! But make sure you finish this task soon:
[E][ ] to sleep (from: 20-10-2020 12:00 PM to: 20-10-2020 03:00 PM)
Now you have 6 items that I need to remember...
_________________
```

### `delete` - Delete a reminder

Delete a reminder using the **number** shown from the reminders list.

Format: `delete (no)`

Expected output:

Shows the reminder that has been deleted and the number of reminders remaining.
```
delete 6
_________________
Finally, something that you don't need me to remind again.
[E][ ] to sleep (from: 20-10-2020 12:00 PM to: 20-10-2020 03:00 PM)
Now you have 5 items that I need to remember...
_________________
```

### `find` - Find specific items

Find all reminders that contain the specified keyword.

Format: `find (keyword)`

Expected output:

Returns the list of reminders that contain the keyword.
```
find to s
_________________
How should I know what you are looking for? Anyway...
1. [D][ ] to swim (by: 20-10-2020 06:00 PM)
2. [E][ ] to sleep (from: 20-10-2020 12:00 PM to: 20-10-2020 03:00 PM)
_________________
```

### `exit` - Exit Duke

Exits the Mom application.

Format: `exit`

Expected output:
```
exit
_________________
Leaving your mom? :(
```

## P.S...

Apologies if you are offended by my application. Please take it lightly 😭😭😭

Image: Cliparts License for non-commerical use