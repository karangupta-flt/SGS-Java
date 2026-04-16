package Game.DataDef;

import Game.Bet.BetMode;

public class PlayOptions {
    public int featureMode;
    public long betAmount;
    public long refBetAmount;
    public long currencyMultiplier;
    public String Action;
    public boolean buyFeature;

    public PlayOptions(int featureMode, long betAmount, long currencyMultiplier, boolean buyFeature){
        this.featureMode = featureMode;
        this.betAmount = betAmount;
        this.currencyMultiplier = currencyMultiplier;
        this.buyFeature = buyFeature;
        //this.refBetAmount = refBetAmount;
        //this.Action = Action;
    }

    public long getCurrencyMultiplier() {
        return 0;
    }

//    public BetMode getFeatureMode() {
//        return BetMode.MODE_NORMAL;
//
//    }

    public long getBetAmount() {

        return betAmount;
    }

    public int getFeatureMode() {
        return featureMode;
    }

    public long getRefBetAmount(){
        return refBetAmount;
    }

    public String getAction(){
        return Action;
    }
}
