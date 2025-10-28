@echo off
echo Compiling Blood Donation Camp Manager...
if not exist bin mkdir bin
javac -d bin src\model\*.java src\service\*.java src\ui\*.java
if %ERRORLEVEL% EQU 0 (
    echo Compilation successful!
    echo Run the application using: run.bat
) else (
    echo Compilation failed. Please check for errors.
)
pause

