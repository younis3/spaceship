import java.awt.*;
import oop.ex2.GameGUI;

/**
 * This class responsible for creating a "special" type of the SpaceShip object.
 * This special behavior is like this:
 * The ship accelerates and turn toward closest ship while attempting to turn shields on then turn away
 * when it get closer to that ship!! (Fake attack behavior (Fake Basher!))
 */
public class Special extends SpaceShip {

    private final double MAXDISTANCE = 0.48;  // max distance to turn shields on.
    private final double MINDISTANCE = 0.28;  // when it gets within this distance ship will turn away and
                                              // put off the shields.
    private final double ANGLE = 0.3;  // turn shields on if less than this angle

    /**
     * Constructor of the Special SpaceShip type
     */
    public Special(){
        super();
    }

    /**
     * Does the actions of this ship for this round.
     * Ship accelerate and turn toward closest ship while shields on then turn away!! (FAKE Basher!).
     * This is called once per round by the SpaceWars game driver.
     *
     * @param game the game object to which this ship belongs.
     */
    @Override
    public void doAction(SpaceWars game) {
        fireRounds++;
        shield = false;

        // Accelerate and turn toward closest ship if > MINDISTANCE. otherwise TURN AWAY !! (FAKE ATTACK MOVE)
        double closestShipDistance = game.getClosestShipTo(this).getPhysics().distanceFrom(this.getPhysics());
        double closestShipAngle = this.getPhysics().angleTo(game.getClosestShipTo(this).getPhysics());
        int turn = this.moveTurn(closestShipDistance, closestShipAngle);
        this.getPhysics().move(true, turn);

        // try to turn on shields if the distance is between MAXDISTANCE and MINDISTANCE
        if (closestShipDistance < MAXDISTANCE && closestShipDistance > MINDISTANCE
                && closestShipAngle < ANGLE){
            this.shieldOn();
        }

        // Regenerate energy
        if (currentEnergy < maximalEnergy) {
            currentEnergy += ENERGYCHARGEPERROUND;
        }

    }


    /**
     * Ship turns towards the closest ship if the distance is bigger than MINDISTANCE. Otherwise it will
     * turn away from it.
     *
     * @param closestShipDistance the distance to the closest ship
     * @param closestShipAngle the angle to the closest ship
     *
     * @return which turn the ship should take (it can be only 1,0 or -1)
     */
    private int moveTurn(double closestShipDistance, double closestShipAngle){
        int turn;
        if (closestShipDistance > MINDISTANCE) {
            //turn towards
            if (closestShipAngle > 0) {
                turn = 1;
            } else if (closestShipAngle < 0) {
                turn = -1;
            } else {
                turn = 0;
            }
        }
        else{
            //turn away
            if (closestShipAngle > 0) {
                turn = -1;
            } else if (closestShipAngle < 0) {
                turn = 1;
            } else {
                turn = 0;
            }
        }
        return turn;
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
