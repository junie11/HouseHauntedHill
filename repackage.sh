#!/bin/bash
javac -d bin src/Game.java && jar cfm HouseHauntedHill.jar MANIFEST.MF -C bin . && java -jar HouseHauntedHill.jar
