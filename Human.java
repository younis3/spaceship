import java.awt.*;
import oop.ex2.GameGUI;

/**
 * This class responsible for creating a "Human Controlled" type of the SpaceShip object.
 * Controlled by the user. The keys are: left-arrow and right-arrow to
 * turn, up-arrow to accelerate, 'd' to fire a shot, 's' to turn on the shield, 'a' to teleport.
 */
public class Human extends SpaceShip {


    /**
     * Constructs the Human Controlled ship
     */
    public Human(){
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

        /*TELEPORT*/
        if (game.getGUI().isTeleportPressed()) {
            this.teleport();
        }

        /* MOVE */
       this.moveShip(game);  //turn and accelerate according to user input

        /*SHIELDS ON*/
        if (game.getGUI().isShieldsPressed()){
            this.shieldOn();
        }

        /*FIRE*/
        else if(game.getGUI().isShotPressed()){
            this.fire(game);
        }

        // Regenerate energy
        if (currentEnergy < maximalEnergy) {
            currentEnergy += ENERGYCHARGEPERROUND;
        }

    }


    /**
     * Turn and accelerate according to user input
     *
     * @param game the game object to which this ship belongs.
     */
    private void moveShip(SpaceWars game){
        boolean accelerate = false;
        int turn = 0;

        //accelerate
        if (game.getGUI().isUpPressed()){
            accelerate = true;
        }
        //turn
        if (game.getGUI().isRightPressed() && game.getGUI().isLeftPressed()){
            turn = 0;
        }
        else if (game.getGUI().isLeftPressed()){
            turn = 1;
        }
        else if (game.getGUI().isRightPressed()){
            turn = -1;
        }

        this.getPhysics().move(accelerate, turn); //move spaceship
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
            return GameGUI.SPACESHIP_IMAGE_SHIELD;  // Human SpaceShip icon (with shield)
        }
        else {
            return GameGUI.SPACESHIP_IMAGE;   // Human SpaceShip icon (withOUT shield)
        }
    }
}
