package bricker.gameobjects;

import bricker.main.BrickerGameManager;
import danogl.GameObject;
import danogl.collisions.Layer;
import danogl.gui.WindowController;
import danogl.gui.rendering.Renderable;
import danogl.util.Counter;
import danogl.util.Vector2;

import static bricker.main.Constants.*;

/**
 * Represents the graphic display of player lives in the game.
 */
public class LifeCountGraphic extends GameObject {
    private GameObject[] hearts;
    private Counter livesCounter;
    private BrickerGameManager brickGameManager;
    private int lastNumOfLivesKnown;
    private Renderable renderable;

    /**
     * Constructor for LifeCountGraphic.
     *
     * @param topLeftCorner      The top-left corner position of the graphic.
     * @param dimensions         The dimensions of the graphic.
     * @param renderable         The renderable object for the graphic.
     * @param livesCounter       The counter for tracking player lives.
     * @param maxLives           The maximum number of lives a player can have.
     * @param windowController   The window controller for handling UI elements.
     * @param brickerGameManager The game manager for handling game objects and layers.
     */
    public LifeCountGraphic(Vector2 topLeftCorner, Vector2 dimensions, Renderable renderable,
                            Counter livesCounter, int maxLives,
                            WindowController windowController, BrickerGameManager brickerGameManager) {
        super(topLeftCorner, dimensions, renderable);
        this.brickGameManager = brickerGameManager;
        this.hearts = new GameObject[maxLives];
        this.livesCounter = livesCounter;
        this.lastNumOfLivesKnown = livesCounter.value();
        this.renderable = renderable;
        for (int i = 0; i < NUM_OF_HEARS_IN_LINE / this.livesCounter.value(); i++) {
            for (int j = 0; j < this.livesCounter.value(); j++) {
                this.hearts[i * NUM_OF_HEARS_IN_LINE + j] = new GameObject(
                        new Vector2(j * (HEART_SIZE + HEART_SPACING) + HEART_OFFSET_X,
                                windowController.getWindowDimensions().y() -
                                        (i * (HEART_SIZE + HEART_SPACING)) - HEART_OFFSET_Y),
                        new Vector2(HEART_SIZE, HEART_SIZE),
                        renderable);
                this.brickGameManager.addObjectToLayer(this.hearts[i * NUM_OF_HEARS_IN_LINE + j], Layer.UI);
            }

        }
    }

    /**
     * Updates the life count graphic based on changes in the player's lives.
     *
     * @param deltaTime The time passed since the last update.
     */

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        if (this.lastNumOfLivesKnown > this.livesCounter.value()) {
            this.brickGameManager.deleteObjectFromLayer(this.hearts[this.livesCounter.value()], Layer.UI);
            this.lastNumOfLivesKnown = this.livesCounter.value();
        } else {
            int j = this.livesCounter.value() % NUM_OF_HEARS_IN_LINE;
            int i = this.livesCounter.value() / NUM_OF_HEARS_IN_LINE;
            if (this.lastNumOfLivesKnown < this.livesCounter.value())
                this.hearts[this.livesCounter.value() - 1] = new GameObject(
                        new Vector2(j * (HEART_SIZE + HEART_SPACING) + HEART_OFFSET_X,
                                this.brickGameManager.getWindowController().getWindowDimensions().y() -
                                        (i * (HEART_SIZE + HEART_SPACING)) - HEART_OFFSET_Y),
                        new Vector2(HEART_SIZE, HEART_SIZE),
                        this.renderable);
            this.brickGameManager.addObjectToLayer(this.hearts[this.livesCounter.value()
                    - 1], Layer.UI);
            this.lastNumOfLivesKnown = this.livesCounter.value();
        }

    }
}


