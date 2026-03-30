package Game.DataDef;

import Game.Bet.BetMode;

public class PlayOptions {
    public int FeatureMode;
    public long betAmount;
    public long refBetAmount;
    public long currencyMultiplier;
    public String Action;
    public boolean buyFeature;

    public PlayOptions(int FeatureMode, long betAmount, long refBetAmount, long currencyMultiplier, String Action, boolean buyFeature){
        this.FeatureMode = FeatureMode;
        this.refBetAmount = refBetAmount;
        this.currencyMultiplier = currencyMultiplier;
        this.Action = Action;
        this.buyFeature = buyFeature;
    }

    public long getCurrencyMultiplier() {
        return 0;
    }

//    public BetMode getFeatureMode() {
//        return BetMode.MODE_NORMAL;
//
//    }

    public long getBetAmount() {

        return 0;
    }

    public BetMode getFeatureMode() {
        return null;
    }
}
