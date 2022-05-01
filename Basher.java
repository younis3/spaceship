import java.awt.*;
import oop.ex2.GameGUI;

/**
 * This class responsible for creating a "Basher" type of the SpaceShip object.
 * This ship attempts to collide with other ships. It will always accelerate, and will
 * constantly turn towards the closest ship. If it gets within a distance of 0.19 units (inclusive)
 * from another ship, it will attempt to turn on its shields.
 */
public class Basher extends SpaceShip {

    private final double DISTANCE = 0.19;


    /**
     * Constructs the Basher ship
     */
    public Basher(){
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


        // Attempt to turn on shields (if needed)
        double closestShipDistance = game.getClosestShipTo(this).getPhysics().distanceFrom(this.getPhysics());

        if (closestShipDistance <= DISTANCE){
            this.shieldOn();
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
