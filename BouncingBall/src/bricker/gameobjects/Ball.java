package bricker.gameobjects;

import danogl.GameObject;
import danogl.collisions.Collision;
import danogl.gui.Sound;
import danogl.gui.rendering.Renderable;
import danogl.util.Counter;
import danogl.util.Vector2;

import static bricker.main.Constants.INITIAL_COUNTER_VALUE;

/**
 * Represents a ball GameObject in the Bricker game.
 * The Ball can collide with other game objects and produces a collision sound.
 */
public class Ball extends GameObject {
    private final Sound collisionSound;
    private static Counter collisionCounter = new Counter(INITIAL_COUNTER_VALUE);

    /**
     * Construct a new GameObject instance.
     *
     * @param topLeftCorner Position of the object, in window coordinates (pixels).
     *                      Note that (0,0) is the top-left corner of the window.
     * @param dimensions    Width and height in window coordinates.
     * @param renderable    The renderable representing the object. Can be null, in which case
     *                      the GameObject will not be rendered.
     */
    public Ball(Vector2 topLeftCorner,
                Vector2 dimensions, Renderable renderable, Sound collisionSound) {
        super(topLeftCorner, dimensions, renderable);
        this.collisionSound = collisionSound;

    }

    /**
     * Handles the collision event with other GameObjects.
     * Updates the ball's velocity, plays the collision sound, and increments the collision counter.
     *
     * @param other     The GameObject with which the ball collided.
     * @param collision The details of the collision.
     * @see Collision
     */
    @Override
    public void onCollisionEnter(GameObject other, Collision collision) {
        super.onCollisionEnter(other, collision);
        Vector2 newVel = getVelocity().flipped(collision.getNormal());
        setVelocity(newVel);
        collisionSound.play();
        collisionCounter.increment();
    }

    /**
     * Gets the current value of the collision counter.
     *
     * @return The number of collisions the ball has experienced.
     */
    public int getCollisionCounter() {
        return collisionCounter.value();
    }


}
