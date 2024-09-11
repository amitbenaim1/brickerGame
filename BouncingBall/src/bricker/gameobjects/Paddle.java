package bricker.gameobjects;

import danogl.GameObject;
import danogl.gui.UserInputListener;
import danogl.gui.WindowController;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;

import java.awt.event.KeyEvent;

import static bricker.main.Constants.PADDLE_VELOCITY;

/**
 * Represents the paddle object in the game.
 * The paddle is controlled by user input (left and right arrow keys) and can move horizontally
 * within the game window. It inherits from the GameObject class.
 */
public class Paddle extends GameObject {

    private final UserInputListener inputListener;
    private final WindowController windowController;


    /**
     * Constructs a Paddle object with the specified position, dimensions, renderable,
     * input listener, and window controller.
     *
     * @param topLeftCorner     The top-left corner position of the paddle.
     * @param dimensions        The dimensions of the paddle.
     * @param renderable        The renderable representing the paddle.
     *                          Can be null if the paddle should not be rendered.
     * @param inputListener     The user input listener for handling keyboard input.
     * @param windowController  The window controller for accessing window dimensions.
     */
    public Paddle(Vector2 topLeftCorner, Vector2 dimensions, Renderable renderable,
                  UserInputListener inputListener, WindowController windowController) {
        super(topLeftCorner, dimensions, renderable);
        this.inputListener = inputListener;
        this.windowController = windowController;
    }
    /**
     * Updates the paddle's position based on user input and ensures it stays within the window bounds.
     *
     * @param deltaTime The time passed since the last update.
     */
    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        Vector2 movementDir = Vector2.ZERO;
        float cur_right_corner = this.getTopLeftCorner().x() + this.getDimensions().x();
        if (inputListener.isKeyPressed(KeyEvent.VK_LEFT)) {
            movementDir = movementDir.add(Vector2.LEFT);
        }
        if (inputListener.isKeyPressed(KeyEvent.VK_RIGHT)) {
            movementDir = movementDir.add(Vector2.RIGHT);
        }
        //passed left corner
        if (this.getTopLeftCorner().x() < Vector2.ZERO.x()) {
            this.setTopLeftCorner(new Vector2(0, this.getTopLeftCorner().y()));
        }
        //passed right corner
        if (cur_right_corner > this.windowController.getWindowDimensions().x()) {
            this.setTopLeftCorner(new Vector2(this.windowController.getWindowDimensions().x()
                    - this.getDimensions().x(), this.getTopLeftCorner().y()));
        }
        setVelocity(movementDir.mult(PADDLE_VELOCITY));
    }
}
