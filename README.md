# Duke project template

This is a project template for a greenfield Java project. It's named after the Java mascot _Duke_. Given below are instructions on how to use it.

## Setting up in Intellij

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Open the project into Intellij as follows:
   1. Click `Open`.
   1. Select the project directory, and click `OK`.
   1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
3. After that, locate the `src/main/java/Duke.java` file, right-click it, and choose `Run Duke.main()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the below as the output:
   ```
Hello from
 ____        _        
|  _ \ _   _| | _____ 
| | | | | | | |/ / _ \
| |_| | |_| |   <  __/
|____/ \__,_|_|\_\___|

Hello! I'm Duke, your personal assistant.

=============================================================================================================== 

Here are some useful commands to get you started!

=============================================================================================================== 

'See list': Take a look at your To-Do list to get your day started!
'mark <task number>': Marks task as done. Try entering 'mark 1' to mark your first task as done!
'unmark <task number>': Unmarks task as not done. Try entering 'unmark 1' to mark your first task as not done!
'Help': If you forgot how to use me, don't be afraid to ask!
To add a task, enter a description of said task and I will add it into the list for you :) 

=============================================================================================================== 

What is your name? (Please enter name)

>> Sans
Hello, Sans. You may enter 'See list' to view your current To-Do list.
>> See list
Understood. Retrieving To-Do list...
Hm... It looks like you have not added any tasks.
Would you like to add more tasks? Enter 'Done' if you are satisfied with this list.
>> Finish Coursemology
Understood. Added task: Finish Coursemology. Anything else?
>> Feed the fish
Understood. Added task: Feed the fish. Anything else?
>> Implement Duke Level 4
Understood. Added task: Implement Duke Level 4. Anything else?
>> See list
Understood. Retrieving To-Do list...
1: □ Finish Coursemology 
2: □ Feed the fish 
3: □ Implement Duke Level 4 
Would you like to add more tasks? Enter 'Done' if you are satisfied with this list.
>> mark 2
You have completed this task. Good job!!
2: √ Feed the fish 
>> mark 1
You have completed this task. Good job!!
1: √ Finish Coursemology 
>> unmark 1
I would strongly encourage you to start on this.
1: □ Finish Coursemology 
>> see list
Understood. Added task: see list. Anything else?
>> See list
Understood. Retrieving To-Do list...
1: □ Finish Coursemology 
2: √ Feed the fish 
3: □ Implement Duke Level 4 
4: □ see list 
Would you like to add more tasks? Enter 'Done' if you are satisfied with this list.
>> Done
Bye, Sans. Hope to see you again soon!
   ```
