# spaceship
Ex2 of the Java OOP course

To run the game, first download the GUI files from the helper files folder then extract into main program directory
____________________


## File description

SpaceShipFactory.java - create all the spaceship objects according to the command line arguments
SpaceShip.java - super class for all spaceship types
Special.java - a ship with some interesting behavior
Drunkard.java - a ship with a drunken pilot.
Human.java - controlled ship (controlled by the user).
Runner.java - a ship that tries to avoid all other ships.
Aggressive.java - a ship that tries to pursue other ships and fire at them.
Basher.java - a ship that deliberately tries to collide with other ships.
____________________


## Design
Since every ship has its own constant behavior (except the drunkard), I decided to keep it clean and simple and use ABSTRACT CLASS Design.
It's clean and easy to understand and of course relationships between classes in this design make sense as well
____________________


## Implementation details
Drunkard: The drunkard ship has some random actions to do. it changes behavior every 200 rounds.
I explained in the javadoc of the every random action what every action does

Special: Decieves other ships that it wants to attack them but it won't!
The ship accelerates and turn toward closest ship while attempting to turn shields on then turn away when it get closer to that ship! (Fake attack behavior)
