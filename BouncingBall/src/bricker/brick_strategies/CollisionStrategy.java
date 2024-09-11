package bricker.brick_strategies;

import danogl.GameObject;

/**
 * The CollisionStrategy interface defines a contract for handling collision events between game objects.
 * Classes implementing this interface must provide an implementation for the onCollision method.
 */
public interface CollisionStrategy {

    /**
     * Handles the collision event between two game objects.
     *
     * @param thisObj  The GameObject representing the object on which the collision occurred.
     * @param otherObj The GameObject representing the other object involved in the collision.
     */
    void onCollision(GameObject thisObj, GameObject otherObj);
}
