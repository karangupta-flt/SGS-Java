package Game.Bet;

import java.util.List;



// Class for bet configuration
public final class BetConfig {
    public final String name;
    public final BetMode mode;
    public final String type;
    public final int size;
    public final int cost;
    public final int defaultMax;
    public final int[] allowed;
    public final double rtp;
    public final int maxWinCapRef;
    public final double maxWin;

    public BetConfig(String name, BetMode mode, String type, int defaultMax, int cost, int[] allowed, double rtp, int maxWinCapRef) {
        this.name = name;
        this.mode = mode;
        this.type = type;
        this.defaultMax = defaultMax;
        this.cost = cost;
        this.allowed = allowed;
        this.size = allowed.length;
        this.rtp = rtp;
        this.maxWinCapRef = maxWinCapRef;
        this.maxWin = Math.round((100.0 * maxWinCapRef / cost)) / 10.0;
    }

    // Example credits per bet
    public static final int CREDITS_PER_BET = 10;

    // Arrays of allowed bets
    public static final int[] BET_NORMAL_ARRAY    = {10, 20, 30, 50, 80, 100, 150, 200, 300, 500, 800, 1000, 1250, 1500, 2000, 3000, 5000, 8000, 10000, 12500, 15000, 20000, 30000, 50000, 80000, 100000};
    public static final int[] BET_ENHANCED_ARRAY  = {15, 30, 45, 75, 120, 150, 225, 300, 450, 750, 1200, 1500, 1875, 2250, 3000, 4500, 7500, 12000, 15000, 18750, 22500, 30000, 45000, 75000, 120000, 150000};
    public static final int[] BET_FB1_ARRAY       = {800, 1600, 2400, 4000, 6400, 8000, 12000, 16000, 24000, 40000, 64000, 80000, 100000, 120000, 160000, 240000, 400000, 640000, 800000, 1000000, 1200000, 1600000, 2400000, 4000000, 6400000, 8000000};
    public static final int[] BET_FB2_ARRAY       = {1200, 2400, 3600, 6000, 9600, 12000, 18000, 24000, 36000, 60000, 96000, 120000, 150000, 180000, 240000, 360000, 600000, 960000, 1200000, 1500000, 1800000, 2400000, 3600000, 6000000, 9600000, 12000000};
    public static final int[] BET_FB3_ARRAY       = {1600, 3200, 4800, 8000, 12800, 16000, 24000, 32000, 48000, 80000, 128000, 160000, 200000, 240000, 320000, 480000, 800000, 1280000, 1600000, 2000000, 2400000, 3200000, 4800000, 8000000, 12800000, 16000000};

    // config.BetConfig objects
    public static final BetConfig BET_NORMAL   = new BetConfig("regular",     BetMode.MODE_NORMAL,        BetTypes.TYPE_REGULAR, 20000,   10,   BET_NORMAL_ARRAY,   RTPConfig.RTP, 100000);
    public static final BetConfig BET_ENHANCED = new BetConfig("bonus-boost", BetMode.MODE_ENHANCED,      BetTypes.TYPE_BOOST,   30000,   15,   BET_ENHANCED_ARRAY, RTPConfig.RTP, 100000);
    public static final BetConfig BET_FB1      = new BetConfig("fb1",         BetMode.MODE_FEATURE_BUY_1, BetTypes.TYPE_FEATURE, 1600000, 800,  BET_FB1_ARRAY,      RTPConfig.RTP, 100000);
    public static final BetConfig BET_FB2      = new BetConfig("fb2",         BetMode.MODE_FEATURE_BUY_2, BetTypes.TYPE_FEATURE, 2400000, 1200, BET_FB2_ARRAY,      RTPConfig.RTP, 100000);
    public static final BetConfig BET_FB3      = new BetConfig("fb3",         BetMode.MODE_FEATURE_BUY_3, BetTypes.TYPE_FEATURE, 3200000, 1600, BET_FB3_ARRAY,      RTPConfig.RTP, 100000);

    // List of all bets
    public static final List<BetConfig> ALL_BETS_CONFIG = List.of(
            BET_NORMAL,
            BET_ENHANCED,
            BET_FB1,
            BET_FB2,
            BET_FB3
    );
}
