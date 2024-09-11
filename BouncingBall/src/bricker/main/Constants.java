package bricker.main;

/**
 * Constants class containing various static final fields used throughout the application.
 * These fields include tags, messages, dimensions, probabilities, and other values.
 */
public class Constants {
    /**
     * Default constructor for the Constants class.
     * This constructor is provided implicitly and does not take any parameters.
     * It initializes the class with default values for constant fields.
     * Note: The Constants class is meant to be a collection of static final fields,
     * and therefore, the default constructor is not intended for instantiation.
     */
    public Constants() {
        // This constructor intentionally left empty.
        // Constants class is not meant to be instantiated.
    }

    /**
     * Represents the tag assigned to the main ball object in the game.
     */
    public static final String MAIN_BALL_TAG = "Main ball";

    /**
     * Represents the tag assigned to the main paddle object in the game.
     */
    public static final String MAIN_PADDLE_TAG = "Main paddle";

// Messages for the user
    /**
     * Message displayed when the player loses the game.
     */
    public static final String LOSING_MESSAGE = "You lose! ";

    /**
     * Message displayed when the player wins the game.
     */
    public static final String WINNING_MESSAGE = "You Win! ";

    /**
     * Message for a play-again dialog.
     */
    public static final String YES_NO_DIALOG_MESSAGE = "Play Again?";

// Paths for images and sounds assets
    /**
     * Path for the heart image asset.
     */
    public static final String HEART_PNG_IMAGE_PATH = "assets/heart.png";
    /**
     * Path for the paddle image asset.
     */
    public static final String PADDLE_IMAGE_PATH = "assets/paddle.png";
    /**
     * Path for the mockBall image asset.
     */
    public static final String MOCK_BALL_IMAGE_PATH = "assets/mockBall.png";
    /**
     * Path for the blop_cut_silenced sound asset.
     */
    public static final String BALL_SOUND_PATH = "assets/blop_cut_silenced.wav";
    /**
     * Path for the ball image asset.
     */
    public static final String BALL_IMAGE_PATH = "assets/ball.png";
    /**
     * Path for the brick image asset.
     */
    public static final String BRICK_IMAGE_PATH = "assets/brick.png";
    /**
     * name of basic strategy to use when using a factory to create this strategy
     */
    public static final String BASIC_STRATEGY_NAME = "basic";
    /**
     * name of camera strategy to use when using a factory to create this strategy
     */
    public static final String CAMERA_STRATEGY_NAME = "camera";
    /**
     * name of extra life strategy to use when using a factory to create this strategy
     */
    public static final String EXTRA_LIFE_STRATEGY_NAME = "extralife";
    /**
     * name of extra paddles strategy to use when using a factory to create this strategy
     */
    public static final String EXTRA_PADDLES_STRATEGY_NAME = "extrapaddles";
    /**
     * name of extra pucks strategy to use when using a factory to create this strategy
     */
    public static final String EXTRA_PUCKS_STRATEGY_NAME = "extrapucks";
    /**
     * name of extra behavior strategy to use when using a factory to create this strategy
     */
    public static final String EXTRA_BEHAVIOR_STRATEGY_NAME = "extrabehavior";


    // Game constants
    /**
     * Dimensions for text displaying lives.
     */
    public static final int TEXT_LIVES_DIMENSIONS = 20;

    /**
     * Dimensions of the game window (X-axis).
     */
    public static final int WINDOW_SIZE_X = 700;

    /**
     * Dimensions of the game window (Y-axis).
     */
    public static final int WINDOW_SIZE_Y = 500;
    /**
     * Number of legal arguments to get from user.
     */
    public static final int NUM_LEGAL_ARGS = 2;
    /**
     * Index of bricks per line argument in the user input
     */

    public static final int BRICKS_PER_LINE_ARGUMENT = 0;
    /**
     * Index of num of lines argument in the user input
     */
    public static final int NUM_LINE_ARGUMENT = 1;


    /**
     * Initial number of lives for the player.
     */
    public static final int INITIAL_LIVES_AMOUNT = 3;

    /**
     * Maximum number of lives a player can have.
     */
    public static final int MAX_LIVES = 4;

    /**
     * Number of different game strategies.
     */
    public static final int NUMBER_OF_STRATEGIES = 5;

    /**
     * Index representing extra behavior in the list of strategies.
     */
    public static final int INDEX_EXTRA_BEHAVIOR = 4;

    /**
     * Value indicating losing lives in the game.
     */
    public static final int LOSING_LIVES_VALUE = 0;

    /**
     * Value indicating winning bricks in the game.
     */
    public static final int WINNING_BRICKS_VALUE = 0;

    /**
     * Initial counter value for game-related purposes.
     */
    public static final int INITIAL_COUNTER_VALUE = 0;

    /**
     * Factor representing the initial location of an object at the center of the game.
     */
    public static final double CENTER_INITIAL_LOCATION_FACTOR = 0.5;

    /**
     * Array of special strategies in the game.
     */
    public static final String[] SPECIAL_STRATEGIES = {"camera", "extralife", "extrapaddles",
            "extrapucks", "extrabehavior"};

    // Game default constants
// Game default constants
    /**
     * Default number of bricks per line in the game.
     */
    public static final int BRICKS_PER_LINE = 8;

    /**
     * Default number of lines of bricks in the game.
     */
    public static final int LINES_OF_BRICKS = 7;

// Borders and walls constants
    /**
     * Red color value for the game borders.
     */
    public static final int BORDER_COLOR_R_VALUE = 50;

    /**
     * Green color value for the game borders.
     */
    public static final int BORDER_COLOR_G_VALUE = 77;

    /**
     * Blue color value for the game borders.
     */
    public static final int BORDER_COLOR_B_VALUE = 173;

    /**
     * Width of the left wall in the game.
     */
    public static final int LEFT_WALL_WIDTH = 10;

    /**
     * Width of the right wall in the game.
     */
    public static final int RIGHT_WALL_WIDTH = 10;

    /**
     * Height of the ceiling in the game.
     */
    public static final int CEILING_HEIGHT = 10;

    /**
     * Offset on the X-axis for the right wall.
     */
    public static final int RIGHT_WALL_OFFSET_X = 5;

    /**
     * Y-coordinate for the top edge of the right wall.
     */
    public static final int RIGHT_WALL_Y = 0;

    /**
     * Y-coordinate for the top edge of the ceiling.
     */
    public static final int CEILING_Y = 5;

    /**
     * Height of the bricks in the game.
     */
    public static final int BRICK_HEIGHT = 15;

    /**
     * Offset on the Y-axis for the main paddle.
     */
    public static final int PADDLE_OFFSET_Y = 30;

// Ball constants
    /**
     * Speed of the game ball.
     */
    public static final float BALL_SPEED = 250;

    /**
     * Dimensions of the game ball.
     */
    public static final int BALL_DIMENSIONS = 35;

    /**
     * Factor representing the initial velocity of the game ball.
     */
    public static final double BALL_INITIAL_VELOCITY_FACTOR = -1;

    /**
     * Initial X-coordinate for the game ball.
     */
    public static final float BALL_INITIAL_LOCATION_X = 0;

    /**
     * Initial Y-coordinate for the game ball.
     */
    public static final float BALL_INITIAL_LOCATION_Y = 0;

// Graphic display of hearts constants
    /**
     * Size of the heart graphic displaying lives.
     */
    public static final int HEART_SIZE = 20;
    // Graphic display of hearts constants
    /**
     * Spacing between hearts in the graphical display.
     */
    public static final int HEART_SPACING = 5;

    /**
     * Number of hearts displayed in a single line.
     */
    public static final int NUM_OF_HEARS_IN_LINE = 4;

    /**
     * X-coordinate offset for the starting position of hearts.
     */
    public static final int HEART_OFFSET_X = 10;

    /**
     * Y-coordinate offset for the starting position of hearts.
     */
    public static final int HEART_OFFSET_Y = 30;

    /**
     * Number of lives required to display hearts in green.
     */
    public static final int GREEN_DISPLAY_LIVES = 3;

    /**
     * Number of lives required to display hearts in yellow.
     */
    public static final int YELLOW_DISPLAY_LIVES = 2;

    /**
     * Number of lives required to display hearts in red.
     */
    public static final int RED_DISPLAY_LIVES = 1;

    /**
     * Space between the graphic and text representations of lives.
     */
    public static final int GRAPHIC_AND_TEXT_LIVES_SPACE = 25;

    /**
     * X-coordinate offset for the text displaying lives.
     */
    public static final int TEXT_LIVES_X_OFFSET = 20;

    /**
     * Y-coordinate offset for the text displaying lives.
     */
    public static final int TEXT_LIVES_Y_OFFSET = 32;

    /**
     * Initial velocity for an extra heart power-up.
     */
    public static final int EXTRA_HEART_INITIAL_VELOCITY = 100;

// Extra paddle constants
    /**
     * Initial location factor for placing the extra paddle in the game.
     */
    public static final double EXTRA_PADDLE_LOCATION = 0.5;

    /**
     * Number of hits required to remove the extra paddle power-up.
     */
    public static final int HITS_TO_REMOVE_EXTRA_PADDLE = 4;

// Puck constants
    /**
     * Dimensions of the puck, a modified version of the game ball.
     */
    public static final float PUCK_DIMENSIONS = (float) (BALL_DIMENSIONS * 0.75);

// Main paddle constants
    /**
     * Velocity of the main paddle in the game.
     */
    public static final float PADDLE_VELOCITY = 300;

    /**
     * Width of the main paddle in the X-axis.
     */
    public static final int PADDLE_DIMENSION_X = 200;

    /**
     * Height of the main paddle in the Y-axis.
     */
    public static final int PADDLE_DIMENSION_Y = 20;

// Camera constants
    /**
     * Number of collisions after which the camera stops.
     */
    public static final int END_CAMERA_COLLISIONS = 4;

    /**
     * Factor by which the camera frame widens during collisions.
     */
    public static final float FRAME_WIDEN_FACTOR = 1.2f;

// Extra behavior constants
    /**
     * Number of extra behaviors/strategies in the game.
     */
    public static final int EXTRA_BEHAVIORS_STRATEGIES = 2;
    public static final int POSSIBLY_EXTRA_BEHAVIOR_INDEX = 0;

// Strategies probabilities constants
    /**
     * Probability of having a brick with basic collisions strategy appearing in the game.
     */
    public static final double BASIC_COLLISION_PROBABILITY = 0.5;

    /**
     * Probability of having a brick with extra paddle strategy appearing in the game.
     */
    public static final double EXTRA_PADDLE_PROBABILITY = 0.6;

    /**
     * Probability of having a brick with extra pucks strategy appearing in the game.
     */
    public static final double EXTRA_PUCKS_PROBABILITY = 0.7;

    /**
     * Probability of having a brick with changing camera behavior in the game.
     */
    public static final double CHANGE_CAMERA_PROBABILITY = 0.8;

    /**
     * Probability of having a brick with an extra life strategy in the game.
     */
    public static final double EXTRA_LIFE_PROBABILITY = 0.9;

}
