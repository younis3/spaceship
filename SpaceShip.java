import java.awt.Image;
import oop.ex2.*;
import java.lang.Math;

/**
 * The API spaceships need to implement for the SpaceWars game. 
 * It is your decision whether SpaceShip.java will be an interface, an abstract class,
 *  a base class for the other spaceships or any other option you will choose.
 */
public abstract class SpaceShip{

    /** CONSTANTS */
    //health
    protected final int MAXHEALTHLEVEL = 22; // Health begins at 22
    protected final int MINHEALTHLEVEL = 0; // Health ends at 0
    protected final int SHOTREDUCEHEALTH = 1; // Being shot at reduces health by 1
    protected final int COLLREDUCEHEALTH = 1; // Collisions between two spaceships reduces both ships' health by 1
    //energy
    protected final int STARTINGENERGYLEVEL = 210; // Energy level starts at 210
    protected final int CURENERGYLEVEL = 190; // Current energy level begins at 190
    protected final int BASHENERGYBENEFIT = 18; // "bashes" another, its maximal energy level goes up by 18
    protected final int HITREDUCEDMAXENERGY = 10; // reduces the maximal energy level by 10
    protected final int ENERGYCHARGEPERROUND = 1; // it goes up by 1 every round
    protected final int FIREENERGYCOST = 19;  // Firing a shot costs 19 energy units
    protected final int TELEPORTCOST = 140;  // Teleporting costs 140
    protected final int SHIELDCOST = 3;  // shield consumes 3 energy units per round

    protected final int MAXFIREROUNDS = 7;  // number of rounds ship can't fire



    protected int maximalEnergy;  // maximal energy of the spaceship (this value will change during the game)
    protected int currentEnergy;  // current energy of the spaceship (this value will change during the game)
    protected int health;  // current health of the spaceship (this value will change during the game)
    protected SpaceShipPhysics shipPhysicsObject;  // physics object of the ship (position, velocity etc..)
    protected boolean shield;  // in order to check if shield is on or not
    protected int fireRounds;  // to count number of rounds ship can't fire

    /**
     * Constructor of the spaceship object
     */
    public SpaceShip(){
        this.newSpaceship();
    }

    /**
     * Creates new spaceship
     */
    public void newSpaceship(){
        shipPhysicsObject = new SpaceShipPhysics();
        maximalEnergy = STARTINGENERGYLEVEL;
        currentEnergy = CURENERGYLEVEL;
        health = MAXHEALTHLEVEL;
        shield = false;
        fireRounds = MAXFIREROUNDS;
    }

    /**
     * Does the actions of this ship for this round. 
     * This is called once per round by the SpaceWars game driver.
     * 
     * @param game the game object to which this ship belongs.
     */
    public abstract void doAction(SpaceWars game);

    /**
     * This method is called every time a collision with this ship occurs 
     */
    public void collidedWithAnotherShip(){
        if (shield){
            maximalEnergy += BASHENERGYBENEFIT;
            currentEnergy += BASHENERGYBENEFIT;
        }
        else {
            maximalEnergy -= HITREDUCEDMAXENERGY;
            //"If, however, the current energy level is above the new maximum, it changes to the new maximum."
            currentEnergy = Math.min(maximalEnergy, currentEnergy);
            health -= COLLREDUCEHEALTH;
        }

        if (isDead()){
            reset();
        }
    }

    /** 
     * This method is called whenever a ship has died. It resets the ship's 
     * attributes, and starts it at a new random position.
     */
    public void reset(){
        this.newSpaceship();
    }

    /**
     * Checks if this ship is dead.
     * 
     * @return true if the ship is dead. false otherwise.
     */
    public boolean isDead() {
        if (health == MINHEALTHLEVEL) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Gets the physics object that controls this ship.
     * 
     * @return the physics object that controls the ship.
     */
    public SpaceShipPhysics getPhysics() {
        return shipPhysicsObject;
    }

    /**
     * This method is called by the SpaceWars game object when ever this ship
     * gets hit by a shot.
     */
    public void gotHit() {

        if (!shield){
            maximalEnergy -= HITREDUCEDMAXENERGY;
            currentEnergy = Math.min(maximalEnergy, currentEnergy);
            health -= SHOTREDUCEHEALTH;
        }

        if (isDead()){
            reset();
        }
    }

    /**
     * Gets the image of this ship. This method should return the image of the
     * ship with or without the shield. This will be displayed on the GUI at
     * the end of the round.
     * 
     * @return the image of this ship.
     */
    public abstract Image getImage();

    /**
     * Attempts to fire a shot.
     * 
     * @param game the game object.
     */
    public void fire(SpaceWars game) {
        if (currentEnergy >= FIREENERGYCOST && fireRounds >= MAXFIREROUNDS) {
            game.addShot(this.getPhysics());
            fireRounds = 0;
            currentEnergy -= FIREENERGYCOST;
        }
       
    }

    /**
     * Attempts to turn on the shield.
     */
    public void shieldOn() {
        if (currentEnergy >= SHIELDCOST){
            shield = true;
            currentEnergy -= SHIELDCOST;
        }
        
    }

    /**
     * Attempts to teleport.
     */
    public void teleport() {
        if (currentEnergy >= TELEPORTCOST){
            shipPhysicsObject = new SpaceShipPhysics();
            currentEnergy -= TELEPORTCOST;
        }
    }
    
}
