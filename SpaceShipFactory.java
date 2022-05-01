import oop.ex2.*;

public class SpaceShipFactory {

    /**
     * Runner: a ship that tries to avoid all other ships.
     */
    private static final String RUNNER = "r";

    /**
     * Basher: a ship that deliberately tries to collide with other ships.
     */
    private static final String BASHER = "b";

    /**
     * Aggressive: a ship that tries to pursue other ships and fire at them.
     */
    private static final String AGGRESSIVE = "a";

    /**
     * Human: controlled ship (controlled by the user).
     */
    private static final String HUMAN = "h";

    /**
     * Drunkard: a ship with a drunken pilot.
     * (The drunkard pilot has some random actions to do. explained in the javadoc of the drunkard class)
     */
    private static final String DRUNKARD = "d";

    /**
     * Special: a ship with a special behavior
     * (behavior is fully explained in the javadoc of the special class as well as in the README)
     */
    private static final String SPECIAL = "s";


    /**
     * creates all the spaceship objects according to the command line arguments
     *
     * @return an array includes all the spaceships that were created
     */
    public static SpaceShip[] createSpaceShips(String[] args) {
        SpaceShip[] shipsArray = new SpaceShip[args.length];
        for (int arg = 0; arg<args.length; arg++){
            switch (args[arg]){
                case RUNNER:
                    shipsArray[arg] = new Runner();
                    break;
                case BASHER:
                    shipsArray[arg] = new Basher();
                    break;
                case AGGRESSIVE:
                    shipsArray[arg] = new Aggressive();
                    break;
                case HUMAN:
                    shipsArray[arg] = new Human();
                    break;
                case DRUNKARD:
                    shipsArray[arg] = new Drunkard();
                    break;
                case SPECIAL:
                    shipsArray[arg] = new Special();
                    break;
            }
        }
        return shipsArray;
    }

}
