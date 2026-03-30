package Game.GamePlay;

import Game.Bet.BetMode;
import Game.DataDef.NextPlay;
import Game.DataDef.PlayOptions;
import Game.DataDef.PlayResponse;
import Game.GameConfig.GameConfig;
import Game.ReelSets.ReelSets;

import java.util.Map;

public abstract class GamePlay {
    public ReelSets reelSets;
    private Map<BetMode, Map<Integer, Boolean>> betModeMap;
    public void betMap(){
        return ;

    }
    //private Random rng = new Random();


    public GameConfig getConfig(){

        return null;
    }
    public abstract void init();
    public abstract PlayResponse Play(PlayOptions options);
    public abstract PlayResponse next(NextPlay next, PlayResponse prev);
    //public void initRNG(String rngOpts) {
        //rng.init(rngOpts);
    //}

    public void collectRands(PlayResponse response) {         //collect RNG values into response
    }

//    public void reseed() {
//        rng.reseed();
//    }

//    public String getRNGLibType() {
//        return "rng.getType()"
//    }

    protected abstract long getRefBetBase(PlayOptions options);

    protected abstract long calculateWins(PlayResponse playResponse);

    public abstract ReelSets initReels();

    protected void createBetMap() {};

    protected void applyCurrencyMultiplier(PlayResponse response) {}


}






