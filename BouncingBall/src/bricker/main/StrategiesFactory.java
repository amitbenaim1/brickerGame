package bricker.main;

import bricker.brick_strategies.*;

/**
 * Factory class responsible for creating instances of various collision strategies used in the game.
 * Provides methods to build different strategies based on the specified type.
 */
public class StrategiesFactory {

    /**
     * Default constructor for StrategiesFactory.
     */
    public StrategiesFactory() {
    }

    /**
     * Builds and returns a BasicCollisionStrategy based on the specified type.
     *
     * @param type               The type of collision strategy to build.
     * @param brickerGameManager The BrickerGameManager instance associated with the collision strategy.
     * @return A BasicCollisionStrategy instance or null if the type is not recognized.
     */
    public BasicCollisionStrategy buildStrategy(String type, BrickerGameManager brickerGameManager) {
        switch (type.toLowerCase()) {
            case ("basic"):
                return new BasicCollisionStrategy(brickerGameManager);
            case ("camera"):
                return new ChangeCameraStrategy(brickerGameManager);
            case ("extralife"):
                return new ExtraLifeStrategy(brickerGameManager);
            case ("extrapaddles"):
                return new ExtraPaddleStrategy(brickerGameManager);
            case ("extrapucks"):
                return new ExtraPucksStrategy(brickerGameManager);
            case ("extrabehavior"):
                return new ExtraBehaviorStrategy(brickerGameManager, true);
            default:
                return null;
        }
    }

    /**
     * Builds and returns a BasicCollisionStrategy with the option to choose ExtraBehaviorStrategy.
     *
     * @param type                   The type of collision strategy to build.
     * @param brickerGameManager     The BrickerGameManager instance associated with the collision strategy.
     * @param canChooseExtraBehavior A boolean indicating whether to include ExtraBehaviorStrategy.
     * @return A BasicCollisionStrategy instance or ExtraBehaviorStrategy with the specified option,
     * or null if the type is not recognized.
     */
    public BasicCollisionStrategy buildStrategy(String type, BrickerGameManager brickerGameManager,
                                                boolean canChooseExtraBehavior) {
        switch (type.toLowerCase()) {
            case ("basic"):
                return new BasicCollisionStrategy(brickerGameManager);
            case ("camera"):
                return new ChangeCameraStrategy(brickerGameManager);
            case ("extralife"):
                return new ExtraLifeStrategy(brickerGameManager);
            case ("extrapaddles"):
                return new ExtraPaddleStrategy(brickerGameManager);
            case ("extrapucks"):
                return new ExtraPucksStrategy(brickerGameManager);
            case ("extrabehavior"):
                return new ExtraBehaviorStrategy(brickerGameManager, canChooseExtraBehavior);
            default:
                return null;
        }
    }
}
