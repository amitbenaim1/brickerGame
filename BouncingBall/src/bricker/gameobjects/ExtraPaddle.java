package bricker.gameobjects;

import bricker.brick_strategies.ExtraPaddleStrategy;
import bricker.main.BrickerGameManager;
import danogl.GameObject;
import danogl.collisions.Collision;
import danogl.collisions.Layer;
import danogl.gui.UserInputListener;
import danogl.gui.WindowController;
import danogl.gui.rendering.Renderable;
import danogl.util.Counter;
import danogl.util.Vector2;

import static bricker.main.Constants.HITS_TO_REMOVE_EXTRA_PADDLE;
import static bricker.main.Constants.INITIAL_COUNTER_VALUE;

/**
 * Represents an ExtraPaddle power-up in the game.
 * Extends the Paddle class and provides additional documentation for better context.
 */
public class ExtraPaddle extends Paddle {

    private Counter countHits;
    private BrickerGameManager brickerGameManager;

    /**
     * Constructs a new ExtraPaddle instance.
     *
     * @param topLeftCorner      Position of the object, in window coordinates (pixels).
     *                           Note that (0,0) is the top-left corner of the window.
     * @param dimensions         Width and height in window coordinates.
     * @param renderable         The renderable representing the object. Can be null, in which case
     *                           the GameObject will not be rendered.
     * @param inputListener      The UserInputListener for handling user input.
     * @param windowController   The WindowController for managing the game window.
     * @param brickerGameManager The BrickerGameManager instance associated with the ExtraPaddle power-up.
     */
    public ExtraPaddle(Vector2 topLeftCorner, Vector2 dimensions, Renderable renderable,
                       UserInputListener inputListener, WindowController windowController,
                       BrickerGameManager brickerGameManager) {
        super(topLeftCorner, dimensions, renderable, inputListener, windowController);
        this.countHits = new Counter(INITIAL_COUNTER_VALUE);
        this.brickerGameManager = brickerGameManager;
    }

    /**
     * Handles the collision with other game objects, incrementing the hit counter.
     * Removes the ExtraPaddle if the hit counter reaches the specified threshold.
     *
     * @param other     The GameObject with which the collision occurred.
     * @param collision The Collision instance representing the details of the collision.
     */
    @Override
    public void onCollisionEnter(GameObject other, Collision collision) {
        super.onCollisionEnter(other, collision);
        this.countHits.increment();
        if (this.countHits.value() >= HITS_TO_REMOVE_EXTRA_PADDLE) {
            this.brickerGameManager.deleteObjectFromLayer(this, Layer.DEFAULT);
            ExtraPaddleStrategy.extraPaddleCounter.reset();
        }
    }
}



