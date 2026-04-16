package Game.GamePlay;

import Game.DataDef.NextPlay;
import Game.DataDef.PlayOptions;
import Game.DataDef.PlayResponse;
import Game.GameConfig.GameConfig;
import Game.ReelSets.ReelSetMain;
import Game.ReelSets.ReelSets;

 public abstract class GamePlay {
    protected ReelSetMain reelSets;


    public abstract GameConfig getConfig();
    public abstract void init();
    public abstract PlayResponse Play(PlayOptions options);
    public abstract PlayResponse next(NextPlay next, PlayResponse prev);

    public abstract void collectRands(PlayResponse response);  //collect RNG values into response
    public abstract ReelSets initReels();

    protected abstract long getRefBetBase(PlayOptions options);
    protected abstract long calculateWins(PlayResponse playResponse);
    protected abstract void createBetMap();
    protected abstract void applyCurrencyMultiplier(PlayResponse response);


 }






