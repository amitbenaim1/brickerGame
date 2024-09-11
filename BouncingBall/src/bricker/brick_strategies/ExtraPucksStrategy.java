package bricker.brick_strategies;

import bricker.main.BrickerGameManager;
import bricker.main.Constants;
import danogl.GameObject;
import danogl.collisions.Layer;
import danogl.gui.Sound;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;
import bricker.gameobjects.Puck;

import java.sql.SQLOutput;
import java.util.Random;

import static bricker.main.Constants.BALL_SOUND_PATH;
import static bricker.main.Constants.MOCK_BALL_IMAGE_PATH;

/**
 * The ExtraPucksStrategy class represents a strategy for handling collision events that generate extra pucks
 * in the game.
 * This strategy extends the BasicCollisionStrategy class and inherits its functionality to handle the basic
 * collision behavior.
 * Additionally, it creates and adds two extra Puck game objects to the game layer upon collision.
 */
public class ExtraPucksStrategy extends BasicCollisionStrategy {
    private BrickerGameManager myBrickerGameManager;

    /**
     * Constructs an ExtraPucksStrategy object with the provided BrickerGameManager instance.
     *
     * @param brickerGameManager The BrickerGameManager instance to be used for managing the game.
     */
    public ExtraPucksStrategy(BrickerGameManager brickerGameManager) {
        super(brickerGameManager);
        this.myBrickerGameManager = brickerGameManager;
    }

    /**
     * Sets the speed of the given puck object randomly.
     *
     * @param puck The Puck object whose speed is to be set.
     */
    private void setSpeed(Puck puck) {
        float puckVelX = Constants.BALL_SPEED;
        float puckVelY = Constants.BALL_SPEED;
        Random rand = new Random();
        if (rand.nextBoolean()) {
            puckVelX *= -1;
        }
        if (rand.nextBoolean()) {
            puckVelY *= -1;
        }
        puck.setVelocity(new Vector2(puckVelX, puckVelY));
    }

    /**
     * Handles the collision event between a brick and another game object.
     * This method overrides the onCollision method of the parent class to add extra puck functionality.
     *
     * @param thisObj  The GameObject representing the brick on which the collision occurred.
     * @param otherObj The GameObject representing the other object involved in the collision.
     */
    @Override
    public void onCollision(GameObject thisObj, GameObject otherObj) {
        // Call the parent method to handle basic collision behavior
        super.onCollision(thisObj, otherObj);

        // Create renderable and sound objects for the puck
        Renderable puckImage = myBrickerGameManager.getImageReader().readImage(MOCK_BALL_IMAGE_PATH,
                true);
        Sound collisionSound = myBrickerGameManager.getSoundReader().readSound(BALL_SOUND_PATH);

        // Determine the location and dimensions for the extra pucks
        Vector2 puckLocation = thisObj.getCenter();
        Vector2 puckDimensions = new Vector2(Constants.PUCK_DIMENSIONS, Constants.PUCK_DIMENSIONS);

        // Create two extra Puck game objects and set their speeds
        Puck puck1 = new Puck(puckLocation, puckDimensions, puckImage, collisionSound, myBrickerGameManager);
        Puck puck2 = new Puck(puckLocation, puckDimensions, puckImage, collisionSound, myBrickerGameManager);
        setSpeed(puck1);
        setSpeed(puck2);

        // Add the extra pucks to the game layer
        myBrickerGameManager.addObjectToLayer(puck1, Layer.DEFAULT);
        myBrickerGameManager.addObjectToLayer(puck2, Layer.DEFAULT);
        System.out.println();
    }
}
