package bricker.brick_strategies;

import bricker.main.BrickerGameManager;
import danogl.GameObject;
import danogl.collisions.Layer;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;
import bricker.gameobjects.ExtraLife;

import static bricker.main.Constants.*;

/**
 * The ExtraLifeStrategy class represents a strategy for handling collision events that grant extra lives to
 * the player.
 * This strategy extends the BasicCollisionStrategy class and inherits its functionality to handle the basic
 * collision behavior.
 * Additionally, it creates an ExtraLife game object upon collision, adding it to the game layer.
 */
public class ExtraLifeStrategy extends BasicCollisionStrategy {
    private final BrickerGameManager brickerGameManager;

    /**
     * Constructs an ExtraLifeStrategy object with the provided BrickerGameManager instance.
     *
     * @param brickerGameManager The BrickerGameManager instance to be used for managing the game.
     */
    public ExtraLifeStrategy(BrickerGameManager brickerGameManager) {
        super(brickerGameManager);
        this.brickerGameManager = brickerGameManager;
    }

    /**
     * Handles the collision event between a brick and another game object.
     * This method overrides the onCollision method of the parent class to add extra life functionality.
     *
     * @param thisObj  The GameObject representing the brick on which the collision occurred.
     * @param otherObj The GameObject representing the other object involved in the collision.
     */
    @Override
    public void onCollision(GameObject thisObj, GameObject otherObj) {
        // Call the parent method to handle basic collision behavior
        super.onCollision(thisObj, otherObj);

        // Create an ExtraLife game object
        Renderable heartImage = this.brickerGameManager.getImageReader().readImage(HEART_PNG_IMAGE_PATH,
                true);
        Vector2 extraLifeLocation = thisObj.getCenter();
        Vector2 extraLifeDimensions = new Vector2(HEART_SIZE, HEART_SIZE);
        ExtraLife extraLife = new ExtraLife(extraLifeLocation, extraLifeDimensions, heartImage,
                this.brickerGameManager);
        extraLife.setVelocity(Vector2.DOWN.mult(EXTRA_HEART_INITIAL_VELOCITY));

        // Add the ExtraLife object to the game layer
        this.brickerGameManager.addObjectToLayer(extraLife, Layer.DEFAULT);
    }
}
