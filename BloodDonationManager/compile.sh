#!/bin/bash
echo "Compiling Blood Donation Camp Manager..."
if [ ! -d "bin" ]; then
    mkdir bin
fi
javac -d bin src/model/*.java src/service/*.java src/ui/*.java
if [ $? -eq 0 ]; then
    echo "Compilation successful!"
    echo "Run the application using: ./run.sh"
else
    echo "Compilation failed. Please check for errors."
fi

