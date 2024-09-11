package bricker.brick_strategies;

import bricker.main.BrickerGameManager;
import danogl.GameObject;
import danogl.collisions.Layer;

/**
 * The BasicCollisionStrategy class represents a basic collision strategy for bricks in a Bricker game.
 * This strategy is invoked when a collision occurs between a brick and another game object.
 * It removes the brick from the game layer and decrements the brick counter in the game manager.
 */
public class BasicCollisionStrategy implements CollisionStrategy {
    private final BrickerGameManager brickerGameManager;

    /**
     * Constructs a BasicCollisionStrategy object with the provided BrickerGameManager instance.
     *
     * @param brickerGameManager The BrickerGameManager instance to be used for managing the game.
     */
    public BasicCollisionStrategy(BrickerGameManager brickerGameManager) {
        this.brickerGameManager = brickerGameManager;
    }

    /**
     * Handles the collision event between a brick and another game object.
     * Removes the brick from the game layer and decrements the brick counter.
     *
     * @param thisObj  The GameObject representing the brick on which the collision occurred.
     * @param otherObj The GameObject representing the other object involved in the collision.
     */
    @Override
    public void onCollision(GameObject thisObj, GameObject otherObj) {
        // Remove the brick from the static objects layer using the game manager
        this.brickerGameManager.deleteObjectFromLayer(thisObj, Layer.STATIC_OBJECTS);

        // Decrement the brick counter in the game manager
        this.brickerGameManager.getBrickCounter().decrement();
    }
}
