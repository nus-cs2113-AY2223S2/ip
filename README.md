# Rica

Welcome to R.I.C.A, a chatbot that provides todo list management through a text-based interface! R.I.C.A. is short for Really-Intelligent-Chat-Assistant, and I hope she’ll be helpful for you.

## User Guide
Don't know how to use Rica? Find out on the [user guide](https://haoyangw.github.io/ip)!

## Getting Started

Download the release edition of Rica(v0.2) [here](https://github.com/haoyangw/ip/releases/download/A-Release/Rica-release.jar) and run it with `java -jar Rica-release.jar`! 

## Setting up in Intellij

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Open the project into Intellij as follows:
   1. Click `Open`.
   1. Select the project directory, and click `OK`.
   1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
3. After that, locate the `src/main/java/Rica.java` file, right-click it, and choose `Run Rica.main()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the below as the output:
   ```
    ____________________________________________________________
     Hello! I'm
     .______              __          ______             ___          
     |   _  \            |  |        /      |           /   \         
     |  |_)  |           |  |       |  ,----'          /  ^  \        
     |      /            |  |       |  |              /  /_\  \       
     |  |\  \----.  __   |  |  __   |  `----.  __    /  _____  \   __ 
     | _| `._____| (__)  |__| (__)   \______| (__)  /__/     \__\ (__)
     That's Really-Intelligent-Chat-Assistant for you!
     How may I be of assistance?
    ____________________________________________________________
   ```
