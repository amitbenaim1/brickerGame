package bricker.main;

import bricker.brick_strategies.*;
import danogl.GameManager;
import danogl.GameObject;
import danogl.collisions.Layer;
import danogl.components.CoordinateSpace;
import danogl.gui.*;
import danogl.gui.rendering.RectangleRenderable;
import danogl.gui.rendering.Renderable;
import danogl.util.Counter;
import danogl.util.Vector2;
import bricker.gameobjects.*;

import java.awt.*;
import java.util.Random;

import static bricker.main.Constants.*;
import static java.awt.event.KeyEvent.VK_W;

/**
 * Manages the main game loop and initializes game elements for the Bricker game.
 */
public class BrickerGameManager extends GameManager {

    private Ball ball;
    private Vector2 windowDimensions;
    private ImageReader imageReader;
    private SoundReader soundReader;
    private UserInputListener inputListener;
    private WindowController windowController;

    private Counter brickCounter;

    private int bricksPerLine;
    private int linesOfBricks;
    private Counter livesCounter;

    private LifeCountText lifeCountText;

    private LifeCountGraphic lifeCountGraphic;

    /**
     * Constructs the BrickerGameManager with the specified window title and dimensions.
     *
     * @param windowTitle      The title of the game window.
     * @param windowDimensions The dimensions of the game window.
     */
    public BrickerGameManager(String windowTitle, Vector2 windowDimensions) {
        super(windowTitle, windowDimensions);
        this.bricksPerLine = Constants.BRICKS_PER_LINE;
        this.linesOfBricks = Constants.LINES_OF_BRICKS;
    }

    /**
     * Constructs the BrickerGameManager with the specified window title, dimensions, bricks per
     * line, and lines of bricks.
     *
     * @param windowTitle      The title of the game window.
     * @param windowDimensions The dimensions of the game window.
     * @param bricksPerLine    The number of bricks per line.
     * @param linesOfBricks    The number of lines of bricks.
     */
    public BrickerGameManager(String windowTitle, Vector2 windowDimensions, int bricksPerLine,
                              int linesOfBricks) {
        super(windowTitle, windowDimensions);
        this.bricksPerLine = bricksPerLine;
        this.linesOfBricks = linesOfBricks;
    }

    /**
     * Initializes the game by setting up game objects, input, and other essential components.
     *
     * @param imageReader      The image reader for loading images.
     * @param soundReader      The sound reader for loading sounds.
     * @param inputListener    The user input listener for handling player input.
     * @param windowController The window controller for managing the game window.
     */
    public void initializeGame(ImageReader imageReader,
                               SoundReader soundReader,
                               UserInputListener inputListener,
                               WindowController windowController) {
        this.imageReader = imageReader;
        this.soundReader = soundReader;
        this.inputListener = inputListener;
        this.windowController = windowController;
        this.brickCounter = new Counter(linesOfBricks * bricksPerLine);
        this.livesCounter = new Counter(Constants.INITIAL_LIVES_AMOUNT);

        super.initializeGame(imageReader, soundReader, inputListener, windowController);

        //create ball
        createBall(imageReader, soundReader);

        //create paddles
        createPaddles(imageReader, inputListener, linesOfBricks);

        //create walls
        createBorders();

        //create background
        createBackground(imageReader, windowDimensions);

        //create brickers
        createBricks(imageReader);

        //create Life Text
        createLifeCountText();

        //create hearts
        create_LifeCountGraphic();

    }

    /**
     * Creates a LifeCountGraphic object to represent the player's remaining lives.
     */
    private void create_LifeCountGraphic() {
        Renderable heartImage = this.imageReader.readImage("assets/heart.png",
                true);
        this.lifeCountGraphic = new LifeCountGraphic(Vector2.ZERO, Vector2.ZERO, heartImage,
                livesCounter, Constants.MAX_LIVES, this.windowController,
                this);


    }

    /**
     * Creates a ball for the game with random starting direction and speed.
     *
     * @param imageReader The image reader for loading ball image.
     * @param soundReader The sound reader for loading collision sound.
     */
    private void createBall(ImageReader imageReader, SoundReader soundReader) {
        float ballVelX = Constants.BALL_SPEED;
        float ballVelY = Constants.BALL_SPEED;
        Random rand = new Random();
        if (rand.nextBoolean()) {
            ballVelX *= BALL_INITIAL_VELOCITY_FACTOR;
        }
        if (rand.nextBoolean()) {
            ballVelY *= BALL_INITIAL_VELOCITY_FACTOR;
        }
        Renderable ballImage = imageReader.readImage(BALL_IMAGE_PATH, true);
        Sound collisionSound = soundReader.readSound(BALL_SOUND_PATH);
        Ball ball = new Ball(new Vector2(BALL_INITIAL_LOCATION_X, BALL_INITIAL_LOCATION_Y),
                new Vector2(Constants.BALL_DIMENSIONS, Constants.BALL_DIMENSIONS), ballImage, collisionSound);
        this.ball = ball;
        ball.setVelocity(new Vector2(ballVelX, ballVelY));
        windowDimensions = windowController.getWindowDimensions();
        ball.setCenter(windowDimensions.mult((float) CENTER_INITIAL_LOCATION_FACTOR));
        this.ball.setTag(MAIN_BALL_TAG);
        gameObjects().addGameObject(ball, Layer.DEFAULT);
    }

    /**
     * Creates paddles for the game.
     *
     * @param imageReader   The image reader for loading paddle image.
     * @param inputListener The user input listener for handling paddle movement.
     * @param linesOfBricks The number of lines of bricks.
     */
    private void createPaddles(ImageReader imageReader, UserInputListener inputListener, int linesOfBricks) {
        Renderable paddleImage = imageReader.readImage("assets/paddle.png", true);
        GameObject userPaddle = new Paddle(
                Vector2.ZERO, new Vector2(PADDLE_DIMENSION_X, PADDLE_DIMENSION_Y),
                paddleImage, inputListener, windowController);
        userPaddle.setCenter(new Vector2((float) (windowDimensions.x() * CENTER_INITIAL_LOCATION_FACTOR),
                (int) windowDimensions.y() - PADDLE_OFFSET_Y));
        userPaddle.setTag(MAIN_PADDLE_TAG);
        gameObjects().addGameObject(userPaddle, Layer.DEFAULT);
    }

    /**
     * Creates border objects (left wall, right wall, and ceiling) for the game.
     */
    private void createBorders() {
        Color borderColor = new Color(BORDER_COLOR_R_VALUE, BORDER_COLOR_G_VALUE, BORDER_COLOR_B_VALUE);

        GameObject leftWall = new GameObject(Vector2.ZERO, new Vector2(LEFT_WALL_WIDTH, windowDimensions.y())
                , new RectangleRenderable(borderColor));
        gameObjects().addGameObject(leftWall, Layer.STATIC_OBJECTS);

        GameObject rightWall = new GameObject(new Vector2(windowDimensions.x() - RIGHT_WALL_OFFSET_X,
                RIGHT_WALL_Y),
                new Vector2(RIGHT_WALL_WIDTH, windowDimensions.y())
                , new RectangleRenderable(borderColor));
        gameObjects().addGameObject(rightWall, Layer.STATIC_OBJECTS);

        GameObject ceiling = new GameObject(Vector2.ZERO, new Vector2(windowDimensions.x(), CEILING_HEIGHT)
                , new RectangleRenderable(borderColor));
        ceiling.setCenter(new Vector2((float) (windowDimensions.x() * CENTER_INITIAL_LOCATION_FACTOR),
                CEILING_Y));
        gameObjects().addGameObject(ceiling, Layer.STATIC_OBJECTS);
    }

    /**
     * Creates bricks for the game with random collision strategies.
     *
     * @param imageReader The image reader for loading brick image.
     */
    private void createBricks(ImageReader imageReader) {
        Renderable brickImage = imageReader.readImage(BRICK_IMAGE_PATH, false);
        Vector2 brickDims = new Vector2(windowDimensions.x() / bricksPerLine, BRICK_HEIGHT);
        for (int i = 0; i < bricksPerLine; i++) {
            for (int j = 0; j < linesOfBricks; j++) {
                double randomProbability = Math.random();
                CollisionStrategy brickCollisionStrategy;
                String currentStrategyName;
                StrategiesFactory strategiesFactory = new StrategiesFactory();
                if (randomProbability < BASIC_COLLISION_PROBABILITY) {
                    currentStrategyName = BASIC_STRATEGY_NAME;
                } else if (randomProbability < EXTRA_PADDLE_PROBABILITY) {
                    currentStrategyName = EXTRA_PADDLES_STRATEGY_NAME;
                } else if (randomProbability < EXTRA_PUCKS_PROBABILITY) {
                    currentStrategyName = EXTRA_PUCKS_STRATEGY_NAME;
                } else if (randomProbability < CHANGE_CAMERA_PROBABILITY) {
                    currentStrategyName = CAMERA_STRATEGY_NAME;
                } else if (randomProbability < EXTRA_LIFE_PROBABILITY) {
                    currentStrategyName = EXTRA_LIFE_STRATEGY_NAME;
                } else {
                    currentStrategyName = EXTRA_BEHAVIOR_STRATEGY_NAME;
                }
                if (currentStrategyName.equals(EXTRA_BEHAVIOR_STRATEGY_NAME)) {
                    brickCollisionStrategy = strategiesFactory.buildStrategy(currentStrategyName,
                            this, true);
                } else {
                    brickCollisionStrategy = strategiesFactory.buildStrategy(currentStrategyName,
                            this);
                }
                GameObject brick = new Brick(new Vector2(i * brickDims.x(), j * brickDims.y()),
                        brickDims, brickImage, brickCollisionStrategy, brickCounter);
                gameObjects().addGameObject(brick, Layer.STATIC_OBJECTS);
            }
        }
    }

    /**
     * Creates the background for the game.
     *
     * @param imageReader      The image reader for loading background image.
     * @param windowDimensions The dimensions of the game window.
     */
    private void createBackground(ImageReader imageReader, Vector2 windowDimensions) {
        Renderable backgroundImage = imageReader.readImage("assets/DARK_BG2_small.jpeg",
                false);
        GameObject background = new GameObject(Vector2.ZERO, windowDimensions, backgroundImage);
        background.setCoordinateSpace(CoordinateSpace.CAMERA_COORDINATES);
        gameObjects().addGameObject(background, Layer.BACKGROUND);
    }

    /**
     * Creates the life count text element for the game.
     */
    private void createLifeCountText() {
        float heartsWidth = Constants.NUM_OF_HEARS_IN_LINE * GRAPHIC_AND_TEXT_LIVES_SPACE;
        float textX = heartsWidth + TEXT_LIVES_X_OFFSET; // Adjust value as needed for the desired left margin
        float textY = windowDimensions.y() - TEXT_LIVES_Y_OFFSET;
        this.lifeCountText = new LifeCountText(new Vector2(textX, textY), new Vector2(TEXT_LIVES_DIMENSIONS,
                TEXT_LIVES_DIMENSIONS), this.livesCounter);
        gameObjects().addGameObject(this.lifeCountText, Layer.UI);
    }

    /**
     * Updates the game state, including checking win/lose conditions.
     *
     * @param deltaTime The time passed since the last update.
     */
    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        float ballHeight = this.ball.getCenter().y();
        String prompt = "";
        this.lifeCountGraphic.update(deltaTime);
        //we lost
        if (ballHeight > windowDimensions.y()) {
            this.livesCounter.decrement();
            this.lifeCountText.update(deltaTime);
            if (this.livesCounter.value() == LOSING_LIVES_VALUE) {
                prompt = LOSING_MESSAGE;
            } else {
                deleteObjectFromLayer(this.ball, Layer.DEFAULT);
                createBall(this.imageReader, this.soundReader);
            }
        }
        //we win
        if (this.brickCounter.value() <= WINNING_BRICKS_VALUE || this.inputListener.isKeyPressed(VK_W)) {
            prompt = WINNING_MESSAGE;

        }
        if (!prompt.isEmpty()) {
            prompt += YES_NO_DIALOG_MESSAGE;
            if (windowController.openYesNoDialog(prompt)) {
                windowController.resetGame();
                ExtraPaddleStrategy.extraPaddleCounter.reset();
            } else {
                windowController.closeWindow();
            }

        }
    }

    /**
     * Deletes an object from a specific layer in the game.
     *
     * @param obj   The game object to be deleted.
     * @param layer The layer from which the object should be deleted.
     */
    public void deleteObjectFromLayer(GameObject obj, int layer) {
        gameObjects().removeGameObject(obj, layer);
    }

    /**
     * Adds an object to a specific layer in the game.
     *
     * @param obj   The game object to be added.
     * @param layer The layer to which the object should be added.
     */
    public void addObjectToLayer(GameObject obj, int layer) {
        gameObjects().addGameObject(obj, layer);
    }

    /**
     * Gets the counter for remaining lives in the game.
     *
     * @return The counter for remaining lives.
     */
    public Counter getLivesCounter() {
        return livesCounter;
    }

    /**
     * Gets the counter for remaining bricks in the game.
     *
     * @return The counter for remaining bricks.
     */
    public Counter getBrickCounter() {
        return brickCounter;
    }

    /**
     * Gets the image reader used for loading images in the game.
     *
     * @return The image reader.
     */
    public ImageReader getImageReader() {
        return this.imageReader;
    }

    /**
     * Gets the sound reader used for loading sounds in the game.
     *
     * @return The sound reader.
     */
    public SoundReader getSoundReader() {
        return this.soundReader;
    }

    /**
     * Gets the window controller for managing the game window.
     *
     * @return The window controller.
     */
    public WindowController getWindowController() {
        return this.windowController;
    }

    /**
     * Gets the user input listener for handling player input.
     *
     * @return The user input listener.
     */

    public UserInputListener getInputListener() {
        return this.inputListener;
    }

    /**
     * Gets the main ball object in the game.
     *
     * @return The main ball.
     */
    public Ball getBall() {
        return this.ball;
    }

    /**
     * The entry point for running the Bricker game.
     *
     * @param args Command-line arguments. If provided, the first argument represents
     *             the number of bricks per line, and the second argument represents
     *             the number of lines of bricks.
     */
    public static void main(String[] args) {
        if (args.length == NUM_LEGAL_ARGS) {
            int bricksPerLine = Integer.parseInt(args[BRICKS_PER_LINE_ARGUMENT]);
            int lines = Integer.parseInt(args[NUM_LINE_ARGUMENT]);
            new BrickerGameManager("BrickerGame", new Vector2(WINDOW_SIZE_X, WINDOW_SIZE_Y),
                    bricksPerLine, lines).run();
        } else {
            new BrickerGameManager("BrickerGame", new Vector2(WINDOW_SIZE_X, WINDOW_SIZE_Y)).run();
        }
    }
}

