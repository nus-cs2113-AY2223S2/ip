@ECHO OFF

REM create bin directory if it doesn't exist
if not exist ..\bin mkdir ..\bin

REM delete output from previous run
if exist ACTUAL.TXT del ACTUAL.TXT

REM delete data folder & duke.txt file from previous run
if exist data del /Q data

REM compile the code into the bin folder
javac  -cp ..\src\main\java -Xlint:none -d ..\bin ..\src\main\java\*.java
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)
REM no error here, errorlevel == 0

REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ..\bin Duke < input.txt > ACTUAL.TXT

REM compare the output to the expected output
FC ACTUAL.TXT EXPECTED.TXT

REM compare the duke.txt to the expected_duke.txt
FC .\data\duke.txt EXPECTED_duke.TXT

REM ------ Rerun Duke to test if data loaded correctly ------

REM run the program, feed commands from input2.txt file and redirect the output to the ACTUAL2.TXT
java -classpath ..\bin Duke < input2.txt > ACTUAL2.TXT

REM compare the output to the expected output
FC ACTUAL2.TXT EXPECTED2.TXT
