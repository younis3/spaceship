import java.awt.*;
import oop.ex2.GameGUI;

/**
 * This class responsible for creating a "Aggressive" type of the SpaceShip object.
 * This ship pursues other ships and tries to fire at them. It will always accelerate,
 * and turn towards the nearest ship. When its angle to the nearest ship is less than 0.21
 * radians, it will try to fire.
 */
public class Aggressive extends SpaceShip {

    private final double FIREANGLE = 0.21;

    /**
     * Constructs the Aggressive ship
     */
    public Aggressive(){
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


        // Try to fire (if possible)
        if (closestShipAngle < FIREANGLE){
            this.fire(game);
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
