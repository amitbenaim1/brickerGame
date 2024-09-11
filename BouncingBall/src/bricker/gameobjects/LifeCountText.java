package bricker.gameobjects;

import danogl.GameObject;
import danogl.gui.rendering.TextRenderable;
import danogl.util.Counter;
import danogl.util.Vector2;

import java.awt.*;

import static bricker.main.Constants.*;
/**
 * Represents the textual display of player lives in the game.
 * This class extends the GameObject class to provide a game object
 * capable of rendering text to represent the number of lives.
 */
public class LifeCountText extends GameObject {
    private Counter numLives;
    private TextRenderable textRenderable;
    /**
     * Constructs a LifeCountText object with the specified position,
     * dimensions, and initial number of lives.
     *
     * @param topLeftCorner The top-left corner position of the text.
     * @param dimensions    The dimensions of the text.
     * @param numLives      The counter for tracking the number of lives.
     */
    public LifeCountText(Vector2 topLeftCorner, Vector2 dimensions,
                         Counter numLives) {
        super(topLeftCorner, dimensions, null);
        this.numLives = numLives;
        this.textRenderable = new TextRenderable(String.valueOf(INITIAL_LIVES_AMOUNT));
        this.renderer().setRenderable(textRenderable);
    }
    /**
     * Updates the text rendering based on the current number of lives.
     *
     * @param deltaTime The time passed since the last update.
     */
    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        this.textRenderable.setString(String.valueOf(numLives.value()));
        if (numLives.value() >= GREEN_DISPLAY_LIVES) {
            this.textRenderable.setColor(Color.green);
        }
        if (numLives.value() == YELLOW_DISPLAY_LIVES) {
            this.textRenderable.setColor(Color.yellow);
        }
        if (numLives.value() == RED_DISPLAY_LIVES) {
            this.textRenderable.setColor(Color.red);
        }
    }
}
