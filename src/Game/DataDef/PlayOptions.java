package Game.DataDef;

import Game.Bet.BetMode;

public class PlayOptions {
    public int featureMode;
    public long betAmount;
    public long refBetAmount;
    public long currencyMultiplier;
    public String Action;
    public boolean buyFeature;

    public PlayOptions(int featureMode, long betAmount, long currencyMultiplier, boolean buyFeature, long refBetAmount){
        this.featureMode = featureMode;
        this.betAmount = betAmount;
        this.currencyMultiplier = currencyMultiplier;
        this.buyFeature = buyFeature;
        this.refBetAmount = refBetAmount;
        //this.Action = Action;
    }

    public long getCurrencyMultiplier() {
        return currencyMultiplier;
    }

    public long getBetAmount() {

        return betAmount;
    }

    public int getFeatureMode() {
        return featureMode;
    }

    public long getRefBetAmount(){
        return refBetAmount;
    }

    public boolean getBuyFeature(){
        return buyFeature;
    }

    public String getAction(){
        return Action;
    }
}
