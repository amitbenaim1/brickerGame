package bricker.gameobjects;

import bricker.main.BrickerGameManager;
import danogl.collisions.Layer;
import danogl.gui.Sound;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;

/**
 * Represents the puck object in the game, extending the Ball class.
 * The puck is a special type of ball with additional functionality,
 * such as handling its update and deletion when it goes beyond the window bounds.
 */
public class Puck extends Ball {

    BrickerGameManager brickerGameManager;

    /**
     * Constructs a Puck object with the specified position, dimensions, renderable,
     * collision sound, and game manager.
     *
     * @param topLeftCorner      The top-left corner position of the puck.
     * @param dimensions         The dimensions of the puck.
     * @param renderable         The renderable representing the puck.
     * @param collisionSound     The sound played on collision with other objects.
     * @param brickerGameManager The game manager for managing game objects and layers.
     */
    public Puck(Vector2 topLeftCorner, Vector2 dimensions, Renderable renderable, Sound collisionSound,
                BrickerGameManager brickerGameManager) {
        super(topLeftCorner, dimensions, renderable, collisionSound);
        this.brickerGameManager = brickerGameManager;
    }

    /**
     * Updates the puck's position and handles its behavior when it goes beyond the window bounds.
     *
     * @param deltaTime The time passed since the last update.
     */
    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        // Check if the puck has moved beyond the bottom edge of the window
        if (this.getTopLeftCorner().y() > this.brickerGameManager.getWindowController()
                .getWindowDimensions().y()) {
            // Delete the puck from the default layer when it goes out of bounds
            brickerGameManager.deleteObjectFromLayer(this, Layer.DEFAULT);
        }
    }

}
