# MKS21X-FinalProject

## Description

We decided to create a roguelike game in the terminal. However, unlike traditional terminal roguelikes, Togue displays each room of the dungeon by itself. Move through the doors and stairs to progress through the endless, spontaneously generating dungeon. Level up your character while fighting enemies with the state of the art "Rock, Paper, Scissors" combat system.

## Intructions

1. Type "resize -s 25 80" in the terminal
2. Type "javac -cp lanterna.jar:. Game.java" to compile the game
3. Type "java -cp lanterna.jar:. Game" to run the game
4. Press space bar to start the game or escape to exit the game
5. Use the arrow keys to move your character around.
- Walking on top of an enemy, labeled with an alphanumeric character, will trigger combat.
- Walking through a door on the sides of the room will transport you to a different room.
- Walking on top of a + sign will transport you to a different floor of the dungeon.
6. Follow the instructions on the screen during combat
7. At any time, press escape to exit the game
8. Try to survive as long as you can!
9. Once you die, press escape to exit the game
10. Be sure to have fun!

## Dev Log

01-03-19

YL: Initial commit

01-04-19

Worked on the backend of the project (making class structures)

YL: Created Togue, Entity, Player, Box classes<br>
MZ: Created Alive interface, Monster class

01-05-19

Took a break day

01-06-19

YL: Worked more on the backend, adding functions to Item, Drop, Monster classes<br>
MZ: Made a Test Branch to start playing around/experimenting with Lanterna

01-07-19

YL: Fixed bugs with the Monster and Togue classes, renamed files to capitalize them<br>
MZ: Worked more with Lanterna, trying to learn how to use it thoroughly

01-08-19

YL: Started working on the placement of entities on the map and generating items dropped by monsters<br>
MZ: Started working on generating the rooms, walls, and the player using Lanterna

01-09-19

YL: Fixed more bugs with the backend of the project<br>
MZ: Added basic player movement and room progression

01-10-19

YL: Added player interactions to items, chests, and weapons.<br>
MZ: Finalized player movement and room progression to make it work smoothly. Merged Test Branch with Master Branch.

01-11-19

YL: Created different types of enemies that can generate, along with their stats and IDs.<br>
MZ: Added seed generation to rooms in order to allow the player to go backwards to a room they've previously been in.

01-12-19

YL: Added more types of enemies; Updated game's user interface to display the player's stats.<br>
MZ: Added enemy generation and the ability to generate different types of enemies.

01-13-19

YL: Fixed bugs with variable declarations in the Player, Drop, and Togue classes.<br>
MZ: Fixed bugs relating to seet integration, loot generation and made the User Interface more user friendly.

01-14-19

YL: Added stair interactions and the ability to progress through different floors<br>
MZ: Added combat screen and the ability to get into combat

01-15-19

YL: Fixed more bugs on staircases and finished making working staircases.<br>
MZ: Finished basic combat and fixed bugs regarding combat

01-16-19

YL: Added visual effects to the combat screen<br>
MZ: Fixed file issues and deleted class files

01-17-19

YL: Took a break day<br>
MZ: Updated Player and Monster classes as well as combat status messages

01-18-19

YL: Added stat bonuses for when your character levels up<br>
MZ: Updated player to have experience points, renamed files to fit updated UML diagram

01-19-19

Took a break day to work on other projects

01-20-19

YL: Added critical hit effects, armor damage mitigation, and dodging<br>
MZ: Added sh scripts to help run project (scrapped); Updated imports in Player class

01-21-19
YL: Updated monster generation and loot dropping<br>
MZ: Polished combat to be smoother and more elegant; Created a starting screen menu
