#!/bin/bash

CLASS=$1
echo $CLASS
rm *class
javac ${CLASS}.java
java $CLASS
