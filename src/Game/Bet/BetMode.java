package Game.Bet;

// Enum for Bet modes
public enum BetMode {
    MODE_NORMAL(0),
    MODE_ENHANCED(1),
    MODE_FEATURE_BUY_1(2),
    MODE_FEATURE_BUY_2(3),
    MODE_FEATURE_BUY_3(4),
    MODE_FEATURE_EOL(5);

    public final int value;

    BetMode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
