package bricker.gameobjects;

import bricker.brick_strategies.CollisionStrategy;
import danogl.GameObject;
import danogl.collisions.Collision;
import danogl.gui.rendering.Renderable;
import danogl.util.Counter;
import danogl.util.Vector2;
/**
 * Represents a brick GameObject in the Bricker game.
 * The Brick can collide with other game objects and has a specific collision strategy.
 */
public class Brick extends GameObject {

    private final CollisionStrategy strategy;
    /**
     * Constructs a new Brick instance.
     *
     * @param topLeftCorner Position of the brick, in window coordinates (pixels).
     *                      Note that (0,0) is the top-left corner of the window.
     * @param dimensions    Width and height of the brick in window coordinates.
     * @param renderable    The renderable representing the brick.
     * @param strategy      The collision strategy applied when the brick collides with other objects.
     * @param brickCounter  Counter to track the number of bricks in the game.
     */
    public Brick(Vector2 topLeftCorner, Vector2 dimensions, Renderable renderable, CollisionStrategy
            strategy, Counter brickCounter) {
        super(topLeftCorner, dimensions, renderable);
        this.strategy = strategy;
    }
    /**
     * Handles the collision event with other GameObjects.
     * Delegates the collision handling to the specific CollisionStrategy associated with the brick.
     *
     * @param other     The GameObject with which the brick collided.
     * @param collision The details of the collision.
     * @see Collision
     * @see CollisionStrategy
     */
    public void onCollisionEnter(GameObject other, Collision collision) {
        super.onCollisionEnter(other, collision);
        this.strategy.onCollision(this, other);
    }
}
