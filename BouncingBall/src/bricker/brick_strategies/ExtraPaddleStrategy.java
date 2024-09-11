package bricker.brick_strategies;

import bricker.main.BrickerGameManager;
import danogl.GameObject;
import danogl.collisions.Layer;
import danogl.gui.ImageReader;
import danogl.gui.UserInputListener;
import danogl.gui.rendering.Renderable;
import danogl.util.Counter;
import danogl.util.Vector2;
import bricker.gameobjects.ExtraPaddle;

import static bricker.main.Constants.*;

/**
 * The ExtraPaddleStrategy class represents a strategy for handling collision events that grant an extra
 * paddle to the player.
 * This strategy extends the BasicCollisionStrategy class and inherits its functionality to handle the basic
 * collision behavior.
 * Additionally, it creates an ExtraPaddle game object upon collision, adding it to the game layer if no extra
 * paddle is currently active.
 */
public class ExtraPaddleStrategy extends BasicCollisionStrategy {
    private final BrickerGameManager brickerGameManager;
    /**
     * Counter for the number of extra paddles in the game
     */
    public static Counter extraPaddleCounter = new Counter(0);

    /**
     * Constructs an ExtraPaddleStrategy object with the provided BrickerGameManager instance.
     *
     * @param brickerGameManager The BrickerGameManager instance to be used for managing the game.
     */
    public ExtraPaddleStrategy(BrickerGameManager brickerGameManager) {
        super(brickerGameManager);
        this.brickerGameManager = brickerGameManager;
    }

    /**
     * Handles the collision event between a brick and another game object.
     * This method overrides the onCollision method of the parent class to add extra paddle functionality.
     *
     * @param thisObj  The GameObject representing the brick on which the collision occurred.
     * @param otherObj The GameObject representing the other object involved in the collision.
     */
    @Override
    public void onCollision(GameObject thisObj, GameObject otherObj) {
        // Call the parent method to handle basic collision behavior
        super.onCollision(thisObj, otherObj);

        // Check if no extra paddle is currently active
        if (extraPaddleCounter.value() == 0) {
            // Create and add an ExtraPaddle game object to the game layer
            GameObject extraPaddle = getExtraPaddle();
            Vector2 extraPaddleLocation = this.brickerGameManager.getWindowController()
                    .getWindowDimensions().mult((float) EXTRA_PADDLE_LOCATION);
            extraPaddle.setCenter(extraPaddleLocation);
            this.brickerGameManager.addObjectToLayer(extraPaddle, Layer.DEFAULT);
            // Increment the extra paddle counter
            extraPaddleCounter.increment();
        }
    }

    /**
     * Creates and returns an ExtraPaddle game object with appropriate settings.
     *
     * @return The ExtraPaddle game object.
     */
    private GameObject getExtraPaddle() {
        ImageReader imageReader = this.brickerGameManager.getImageReader();
        UserInputListener inputListener = this.brickerGameManager.getInputListener();
        Renderable paddleImage = imageReader.readImage(PADDLE_IMAGE_PATH, true);
        return new ExtraPaddle(
                Vector2.ZERO, new Vector2(PADDLE_DIMENSION_X, PADDLE_DIMENSION_Y),
                paddleImage, inputListener, this.brickerGameManager.getWindowController(),
                brickerGameManager);
    }
}
