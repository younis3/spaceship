import oop.ex2.GameGUI;
import java.util.Random;
import java.awt.*;

/**
 * This class responsible for creating a "Drunkard" type of the SpaceShip object. a ship where it pilot had
 * too much drink.
 * The drunkard ship has some random actions to do. explained in the javadoc of the every random action
 * method).
 */
public class Drunkard extends SpaceShip {

    private final int CHANGEACTIONROUNDS = 200;  // Changes random behavior every 200 rounds
    private Random randomAction = new Random();
    private int roundsCounter;  // in order to change the random behavior every CHANGEACTIONROUNDS rounds
    int action;  // variable to store the random generated number

    /**
     * Constructs the Drunkard ship
     */
    public Drunkard(){
        super();
        roundsCounter = 0;
        action = randomAction.nextInt(7) + 1;
    }

    /**
     * Does the actions of this ship for this round.
     * This is called once per round by the SpaceWars game driver.
     *
     * @param game the game object to which this ship belongs.
     */
    @Override
    public void doAction(SpaceWars game) {
        roundsCounter++;
        fireRounds++;
        shield = false;

        if (roundsCounter > CHANGEACTIONROUNDS) {  // Changes random behavior every 200 rounds
            action = randomAction.nextInt(7) + 1;
            roundsCounter = 0;
        }

        switch (action){
            case 1: this.randomAction1(game);
                break;
            case 2: this.randomAction2(game);
                break;
            case 3: this.randomAction3();
                break;
            case 4: this.randomAction4();
                break;
            case 5: this.randomAction5(game);
                break;
            case 6: this.randomAction6();
                break;
            case 7: this.randomAction7(game);
                break;
        }

        // Regenerate energy
        if (currentEnergy < maximalEnergy) {
            currentEnergy += ENERGYCHARGEPERROUND;
        }

    }

    /**
     * this action makes the ship keep firing while constantly turning to the left and
     * slowing down (acceleration = false)
     *
     * @param game the game object to which this ship belongs.
     */
    private void randomAction1(SpaceWars game) {
        // keep turning to the right while slowing down
        this.getPhysics().move(false, 1);

        //fire
        this.fire(game);
    }

    /**
     * this action makes the ship keep firing while constantly turning to the right
     * and slowing down (acceleration = false)
     *
     * @param game the game object to which this ship belongs.
     */
    private void randomAction2(SpaceWars game){
        // keep turning to the right while slowing down
        this.getPhysics().move(false, -1);

        //fire
        this.fire(game);

    }


    /**
     * Drunkard pilot doesn't feel safe!!
     * this action makes the ship keep attempting to turn the shields on while constantly turning
     * to the left and accelerating
     */
    private void randomAction3(){
        // keep turning to the left while accelerating
        this.getPhysics().move(true, 1);

        //turn shields on
        this.shieldOn();
    }


    /**
     * Drunkard likes to disappear for no reason ;)
     * this action makes the ship keep teleporting whenever possible
     */
    private void randomAction4(){
        //keep teleporting whenever possible
        this.teleport();
        this.getPhysics().move(true, 0);
    }


    /**
     * Drunkard becomes a MONSTER without mercy!
     * this action makes the ship keeps turning toward the closest ship and fire while shields on!
     *
     * @param game the game object to which this ship belongs.
     */
    private void randomAction5(SpaceWars game){
        // Accelerate and turn toward closest ship
        double closestShipAngle = this.getPhysics().angleTo(game.getClosestShipTo(this).getPhysics());

        if (closestShipAngle > 0) {
            this.getPhysics().move(true, 1);
        }
        else if (closestShipAngle < 0){
            this.getPhysics().move(true, -1);
        }
        else{
            this.getPhysics().move(true, 0);
        }

        // attempt to turn shields on
        this.shieldOn();

        // try to fire
        this.fire(game);
    }

    /**
     * Drunkard gone PEACEFUL!!
     * this action makes the ship just accelerate and move
     */
    private void randomAction6(){
        // just move and accelerate (without turning)
        this.getPhysics().move(true, 0);
    }


    /**
     * this action makes the ship keeps avoiding collision while firing non-stop (whenever energy levels
     * allow)
     *
     * @param game the game object to which this ship belongs.
     */
    private void randomAction7(SpaceWars game){

        // Accelerate and turn away
        double closestShipAngle = game.getClosestShipTo(this).getPhysics().angleTo(this.getPhysics());
        if (closestShipAngle > 0) {
            this.getPhysics().move(true, -1);
        }
        else if (closestShipAngle < 0){
            this.getPhysics().move(true, 1);
        }
        else{
            this.getPhysics().move(true, 0);
        }

        // try to fire
        this.fire(game);
    }



    /**
     * Gets the image of this ship. This method should return the image of the
     * ship with or without the shield. This will be displayed on the GUI at
     * the end of the round.
     *
     * @return the image of this ship.
     */
    @Override
    public Image getImage() {
        if (shield){
            return GameGUI.ENEMY_SPACESHIP_IMAGE_SHIELD;
        }
        else {
            return GameGUI.ENEMY_SPACESHIP_IMAGE;
        }
    }

}
