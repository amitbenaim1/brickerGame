package bricker.gameobjects;

import bricker.main.BrickerGameManager;
import danogl.GameObject;
import danogl.collisions.Collision;
import danogl.collisions.Layer;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;

import static bricker.main.Constants.MAIN_PADDLE_TAG;
import static bricker.main.Constants.MAX_LIVES;

/**
 * Represents an ExtraLife power-up in the game.
 * Extends the GameObject class and provides additional documentation for better context.
 */
public class ExtraLife extends GameObject {

    private BrickerGameManager brickerGameManager;

    /**
     * Constructs a new ExtraLife instance.
     *
     * @param topLeftCorner      Position of the object, in window coordinates (pixels).
     *                           Note that (0,0) is the top-left corner of the window.
     * @param dimensions         Width and height in window coordinates.
     * @param renderable         The renderable representing the object. Can be null, in which case
     *                           the GameObject will not be rendered.
     * @param brickerGameManager The BrickerGameManager instance associated with the ExtraLife power-up.
     */
    public ExtraLife(Vector2 topLeftCorner, Vector2 dimensions, Renderable renderable,
                     BrickerGameManager brickerGameManager) {
        super(topLeftCorner, dimensions, renderable);
        this.brickerGameManager = brickerGameManager;
    }

    /**
     * Updates the ExtraLife instance, checking if it is out of bounds and should be removed.
     *
     * @param deltaTime The time elapsed since the last frame update.
     */
    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        float extraLifeHeight = this.getCenter().y();
        if (extraLifeHeight > this.brickerGameManager.getWindowController().getWindowDimensions().y()) {
            this.brickerGameManager.deleteObjectFromLayer(this, Layer.DEFAULT);
        }
    }

    /**
     * Handles the collision with other game objects, providing an extra life to the player
     * if the collision is with the main paddle.
     *
     * @param other     The GameObject with which the collision occurred.
     * @param collision The Collision instance representing the details of the collision.
     */
    @Override
    public void onCollisionEnter(GameObject other, Collision collision) {
        super.onCollisionEnter(other, collision);
        int curCounter = this.brickerGameManager.getLivesCounter().value();
        if (curCounter < MAX_LIVES) {
            this.brickerGameManager.getLivesCounter().increment();
        }
        this.brickerGameManager.deleteObjectFromLayer(this, Layer.DEFAULT);
    }

    /**
     * Specifies whether the ExtraLife instance should collide with the given game object.
     * Only collides with the main paddle.
     *
     * @param other The GameObject to check for collision.
     * @return True if the ExtraLife should collide with the specified object, false otherwise.
     */
    @Override
    public boolean shouldCollideWith(GameObject other) {
        return other.getTag().equals(MAIN_PADDLE_TAG);
    }
}
