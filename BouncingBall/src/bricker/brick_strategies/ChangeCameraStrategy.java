package bricker.brick_strategies;

import bricker.main.BrickerGameManager;
import bricker.main.Constants;
import danogl.GameObject;
import danogl.collisions.Layer;
import danogl.gui.rendering.Camera;
import danogl.util.Vector2;
import bricker.gameobjects.CameraBehavior;

import java.util.Objects;

import static bricker.main.Constants.FRAME_WIDEN_FACTOR;
import static bricker.main.Constants.MAIN_BALL_TAG;

/**
 * The ChangeCameraStrategy class represents a strategy for changing the camera behavior upon collision with a
 * specific game object.
 * This strategy extends the BasicCollisionStrategy class and inherits its functionality to handle the
 * collision event.
 * Additionally, it modifies the camera behavior and settings in response to the collision.
 */
public class ChangeCameraStrategy extends BasicCollisionStrategy {
    private final BrickerGameManager brickerGameManager;

    /**
     * Constructs a ChangeCameraStrategy object with the provided BrickerGameManager instance.
     *
     * @param brickerGameManager The BrickerGameManager instance to be used for managing the game.
     */
    public ChangeCameraStrategy(BrickerGameManager brickerGameManager) {
        super(brickerGameManager);
        this.brickerGameManager = brickerGameManager;
    }

    /**
     * Handles the collision event between a brick and another game object.
     * This method overrides the onCollision method of the parent class to add camera behavior modification.
     *
     * @param thisObj  The GameObject representing the brick on which the collision occurred.
     * @param otherObj The GameObject representing the other object involved in the collision.
     */
    @Override
    public void onCollision(GameObject thisObj, GameObject otherObj) {
        // Call the parent method to handle basic collision behavior
        super.onCollision(thisObj, otherObj);

        // Check if the collided object is the main ball and no camera is currently set
        if (Objects.equals(otherObj.getTag(), MAIN_BALL_TAG) && this.brickerGameManager.camera() == null) {
            // Set a new camera with modified settings based on collision
            this.brickerGameManager.setCamera(new Camera(otherObj, Vector2.ZERO,
                    this.brickerGameManager.getWindowController().getWindowDimensions()
                            .mult(FRAME_WIDEN_FACTOR),
                    this.brickerGameManager.getWindowController().getWindowDimensions()));

            // Add a camera behavior to the game objects layer
            CameraBehavior cameraBehavior = new CameraBehavior(Vector2.ZERO, Vector2.ZERO, null,
                    brickerGameManager, Constants.END_CAMERA_COLLISIONS);
            this.brickerGameManager.addObjectToLayer(cameraBehavior, Layer.FOREGROUND);
        }
    }
}
