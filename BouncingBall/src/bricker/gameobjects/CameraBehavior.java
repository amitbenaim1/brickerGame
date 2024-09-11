package bricker.gameobjects;

import bricker.main.BrickerGameManager;
import danogl.GameObject;
import danogl.collisions.Layer;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;


/**
 * Represents a camera behavior for controlling the game camera based on ball collisions.
 * Extends the GameObject class and provides additional documentation for better context.
 */
public class CameraBehavior extends GameObject {
    private BrickerGameManager brickGameManager;
    private Ball ball;
    private int collisionForEndCamera;
    private final int initialBallCollisionCounter;

    /**
     * Constructs a new CameraBehavior instance.
     *
     * @param topLeftCorner         Position of the object, in window coordinates (pixels)
     * @param dimensions            Width and height in window coordinates.
     * @param renderable            The renderable representing the object. Can be null, in which case
     *                              the GameObject will not be rendered.
     * @param brickGameManager      The BrickGameManager instance associated with the camera behavior.
     * @param collisionForEndCamera The number of collisions required to end the camera behavior.
     */
    public CameraBehavior(Vector2 topLeftCorner, Vector2 dimensions, Renderable renderable, BrickerGameManager
            brickGameManager, int collisionForEndCamera) {
        super(topLeftCorner, dimensions, renderable);
        this.brickGameManager = brickGameManager;
        this.ball = this.brickGameManager.getBall();
        this.collisionForEndCamera = collisionForEndCamera;
        this.initialBallCollisionCounter = this.ball.getCollisionCounter();

    }

    /**
     * Updates the camera behavior based on ball collisions.
     *
     * @param deltaTime The time elapsed since the last frame update.
     */
    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        int ballCollisions = this.ball.getCollisionCounter();
        if (ballCollisions - 1 >=
                this.initialBallCollisionCounter + this.collisionForEndCamera) {
            this.brickGameManager.setCamera(null);
            this.brickGameManager.deleteObjectFromLayer(this, Layer.FOREGROUND);
        }

    }
}
