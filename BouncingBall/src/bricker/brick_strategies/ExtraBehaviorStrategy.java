package bricker.brick_strategies;

import bricker.main.BrickerGameManager;
import bricker.main.StrategiesFactory;
import danogl.GameObject;

import java.util.Random;

import static bricker.main.Constants.*;

/**
 * The ExtraBehaviorStrategy class represents a strategy for handling collision events with additional
 * behaviors.
 * This strategy extends the BasicCollisionStrategy class and inherits its functionality to handle the basic
 * collision behavior.
 * Additionally, it adds extra behaviors to the collision event based on randomly chosen strategies.
 */
public class ExtraBehaviorStrategy extends BasicCollisionStrategy {
    private final BrickerGameManager brickerGameManager;
    private final BasicCollisionStrategy[] strategies;

    /**
     * Constructs an ExtraBehaviorStrategy object with the provided BrickerGameManager instance and option to
     * choose extra behaviors.
     *
     * @param brickerGameManager     The BrickerGameManager instance to be used for managing the game.
     * @param canChooseExtraBehavior A boolean value indicating whether the strategy can choose
     *                               extra behaviors.
     */
    public ExtraBehaviorStrategy(BrickerGameManager brickerGameManager, boolean canChooseExtraBehavior) {
        super(brickerGameManager);
        this.brickerGameManager = brickerGameManager;
        this.strategies = new BasicCollisionStrategy[EXTRA_BEHAVIORS_STRATEGIES];
        StrategiesFactory strategiesFactory = new StrategiesFactory();

        // Randomly choose the first strategy, which may include an extra behavior
        Random random = new Random();
        int indexStrategy;
        if (canChooseExtraBehavior) {
            indexStrategy = random.nextInt(NUMBER_OF_STRATEGIES);
        } else {
            indexStrategy = random.nextInt(NUMBER_OF_STRATEGIES - 1);
        }
        strategies[POSSIBLY_EXTRA_BEHAVIOR_INDEX] = getStrategyWithExtraBehavior(strategiesFactory,
                indexStrategy, brickerGameManager);
        // Populate remaining strategies with random choices
        for (int i = 1; i < this.strategies.length; i++) {
            indexStrategy = random.nextInt(NUMBER_OF_STRATEGIES - 1);
            strategies[i] = strategiesFactory.buildStrategy(SPECIAL_STRATEGIES[indexStrategy],
                    brickerGameManager);
        }
    }

    /**
     * Retrieves a strategy with an extra behavior based on the given index.
     *
     * @param strategiesFactory  The StrategiesFactory instance to create strategies.
     * @param index              The index representing the desired strategy.
     * @param brickerGameManager The BrickerGameManager instance to be used for managing the game.
     * @return A BasicCollisionStrategy with or without extra behavior, based on the index.
     */
    private BasicCollisionStrategy getStrategyWithExtraBehavior(StrategiesFactory strategiesFactory,
                                                                int index,
                                                                BrickerGameManager brickerGameManager) {
        if (index == INDEX_EXTRA_BEHAVIOR) {
            return strategiesFactory.buildStrategy(SPECIAL_STRATEGIES[index],
                    brickerGameManager, false);
        }
        return strategiesFactory.buildStrategy(SPECIAL_STRATEGIES[index],
                brickerGameManager);
    }


    /**
     * Handles the collision event between a brick and another game object.
     * This method overrides the onCollision method of the parent class to add extra behaviors.
     *
     * @param thisObj  The GameObject representing the brick on which the collision occurred.
     * @param otherObj The GameObject representing the other object involved in the collision.
     */
    @Override
    public void onCollision(GameObject thisObj, GameObject otherObj) {
        // Call the parent method to handle basic collision behavior
        super.onCollision(thisObj, otherObj);
        // Execute extra behaviors for each strategy
        for (BasicCollisionStrategy strategy : this.strategies) {
            strategy.onCollision(thisObj, otherObj);
            this.brickerGameManager.getBrickCounter().increment();
        }
    }
}


