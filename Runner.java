import oop.ex2.GameGUI;
import oop.ex2.SpaceShipPhysics;
import java.lang.Math;

import java.awt.*;

/**
 * This class responsible for creating a "Runner" type of the SpaceShip object.
 * This spaceship attempts to run away from the fight. It will always accelerate, and
 * will constantly turn away from the closest ship. If the nearest ship is closer than 0.25 units,
 * and if its angle to the Runner is less than 0.23 radians, the Runner feels threatened and will
 * attempt to teleport.
 */
public class Runner extends SpaceShip {

    private final double ANGLE = 0.23;
    private final double DISTANCE = 0.25;


    /**
     * Constructs the Runner ship
     */
    public Runner(){
        super();
    }

    /**
     * Does the actions of this ship for this round.
     * This is called once per round by the SpaceWars game driver.
     *
     * @param game the game object to which this ship belongs.
     */
    @Override
    public void doAction(SpaceWars game) {
        fireRounds++;
        shield = false;

        // Attempt to teleport (if needed)
        double closestShipAngle = game.getClosestShipTo(this).getPhysics().angleTo(this.getPhysics());
        double closestShipDistance = game.getClosestShipTo(this).getPhysics().distanceFrom(this.getPhysics());

        if(closestShipAngle < ANGLE && closestShipDistance < DISTANCE ){
            this.teleport();
        }

        // Accelerate and turn away
        if (closestShipAngle > 0) {
            this.getPhysics().move(true, -1);
        }
        else if (closestShipAngle < 0){
            this.getPhysics().move(true, 1);
        }
        else{
            this.getPhysics().move(true, 0);
        }


        // Regenerate energy
        if (currentEnergy < maximalEnergy) {
            currentEnergy += ENERGYCHARGEPERROUND;
        }
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
