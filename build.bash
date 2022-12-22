#!/bin/bash
set -u -e
clear
javac Game.java View.java Controller.java Model.java Pipe.java Mario.java Json.java Sprite.java Goomba.java Fireball.java FireGoomba.java
java Game
